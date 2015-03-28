/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.commons.utils;

import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.security.SecureRandom;

/**
 * DOCUMENT ME!
 *
 * @version  1.1
 */
public class PWGenerator {

    private static final int PW_LENGTH = 32;
    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new PWGenerator object.
     */
    private PWGenerator() {
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(final String[] args) {
        // assume 32 bit int and 64 bit long
        
        Point point;
        try {
            point = MouseInfo.getPointerInfo().getLocation();
        } catch(final HeadlessException | SecurityException ex) {
            point = null;
        }
        
        final long now = System.currentTimeMillis();
        
        final byte[] seed;
        if(point == null) {
            seed = new byte[4];
        } else {
            seed = new byte[8];
            
            // upper bytes usually zero thus only lower bytes
            seed[4] = (byte)(point.x);
            seed[5] = (byte)(point.x >> 8);
            
            seed[6] = (byte)(point.y);
            seed[7] = (byte)(point.y >> 8);
        }
        
        // only use the portion that changes frequently
        seed[0] = (byte)(now);
        seed[1] = (byte)(now >> 8);
        seed[2] = (byte)(now >> 16);
        seed[3] = (byte)(now >> 24);
        
        // use proper seed
        final SecureRandom sr = new SecureRandom(seed);

        int charCount = 0;
        final int maxChars = PW_LENGTH;
        final char[] result = new char[maxChars];
        while (charCount < maxChars) {
            final int c = sr.nextInt() % 127;
            if (((c < 48) || (c > 57)) && ((c < 65) || (c > 90)) && ((c < 97) || (c > 122))) {
                // only 0-9A-Za-z
                // continue;
            } else {
                result[charCount++] = (char)c;
            }
        }

        System.out.println(String.valueOf(result));
    }
}
