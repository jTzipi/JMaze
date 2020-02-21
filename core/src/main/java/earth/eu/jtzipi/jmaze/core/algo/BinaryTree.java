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
 * Binary Tree algorithm on 2D Grid.
 *
 * @author jTzipi
 */
public class BinaryTree implements IPlantable<IGrid2D<? extends ICell2D>> {


    public BinaryTree() {

    }


    @Override
    public void plant( IGrid2D<? extends ICell2D> grid2D ) {

        for ( ICell2D quad : grid2D.getCells() ) {
            bash( ( ICell2DQuad ) quad );
        }


    }

    private void bash( ICell2DQuad cell ) {

        ICell2D en = cell.getEast();
        ICell2D nn = cell.getNorth();

        List<ICell2D> nbL = new ArrayList<>();
        if ( !en.equals( ICell2D.Unknown.SINGLETON ) ) {
            nbL.add( en );

        }
        if ( !nn.equals( ICell2D.Unknown.SINGLETON ) ) {
            nbL.add( nn );
        }
        int size = nbL.size();
        ICell2D toLink;

        switch ( size ) {
            case 1:
                toLink = nbL.get( 0 );
                break;
            case 2:
                toLink = nbL.get( Utils.rand( size ) );
                break;
            default:
                toLink = null;
        }

        if ( null != toLink ) {
            cell.link( toLink, true );
        }

    }
}
