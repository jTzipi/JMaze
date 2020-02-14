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


import earth.eu.jtzipi.jmaze.core.Dir2D;
import earth.eu.jtzipi.jmaze.core.IMove2D;

import java.util.*;

/**
 * Quadratic Cell of a cartesian coordinate system.
 * <p>
 * Important:
 * <br>
 * We initialize all neighbours to {@link ICell2D.Unknown} to not add them later during grid generation.
 * We initialize all weights to {@link IWeight#FREE}
 * </p>
 */
public class Cell2DQuad extends AbstractCell implements ICell2DQuad {
    public static final Comparator<ICell2D> COMP = Comparator.comparing( ICell2D::getRow ).thenComparing( ICell2D::getCol );


    //@Override
    // private static final Logger LOGGER = LoggerFactory.getLogger( Cell2DQuad.class );
    /**
     * Map of neighbours of this cell.
     */
    private EnumMap<Dir2D, ICell2D> neighbourMap;   // Neighbours of this cell
    public static final Set<? extends IMove2D> MOVE_SET = EnumSet.of( Dir2D.N, Dir2D.E, Dir2D.W );
    private EnumMap<Dir2D, Double> weightMap;       // weight map

    /**
     * Quadratic cell.
     * Each
     *
     * @param row index row
     * @param col index column
     */
    Cell2DQuad( final int row, final int col ) {
        super( row, col );
        this.neighbourMap = new EnumMap<>( Dir2D.class );
        this.setNeighbourNorth( Unknown.SINGLETON );
        this.setNeighbourEast( Unknown.SINGLETON );
        this.setNeighbourWest( Unknown.SINGLETON );
        this.setNeighbourSouth( Unknown.SINGLETON );
        this.weightMap = new EnumMap<>( Dir2D.class );
        this.setWeightEast( IWeight.FREE );
        this.setWeightNorth( IWeight.FREE );
        this.setWeightWest( IWeight.FREE );
        this.setWeightSouth( IWeight.FREE );
    }

    /**
     * Create a new quadratic cell.
     *
     * @param row    row &ge; {@linkplain ICell2D#ROW_MIN}
     * @param column column &ge; {@linkplain ICell2D#COLUMN_MIN}
     * @return quad cell
     * @throws IllegalArgumentException if {@code row}|{@code column} are &lt;
     */
    public static Cell2DQuad of( int row, int column ) {

        if ( ROW_MIN > row ) {

            throw new IllegalArgumentException( new IndexOutOfBoundsException( "row[" + row + "] <= ROW_MIN" ) );
        }
        if ( COLUMN_MIN > column ) {
            throw new IllegalArgumentException( new IndexOutOfBoundsException( "column[" + column + "] <= COLUMN_MIN" ) );
        }

        return new Cell2DQuad( row, column );
    }


    @Override
    public ICell2D getNorth() {
        return neighbourMap.getOrDefault( Dir2D.N, Unknown.SINGLETON );
    }

    @Override
    public ICell2D getEast() {
        return neighbourMap.getOrDefault( Dir2D.E, Unknown.SINGLETON ); // return null;
    }

    @Override
    public ICell2D getWest() {
        return neighbourMap.getOrDefault( Dir2D.W, Unknown.SINGLETON ); // ;
    }

    @Override
    public ICell2D getSouth() {
        return neighbourMap.getOrDefault( Dir2D.S, Unknown.SINGLETON );
    }

    /**
     * Set neighbour north.
     *
     * @param neighbour neighbour
     */
    public void setNeighbourNorth( ICell2D neighbour ) {

        neighbourMap.put( Dir2D.N, neighbour );
    }

    public void setNeighbourEast( ICell2D neighbour ) {

        neighbourMap.put( Dir2D.E, neighbour );
    }

    /**
     * Set neighbour west.
     *
     * @param neighbour neighbour
     */
    public void setNeighbourWest( ICell2D neighbour ) {

        neighbourMap.put( Dir2D.W, neighbour );
    }

    /**
     * Set neighbour south.
     *
     * @param neighbour neighbour
     */
    public void setNeighbourSouth( ICell2D neighbour ) {

        neighbourMap.put( Dir2D.S, neighbour );
    }

    public void setWeightNorth( double weight ) {

        weightMap.put( Dir2D.N, weight );
    }

    public void setWeightEast( double weight ) {

        weightMap.put( Dir2D.E, weight );
    }

    public void setWeightWest( double weight ) {

        weightMap.put( Dir2D.W, weight );
    }

    public void setWeightSouth( double weight ) {

        weightMap.put( Dir2D.S, weight );
    }


    @Override
    public <M extends IMove2D> double getWeightForMove( M move ) {
        if ( !MOVE_SET.contains( move ) ) {
            throw new IllegalArgumentException( "Move '" + move + "' is not supported" );
        }

        return weightMap.get( move );
    }

    @Override
    public Map<IMove2D, ICell2D> getNeighbours() {
        // Set<ICell2D> neighours = neighbourMap.values().stream().filter( ic -> ic !=  Unknown.SINGLETON ).collect( Collectors.toSet());


        return Collections.unmodifiableMap( neighbourMap );
    }

    @Override
    public int hashCode() {
        int result = 29;
        result = 31 * result * Integer.hashCode( getRow() );
        result = 31 * result * Integer.hashCode( getCol() );

        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( !( obj instanceof ICell2DQuad ) ) {
            return false;
        }

        ICell2DQuad ocq = ( ICell2DQuad ) obj;

        return this.getRow() == ocq.getRow() && this.getCol() == ocq.getCol();
    }

    @Override
    public String toString() {
        return "Cell2DQuad{" +
                "row=" + row +
                ", col=" + col + " Neigbour " + neighbourMap.size() +
                '}';
    }
}
