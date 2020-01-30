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

import earth.eu.jtzipi.jmaze.core.cell.ICell2D;

import java.util.List;

/**
 * Animate steps of building a maze.
 */
public interface IAnimatable {

    /**
     * Link two cells.
     *
     * @param cell
     * @param other
     */
    void link( ICell2D cell, ICell2D other );

    /**
     * @param cellList
     * @param randCell
     */
    void randomOf( List<ICell2D> cellList, ICell2D randCell );

    /**
     * Update list of cells currently linked.
     *
     * @param linkedList linked cells
     */
    void updateLinked( List<ICell2D> linkedList );

    /**
     * Update list of cells temporary added to later link.
     *
     * @param tempList temp cells
     */
    void updateTemp( List<ICell2D> tempList );

    /**
     * Update current cell.
     *
     * @param cell cell
     */
    void update( ICell2D cell );

    /**
     * Display a info .
     *
     * @param msgStr info
     */
    void info( String msgStr );
}
