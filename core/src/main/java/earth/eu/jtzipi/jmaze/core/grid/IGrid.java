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

package earth.eu.jtzipi.jmaze.core.grid;

import earth.eu.jtzipi.jmaze.core.cell.ICell;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Root grid interface.
 *
 * @param <C> cell
 */
public interface IGrid<C extends ICell> {
    /**
     * Minimal grid size .
     */
    int MIN_SIZE = 1;

    /**
     * Return random cell of this grid.
     *
     * @return random cell
     */
    C getRandomCell();

    /**
     * Return all cells contained.
     *
     * @return cells of this grid
     */
    List<C> getCells();

    /**
     * @param row
     * @param col
     * @return
     */
    boolean isInbound( int row, int col );

    /**
     * Return all cells with only one linked cell.
     *
     * @return list of cells with only one linked neighbour
     * @throws NullPointerException if {@linkplain #getCells()} is {@code null}
     */
    default List<C> getSingleLinkedCells() {
        // ;
        return Objects.requireNonNull( getCells() ).stream().filter( cell -> cell.getLinkedCells().size() == 1 ).collect( Collectors.toList() );
    }
}
