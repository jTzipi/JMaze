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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of 'Recursive Backtracker' algo for 2D cells.
 */
public class RecursiveBacktracker implements IPlantable<IGrid2D<? extends ICell2D>> {

    public RecursiveBacktracker() {

    }

    @Override
    public void plant( IGrid2D<? extends ICell2D> grid ) {

        Deque<ICell2D> deque = new ArrayDeque<>();

        ICell2D rand = grid.getRandomCell();
        deque.push( rand );

        // while we have cells
        while ( !deque.isEmpty() ) {
            // get top cell
            ICell2D peek = deque.peek();
            // and look if there are cells unlinked to other
            List<ICell2D> nbl = peek.getNeighbours()
                    .values()
                    .stream()
                    .filter( cell -> cell != ICell2D.Unknown.SINGLETON && cell.getLinkedCells().isEmpty() )
                    .collect( Collectors.toList() );

            // no unlinked neighbours -> recursive track back
            if ( nbl.isEmpty() ) {

                deque.pop();
            } else {

                ICell2D nb = Utils.randOf( nbl );
                peek.link( nb, true );

                // append unlinked
                deque.push( nb );
            }
        }
    }
}
