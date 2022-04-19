package com.xll.sparseArray;

import org.testng.annotations.Test;

/**
 * @Author: xll
 * @Date: 2021/08/26/9:05
 * @Description:
 */
public class TestArray {

    /**
     * 遍历(打印)二维数组
     */
    public static void printArray(int[][] array) {

        for (int[] row : array) {
            for (int data : row) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
    }

    /**
     * 自定义二维数组
     */
    public static int[][] createArray(){
        int[][] array = new int[5][5];
        array[0][0] = 5;
        array[1][1] = 5;
        array[2][4] = 8;
        array[3][1] = 5;
        array[4][3] = 5;
        array[4][4] = 5;
        return array;
    }

    @Test
    public void testPrintArray(){
        int[][] array = createArray();
        printArray(array);
        System.out.println(array.length);
        /**
         * 5 0 0 0 0
         * 0 5 0 0 0
         * 0 0 5 0 0
         * 0 0 0 5 0
         * 0 0 0 0 5
         */
    }

    @Test
    public void testSparseArray(){
        int[][] array = this.createArray();
        int[][] sparseArray = ArrayUtil.sparseArray(array);
        printArray(sparseArray);
        /**
         * 5 5 5
         * 0 0 5
         * 1 1 5
         * 2 2 5
         * 3 3 5
         * 4 4 5
         */
        int[][] array2 = ArrayUtil.array(sparseArray);
        printArray(array2);
        /**
         * 5 0 0 0 0
         * 0 5 0 0 0
         * 0 0 5 0 0
         * 0 0 0 5 0
         * 0 0 0 0 5
         */
    }


}
