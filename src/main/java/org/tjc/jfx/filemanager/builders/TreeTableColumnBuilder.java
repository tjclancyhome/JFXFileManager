package org.tjc.jfx.filemanager.builders;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author tjclancy
 * @param <S>
 * @param <T>
 */
@Slf4j
public class TreeTableColumnBuilder<S, T> {
    private Callback<TreeTableColumn<S, T>, TreeTableCell<S, T>> cellFactory = null;
    private Callback<TreeTableColumn.CellDataFeatures<S, T>, ObservableValue<T>> cellValueFactory = null;
    private ContextMenu contextMenu = null;
    private boolean editable = false;
    private Node graphic = null;
    private Double maxWidth = null;
    private Double minWidth = 0.0;
    private Double prefWidth = null;
    private boolean resizable = true;
    private boolean sortable = true;
    private Node sortNode = null;
    private String style = null;
    private String text = "[Default Column Name]";
    private Object userData = null;
    private boolean visible = true;
    private List<String> styleClasses = new ArrayList<>();

    public TreeTableColumnBuilder() {
    }

    public TreeTableColumnBuilder(String columnName) {
        this.text = columnName;
    }

    public TreeTableColumnBuilder<S, T> setColumnName(String columnName) {
        this.text = columnName;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setCellFactory(
        Callback<TreeTableColumn<S, T>, TreeTableCell<S, T>> cellFactory) {
        this.cellFactory = cellFactory;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setCellValueFactory(
        Callback<TreeTableColumn.CellDataFeatures<S, T>, ObservableValue<T>> cellValueFactory) {
        this.cellValueFactory = cellValueFactory;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setTreeItemPropertyValueFactory(String fieldName) {
        return setCellValueFactory(new TreeItemPropertyValueFactory<>(fieldName));
    }

    public TreeTableColumnBuilder<S, T> setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setEditable(boolean editable) {
        this.editable = editable;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setGraphic(Node graphic) {
        this.graphic = graphic;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setMaxWidth(double maxWidth) {
        this.maxWidth = maxWidth;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setMinWidth(double minWidth) {
        this.minWidth = minWidth;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setPrefWidth(double prefWidth) {
        this.prefWidth = prefWidth;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setResizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setSortable(boolean sortable) {
        this.sortable = sortable;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setSortNode(Node sortNode) {
        this.sortNode = sortNode;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setStyle(String style) {
        this.style = style;
        return this;
    }

    public TreeTableColumnBuilder<S, T> addStyleClass(String styleClassName) {
        this.styleClasses.add(styleClassName);
        return this;
    }

    public TreeTableColumnBuilder<S, T> setText(String text) {
        this.text = text;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setUserData(Object userData) {
        this.userData = userData;
        return this;
    }

    public TreeTableColumnBuilder<S, T> setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public TreeTableColumn<S, T> build() {
        TreeTableColumn<S, T> column = new TreeTableColumn<>(text);

        if (cellFactory != null) {
            column.setCellFactory(cellFactory);
        }
        if (cellValueFactory != null) {
            column.setCellValueFactory(cellValueFactory);
        }
        if (contextMenu != null) {
            column.setContextMenu(contextMenu);
        }
        if (graphic != null) {
            column.setGraphic(graphic);
        }
        if (sortNode != null) {
            column.setSortNode(sortNode);
        }
        if (style != null) {
            column.setStyle(style);
        }
        if (userData != null) {
            column.setUserData(userData);
        }
        if (maxWidth != null) {
            column.setMaxWidth(maxWidth);
        }
        if (minWidth != null) {
            column.setMinWidth(minWidth);
        }
        if (prefWidth != null) {
            column.setPrefWidth(prefWidth);
        }
        if (!styleClasses.isEmpty()) {
            column.getStyleClass().addAll(styleClasses);
        }

        column.setResizable(resizable);
        column.setSortable(sortable);
        column.setText(text);
        column.setVisible(visible);
        column.setEditable(editable);

        return column;
    }

}
