package org.tjc.jfx.filemanager;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import static java.util.Arrays.asList;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.tjc.common.utils.SystemUtils;
import org.tjc.jfx.filemanager.builders.TreeTableColumnBuilder;
import org.tjc.jfx.filemanager.model.PathData;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
@Slf4j
public class FileManagerSceneController {
    private static final String TITLE_FORMAT_PATTERN = "%s - JFXFileManager";
    @FXML
    private VBox appContainer;
    @FXML
    private CheckMenuItem checkMenuItemShowHidden;
    @FXML
    private Accordion favoritesAccordian;
    @FXML
    private ListView<String> favoritesListView;
    @FXML
    private TitledPane favoritesTitledPane;
    @FXML
    private Menu menuFile;
    @FXML
    private Menu menuEdit;
    @FXML
    private Menu menuView;
    @FXML
    private Menu menuHelp;
    @FXML
    private CheckMenuItem showToolBarMenuItem;
    @FXML
    private HBox statusBar;
    @FXML
    private Label statusBarItemCount;
    @FXML
    private ToolBar toolBar;
    @FXML
    private TreeTableView<PathData> treeTable;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private SplitPane primarySplitPane;

    private BooleanProperty showHiddenFiles;
    private BooleanProperty toolBarHidden;

    private final StringProperty formattedStageTitle = new SimpleStringProperty("");
    private Stage stage;
    private PathTreeItem rootTreeItem;
    private Config conf;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        log.trace("initializing FileExplorer components.");
        conf = ConfigFactory.load();
        showHiddenFiles = new SimpleBooleanProperty(conf.getBoolean("app.showHiddenFiles"));
        toolBarHidden = new SimpleBooleanProperty(true);
        log.trace("##### showHiddenFiles: {}", showHiddenFiles);

