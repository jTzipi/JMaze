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

package earth.eu.jtzipi.jmaze.core.algo;

import earth.eu.jtzipi.jmaze.core.Utils;
import earth.eu.jtzipi.jmaze.core.cell.ICell2D;
import earth.eu.jtzipi.jmaze.core.cell.ICell2DQuad;
import earth.eu.jtzipi.jmaze.core.grid.IGrid2D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Ellers implements IPlantable<IGrid2D<? extends ICell2D>> {


    Ellers() {

    }


    @Override
    public void plant( IGrid2D<? extends ICell2D> grid ) {

        int cols = grid.getCols();
        RowState rowState = new RowState( 0, cols );

        // for all rows
        for ( int i = 0; i < grid.getRows(); i++ ) {
            ICell2D[] row = grid.getCellsForRow( i );

            // until we have neighbour west
            for ( int j = 1; j < row.length; j++ ) {
                //
                if ( row[j] instanceof ICell2DQuad ) {

                    ICell2DQuad cq = ( ICell2DQuad ) row[j];

                    int set = rowState.setFor( cq );
                    int prev = rowState.setFor( cq.getWest() );

                    boolean link = set != prev && ( cq.getSouth() == ICell2D.Unknown.SINGLETON || Utils.rand( 2 ) == 0 );

                    if ( link ) {

                        cq.link( cq.getWest(), true );
                        rowState.merge( prev, set );
                    }
                }
            }

            // not last row
            if ( i < grid.getRows() - 1 ) {
                // TODO
                rowState = new RowState( rowState.nextSet, cols );


            }


        }
    }

    /**
     * Row State.
     */
    private final class RowState {


        private int[] rowCells;
        private int nextSet;
        private Map<Integer, Set<ICell2D>> cellM = new HashMap<>();

        private RowState( int next, int cells ) {

            this.nextSet = next;
            this.rowCells = new int[cells];
        }

        private void record( int set, ICell2D cell2D ) {
            rowCells[cell2D.getCol()] = set;
            cellM.compute( set, ( si, s ) -> new HashSet<>() ).add( cell2D );
        }

        private int setFor( ICell2D cell2D ) {

            int col = cell2D.getCol();
            // cell is not present
            if ( 0 == rowCells[col] ) {

                record( nextSet, cell2D );
                nextSet++;
            }
            return rowCells[col];
        }

        private void merge( int winner, int loser ) {

            for ( ICell2D cell : cellM.get( loser ) ) {
                rowCells[cell.getCol()] = winner;
                cellM.get( winner ).add( cell );
            }

            cellM.remove( loser );
        }


    }
}
