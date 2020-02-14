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
import java.util.List;
import java.util.stream.Collectors;

public class GrowingTree implements IPlantable<IGrid2D<? extends ICell2D>> {

    GrowingTree() {

    }

    @Override
    public void plant( IGrid2D<? extends ICell2D> grid ) {

        ICell2D rand = grid.getRandomCell();

        List<ICell2D> activL = new ArrayList<>();
        activL.add( rand );

        while ( !activL.isEmpty() ) {


            // TODO: use cell chooser
            ICell2D cell2D = Utils.randOf( activL );


            List<ICell2D> nbL = cell2D.getNeighbours().values().stream().filter( c -> c.getLinkedCells().isEmpty() ).collect( Collectors.toList() );

            // there are unlinked cells
            // get random and link
            if ( !nbL.isEmpty() ) {
                ICell2D link = Utils.randOf( nbL );
                cell2D.link( link, true );
                activL.add( link );

            } else {
                activL.remove( cell2D );
            }
        }

    }


}