        initToolBar();
        initMenuBarItems();
        initFileBrowserTreeTable();
        initFavorites();
    }

    public void setStage(Stage stage) {
        log.trace("##### Entered setStage(): stage: {}", stage);
        this.stage = stage;
        this.stage.titleProperty().bind(formattedStageTitle);
        formattedStageTitle.setValue(
            String.format(TITLE_FORMAT_PATTERN, rootTreeItem.getValue().getPath()));
    }

    private void initMenuBarItems() {
        log.trace("##### Entered initMenuBarItems()");
        menuItemClose.setOnAction(e -> {
            Platform.exit();
        });
        showHiddenFiles.bindBidirectional(checkMenuItemShowHidden.selectedProperty());
        showHiddenFiles.addListener((Observable p) -> {
            log.debug("##### showHiddenFiles.listener: p: {}", p);
        });

        toolBarHidden.addListener(o -> {
            log.debug("##### o: {}", o);
        });

        showToolBarMenuItem.setOnAction((ActionEvent event) -> {
            log.debug(eventToString(event));
            Platform.runLater(() -> {
                if (toolBarHidden.get() == true) {
                    log.debug("##### toolbar was hidden... now showing.");
                    showToolBar();
                    toolBarHidden.setValue(Boolean.FALSE);
                    showToolBarMenuItem.selectedProperty().set(true);
                }
                else {
                    log.debug("##### toolbar was shown... now hiding.");
                    hideToolBar();
                    toolBarHidden.setValue(Boolean.TRUE);
                    showToolBarMenuItem.selectedProperty().set(false);
                }
            });
        });
    }

    private void initToolBar() {
        log.debug("##### Entering initToolBar: toolBarHidden: {}", toolBarHidden.getValue());
        if (toolBarHidden.get() == true) {
            hideToolBar();
            showToolBarMenuItem.selectedProperty().set(false);
        }
        else {
            showToolBar();
            showToolBarMenuItem.selectedProperty().set(true);

        }
    }

    private void hideToolBar() {
        log.debug("##### Entering hideToolBar");
        Node n = appContainer.getChildren().get(1);
        log.debug("##### node: {}", n);
        if (appContainer.getChildren().get(1) instanceof ToolBar) {
            log.debug("##### toolbar exists... removing");
            appContainer.getChildren().remove(1);
        }
    }

    private void showToolBar() {
        log.debug("##### Entering showToolBar");
        Node n = appContainer.getChildren().get(1);
        log.debug("##### node: {}", n);
        if (!(appContainer.getChildren().get(1) instanceof ToolBar)) {
            log.debug("##### toolbar does not exist... adding");
            appContainer.getChildren().add(1, toolBar);
        }
    }

    private void initFileBrowserTreeTable() {
        log.trace("##### Entered initFileBrowserTreeTable()");

        final TreeTableColumn<PathData, String> nameColumn = newColumnBuilder("Name")
            .setPrefWidth(250)
            .setTreeItemPropertyValueFactory("name")
            .build();

        final TreeTableColumn<PathData, String> sizeColumn = newColumnBuilder("Size")
            .setPrefWidth(150)
            .setTreeItemPropertyValueFactory("size")
            .addStyleClass("size-tree-table-cell")
            .build();

        final TreeTableColumn<PathData, String> lastModifiedColumn = newColumnBuilder("Last Modified")
            .setPrefWidth(200)
            .setTreeItemPropertyValueFactory("lastModifiedTimeString")
            .build();

        final TreeTableColumn<PathData, String> kindColumn = newColumnBuilder("Kind")
            .setPrefWidth(150)
            .setTreeItemPropertyValueFactory("kind")
            .build();

        rootTreeItem = TreeItems.createPathTreeItem(PathData.of(SystemUtils.getUserHomePath()));
        log.trace("##### rootTreeItem: {}", rootTreeItem);
        if (log.isTraceEnabled()) {
            rootTreeItem.addEventHandler(EventType.ROOT, event -> {
                log.trace("##### ----------rootTreeItem----------");
                log.trace("##### eventType    : {}", event.getEventType());
                log.trace("##### source       : {}", event.getSource());
                log.trace("##### target       : {}", event.getTarget());
                log.trace("##### source parent: {}", ((TreeItem) event.getSource()).getParent());
                log.trace("##### target parent: {}", ((TreeItem) event.getTarget()).getParent());
                log.trace("##### ----------rootTreeItem----------");
            });
        }

        treeTable.rootProperty().setValue(rootTreeItem);
        treeTable.setRowFactory(new TreeTableRowFactory());
        treeTable.showRootProperty().set(false);
        treeTable.getColumns().setAll(asList(nameColumn, lastModifiedColumn, sizeColumn, kindColumn));

        if (log.isTraceEnabled()) {
            treeTable.getRoot().getChildren().addListener((Change<? extends TreeItem> change) -> {
                log.trace("##### change.list.size: {}", change.getList().size());
            });
        }

        treeTable.setVisible(true);
    }

    private static class TreeTableRowFactory implements Callback<TreeTableView<PathData>, TreeTableRow<PathData>> {
        @Override
        public TreeTableRow<PathData> call(TreeTableView<PathData> param) {
            log.trace("##### Entered TreeTableRowFactory.call(): param: {}", param);

            final TreeTableRow<PathData> row = new TreeTableRow<>();
            final ContextMenu rowMenu = new ContextMenu();

            rowMenu.getItems().add(newMenuItem("Open", action -> {
                TreeItem<PathData> treeItem = row.getTreeItem();
                log.debug("##### Opening...: {}", treeItem.getValue());
            }));

            /*
             * Open With is a menu containing a list of compatible programs that can open the file or directory
             * pointed to. For now it's a dummy list.
             */
            rowMenu.getItems().add(newMenu("Open With",
                newMenuItem("A", action -> {
                    TreeItem<PathData> treeItem = row.getTreeItem();
                    log.debug("##### Opening {} with program A", treeItem.getValue());
                }),
                newMenuItem("B", action -> {
                    TreeItem<PathData> treeItem = row.getTreeItem();
                    log.debug("##### Opening {} with program B", treeItem.getValue());
                }),
                newMenuItem("C", action -> {
                    TreeItem<PathData> treeItem = row.getTreeItem();
                    log.debug("##### Opening {} with program C", treeItem.getValue());
                })));

            addSeparator(rowMenu);

            rowMenu.getItems().add(newMenuItem("Copy", action -> {
                TreeItem<PathData> treeItem = row.getTreeItem();
                log.debug("##### Copying...: {}", treeItem.getValue());
            }));

            rowMenu.getItems().add(newMenuItem("Cut", action -> {
                TreeItem<PathData> treeItem = row.getTreeItem();
                log.debug("##### Cutting...: {}", treeItem.getValue());
            }));

            rowMenu.getItems().add(newMenuItem("Paste", action -> {
                TreeItem<PathData> treeItem = row.getTreeItem();
                log.debug("##### Pasting...: {}", treeItem.getValue());
            }));

            rowMenu.getItems().add(newMenuItem("Delete", action -> {
                TreeItem<PathData> treeItem = row.getTreeItem();
                log.debug("##### Deleting...: {}", treeItem.getValue());
            }));

            row.contextMenuProperty()
                .bind(
                    Bindings
                        .when(Bindings.isNotNull(row.itemProperty()))
                        .then(rowMenu)
                        .otherwise((ContextMenu) null));
            return row;
        }

        private MenuItem newMenuItem(String text) {
            MenuItem menuItem = new MenuItem(text);
            return menuItem;
        }

        private Menu newMenu(String text) {
            Menu menu = new Menu(text);
            return menu;
        }

        private Menu newMenu(String text, MenuItem... menuItems) {
            Menu menu = new Menu(text, null, menuItems);
            return menu;
        }

        private MenuItem newMenuItem(String text, EventHandler<ActionEvent> handler) {
            MenuItem menuItem = newMenuItem(text);
            menuItem.setOnAction(handler);
            return menuItem;
        }

        private void addSeparator(ContextMenu contextMenu) {
            contextMenu.getItems().add(new SeparatorMenuItem());
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE, true);
        }

    }

    private TreeTableColumnBuilder<PathData, String> newColumnBuilder(String columnName) {
        return TreeTables.newColumnBuilder(columnName);
    }

    private void initFavorites() {
        favoritesListView.getItems().addAll("A", "B", "C");
        favoritesAccordian.expandedPaneProperty().setValue(favoritesTitledPane);
    }

    private String eventToString(ActionEvent e) {
        return String.format("event: type: %s, source: %s, target: %s", e.getEventType(), e.getSource(), e.getTarget());
    }

}
