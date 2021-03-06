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
 * A cell with hexagonal form for 2D grid.
 */
public interface ICell2DHex extends ICell2D {

    ICell2D getNorthEast();

    ICell2D getNorthWest();

    ICell2D getSouthEast();

    ICell2D getSouthWest();

    ICell2D getNorth();

    ICell2D getSouth();
}





