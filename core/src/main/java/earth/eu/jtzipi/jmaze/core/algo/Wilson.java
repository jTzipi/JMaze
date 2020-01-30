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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of Wilson's Algorithm for 2D grid.
 *
 * @author jTzipi
 */
public final class Wilson implements IPlantable<IGrid2D<? extends ICell2D>> {

    private static final Logger LOGGER = LoggerFactory.getLogger( Wilson.class );

    public Wilson() {

    }

    @Override
    public void plant( IGrid2D<? extends ICell2D> grid ) {

        List<? extends ICell2D> unvisited = grid.getCells();
        ICell2D first = Utils.randOf( unvisited );
        // LOGGER.info( "first Cell '" + first + "'" );
        unvisited.remove( first );

        // until all cells are visited
        while ( !unvisited.isEmpty() ) {
            List<ICell2D> pathL = new ArrayList<>();
            ICell2D sample = Utils.randOf( unvisited );
            pathL.add( sample );

            // until we find a visited cell
            while ( unvisited.contains( sample ) ) {


                // LOGGER.info( "ICell  " + sample + " NB " + sample.getNeighbours() );
                Map<IMove2D, ICell2D> nbm = sample.getNeighbours();
                List<ICell2D> nbl = nbm.values().stream().filter( c -> c != ICell2D.Unknown.SINGLETON ).collect( Collectors.toList() );
                sample = Utils.randOf( nbl );

                int position = pathL.indexOf( sample );

                // cell is present
                if ( position >= 0 ) {

                    // remove loop
                    pathL = pathL.subList( 0, position + 1 );

                } else {
                    pathL.add( sample );

                }

                LOGGER.warn( "Gadi '" + sample );
            }

            // now link all path element to each other
            for ( int g = 0; g < pathL.size() - 1; g++ ) {

                ICell2D cell2D = pathL.get( g );
                cell2D.link( pathL.get( g + 1 ), true );

            }
            // remove path from unvisited
            unvisited.removeAll( pathL );
            LOGGER.warn( "bac " + unvisited.size() );
        }
    }
}
