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

import earth.eu.jtzipi.jmaze.core.cell.ICell2D;
import earth.eu.jtzipi.jmaze.core.cell.ICell2DQuad;
import earth.eu.jtzipi.jmaze.core.grid.IGrid2D;

import java.util.*;
import java.util.stream.Collectors;

// import org.eu.jtzipi.jmaze.grid.IGrid2D;

/**
 * Implementation of "Kruskal's" algorithm.
 *
 * @author jTzipi
 */
public class Kruskal implements IPlantable<IGrid2D<? extends ICell2D>> {

    @Override
    public void plant( IGrid2D<? extends ICell2D> grid ) {


        State state = new State( grid );
        // shuffle
        Collections.shuffle( state.neighbourList );

        while ( !state.neighbourList.isEmpty() ) {
            ICell2D[] gadi = state.neighbourList.remove( 0 );

            ICell2D c1 = gadi[0];
            ICell2D c2 = gadi[1];

            if ( state.canMerge( c1, c2 ) ) {
                c1.link( c2, true );
                state.merge( c2, c1 );
            }
        }

    }

    /**
     * State of cells
     */
    static class State {

        private final IGrid2D<? extends ICell2D> grid;

        private Map<ICell2D, Long> cellSetMap;
        private Map<Long, Set<ICell2D>> cellsPerSetMap;
        private List<ICell2D[]> neighbourList;

        State( final IGrid2D<? extends ICell2D> grid ) {
            this.grid = grid;
            init();
        }


        private void init() {

            this.cellSetMap = new HashMap<>();
            this.cellsPerSetMap = new HashMap<>();
            this.neighbourList = new ArrayList<>();

            for ( ICell2D cell : this.grid.getCells() ) {

                if ( cell instanceof ICell2DQuad ) {

                    ICell2DQuad cq = ( ICell2DQuad ) cell;
                    Long sid = cellSetMap.merge( cq, 0L, Long::sum );

                    Set<ICell2D> tempS = new HashSet<>();
                    tempS.add( cq );
                    cellsPerSetMap.put( sid, tempS );

                    ICell2D e = cq.getEast();
                    ICell2D s = cq.getSouth();

                    if ( ICell2D.Unknown.SINGLETON != e ) {
                        neighbourList.add( new ICell2D[]{cq, e} );
                    }
                    if ( ICell2D.Unknown.SINGLETON != s ) {
                        neighbourList.add( new ICell2D[]{cq, s} );
                    }
                }

            }
        }

        boolean canMerge( ICell2D cellOne, ICell2D cellTwo ) {
            Objects.requireNonNull( cellOne );
            Objects.requireNonNull( cellTwo );
            return 0 != cellSetMap.get( cellOne ).compareTo( cellSetMap.get( cellTwo ) );
        }

        void merge( ICell2D cellOne, ICell2D cellTwo ) {
            Objects.requireNonNull( cellOne );
            Objects.requireNonNull( cellTwo );

            Long winnerID = cellSetMap.get( cellOne );
            Long otherID = cellSetMap.get( cellTwo );

            // finde alle anderen Zellen
            Set<ICell2D> set = cellSetMap.entrySet()
                    .stream()
                    .filter( entry -> entry.getValue().compareTo( otherID ) == 0L )
                    .map( Map.Entry::getKey )
                    .collect( Collectors.toSet() );

            for ( ICell2D lc : set ) {
                cellSetMap.replace( lc, winnerID );
            }

            cellsPerSetMap.get( winnerID ).addAll( set );
            cellsPerSetMap.remove( otherID );
        }
    }


}
