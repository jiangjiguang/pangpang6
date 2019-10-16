package com.pangpang6.books.offer.chapter2;

/**
 * 机器人的运动范围
 */
public class P92_RobotMove {
    /**
     * @param threshold 限制的格子数
     * @param rowLen    行数
     * @param colLen    列数
     * @returnb 机器人能够到达的格子数
     */
    public static int movingCount(int threshold, int rowLen, int colLen) {
        if (rowLen <= 0 || colLen <= 0 || threshold < 0) {
            return 0;
        }
        //是否到达过
        boolean[][] visitFlag = new boolean[rowLen][colLen];

        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                visitFlag[row][col] = false;
            }
        }
        return movingCountCore(threshold, rowLen, colLen, 0, 0, visitFlag);
    }

    public static int movingCountCore(int threshold, int rowLen, int colLen, int row, int col, boolean[][] visitFlag) {
        int count = 0;
        if (canGetIn(threshold, rowLen, colLen, row, col, visitFlag)) {
            visitFlag[row][col] = true;
            count = 1 + movingCountCore(threshold, rowLen, colLen, row - 1, col, visitFlag) +
                    movingCountCore(threshold, rowLen, colLen, row + 1, col, visitFlag) +
                    movingCountCore(threshold, rowLen, colLen, row, col - 1, visitFlag) +
                    movingCountCore(threshold, rowLen, colLen, row, col + 1, visitFlag);
        }
        return count;
    }

    //是否能进入某个格子
    private static boolean canGetIn(int threshold, int rowLen, int colLen, int row, int col, boolean[][] visitFlag) {
        return row >= 0 && col >= 0 && row < rowLen
                && col < colLen && !visitFlag[row][col]
                && getDigitSum(row) + getDigitSum(col) <= threshold;
    }

    private static int getDigitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(0, 3, 4)); //1
        System.out.println(movingCount(1, 3, 4)); //3
        System.out.println(movingCount(9, 2, 20)); //36
    }
}
