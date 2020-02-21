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

package earth.eu.jtzipi.jmaze.ui.ui;


import earth.eu.jtzipi.jmaze.core.algo.Algos;
import earth.eu.jtzipi.jmaze.core.cell.ICell2D;
import earth.eu.jtzipi.jmaze.core.cell.ICell2DQuad;
import earth.eu.jtzipi.jmaze.core.grid.GridQuad2D;
import earth.eu.jtzipi.jmaze.core.grid.IGrid2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Maze Panel.
 * <p>
 * All kind of two dimensional mazes can be drawn here.
 * Since each cell of a grid knows it's position we only need to
 * add each cell of our grid to a wrapper class like {@code QuadTile}.
 *
 *
 * </p>
 */
public class MazePane extends Pane {



    private IGrid2D<? extends ICell2D> grid;    // grid



    /**
     * Create a new grid.
     */
    void createMazeTiles( int rows, int cols ) {
        getChildren().clear();
        this.grid = GridQuad2D.of( rows, cols );

        List<? extends ICell2D> cL = grid.getCells();

        for ( ICell2D cell : cL ) {

            if ( cell instanceof ICell2DQuad ) {
                ICell2DQuad cell2D = ( ICell2DQuad ) cell;
                getChildren().add( new QuadTile<>( cell2D ) );
            }

        }
    }

    /**
     * Plant a maze.
     */
    void plant( Algos algo ) {

        algo.plant( grid );

        for ( Node qtile : getChildren() ) {

            if ( qtile instanceof QuadTile ) {
                QuadTile<?> qt = ( QuadTile<?> ) qtile;
                qt.update();

            }

        }

    }
}
