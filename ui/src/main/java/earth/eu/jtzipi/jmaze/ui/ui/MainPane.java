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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public final class MainPane extends BorderPane {


    private ComboBox<Algos> algoCBox;


    private Button createB;
    private Button plantB;
    private Spinner<Integer> rowSpin;
    private Spinner<Integer> colSpin;

    private MazePane mp;


    private MainPane() {

        createMainPane();
    }


    public static MainPane of() {

        return new MainPane();
    }

    private void createMainPane() {
        mp = new MazePane();

        algoCBox = new ComboBox<>();
        algoCBox.setCellFactory( cb -> new AlgoListCell() );

        algoCBox.getItems().addAll( Algos.values() );


        plantB = new Button( "plant" );
        createB = new Button( "create" );

        rowSpin = new Spinner<>( 1, 1000, 12, 1 );
        rowSpin.setPrefWidth( 70D );

        colSpin = new Spinner<>( 1, 1000, 12, 1 );
        colSpin.setPrefWidth( 70D );

        mp.bindAlgo( algoCBox.valueProperty() );
        mp.bindRowProperty( rowSpin.valueProperty() );
        mp.bindColumnProperty( colSpin.valueProperty() );

        Label rowLab = new Label( "R" );
        Label columnLab = new Label( "C" );

        ToolBar ntbar = new ToolBar( algoCBox, new Separator(), rowLab, rowSpin, columnLab, colSpin, createB, plantB );


        setCenter( mp );
        setTop( ntbar );

        createB.setOnAction( ae -> mp.updateMaze() );
        plantB.setOnAction( ae -> mp.plant() );
    }
}
