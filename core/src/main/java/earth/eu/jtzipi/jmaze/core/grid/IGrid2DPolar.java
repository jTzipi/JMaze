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


import earth.eu.jtzipi.jmaze.core.cell.ICell2DPolar;

/**
 * Defines a grid with polar coordinate.
 * We use rows and cols too.
 *
 * @author jTzipi
 */
public interface IGrid2DPolar<C extends ICell2DPolar> extends IGrid<C> {

    // public static final
    /**
     * PI *2 == 360°.
     */
    double PI_2 = Math.PI * 2D;

    /**
     * Maximum rows.
     */
    int MAX_ROWS = 700_000;

    /**
     * Return the cell for row and column.
     *
     * @param row row
     * @param col column
     * @return cell or {@linkplain ICell2DPolar.Unknown};
     * @throws IndexOutOfBoundsException if {@code row}|{@code col} are oob
     */
    C getCell( int row, int col );

    /**
     * Return rows of this grid.
     *
     * @return rows
     */
    int getRows();

    /**
     * Return column for row of this grid.
     *
     * @param row column ( &ge; 0 && &lt; getRows() )
     * @return column for row
     * @throws IndexOutOfBoundsException if {@code row} {@literal >=} {@link #getRows()} || {@literal < 0}
     */
    int getCols( int row );

    /**
     * Return row height.
     *
     * @return row height
     */
    default double getRowHeight() {
        return 1D / getRows();
    }

}
