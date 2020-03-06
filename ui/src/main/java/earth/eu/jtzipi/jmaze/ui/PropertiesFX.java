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

package earth.eu.jtzipi.jmaze.ui;

import earth.eu.jtzipi.jmaze.ui.segment.ISegment;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.When;
import javafx.beans.property.*;

/**
 * Global Shared Properties.
 */
public final class PropertiesFX {

    public static final double WINDOW_PREF_WIDTH = 1000D;
    public static final double WINDOW_PREF_HEIGHT = 1000D;


    /**
     * Min tile length.
     */
    public static final double MIN_LEN_TILE = 10D;
    /**
     * Pref tile length.
     */
    public static final double PREF_LEN_TILE = 50D;
    /**
     * Max tile length.
     */
    public static final double MAX_LEN_TILE = 100D;

    public static final double GAP_X = 10D;
    public static final double GAP_Y = 10D;
    /**
     * That is the length of a tile.
     * Main Property every other is derived from.
     */
    public static final DoubleProperty FX_TILE_WIDTH_PROP = new SimpleDoubleProperty( PREF_LEN_TILE );
    /**
     * Window width.
     */
    public static final DoubleProperty FX_WINDOW_WIDTH_PROP = new SimpleDoubleProperty( WINDOW_PREF_WIDTH );

    /**
     * Window height.
     */
    public static final DoubleProperty FX_WINDOW_HEIGHT_PROP = new SimpleDoubleProperty( WINDOW_PREF_HEIGHT );

    /**
     *
     */
    public static final DoubleProperty FX_EDGE_WIDTH_PROP = new SimpleDoubleProperty( PREF_LEN_TILE );
    /**
     * Mouse map grid x position.
     * This is the x or column position mouse is currently hovering over or -1 if not hovered.
     */
    public static final IntegerProperty FX_MOUSE_X_PROP = new SimpleIntegerProperty( -1 );
    /**
     * Mouse map grid y position.
     * This is the y or row position mouse is hovering over or -1 if.
     */
    public static final IntegerProperty FX_MOUSE_Y_PROP = new SimpleIntegerProperty( -1 );
    /**
     * Show edge prop.
     */
    public static final BooleanProperty FX_SHOW_MAP_EDGE_PROP = new SimpleBooleanProperty( true );

    /**
     * A small segment's .
     */
    public static NumberBinding SEGMENT_LEN_SMALL = FX_TILE_WIDTH_PROP.multiply( ISegment.LEN_SMALL );


    /**
     * North Edge length.
     */
    public static DoubleProperty FX_GAP_EDGE_NORTH_PROP = new SimpleDoubleProperty( GAP_Y );
    /**
     * West edge length.
     */
    public static DoubleProperty FX_GAP_EDGE_WEST_PROP = new SimpleDoubleProperty( GAP_X );
    /**
     * Offset left and top of map pane to show legend.
     */
    public static DoubleProperty FX_TILE_OFFSET_PROP;
    /**
     * X offset of tile mouse is over.
     * That is location to draw the left wall of tile on position x with all gaps included.
     * That value is always >= 0.
     */
    public static NumberBinding FX_TILE_HOVER_OFF_X_BIND;
    /**
     * Y offset of tile mouse is over.
     * That is location to draw the north wall of tile on position y with all gaps.
     * That value is always >= 0.
     */
    public static NumberBinding FX_TILE_HOVER_OFF_Y_BIND;

    static {

        DoubleBinding tileXOffset = FX_TILE_WIDTH_PROP.multiply( FX_MOUSE_X_PROP );
        DoubleBinding tileYOffset = FX_TILE_WIDTH_PROP.multiply( FX_MOUSE_Y_PROP );


        FX_TILE_HOVER_OFF_X_BIND = new When( FX_SHOW_MAP_EDGE_PROP )
                .then( tileXOffset.add( FX_GAP_EDGE_WEST_PROP ).add( FX_EDGE_WIDTH_PROP ) )
                .otherwise( tileXOffset.add( FX_GAP_EDGE_WEST_PROP ) );

        FX_TILE_HOVER_OFF_Y_BIND = new When( FX_SHOW_MAP_EDGE_PROP )
                .then( tileYOffset.add( FX_GAP_EDGE_NORTH_PROP ).add( FX_EDGE_WIDTH_PROP ) )
                .otherwise( tileYOffset.add( FX_GAP_EDGE_NORTH_PROP ) );


    }

    private PropertiesFX() {

    }

}


// }
