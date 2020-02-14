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

public interface IMove {

    enum Mode {

        /**
         * Move denied for this cell.
         * e.G. you can not move south east on a quadratic cell.
         */
        DENIED,
        /**
         * Move permitted. That is we have a passage from this node to node in move direction.
         */
        PERMITTED,

        /**
         * Move this way is not allowed due to unknown cell or grid structure.
         * e.G. you can not move north on a triangle cell if this cell is upward.
         */
        UNAPPROACHABLE;

    }

}
