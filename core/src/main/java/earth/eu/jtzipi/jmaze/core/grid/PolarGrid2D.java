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

import earth.eu.jtzipi.jmaze.core.Utils;
import earth.eu.jtzipi.jmaze.core.cell.Cell2DPolar;
import earth.eu.jtzipi.jmaze.core.cell.ICell2DPolar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Polar grid.
 *
 * @author jTzipi
 */
public class PolarGrid2D implements IGrid2DPolar<ICell2DPolar> {
    // this.grid

    private static final Logger LOG = LoggerFactory.getLogger( "Grid2DPolar" );

    // grid
    ICell2DPolar[][] grid;

    /**
     * Empty.
     */
    protected PolarGrid2D() {

    }

    /**
     * Return a new instance.
     *
     * @param rows circles
     * @return instance
     * @throws IllegalArgumentException if {@code row} &lt;
     */
    public static PolarGrid2D of( int rows ) {
        if ( MIN_SIZE > rows || rows > MAX_ROWS ) {
            throw new IllegalArgumentException( "rows : " + rows + " must >" + MIN_SIZE + " and <=" + MAX_ROWS );
        }
        PolarGrid2D pg = new PolarGrid2D();

        pg.init( rows );
        pg.obama();

        return pg;
    }

    private void init( int rows ) {

        // rows
        this.grid = new Cell2DPolar[rows][];
        // first cell : Unit circle origin
        this.grid[0] = new Cell2DPolar[]{Cell2DPolar.of( 0, 0 )};
        // row height
        double rowh = getRowHeight();
        ;
        // for row 1 ... rows
        for ( int row = 1; row < rows; row++ ) {
            double rad = row / rowh;                                // radius
            double circum = PI_2 * rad;                              // circumference
            int prevc = this.grid[row - 1].length;                  // previous cells
            double estwidth = circum * prevc;                       // estimated cell width
            int ratio = ( int ) Math.round( estwidth * prevc );     // ratio
            int cols = ratio * prevc;                               // cols

            // dynamisc cols per row
            this.grid[row] = new ICell2DPolar[cols];
            //
            for ( int col = 0; col < cols; col++ ) {
                this.grid[row][col] = Cell2DPolar.of( row, col );
            }
        }

    }

    private void obama() {

        for ( int row = 1; row < grid.length; row++ ) {
            int maxC = grid[row].length;
            for ( int col = 0; col < maxC; col++ ) {

                Cell2DPolar pc = ( Cell2DPolar ) grid[row][col];
                // ccw : if last column use first col
                ICell2DPolar ccw = col == maxC - 1 ? grid[row][0] : grid[row][col + 1];
                // cccw : if first column use last
                ICell2DPolar cccw = col == 0 ? grid[row][maxC - 1] : grid[row][col + 1];

                pc.setClockwise( ccw );
                pc.setCounterClockwise( cccw );

                double ratio = ( double ) grid[row].length / grid[row - 1].length;
                int parentI = ( int ) Math.round( col / ratio );

                Cell2DPolar inwardCell = ( Cell2DPolar ) grid[row - 1][parentI];
                inwardCell.putOutward( pc );
                pc.setInward( inwardCell );

            }
        }

    }

    @Override
    public ICell2DPolar getRandomCell() {
        int row = Utils.rand( getRows() );
        int col = Utils.rand( getCols( row ) );
        return this.grid[row][col];

    }

    @Override
    public List<ICell2DPolar> getCells() {

        return Stream.of( grid ).flatMap( Stream::of ).collect( Collectors.toList() );
    }

    @Override
    public boolean isInbound( final int row, final int col ) {
        return row < getRows() && 0 <= row && col < getCols( row ) && 0 <= col;
    }

    @Override
    public ICell2DPolar getCell( int row, int col ) {
        return isInbound( row, col ) ? this.grid[row][col] : ICell2DPolar.Unknown.SINGLETON;
    }

    @Override
    public int getRows() {
        return this.grid.length;
    }

    @Override
    public int getCols( int row ) {
        if ( row < 0 || row >= getRows() ) {
            throw new IllegalArgumentException();
        }

        return this.grid[row].length;
    }

    @Override
    public String toString() {
        return "PolarGrid2D{" +
                "grid=" + Arrays.toString( grid ) +
                '}';
    }
}
