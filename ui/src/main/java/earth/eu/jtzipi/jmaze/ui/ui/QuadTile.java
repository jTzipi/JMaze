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
import earth.eu.jtzipi.jmaze.ui.ColorUtils;
import earth.eu.jtzipi.jmaze.ui.PropertiesFX;
import earth.eu.jtzipi.modules.fx.shape.ShapeBuilder;
import javafx.animation.FillTransition;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * Tile for 2D quadratic maze.
 */
public class QuadTile<C extends ICell2DQuad> extends Region {

    private final C cell;

    private Color mouseOverColor = Color.rgb( 122, 122, 254 );
    private Color bgColor = Color.rgb( 0, 0, 0, 0 );

    private Background bgMouseover = new Background( new BackgroundFill( mouseOverColor, CornerRadii.EMPTY, Insets.EMPTY ) );


    private FillTransition mouseOverEaseT;


    QuadTile( C cell ) {
        this.cell = cell;
        create();
        init();
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

    void init() {

        setOnMouseEntered( ev -> onMouseEntered() );
        setOnMouseExited( ev -> onMouseExited() );
    }

    void update() {
        putWalls();

    }

    private void putWalls() {

        getChildren().clear();
        ICell2D north = cell.getNorth();
        ICell2D west = cell.getWest();
        ICell2D east = cell.getEast();
        ICell2D south = cell.getSouth();
        List<Shape> shapeList = new ArrayList<>();

        double len = PropertiesFX.FX_TILE_WIDTH_PROP.doubleValue();

        if ( !cell.isLinked( north ) ) {
            Shape wallNorth = ShapeBuilder.create().lx( len ).build();
            shapeList.add( wallNorth );
        }

        if ( !cell.isLinked( east ) ) {
            Shape eastWall = ShapeBuilder.create().mx( len ).ly( len ).build();
            shapeList.add( eastWall );
        }

        if ( !cell.isLinked( west ) ) {
            Shape westWall = ShapeBuilder.create().ly( len ).build();
            shapeList.add( westWall );
        }

        if ( !cell.isLinked( south ) ) {
            Shape southWall = ShapeBuilder.create().my( len ).lx( len ).build();
            shapeList.add( southWall );
        }

        Text t = new Text( "[" + cell.getRow() + " " + cell.getCol() + "]" );


        t.setFill( mouseOverColor );
        t.setLayoutX( 10D );
        t.setLayoutY( len - 10D );

        shapeList.add( t );
        getChildren().setAll( shapeList );
    }

    private void onMouseEntered() {

        // update mouse x and y grid position
        PropertiesFX.FX_MOUSE_X_PROP.setValue( cell.getCol() );
        PropertiesFX.FX_MOUSE_Y_PROP.setValue( cell.getRow() );

        Color bg = ColorUtils.computeBlue( 0, cell.getCol() * cell.getRow(), 12 * 12 );
        setBackground( new Background( new BackgroundFill( bg, CornerRadii.EMPTY, Insets.EMPTY ) ) );
    }

    private void onMouseExited() {


        setBackground( null );
    }
}
