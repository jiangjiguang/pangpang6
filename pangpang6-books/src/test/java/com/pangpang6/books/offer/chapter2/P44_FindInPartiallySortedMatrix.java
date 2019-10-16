package com.pangpang6.books.offer.chapter2;

/**
 * 二维数组，从左到右递增，从上到下递增，输入一个整数，判断数组中是否含有
 */
public class P44_FindInPartiallySortedMatrix {
    public static boolean findInPartiallySortedMatrix(int[][] data, int target) {
        if (data == null || data.length == 0 || data[0].length == 0) {
            return false;
        }
        int rowMax = data.length - 1, colMax = data[0].length - 1;
        int rowCur = data.length - 1, colCur = 0;
        while (true) {
            //当前行小于0 当前行大于最大行  当前列小于0  当前列大于最大列
            if (rowCur < 0 || rowCur > rowMax || colCur < 0 || colCur > colMax)
                return false;
            //若相等，则说明找到了
            if (data[rowCur][colCur] == target) {
                return true;
            }
            //最大行  最小列 若比目标值大 则行减一
            else if (data[rowCur][colCur] > target) {
                rowCur--;
            }
            //最大行  最小列 若比目标值大 则列减一
            else {
                colCur++;
            }

        }
    }

    public static void main(String[] args) {

        int[][] data = {{1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}};
        System.out.println(data.length);
        System.out.println(findInPartiallySortedMatrix(data, 10));
        System.out.println(findInPartiallySortedMatrix(data, 5));
    }
}
