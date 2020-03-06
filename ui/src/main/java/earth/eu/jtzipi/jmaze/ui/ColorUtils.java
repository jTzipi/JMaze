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


import earth.eu.jtzipi.modules.utils.Utils;
import javafx.scene.paint.Color;

/**
 *
 */
public final class ColorUtils {

    public static final int COLOR_OFFSET_MIN = 0;
    public static final int COLOR_OFFSET_MAX = 254;
    public static final int COLOR_MIN = 0;
    public static final int COLOR_MAX = 255;

    private ColorUtils() {

    }



    /**
     * Compute blue tone with an offset.
     *
     * @param offset offset [{@linkplain #COLOR_OFFSET_MIN}..{@linkplain #COLOR_OFFSET_MAX}]
     * @param value  value &ge; 0
     * @param max    max value &gt; 0
     * @return blue tone color
     * @throws IllegalArgumentException if {@code max} &lt; {@code value}
     */
    public static Color computeBlue( int offset, double value, double max ) {
        if ( 0D > value || 0D >= max ) {
            throw new IllegalArgumentException( "max must > 0 value >= 0 but was max ' + max + ' value '" + value );
        }
        if ( max < value ) {
            throw new IllegalArgumentException( "max ' + max + ' < value '" + value );
        }
        offset = Utils.clamp( offset, COLOR_OFFSET_MIN, COLOR_OFFSET_MAX );
        value = Utils.clamp( value, 0D, max );

        double percent = value / max;
        int blue = offset + ( int ) ( percent * ( COLOR_MAX - offset ) );

        return Color.rgb( COLOR_MIN, COLOR_MIN, blue );
    }

    private static double percent( double value, double max ) {
        return 0D == value
                ? 0D
                : value == max
                ? 100D
                : value / max * 100D;
    }
}
