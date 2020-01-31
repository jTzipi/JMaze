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
import earth.eu.jtzipi.jmaze.core.grid.IGrid2D;

import java.util.Objects;

/**
 * Algorithms to create mazes.
 */
public enum Algos implements IPlantable<IGrid2D<? extends ICell2D>> {

    ALDOUS_BRODER( new AldousBroder(), "Aldouse Broder" ),
    BINARY_TREE( new BinaryTree(), "Binary Tree" );


    private final IPlantable<IGrid2D<? extends ICell2D>> pl;
    private final String name;

    Algos( IPlantable<IGrid2D<? extends ICell2D>> plantable, String nameStr ) {
        this.pl = plantable;
        this.name = nameStr;
    }

    @Override
    public void plant( IGrid2D<? extends ICell2D> grid ) {
        Objects.requireNonNull( grid );
        pl.plant( grid );
    }


    public String getAlgoName() {
        return name;
    }
}
