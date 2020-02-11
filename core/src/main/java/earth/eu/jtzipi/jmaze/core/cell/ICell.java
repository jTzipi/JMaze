/*
 * Copyright 2020 (c) Tim Langhammer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package earth.eu.jtzipi.jmaze.core.cell;

import java.util.List;
import java.util.Objects;

/**
 * Provide minimal cell API.
 * <p>
 * A cell knows other <i>linked</i> cells.
 * That is cells who are connected to this.
 * A cell can be linked and unlinked to other.
 */
public interface ICell {
    /**
     * Minimal row position.
     */
    int ROW_MIN = 0;
    /**
     * Minimal col position.
     */
    int COLUMN_MIN = 0;

    /**
     * Return whether this cell is linked to other.
     *
     * @param other other cell
     * @return {@code true} if {@code other} is linked
     * @throws NullPointerException if {@code other} null
     */
    default boolean isLinked( ICell other ) {
        Objects.requireNonNull( other );
        return getLinkedCells().contains( other );
    } // ;


    /**
     * Return row this cell is located.
     *
     * @return row
     */
    int getRow();

    /**
     * Return column of this cell.
     *
     * @return column
     */
    int getCol();

    /**
     * Return all cells linked to this .
     *
     * @return linked list
     */
    List<ICell> getLinkedCells();

    /**
     * Link a cell to this.
     * <p>
     * That is link cell to list of linked cells of this.
     * </p>
     *
     * @param cell     other cell
     * @param bidiProp link also this to {@code cell}
     */
    void link( ICell cell, boolean bidiProp );

    /**
     * Unlink a cell.
     *
     * @param cell     other cell
     * @param bidiProp unlink also {@code cell}
     */
    void unlink( ICell cell, boolean bidiProp );
}
