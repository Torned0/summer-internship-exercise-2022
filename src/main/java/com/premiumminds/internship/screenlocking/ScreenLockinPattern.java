package com.premiumminds.internship.screenlocking;

/*
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
*/

import java.util.concurrent.*;

/**
 * Created by aamado on 05-05-2022.
 */
class ScreenLockinPattern implements IScreenLockinPattern {

    /**
     * Method to count patterns from firstPoint with the length
     *
     * @param firstPoint initial matrix position
     * @param length     the number of points used in pattern
     * @return number of patterns
     */
    public Future<Integer> countPatternsFrom(final int firstPoint, final int length) {

        ExecutorService service = Executors.newFixedThreadPool(4);
        return service.submit(() -> calculatePatterns(firstPoint, length));

    }

    boolean[] usedNumbers = new boolean[9];

    public int calculatePatterns(int startPos, int maxLength) {

        int possible = 0;

        if (!validSize(maxLength) || !validStart(startPos)) {
            return 0;
        }

        if (maxLength == 1) {
            return 1;
        }

        usedNumbers[startPos - 1] = true;

        for (int i = 1; i <= 9; i++) {
            if (!usedNumbers[i - 1] && valid(startPos, i)) {
                possible += calculatePatterns(i, maxLength - 1);
            }
        }

        usedNumbers[startPos - 1] = false;
        return possible;
    }

    static boolean validSize(int length) {
        return length <= 9 && length > 0;
    }

    static boolean validStart(int start) {
        return start <= 9 && start > 0;
    }

    private boolean valid(int currPos, int nextPos) {
        /*
         *Checks for the conditions required for the move to be legal
         * */
        switch (currPos) {
            case 5:             // 5 is center, can go anywhere
                return true;
            case 1:
                return !(nextPos == 7 && !usedNumbers[3]) && !(nextPos == 9 && !usedNumbers[4]) && !(nextPos == 3 && !usedNumbers[1]);
            case 2:
                return !(nextPos == 8 && !usedNumbers[4]);
            case 3:
                return !(nextPos == 1 && !usedNumbers[1]) && !(nextPos == 9 && !usedNumbers[5]) && !(nextPos == 7 && !usedNumbers[4]);
            case 4:
                return !(nextPos == 6 && !usedNumbers[4]);
            case 6:
                return !(nextPos == 4 && !usedNumbers[4]);
            case 7:
                return !(nextPos == 1 && !usedNumbers[3]) && !(nextPos == 3 && !usedNumbers[4]) && !(nextPos == 9 && !usedNumbers[7]);
            case 8:
                return !(nextPos == 2 && !usedNumbers[4]);
            case 9:
                return !(nextPos == 1 && !usedNumbers[4]) && !(nextPos == 3 && !usedNumbers[5]) && !(nextPos == 7 && !usedNumbers[7]);
        }
        System.out.println("Error");
        return false;
    }
}
