package org.tjc.jfx.filemanager.model;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
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
public class PathModelTest {

    @Before
    public void setup() {
        setShowOutput(true);
    }

    @After
    public void tearDown() {
        setShowOutput(false);
    }

    /**
     * Test of newInstance method, of class PathData.
     */
    @Test
    public void testNewInstance() {
        writeBanner(getMethodName());
        PathData pm = PathData.newInstance(Paths.get("target"));
        assertThat(pm).isNotNull();
    }

    /**
     * Test of getLength method, of class PathData.
     */
    @Test
    public void testLength() {
        writeBanner(getMethodName());
        PathData pm = PathData.newInstance(Paths.get("src/test/resources/logback-test.xml"));
        assertThat(pm).isNotNull();
        writeMessage("length in bytes: {0}", Long.toString(pm.getLength()));
    }

    /**
     * Test of childItems method, of class PathData.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testChildItems() throws IOException {
        writeBanner(getMethodName());
        PathData pm = PathData.newInstance(Paths.get("target"));
        assertThat(pm).isNotNull();
        pm.childItems().forEach(pathModel -> writeMessage("pathModel: {0}", pathModel));
    }

    /**
     * Test of pathProperty method, of class PathData.
     */
    @Test
    public void testPathProperty() {
        writeBanner(getMethodName());
        PathData pm = PathData.of(Paths.get("target"));
        assertThat(pm).isNotNull();
        assertThat(pm.pathProperty()).isNotNull();

    }

    /**
     * Test of isDirectory method, of class PathData.
     */
    @Test
    public void testIsDirectory() {
        writeBanner(getMethodName());
        PathData pm = PathData.newInstance(Paths.get("target"));
        assertThat(pm).isNotNull();
        assertThat(pm.isDirectory()).isTrue();
    }

    /**
     * Test of isExecutable method, of class PathData.
     */
    @Test
    public void testIsExecutable() {
        writeBanner(getMethodName());
        PathData pm = PathData.newInstance(Paths.get("target"));
        assertThat(pm).isNotNull();
        assertThat(pm.isExecutable()).isTrue();
    }

    /**
     * Test of isHidden method, of class PathData.
     */
    @Test
    public void testIsHidden() {
        writeBanner(getMethodName());
        PathData pm = PathData.newInstance(Paths.get("target"));
        assertThat(pm).isNotNull();
        assertThat(pm.isHidden()).isFalse();
    }

    /**
     * Test of isReadable method, of class PathData.
     */
    @Test
    public void testIsReadable() {
        writeBanner(getMethodName());
        PathData pm = PathData.newInstance(Paths.get("target"));
        assertThat(pm).isNotNull();
        assertThat(pm.isReadable()).isTrue();
    }

    /**
     * Test of isRegularFile method, of class PathData.
     */
    @Test
    public void testIsRegularFile() {
        writeBanner(getMethodName());
        PathData pm = PathData.newInstance(Paths.get("src/test/resources/logback-test.xml"));
        assertThat(pm).isNotNull();
        assertThat(pm.isRegularFile()).isTrue();
    }

    /**
     * Test of isSymbolicLink method, of class PathData.
     */
    @Test
    public void testIsSymbolicLink() {
        writeBanner(getMethodName());
        PathData pm = PathData.newInstance(Paths.get("target"));
        assertThat(pm).isNotNull();
        assertThat(pm.isSymbolicLink()).isFalse();
    }

    /**
     * Test of isWriteable method, of class PathData.
     */
    @Test
    public void testIsWriteable() {
        writeBanner(getMethodName());
        PathData pm = PathData.of(Paths.get("target"));
        assertThat(pm).isNotNull();
        assertThat(pm.isWriteable()).isTrue();
    }

    /**
     * Test of getLastModifiedTime method, of class PathData.
     */
    @Test
    public void testGetLastModifiedTime() throws IOException {
        writeBanner(getMethodName());
        PathData pm = PathData.of(Paths.get("target"));
        assertThat(pm).isNotNull();
        assertThat(pm.getLastModifiedTime()).isGreaterThan(Long.MIN_VALUE);
    }

    /**
     * Test of getPath method, of class PathData.
     */
    @Test
    public void testGetPath() {
        writeBanner(getMethodName());
        PathData pm = PathData.of(Paths.get("target"));
        assertThat(pm).isNotNull();
        Path path = pm.getPath();
        writeMessage("path: {0}", path.toAbsolutePath());
        assertThat(pm).hasFieldOrProperty("name");
        assertThat(pm.getName()).endsWith("target");
    }

    /**
     * Test of toString method, of class PathData.
     */
    @Test
    public void testToString() {
        writeBanner(getMethodName());
    }

    /**
     * Test of of method, of class PathData.
     */
    @Test
    public void testOf() {
    }

    /**
     * Test of getLength method, of class PathData.
     */
    @Test
    public void testGetLength() {
    }

    /**
     * Test of getSize method, of class PathData.
     */
    @Test
    public void testGetSize() {
    }

    /**
     * Test of getName method, of class PathData.
     */
    @Test
    public void testGetName() {
    }

    /**
     * Test of getLastModifiedTimeString method, of class PathData.
     */
    @Test
    public void testGetLastModifiedTimeString() {
    }

}
