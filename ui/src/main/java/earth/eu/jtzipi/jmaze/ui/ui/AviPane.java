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

import earth.eu.jtzipi.jmaze.ui.PropertiesFX;
import earth.eu.jtzipi.modules.fx.shape.ShapeBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * A pane above MazePane.
 * Here we display highlights e.G. hovered cells or clicked.
 */
public class AviPane extends Pane {

    private Shape hover;    // Shape to indicate hover position
    private Shape clicked;  // Shape to indicate clicked position

    AviPane() {

        createAviPane();
    }

    private void createAviPane() {

        double segLen = PropertiesFX.SEGMENT_LEN_SMALL.doubleValue();
        double tileLen = PropertiesFX.FX_TILE_WIDTH_PROP.doubleValue();

        double ps1 = segLen + 2D;
        double ps2 = tileLen + 2D - segLen;
        double ps3 = tileLen + 4D;

        // hover path
        hover = ShapeBuilder.create().strokeWidth( 1.6D ).mx( 0D ).my( 0D )
                .lx( ps1 ).mx( ps2 ).lx( ps3 ) // north
                .ly( ps1 ).my( ps2 ).ly( ps3 ) // east
                .lx( ps2 ).mx( ps1 ).lx( 0D ) // south
                .ly( ps2 ).my( ps1 ).ly( 0D ) // west
                .build();

        hover.setStroke( Color.rgb( 254, 52, 52 ) );
        hover.layoutXProperty().bind( PropertiesFX.FX_TILE_HOVER_OFF_X_BIND.subtract( 2D ) );
        hover.layoutYProperty().bind( PropertiesFX.FX_TILE_HOVER_OFF_Y_BIND.subtract( 2D ) );

        clicked = ShapeBuilder.create().strokeWidth( 1.6D ).mx( 0D ).my( 0D )
                .lx( ps1 ).mx( ps2 ).lx( ps3 ) // north
                .ly( ps1 ).my( ps2 ).ly( ps3 ) // east
                .lx( ps2 ).mx( ps1 ).lx( 0D ) // south
                .ly( ps2 ).my( ps1 ).ly( 0D ) // west
                .build();

        clicked.setStroke( Color.RED );
        clicked.setVisible( false );

    }
}
