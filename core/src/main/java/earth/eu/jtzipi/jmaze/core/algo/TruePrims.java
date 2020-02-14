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
import earth.eu.jtzipi.jmaze.core.grid.IGrid2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class TruePrims implements IPlantable<IGrid2D<? extends ICell2D>> {

    private static Map<ICell2D, Double> weightedCells( IGrid2D<? extends ICell2D> grid, double bound ) {

        Map<ICell2D, Double> map = new HashMap<>();

        for ( ICell2D c : grid.getCells() ) {
            map.put( c, Utils.randDouble( bound ) );
        }

        return map;

    }

    @Override
    public void plant( IGrid2D<? extends ICell2D> grid ) {


        List<ICell2D> gadi = new ArrayList<>();
        Map<ICell2D, Double> costM = weightedCells( grid, 150D );
        ICell2D randC = grid.getRandomCell();

        gadi.add( randC );

        while ( !gadi.isEmpty() ) {

            // best path from rand
            ICell2D temp = randC.getNeighbours()
                    .values()
                    .stream()
                    .min( ( cOne, cTwo ) -> ( int ) Math.min( costM.get( cOne ), costM.get( cTwo ) ) )
                    .get();

            List<ICell2D> unlinkedL = temp.getNeighbours()
                    .values()
                    .stream()
                    .filter( c -> c.getLinkedCells().isEmpty() )
                    .collect( Collectors.toList() );

            if ( !unlinkedL.isEmpty() ) {
                ICell2D ulc = Utils.randOf( unlinkedL );
                temp.link( ulc, true );
                gadi.add( ulc );

            } else {
                gadi.remove( temp );
            }
        }
    }
}
