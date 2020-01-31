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

package earth.eu.jtzipi.jmaze.core.distance;


import earth.eu.jtzipi.jmaze.core.cell.ICell2D;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Implementation of <a href="https://en.wikipedia.org/wiki/Edsger_W._Dijkstra"></a>Edsger W. Dijkstra</a>'s <a href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">algorithm</a>.
 *
 * @author jTzipi
 */
public class Dijkstra implements IDistanceMetric<ICell2D> {

    private final Map<ICell2D, Long> distanceMap = new HashMap<>();
    private final Queue<ICell2D> frontierQ = new PriorityQueue<>();

    @Override
    public Map<ICell2D, Long> calculate( ICell2D root ) {

        frontierQ.add( root );
        distanceMap.put( root, 0L );

        while ( !frontierQ.isEmpty() ) {

            // remove top most cell
            ICell2D cell = frontierQ.remove();
            long distance = distanceMap.get( cell );

            for ( ICell2D cellNeighbour : cell.getNeighbours().values() ) {
                // Check present
                if ( cellNeighbour.isLinked( cell ) && !distanceMap.containsKey( cellNeighbour ) ) {

                    frontierQ.add( cellNeighbour );
                    distanceMap.put( cellNeighbour, distance + 1L );
                }

            }


        }
        return distanceMap;
    }


}
