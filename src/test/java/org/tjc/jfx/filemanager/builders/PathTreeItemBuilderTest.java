package org.tjc.jfx.filemanager.builders;

import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.tjc.common.utils.SystemUtils.getUserHomePath;
import static org.tjc.common.utils.test.UnitTestSupport.getMethodName;
import static org.tjc.common.utils.test.UnitTestSupport.setShowOutput;
import static org.tjc.common.utils.test.UnitTestSupport.writeBanner;
import static org.tjc.common.utils.test.UnitTestSupport.writeMessage;
import static org.tjc.common.utils.test.UnitTestSupport.writeln;
import org.tjc.jfx.filemanager.PathTreeItem;
import org.tjc.jfx.filemanager.model.PathData;

/**
 *
 * @author tjclancy
 */
public class PathTreeItemBuilderTest {

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
    public void testPathTreeItemBuilder() {
        writeBanner(getMethodName());

        PathData pathModel = new PathData(getUserHomePath());

        PathTreeItemBuilder builder = PathTreeItemBuilder.newInstance(pathModel);
        PathTreeItem treeItem = builder
            .setExpanded(true)
            .buid();

        writeMessage("##### treeItem: {0}", treeItem);

        assertThat(treeItem).isNotNull();
        assertThat(treeItem.getValue()).isNotNull();
        assertThat(treeItem.getValue()).isNotNull();
        if (treeItem.getChildren() == null) {
            writeln("##### getChildren() is null.");
        }
        assertThat(treeItem.getChildren()).isNotNull();
        writeMessage("##### treeItem.getChildren: {0}", treeItem.getChildren());
        //assertThat(treeItem.getChildren()).isNotEmpty();
    }
}
