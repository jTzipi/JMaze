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

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of
 *
 * @author jTzipi
 */
public class BundWithDill implements IPlantable<IGrid2D<? extends ICell2D>> {

    BundWithDill() {

    }

    @Override
    public void plant( IGrid2D<? extends ICell2D> grid ) {


        ICell2D rand = grid.getRandomCell();

        while ( null != rand ) {


            List<ICell2D> unlinkedL = rand.getNeighbours().values().stream().filter( cell -> cell.getLinkedCells().isEmpty() ).collect( Collectors.toList() );

            // all neighbours are linked
            if ( unlinkedL.isEmpty() ) {
                // temporarily
                rand = null;

                // for all cells
                for ( ICell2D cell2D : grid.getCells() ) {

                    // get linked neighbours
                    List<ICell2D> nbL = cell2D.getNeighbours().values().stream().filter( c -> !c.getLinkedCells().isEmpty() ).collect( Collectors.toList() );

                    // we have a cell with no links but neighbour do
                    if ( cell2D.getLinkedCells().isEmpty() && !nbL.isEmpty() ) {

                        rand = cell2D;
                        ICell2D linkTo = Utils.randOf( nbL );
                        rand.link( linkTo, true );
                    }
                }


            } else {

                // get random unlinked cell and link
                ICell2D nbc = Utils.randOf( unlinkedL );
                rand.link( nbc, true );

                rand = nbc;
            }
        }
    }


}
