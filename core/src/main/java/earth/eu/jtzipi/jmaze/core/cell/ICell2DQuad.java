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

package earth.eu.jtzipi.jmaze.core.cell;

/**
 * A quadratic cell.
 */
public interface ICell2DQuad extends ICell2D {

    /**
     * Return neighbour cell to north.
     *
     * @return north neighbour
     */
    ICell2D getNorth();

    /**
     * Return neighbour cell to east.
     *
     * @return east neighbour
     */
    ICell2D getEast();

    /**
     * Return neighbour cell to west.
     *
     * @return east neighbour
     */
    ICell2D getWest();

    /**
     * Return neighbour cell to south east.
     *
     * @return east neighbour
     */
    ICell2D getSouth();

    /**
     * Return whether north neighbour is present.
     *
     * @return
     */
    default boolean isNeighbourNorth() {
        return getNorth() != Unknown.SINGLETON;
    }

    /**
     * Return whether neighbour east is present.
     *
     * @return {@code true} if neighbour east is present
     */
    default boolean isNeighbourEast() {
        return getEast() != Unknown.SINGLETON;
    }

    /**
     * Return whether  neighbour is present.
     *
     * @return {@code true} if neighbour west is present
     */
    default boolean isNeighbourWest() {
        return getWest() != Unknown.SINGLETON;
    }

    /**
     * Return whether south neighbour is present.
     *
     * @return {@code true} if neighbour south is present
     */
    default boolean isNeighbourSouth() {
        return getSouth() != Unknown.SINGLETON;
    }

}
