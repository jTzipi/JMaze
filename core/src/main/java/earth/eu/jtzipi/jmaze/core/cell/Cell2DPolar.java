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

import java.util.List;

/**
 * Implementation of polar cell on two dimensional grid.
 *
 * @author jTzipi
 */
public class Cell2DPolar extends AbstractCell implements ICell2DPolar {

    ICell2DPolar inward;
    ICell2DPolar cw;
    ICell2DPolar ccw;
    List<ICell2DPolar> outwardL;

    /**
     * Polar.
     *
     * @param row
     * @param column
     */
    Cell2DPolar( int row, int column ) {
        super( row, column );
    }

    /**
     * Return polar cell for row and column.
     *
     * @param row row
     * @param col column
     * @return polar cell
     * @throws IllegalArgumentException if {@code row}|{@code col} &lt; 0
     */
    public static Cell2DPolar of( int row, int col ) {
        if ( 0 > row || 0 > col ) {
            throw new IllegalArgumentException( new IndexOutOfBoundsException( "row '" + row + "' || column '" + col + "' are < 0" ) );
        }

        return new Cell2DPolar( row, col );
    }

    @Override
    public ICell2DPolar getInward() {
        return inward;
    }

    @Override
    public ICell2DPolar getClockwise() {
        return cw;
    }

    @Override
    public ICell2DPolar getCounterClockwise() {
        return ccw;
    }

    @Override
    public List<ICell2DPolar> getOutwardCells() {
        return outwardL;
    }

    public void setCounterClockwise( final ICell2DPolar ccwCell ) {
        this.ccw = ccwCell;
    }

    public void setClockwise( final ICell2DPolar cwCell ) {
        this.cw = cwCell;
    }

    public void setInward( final ICell2DPolar inwardCell ) {
        this.inward = inwardCell;
    }

    public void putOutward( final ICell2DPolar owCell ) {
        this.getOutwardCells().add( owCell );
    }

    public void setOutwardList( List<ICell2DPolar> outwardL ) {
        this.outwardL = outwardL;
    }

    @Override
    public int hashCode() {
        int result = 53;
        result = 31 * result + Integer.hashCode( getRow() );
        result = 31 * result + Integer.hashCode( getCol() );
        result = 31 * result + Integer.hashCode( getClockwise().getCol() );
        result = 31 * result + Integer.hashCode( getCounterClockwise().getCol() );

        return result;
    }

    @Override
    public boolean equals( Object object ) {
        if ( this == object ) {
            return true;
        }
        if ( !( object instanceof ICell2DPolar ) ) {
            return false;
        }

        ICell2DPolar ocq = ( ICell2DPolar ) object;

        return this.getRow() == ocq.getRow() && this.getCol() == ocq.getCol();
    }

    @Override
    public String toString() {
        return "Cell2DPolar{" +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
