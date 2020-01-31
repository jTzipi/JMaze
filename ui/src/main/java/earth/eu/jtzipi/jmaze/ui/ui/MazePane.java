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
import javafx.beans.property.*;
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


    // private static final int ROW_DEF = 14;
    // private static final int COLUMN_DEF = 12;

    private final IntegerProperty fxRowProp = new SimpleIntegerProperty();
    private final IntegerProperty fxColumnProp = new SimpleIntegerProperty();
    private final ObjectProperty<Algos> fxPlantProp = new SimpleObjectProperty<>();
    private IGrid2D<? extends ICell2D> grid;    // grid


    MazePane() {

    }

    /**
     * Bind algo to this algo property.
     *
     * @param algoProp algo prop
     */
    void bindAlgo( ObjectProperty<Algos> algoProp ) {
        this.fxPlantProp.bind( algoProp );
    }

    /**
     * Bind spinner row value property to this row property .
     *
     * @param ip row property
     */
    void bindRowProperty( final ReadOnlyObjectProperty<Integer> ip ) {
        this.fxRowProp.bind( ip );
    }

    void bindColumnProperty( final ReadOnlyObjectProperty<Integer> ip ) {
        this.fxColumnProp.bind( ip );
    }

    /**
     * Create a new grid.
     */
    void updateMaze() {
        getChildren().clear();
        this.grid = GridQuad2D.of( fxRowProp.getValue(), fxColumnProp.getValue() );

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
    void plant() {


        if ( fxPlantProp.getValue() != null ) {
            fxPlantProp.getValue().plant( grid );
        }

        for ( Node qtile : getChildren() ) {

            if ( qtile instanceof QuadTile ) {
                QuadTile<?> qt = ( QuadTile<?> ) qtile;
                qt.update();

            }

        }

    }
}
