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


public interface ICell2DTri extends ICell2D {

    /**
     * Whether this cell is upside triangle.
     *
     * @return {@code true} if this is upside
     */
    boolean isUpside();

    /**
     * Neighbour cell located east.
     *
     * @return east neighbour
     */
    ICell2D getEast();

    /**
     * Neighbour cell located north if not {@linkplain #isUpside()} .
     *
     * @return north neighbour if not upside
     */
    ICell2D getNorth();

    /**
     * Neighbour cell located west.
     *
     * @return west neighbour
     */
    ICell2D getWest();

    /**
     * Neighbour cell located south if {@linkplain #isUpside()}.
     *
     * @return south neighbour if upside
     */
    ICell2D getSouth();
}
