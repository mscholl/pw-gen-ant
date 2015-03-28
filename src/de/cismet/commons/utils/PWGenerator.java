/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.commons.utils;

import java.security.SecureRandom;

/**
 * DOCUMENT ME!
 *
 * @version  1.0
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
        // use proper seed
        final SecureRandom sr = new SecureRandom(new byte[] { 77, 92, 126, 1, 2, 34, 84, 32 });

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

        System.out.println(result);
    }
}
