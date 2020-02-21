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
import earth.eu.jtzipi.jmaze.ui.PropertiesFX;
import javafx.beans.binding.NumberBinding;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public final class MainPane extends BorderPane {


    private ComboBox<Algos> algoCBox;


    private Button createB;
    private Button plantB;
    private Spinner<Integer> rowSpin;
    private Spinner<Integer> colSpin;




    private MainPane() {

        createMainPane();
    }


    public static MainPane of() {

        return new MainPane();
    }

    private void createMainPane() {
        MazePane mp = new MazePane();
        AviPane aviPane = new AviPane();
        algoCBox = new ComboBox<>();
        algoCBox.setCellFactory( cb -> new AlgoListCell() );

        algoCBox.getItems().addAll( Algos.values() );
        algoCBox.getSelectionModel().select( 3 );


        plantB = new Button( "plant" );
        createB = new Button( "create" );

        rowSpin = new Spinner<>( 1, 1000, 12, 1 );
        rowSpin.setPrefWidth( 70D );

        colSpin = new Spinner<>( 1, 1000, 12, 1 );
        colSpin.setPrefWidth( 70D );

        Label algoLab = new Label( "Algorithm" );
        Label rowLab = new Label( "R" );
        Label columnLab = new Label( "C" );

        ToolBar ntbar = new ToolBar( algoLab, algoCBox, new Separator(), rowLab, rowSpin, columnLab, colSpin, createB, plantB );

        NumberBinding spPrefWidthBind = PropertiesFX.FX_WINDOW_WIDTH_PROP.subtract( 150D );
        NumberBinding spPrefHeightBind = PropertiesFX.FX_WINDOW_HEIGHT_PROP.subtract( 170D );

        ScrollPane sp = new ScrollPane( mp );
        sp.setPannable( true );
        sp.prefWidthProperty().bind( spPrefWidthBind );
        sp.prefHeightProperty().bind( spPrefHeightBind );


        setCenter( sp );
        setTop( ntbar );

        createB.setOnAction( ae -> mp.createMazeTiles( rowSpin.getValue(), colSpin.getValue() ) );
        plantB.setOnAction( ae -> mp.plant( algoCBox.getValue() ) );
    }
}
