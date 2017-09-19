package org.tjc.jfx.filemanager;

import org.tjc.jfx.filemanager.builders.TreeTableColumnBuilder;

/**
 *
 * @author tjclancy
 */
public final class TreeTables {

    private TreeTables() {
    }

    public static <S, T> TreeTableColumnBuilder<S, T> newColumnBuilder(String columnName) {
        return new TreeTableColumnBuilder<>(columnName);
    }

}
