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

package earth.eu.jtzipi.jmaze.core;



/**
 * Implementation of a planar move.
 * <p>
 * A planar move on a 2D surface can have either
 * 4 or 8 neighbour position.
 * </p>
 */
public enum Dir2D implements IMove2D {

    /**
     *
     */
    N( MovePlane.of( -1, 0 ) ),
    NW( MovePlane.of( -1, -1 ) ),
    NE( MovePlane.of( -1, 1 ) ),
    E( MovePlane.of( 0, 1 ) ),
    /**
     * West.
     */
    W( MovePlane.of( 0, -1 ) ),
    S( MovePlane.of( 1, 0 ) ),
    SW( MovePlane.of( 1, -1 ) ),
    SE( MovePlane.of( 1, 1 ) ),

    C( MovePlane.of( 0, 0 ) );

    IMove2D plane;

    Dir2D( IMove2D move ) {
        this.plane = move;
    }

    @Override
    public int xPos() {
        return plane.xPos();
    }

    @Override
    public int yPos() {
        return plane.yPos();
    }
}
