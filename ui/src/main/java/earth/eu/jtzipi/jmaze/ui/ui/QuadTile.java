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

import earth.eu.jtzipi.jmaze.core.cell.ICell2D;
import earth.eu.jtzipi.jmaze.core.cell.ICell2DQuad;
import earth.eu.jtzipi.jmaze.ui.PropertiesFX;
import earth.eu.jtzipi.modules.fx.shape.ShapeBuilder;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;


/**
 * Tile for 2D quadratic maze.
 */
public class QuadTile<C extends ICell2DQuad> extends Region {

    private final C cell;

    private Background bg;

    QuadTile( C cell ) {
        this.cell = cell;
        create();
        putWalls();
    }

    void create() {
        //
        DoubleProperty wp = PropertiesFX.FX_TILE_WIDTH_PROP;

        prefWidthProperty().bind( wp );
        prefHeightProperty().bind( wp );

        NumberBinding posXBind = wp.multiply( cell.getCol() );
        NumberBinding posYBind = wp.multiply( cell.getRow() );

        layoutXProperty().bind( posXBind.add( PropertiesFX.FX_GAP_EDGE_WEST_PROP ) );
        layoutYProperty().bind( posYBind.add( PropertiesFX.FX_GAP_EDGE_NORTH_PROP ) );
    }

    protected void update() {
        putWalls();

    }

    private void putWalls() {

        getChildren().clear();
        ICell2D north = cell.getNorth();
        ICell2D west = cell.getWest();
        ICell2D east = cell.getEast();
        ICell2D south = cell.getSouth();
        List<Shape> wall = new ArrayList<>();

        double len = PropertiesFX.FX_TILE_WIDTH_PROP.doubleValue();

        if ( !cell.isLinked( north ) ) {
            Shape wallNorth = ShapeBuilder.create().lx( len ).build();
            wall.add( wallNorth );
        }

        if ( !cell.isLinked( east ) ) {
            Shape eastWall = ShapeBuilder.create().mx( len ).ly( len ).build();
            wall.add( eastWall );
        }

        if ( !cell.isLinked( west ) ) {
            Shape westWall = ShapeBuilder.create().ly( len ).build();
            wall.add( westWall );
        }

        if ( !cell.isLinked( south ) ) {
            Shape southWall = ShapeBuilder.create().my( len ).lx( len ).build();
            wall.add( southWall );
        }

        getChildren().setAll( wall );
    }
}
