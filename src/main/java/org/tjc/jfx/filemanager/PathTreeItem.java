package org.tjc.jfx.filemanager;

import java.util.stream.Collectors;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import lombok.extern.slf4j.Slf4j;
import org.tjc.jfx.filemanager.model.PathData;

/**
 *
 * @author tjclancy
 */
@Slf4j
public class PathTreeItem extends TreeItem<PathData> {
    private boolean isLeaf = false;
    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeaf = true;
    private BooleanProperty showHiddenFiles = new SimpleBooleanProperty(true);

    public PathTreeItem() {
    }

    public PathTreeItem(PathData value) {
        super(value);
    }

    public PathTreeItem(PathData value, Node graphic) {
        super(value, graphic);
    }

    public static PathTreeItem newInstance() {
        return new PathTreeItem();
    }

    public static PathTreeItem newInstance(PathData file) {
        return new PathTreeItem(file);
    }

    public static PathTreeItem newInstance(PathData path, Node graphic) {
        return new PathTreeItem(path, graphic);
    }

    public BooleanProperty getShowHiddenFiles() {
        return showHiddenFiles;
    }

    public BooleanProperty showHiddenFilesProperty() {
        return showHiddenFiles;
    }

    @Override
    public ObservableList<TreeItem<PathData>> getChildren() {
        log.trace("##### Entered getChildren()");
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            super.getChildren().setAll(buildChildren(this));
        }
        return super.getChildren();
    }

    @Override
    public boolean isLeaf() {
        log.trace("##### Entered isLeaf()");
        if (isFirstTimeLeaf) {
            isFirstTimeLeaf = false;
            PathData pathModel = getValue();
            isLeaf = pathModel.isRegularFile();
        }
        return isLeaf;
    }

    private ObservableList<TreeItem<PathData>> buildChildren(PathTreeItem treeItem) {
        log.trace("##### Entered buildChildren(): treeItem: {}", treeItem);
        PathData pathData = treeItem.getValue();
        log.trace("##### pathData: {}", pathData);
        ObservableList<TreeItem<PathData>> children;
        if (pathData != null && pathData.isDirectory()) {
            log.trace("##### pathData != null && pathData.isDirectory");
            children = FXCollections.observableArrayList(
                pathData
                    .childItems()
                    .stream()
                    .filter(data -> includeHidden(data))
                    .map(data -> TreeItems.createPathTreeItem(data))
                    .collect(Collectors.toList()));
        }
        else {
            children = FXCollections.emptyObservableList();
        }

        log.trace("##### Exited buildChildren()");
        return children;
    }

    private boolean includeHidden(PathData data) {
        log.trace("##### Entered includeHidden: data: {}", data);
        boolean include = true;
        if (data.isHidden()) {
            log.trace("##### File: {}, is hidden.", data);

            if (showHiddenFiles.get() == false) {
                log.trace("##### showHiddenFiles.get(): {}", showHiddenFiles.get());
                include = false;
            }
        }
        log.trace("##### File: {}, is NOT hidden.", data);
        log.trace("##### include: {}", include);
        return include;
    }

}
