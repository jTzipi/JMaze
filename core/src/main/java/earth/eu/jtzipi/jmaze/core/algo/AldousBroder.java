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

import earth.eu.jtzipi.jmaze.core.IMove2D;
import earth.eu.jtzipi.jmaze.core.Utils;
import earth.eu.jtzipi.jmaze.core.cell.ICell2D;
import earth.eu.jtzipi.jmaze.core.grid.IGrid2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of Aldous & Broder's algorithm for a 2D grid.
 *
 * @author jTzipi
 */
public final class AldousBroder implements IPlantable<IGrid2D<? extends ICell2D>> {


    private static final Logger LOG = LoggerFactory.getLogger( AldousBroder.class );

    public AldousBroder() {

    }

    @Override
    public String toString() {
        return "AldousBroder@Grid2D>Cell2D";
    }

    @Override
    public void plant( IGrid2D<? extends ICell2D> grid ) {

        ICell2D c2d = grid.getRandomCell();
        long unvisited = grid.getDim() - 1;

        while ( 0 < unvisited ) {

            Map<IMove2D, ICell2D> nbm = c2d.getNeighbours();
            List<ICell2D> nbl = nbm.values().stream().filter( c -> c != ICell2D.Unknown.SINGLETON ).collect( Collectors.toList() );

            if ( nbl.isEmpty() ) {

                LOG.error( "No non Unknown neighbours for '" + c2d );
                break;
            }

            ICell2D cell = Utils.randOf( nbl );
            if ( cell.getLinkedCells().isEmpty() ) {
                c2d.link( cell, true );
                unvisited--;
            }

            c2d = cell;
        }

    }
}
