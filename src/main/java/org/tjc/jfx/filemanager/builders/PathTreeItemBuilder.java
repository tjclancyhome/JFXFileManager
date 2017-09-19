package org.tjc.jfx.filemanager.builders;

import javafx.scene.Node;
import lombok.extern.slf4j.Slf4j;
import org.tjc.jfx.filemanager.PathTreeItem;
import org.tjc.jfx.filemanager.model.PathData;

/**
 *
 * @author tjclancy
 */
@Slf4j
public class PathTreeItemBuilder {
    private boolean expanded = false;
    private Node graphic = null;
    private PathData value = null;

    private PathTreeItemBuilder() {
    }

    private PathTreeItemBuilder(final PathData value) {
        this.value = value;
    }

    private PathTreeItemBuilder(final PathData value, final Node graphic) {
        this.value = value;
        this.graphic = graphic;
    }

    public static PathTreeItemBuilder newInstance() {
        return new PathTreeItemBuilder();
    }

    public static PathTreeItemBuilder newInstance(final PathData value) {
        return new PathTreeItemBuilder(value);
    }

    public static PathTreeItemBuilder newInstance(final PathData value, final Node graphic) {
        return new PathTreeItemBuilder(value, graphic);
    }

    public PathTreeItemBuilder setExpanded(boolean expanded) {
        log.trace("Entered setExpanded(): expanded: {}", expanded);
        this.expanded = expanded;
        return this;
    }

    public PathTreeItemBuilder setGraphic(Node graphic) {
        log.trace("Entered setGraphic(): graphic: {}", graphic);
        this.graphic = graphic;
        return this;
    }

    public PathTreeItemBuilder setValue(PathData value) {
        log.trace("Entered setValue(): value: {}", value);
        this.value = value;
        return this;
    }

    public PathTreeItem buid() {
        log.trace("##### Entered build(): value: {}", value);
        log.trace("##### Entered build(): value is hidden?: {}", value.isHidden());
        PathTreeItem treeItem = new PathTreeItem(value);
        treeItem.setExpanded(expanded);
        treeItem.setGraphic(graphic);
        treeItem.setValue(value);
        log.trace("##### Exiting build(): treetem: {}", treeItem);

        return treeItem;
    }
}
