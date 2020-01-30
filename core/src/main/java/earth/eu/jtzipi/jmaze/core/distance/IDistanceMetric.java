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
package earth.eu.jtzipi.jmaze.core.distance;

import earth.eu.jtzipi.jmaze.core.cell.ICell;

import java.util.Map;

/**
 * Defines metrics to measure distance between two points.
 *
 * @author Langhammer, Tim | Earth >> Europe >> Potsdam |
 * <a href="mailto:tlhammer@mailbox.org" alt="email">mail</a> | "Tolerance
 * first"
 */
public interface IDistanceMetric<C extends ICell> {

    /**
     * Starting from {@code cell} calculate the distances to all other linked
     * cells.
     *
     * @param cell cell to get distance
     * @return distance from root to {@code cell}
     */
    Map<C, Long> calculate( C cell );

}
