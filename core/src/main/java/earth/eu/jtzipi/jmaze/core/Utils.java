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

package earth.eu.jtzipi.jmaze.core;

import earth.eu.jtzipi.jmaze.core.cell.ICell;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utilities.
 *
 * @author jTzipi
 */
public final class Utils {


    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();


    /**
     * Return a pseudorandom number between zero and {@code bound}.
     *
     * @param bound upper bound
     * @return random number [0-boun]
     * @throws IllegalArgumentException if {@code bound} &le; 0
     */
    public static int rand( int bound ) {
        if ( 0 < bound ) {

            return RANDOM.nextInt( bound );
        }
        throw new IllegalArgumentException( "size[" + bound + "] <= 0 is  forbidden" );
    }

    public static double randDouble( double bound ) {

        return RANDOM.nextDouble( bound );
    }

    /**
     * @param set
     * @param <C>
     * @return
     */
    public static <C extends ICell> C randOf( Set<C> set ) {
        Objects.requireNonNull( set );
        List<C> cl = new ArrayList<>( set );

        return randOf( cl );
    }

    /**
     * @param cl
     * @param <C>
     * @return
     */
    public static <C extends ICell> C randOf( List<C> cl ) {
        Objects.requireNonNull( cl );

        return cl.get( rand( cl.size() ) );
    }

}
