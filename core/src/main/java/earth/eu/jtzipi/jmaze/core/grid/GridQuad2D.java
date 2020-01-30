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
import earth.eu.jtzipi.jmaze.core.cell.Cell2DQuad;
import earth.eu.jtzipi.jmaze.core.cell.ICell2DQuad;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * A planar two dimensional grid that have quadratic cells.
 *
 * @author jTzipi
 */
public class GridQuad2D extends AbstractPlanarGrid2D<ICell2DQuad> {

    /**
     * grid.
     */
    private final Cell2DQuad[][] grid;

    /**
     * CartesianGrid C.
     *
     * @param rows rows &ge; {@linkplain IGrid2D#MIN_SIZE}
     * @param cols columns &ge; {@linkplain IGrid2D#MIN_SIZE}
     */
    GridQuad2D( int rows, int cols ) {
        super( rows, cols );
        grid = new Cell2DQuad[rows][cols];
    }

    /**
     * Create a 2D grid.
     *
     * @param rows   row &gt;
     * @param column column &gt;
     * @return grid of dim [{@code rows}|{@code column}]
     */
    public static GridQuad2D of( final int rows, final int column ) {
        if ( MIN_SIZE > rows || MIN_SIZE > column ) {
            throw new IllegalArgumentException( "rows[=" + rows + "]|column[=" + column + "] <" + MIN_SIZE );
        }

        GridQuad2D cartesianGrid = new GridQuad2D( rows, column );
        cartesianGrid.init();

        return cartesianGrid;
    }

    @Override
    protected void setup() {
        // to configure
    }

    @Override
    protected void init() {

        // upper left cell

        for ( int ri = 0; ri < getRows(); ri++ ) {

            for ( int ci = 0; ci < getCols(); ci++ ) {

                Cell2DQuad cell = Cell2DQuad.of( ri, ci );
                grid[ri][ci] = cell;
// north and west are known
// add neighbour to N|E|W|S
                if ( 0 < ri ) {

                    Cell2DQuad north = grid[ri - 1][ci];
                    north.setNeighbourSouth( cell );
                    cell.setNeighbourNorth( north );
                }

                if ( 0 < ci ) {
                    Cell2DQuad west = grid[ri][ci - 1];
                    west.setNeighbourEast( cell );
                    cell.setNeighbourWest( west );
                }
                // ICell2D west = 0 == ci ? ICell2D.Unknown.SINGLETON :
            }

        }


    }

    @Override
    public ICell2DQuad getRandomCell() {

        int row = Utils.rand( getRows() );
        int col = Utils.rand( row );

        return getCell( row, col );
    }

    @Override
    public List<ICell2DQuad> getCells() {
        return Stream.of( grid ).flatMap( Stream::of ).collect( toList() );
    }

    @Override
    public ICell2DQuad getCell( int row, int col ) {
        if ( !isInbound( row, col ) ) {
            throw new IndexOutOfBoundsException( "[row=row|col=col] out of bounds" );
        }
        return grid[row][col];
    }

    @Override
    public ICell2DQuad[] getCellsForRow( int row ) {
        if ( getRows() >= row ) {
            throw new IllegalArgumentException( new IndexOutOfBoundsException( "row >= getRows" ) );
        }

        return grid[row];
    }

    @Override
    public String toString() {
        return "GridQuad2D{" +
                "grid=" + Arrays.toString( grid ) +
                '}';
    }
}
