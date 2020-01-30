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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractCell2D implements ICell2D, Comparable<ICell2D> {

    public static final Comparator<ICell2D> COMP = Comparator.comparing( ICell2D::getRow ).thenComparing( ICell2D::getCol );

    private static final Logger LOGGER = LoggerFactory.getLogger( AbstractCell2D.class );

    final int row;    // row
    final int col;    //
    final List<ICell> linkedCellL;            // linked cells

    AbstractCell2D( final int row, final int column ) {
        this.row = row;
        this.col = column;

        this.linkedCellL = new LinkedList<>();
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public boolean isLinked( ICell other ) {
        return getLinkedCells().contains( other );
    }

    @Override
    public List<ICell> getLinkedCells() {
        return this.linkedCellL;
    }

    @Override
    public void link( ICell cell, boolean bidiProp ) {
        Objects.requireNonNull( cell );
        if ( isLinked( cell ) ) {
            LOGGER.warn( "Cell '" + cell + " was already linked" );
        }
        boolean add = getLinkedCells().add( cell );
        LOGGER.debug( "Linked '" + this + "' to '" + cell + "' > " + add );

        // link other
        if ( bidiProp ) {
            cell.link( this, false );
        }
    }

    @Override
    public void unlink( ICell cell, boolean bidiProp ) {
        Objects.requireNonNull( cell );
        if ( !isLinked( cell ) ) {
            LOGGER.warn( "Cell 'cell' was not linked" );
            return;
        }

        boolean removed = getLinkedCells().remove( cell );
        LOGGER.debug( "Unlinked 'cell'from this > '" + removed );
        if ( bidiProp ) {
            cell.unlink( this, false );
        }

    }

    @Override
    public int compareTo( ICell2D other ) {
        return COMP.compare( this, other );
    }
}
