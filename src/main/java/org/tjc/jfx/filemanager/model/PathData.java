package org.tjc.jfx.filemanager.model;

import static com.google.common.base.Preconditions.checkArgument;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import static lombok.Lombok.checkNotNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.tjc.common.utils.MemoryMetricsConverters;
import static org.tjc.common.utils.StringFormatUtils.yesOrNo;

/**
 *
 * @author tjclancy
 */
@Slf4j
public class PathData {
    private final ObjectProperty<Path> pathProperty;

    public PathData(Path path) {
        checkNotNull(path, "The 'path' argument is null.");
        checkArgument(Files.exists(path), String.format("The path: %s does not exist.", path));
//        try {
//            inspectPath(path);
//        }
//        catch (IOException ex) {
//        }
        pathProperty = new SimpleObjectProperty<>(path);
    }

    public static PathData newInstance(Path path) {
        return new PathData(path);
    }

    public static PathData of(Path path) {
        return new PathData(path);
    }

    @SneakyThrows(IOException.class)
    public long getLength() {
        return Files.size(getPath());
    }

    public String getSize() {
        if (isDirectory()) {
            return "--";
        }
        else {
            return MemoryMetricsConverters.toMostRelevantString((double) getLength());
        }
    }

    @SneakyThrows(IOException.class)
    public List<PathData> childItems() {
        if (!isDirectory()) {
            throw new IllegalStateException("The file item is not a directory.");
        }
        return Files.list(getPath()).map((Path p) -> new PathData(p)).collect(Collectors.toList());
    }

    public String getName() {
        Path path = getPath();
        return path.getName(path.getNameCount() - 1).toString();
    }

    public String getKind() {
        Path path = getPath();
        if (Files.isDirectory(path)) {
            return "Folder";
        }
        else {
            return "Document";
        }
    }

    public ObjectProperty<Path> pathProperty() {
        return pathProperty;
    }

    public boolean isDirectory() {
        return Files.isDirectory(getPath());
    }

    public boolean isExecutable() {
        return Files.isExecutable(getPath());
    }

    @SneakyThrows(IOException.class)
    public boolean isHidden() {
        boolean hidden = false;
        hidden = Files.isHidden(getPath());
        return hidden;
    }

    public boolean isReadable() {
        return Files.isReadable(getPath());
    }

    public boolean isRegularFile() {
        return Files.isRegularFile(getPath());
    }

    public boolean isSymbolicLink() {
        return Files.isSymbolicLink(getPath());
    }

    public boolean isWriteable() {
        return Files.isWritable(getPath());
    }

    @SneakyThrows(IOException.class)
    public ObjectProperty<Path> rename(String newName) {
        Path newPath = Files.move(getPath(), Paths.get(newName));
        pathProperty.setValue(newPath);
        return pathProperty;
    }

    @SneakyThrows(IOException.class)
    public long getLastModifiedTime() {
        FileTime fileTime = Files.getLastModifiedTime(getPath());
        return fileTime.toMillis();
    }

    @SneakyThrows(IOException.class)
    public String getLastModifiedTimeString() {
        FileTime fileTime = Files.getLastModifiedTime(getPath());
        ZonedDateTime dt = ZonedDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
        return dt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

    public Path getPath() {
        return pathProperty.getValue();
    }

    @Override
    public String toString() {
        return getPath().toString();
    }

    @SneakyThrows(IOException.class)
    private void inspectPath(Path path) {
        final String logPattern =
            "File: {} -> directory? {}, regular? {}, executable? {}, hidden? {}, readable? {}, writable? {}, symlnk? {}";
        log.debug("==================================");

        log.debug(logPattern,
            path.getFileName(),
            yesOrNo(Files.isDirectory(path)),
            yesOrNo(Files.isRegularFile(path)),
            yesOrNo(Files.isExecutable(path)),
            yesOrNo(Files.isHidden(path)),
            yesOrNo(Files.isReadable(path)),
            yesOrNo(Files.isWritable(path)),
            yesOrNo(Files.isSymbolicLink(path)));
//        log.info("{} is directory   : {}", path.getFileName(), Files.isDirectory(path));
//        log.info("{} is executable  : {}", path.getFileName(), Files.isExecutable(path));
//        try {
//            log.info("{} is hidden      : {}", path.getFileName(), Files.isHidden(path));
//        }
//        catch (IOException ex) {
//        }
//        log.info("{} is readable    : {}", path.getFileName(), Files.isReadable(path));
//        log.info("{} is writable    : {}", path.getFileName(), Files.isWritable(path));
//        log.info("{} is regular file: {}", path.getFileName(), Files.isRegularFile(path));
//        log.info("{} is symlink     : {}", path.getFileName(), Files.isSymbolicLink(path));
//        log.info("==================================");
    }

}
