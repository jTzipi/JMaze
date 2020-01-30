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
 * Abstract Cartesian grid.
 * <p>
 * A cartesian grid is a two dimensional area with at least
 * one row and one column.
 *
 * </p>
 *
 * @param <C>
 */
public abstract class AbstractPlanarGrid2D<C extends ICell2D> implements IGrid2D<C> {

    int rows;           // rows
    int cols;           // cols

    /**
     * Abstract 2D Grid.
     *
     * @param r row
     * @param c column
     */
    AbstractPlanarGrid2D( int r, int c ) {
        this.rows = r;
        this.cols = c;
    }

    /**
     * Return whether [row|col] are valid.
     *
     * @param row row
     * @param col column
     * @return {@code true} if {@code row|col} are inbound
     */
    public boolean isInbound( final int row, final int col ) {
        return row < getRows() && 0 <= row && col < getCols() && 0 <= col;
    }

    /**
     * Setup for this grid.
     */
    protected void setup() {

    }

    /**
     * Construct grid here.
     */
    protected abstract void init();

    public final int getRows() {
        return rows;
    }

    public final int getCols() {
        return cols;
    }
}
