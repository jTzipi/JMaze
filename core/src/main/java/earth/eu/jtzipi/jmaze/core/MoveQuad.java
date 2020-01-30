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
 * Moves on a 2D quadratic grid.
 */
public enum MoveQuad implements IMove2D {

    /**
     * Move north.
     */
    N( MovePlane.of( -1, 0 ) ),
    /**
     * Move east.
     */
    E( MovePlane.of( 0, 1 ) ),
    /**
     * Move west.
     */
    W( MovePlane.of( 0, -1 ) ),
    /**
     * Move south.
     */
    S( MovePlane.of( 1, 0 ) ),
    ;

    private IMove2D mp;

    /**
     * Move.
     *
     * @param move
     */
    MoveQuad( IMove2D move ) {
        this.mp = move;
    }


    @Override
    public int xPos() {
        return mp.xPos();
    }

    @Override
    public int yPos() {
        return mp.yPos();
    }
}
