package org.mbiczak;

import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Sorts array of integers, does not remove duplications.
 */
@Service
public class ArraySorter {
    /**
     * Sorts array of integers
     *
     * @param unsorted integer arrays to be sorted
     * @return sorted integer arrays
     */
    public int[] sort(int[] unsorted) {
        int[] copy = Arrays.copyOf(unsorted, unsorted.length);
        Arrays.sort(copy);
        return copy;
    }
}
