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

import earth.eu.jtzipi.jmaze.core.IMove2D;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Basic 2D'cell' API.
 * <p>
 * A cell is a location on a grid.
 * A cell knows its location stored as rows and columns.
 * A cell knows each other cell who is linked that is got a passage from this cell to other cell.
 * </p>
 */
public interface ICell2D extends ICell {


    /**
     * Return all neighbour cells of this cell.
     * <u>Important</u>: this return visitable cells.
     * That is cells of type not equal to {@linkplain Unknown#SINGLETON}.
     *
     * @return neighbour cells that can be visited
     */
    Map<IMove2D, ICell2D> getNeighbours();

    /**
     * Unknown planar cell.
     */
    enum Unknown implements ICell2D {

        SINGLETON;

        @Override
        public boolean isLinked( ICell other ) {
            return false;
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

        @Override
        public int getRow() {
            return -1;
        }

        @Override
        public int getCol() {
            return -1;
        }

        @Override
        public Map<IMove2D, ICell2D> getNeighbours() {
            return Collections.emptyMap();
        }

        @Override
        public String toString() {
            return "Unknown Cell2D";
        }
    }
}
