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

import earth.eu.jtzipi.jmaze.core.grid.IGrid2D;
import earth.eu.jtzipi.modules.fx.canvas.AbstractAnimatedWidget;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MazePanel extends AbstractAnimatedWidget {


    ObjectProperty<IGrid2D> fxGrid2DProp = new SimpleObjectProperty<>( this, "FX_GRID_2D_PROP", null );

    @Override
    protected void start() {

    }

    @Override
    protected void pause() {

    }

    @Override
    protected void stop() {

    }

    @Override
    protected void resume() {

    }
}
