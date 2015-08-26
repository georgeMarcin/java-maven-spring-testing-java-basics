package org.mbiczak;

import org.springframework.stereotype.Service;

/**
 * Compresses input String, works for alphanumerical values, however does not distinguish negative integers.
 * Negative integers are treated as two distinct characters (-x -> '-', 'x'). Compression format is as follows:
 * - N characters in a row are aggregated as follows: aaa -> a3
 * - single character is left: aaab -> a3b
 * - if compressed result is longer or equal to uncompressed input, uncompressed input is returned
 */
@Service
public class StringCompressor {
    private StringBuilder builder = new StringBuilder();

    /**
     * Compresses input String according to compression format.
     *
     * @param uncompressed input to be compressed
     * @return compressed input
     */
    public String compress(String uncompressed) {
        // check null
        if(uncompressed == null) {
            throw new NullPointerException("Input is NULL."); // this behaviour should depend on specification
        }

        // check for empty input and single char input
        if(uncompressed.length() < 2) {
            return uncompressed;
        }

        // compression related data
        int count = 0;
        char prev = 0;
        String compressed;

        for(char character : uncompressed.toCharArray()) {
            if(character == prev) {
                count++;
            } else if(prev == 0) {
                count++;
                prev = character;
            } else{
                // apply compression
                concat(prev, count);

                // reset compression related data
                count = 1;
                prev = character;
            }
        }

        // apply final compression
        concat(prev, count);

        compressed = builder.toString();
        builder.setLength(0); // "reset" builder

        if(compressed.length() >= uncompressed.length()) {
            return uncompressed;
        } else {
            return compressed;
        }
    }

    /**
     * Helper method which builds compressed result.
     *
     * @param character aggregated character
     * @param count number of occurences of the character
     */
    private void concat(char character, int count) {
        if(count == 1) {
            builder.append(character);
        } else {
            builder.append(character);
            builder.append(count);
        }
    }
}
