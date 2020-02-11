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

package earth.eu.jtzipi.jmaze.core.cell;

import java.util.Collections;
import java.util.List;

/**
 * A Polar Cell for 2D polar grid.
 * <p>
 * Polar cells have always one inner neighbour but may have more then one outward neighbour.
 * They have clockwise and counter clockwise neighbours.
 * Each cell is placed on a concentric circle.
 * </p>
 *
 * @author jTzipi
 */
public interface ICell2DPolar extends ICell {

    /**
     * Return single inward neighbour cell.
     *
     * @return
     */
    ICell2DPolar getInward();

    /**
     * Return neighbour clockwise.
     *
     * @return
     */
    ICell2DPolar getClockwise();

    /**
     * Return neighbour counter clockwise.
     *
     * @return
     */
    ICell2DPolar getCounterClockwise();

    /**
     * Return list of outward neighbours.
     *
     * @return
     */
    List<ICell2DPolar> getOutwardCells();

    enum Unknown implements ICell2DPolar {

        SINGLETON;


        @Override
        public ICell2DPolar getInward() {
            return this;
        }

        @Override
        public ICell2DPolar getClockwise() {
            return this;
        }

        @Override
        public ICell2DPolar getCounterClockwise() {
            return this;
        }

        @Override
        public List<ICell2DPolar> getOutwardCells() {
            return Collections.emptyList();
        }

        @Override
        public String toString() {
            return "Unknown Cell Polar";
        }

        @Override
        public int getRow() {
            return -1;
        }

        @Override
        public int getCol() {
            return -1;
        }

        @Override
        public List<ICell> getLinkedCells() {
            return Collections.emptyList();
        }

        @Override
        public void link( ICell cell, boolean bidiProp ) {

            throw new UnsupportedOperationException( "Unknown cell!" );
        }

        @Override
        public void unlink( ICell cell, boolean bidiProp ) {
            throw new UnsupportedOperationException( "Unknown cell!" );
        }
    }

}
