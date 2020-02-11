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

import earth.eu.jtzipi.jmaze.core.cell.ICell2D;


/**
 * Basic grid API storing 2D cell.
 *
 * @author jTzipi
 */
public interface IGrid2D<C extends ICell2D> extends IGrid<C> {


    /**
     * Return rows of this grid.
     *
     * @return rows &ge; {@linkplain IGrid#MIN_SIZE}
     */
    int getRows();

    /**
     * Return cols of this grid.
     *
     * @return cols &ge; {@linkplain IGrid#MIN_SIZE}
     */
    int getCols();


    /**
     * Return cell @[row, col].
     *
     * @param row row &ge; 0
     * @param col column &ge; 0
     * @return cell
     * @throws IndexOutOfBoundsException if {@code row|column} are &lt; 0
     */
    C getCell( int row, int col );


    /**
     * Return row of this grid.
     *
     * @param row row &ge; {@code 0}
     * @return iterator for all rows
     * @throws IndexOutOfBoundsException if {@code row} &lt; 0 || &ge; {@link #getRows()}
     */
    C[] getCellsForRow( int row );

    /**
     * Return dimension of this grid.
     *
     * @return dimension
     */
    default long getDim() {
        return getRows() * getCols();
    }
}
