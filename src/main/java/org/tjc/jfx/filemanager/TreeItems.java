package org.tjc.jfx.filemanager;

import javafx.scene.Node;
import org.tjc.jfx.filemanager.builders.PathTreeItemBuilder;
import org.tjc.jfx.filemanager.model.PathData;

/**
 *
 * @author tjclancy
 */
public final class TreeItems {

    private TreeItems() {
    }

    public static PathTreeItem createPathTreeItem(final PathData pathModel) {
        return newPathTreeItemBuilder(pathModel).buid();
    }

    public static PathTreeItemBuilder newPathTreeItemBuilder() {
        return PathTreeItemBuilder.newInstance();
    }

    public static PathTreeItemBuilder newPathTreeItemBuilder(final PathData pathModel) {
        return PathTreeItemBuilder.newInstance(pathModel);
    }

    public static PathTreeItemBuilder newPathTreeItemBuilder(final PathData pathModel, final Node graphic) {
        return PathTreeItemBuilder.newInstance(pathModel, graphic);
    }

//    public static <T> TreeItemBuilder<T> newTreeItemBuilder() {
//        return TreeItemBuilder.<T>newInstance();
//    }
//
//    public static <T> TreeItemBuilder<T> newTreeItemBuilder(T value) {
//        return TreeItemBuilder.<T>newInstance(value);
//    }
//
//    public static <T> TreeItemBuilder<T> newTreeItemBuilder(T value, Node graphic) {
//        return TreeItemBuilder.<T>newInstance(value, graphic);
//    }
}
