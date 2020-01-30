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

import java.util.ArrayList;
import java.util.List;


/**
 * Sidewinder Algorithm for 2D grid with Four Neighbour.
 * <p>
 * <h3>Description</h3><br/>
 * <br/>
 *
 * @author jTzipi
 */
public class Sidewinder implements IPlantable<IGrid2D<? extends ICell2DQuad>> {


    @Override
    public void plant( IGrid2D<? extends ICell2DQuad> grid ) {

        //
        for ( int r = 0; r < grid.getRows(); r++ ) {
            ICell2DQuad[] row = grid.getCellsForRow( r );
            List<ICell2DQuad> cL = new ArrayList<>();

            //
            for ( ICell2DQuad cell : row ) {
                // get east and north neighbour and check close condition
                boolean eastEnd = cell.getEast() == ICell2D.Unknown.SINGLETON;
                boolean northEnd = cell.getNorth() == ICell2D.Unknown.SINGLETON;

                if ( eastEnd || ( !northEnd && Utils.rand( 2 ) == 0 ) ) {
                    ICell2DQuad toLink = cL.get( Utils.rand( cL.size() ) );
                    ICell2D north = toLink.getNorth();
                    if ( north != ICell2D.Unknown.SINGLETON ) {
                        toLink.link( north, true );
                    }
                    cL.clear();
                } else {

                    // we are here not @east end
                    // so we can safe link to east
                    cell.link( cell.getEast(), true );
                }

            }
        }
    }
}
