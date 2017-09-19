package org.tjc.jfx.filemanager.builders;

import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.tjc.common.utils.test.UnitTestSupport.getMethodName;
import static org.tjc.common.utils.test.UnitTestSupport.setShowOutput;
import static org.tjc.common.utils.test.UnitTestSupport.writeBanner;
import static org.tjc.common.utils.test.UnitTestSupport.writeMessage;

/**
 *
 * @author tjclancy
 */
public class TreeTableColumnBuilderTest {
    @Before
    public void setup() {
        setShowOutput(true);
    }

    @After
    public void tearDown() {
        setShowOutput(false);
    }

    //@Ignore
    @Test
    public void testTreeTableColumnBuilder() {
        writeBanner(getMethodName());

        TreeTableColumnBuilder<String, String> builder = new TreeTableColumnBuilder<>("Test Column");
        TreeTableColumn<String, String> column =
            builder
                .setCellValueFactory(new TreeItemPropertyValueFactory<>("testColumn"))
                .setEditable(true)
                .setMinWidth(500)
                .setPrefWidth(800)
                .setResizable(true)
                .setSortable(false)
                .setVisible(true)
                .build();

        assertThat(column).isNotNull();
        assertThat(column.isEditable()).isTrue();
        assertThat(column.getMaxWidth()).isEqualTo(5000);
        assertThat(column.getMinWidth()).isEqualTo(500);
        assertThat(column.getPrefWidth()).isEqualTo(800);
        assertThat(column.isResizable()).isTrue();
        assertThat(column.isSortable()).isFalse();
        assertThat(column.isVisible()).isTrue();

        /*
         * display field values.
         */
        writeMessage("column                 : {0}", column);
        writeMessage("column.cellValueFactory: {0}", column.getCellValueFactory());
        writeMessage("column.editable        : {0}", column.isEditable());
        writeMessage("column.maxWidth        : {0}", column.getMaxWidth());
        writeMessage("column.minWidth        : {0}", column.getMinWidth());
        writeMessage("column.prefWidth       : {0}", column.getPrefWidth());
        writeMessage("column.resizable       : {0}", column.isResizable());
        writeMessage("column.sortable        : {0}", column.isSortable());
        writeMessage("column.visible         : {0}", column.isVisible());

    }

    /**
     * Test of setColumnName method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetColumnName() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setCellFactory method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetCellFactory() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setCellValueFactory method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetCellValueFactory() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setTreeItemPropertyValueFactory method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetTreeItemPropertyValueFactory() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setContextMenu method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetContextMenu() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setEditable method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetEditable() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setGraphic method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetGraphic() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setMaxWidth method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetMaxWidth() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setMinWidth method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetMinWidth() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setPrefWidth method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetPrefWidth() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setResizable method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetResizable() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setSortable method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetSortable() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setSortNode method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetSortNode() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setStyle method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetStyle() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setText method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetText() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setUserData method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetUserData() {
        writeBanner(getMethodName());
    }

    /**
     * Test of setVisible method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testSetVisible() {
        writeBanner(getMethodName());
    }

    /**
     * Test of build method, of class TreeTableColumnBuilder.
     */
    @Test
    public void testBuild() {
        writeBanner(getMethodName());
    }
}
