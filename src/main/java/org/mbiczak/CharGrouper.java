package org.mbiczak;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Sorts and compresses String and characters. The sort functionality is executed with the use of ArraySorter class
 * and compressions is done according to StringCompressor class specification.
 */
@Service
public class CharGrouper {
    @Autowired
    private ArraySorter sorter;
    @Autowired
    private StringCompressor compressor;

    /**
     * Method used for sorting and compressing arrays of characters.
     *
     * @param input which will be sorted and then compressed
     * @return sorted and compressed input
     */
    public String group(char[] input) {
        int[] sortedData = sorter.sort(castCharArrayToIntArray(input));

        if(sortedData.length < 2) {
            return castIntArrayToString(sortedData);
        }

        return compressor.compress(castIntArrayToString(sortedData));
    }

    /**
     * Mehtod used for sorting and compressing Strings.
     *
     * @param input which will be sorted and then compressed
     * @return sorted and compressed input
     */
    public String group(String input) {
        return group(input.toCharArray());
    }

    /**********
        UTILS
     **********/

    public static int[] castStringToIntArray(String input) {
        return castCharArrayToIntArray(input.toCharArray());
    }

    /**
     * Converts character array to integer array.
     *
     * @param input array to be converted
     * @return the array of integers from character array
     */
    public static int[] castCharArrayToIntArray(char[] input) {
        int[] result = new int[input.length];

        for(int i=0; i<input.length; i++) {
            result[i] = input[i];
        }

        return result;
    }

    /**
     * Converts array of integers to String.
     *
     * @param input array of integers to be converted
     * @return the String created from integer array
     */
    public static String castIntArrayToString(int[] input) {
        char[] result = new char[input.length];

        for(int i=0; i<input.length; i++) {
            result[i] = (char)input[i];
        }

        return new String(result);
    }
}
