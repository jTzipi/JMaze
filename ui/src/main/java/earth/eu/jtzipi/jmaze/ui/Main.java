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

import earth.eu.jtzipi.jmaze.ui.ui.MainPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start( Stage stage ) throws Exception {

        // IGrid2D<? extends ICell2DQuad> grid = GridQuad2D.of( 12, 10 );
        // LoggerFactory.getLogger( Main.class ).info( "Grid " + grid.getCells() );
        final Scene scene = new Scene( MainPane.of(), PropertiesFX.WINDOW_PREF_WIDTH, PropertiesFX.WINDOW_PREF_HEIGHT );

        stage.setTitle( "Java Maze Batch Tool" );
        stage.setScene( scene );

        stage.show();


    }
}
