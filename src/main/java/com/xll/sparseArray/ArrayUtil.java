package com.xll.sparseArray;

/**
 * @Author: xll
 * @Date: 2021/08/26/9:00
 * @Description: 二维数组与稀疏数组的互相转化
 */
public class ArrayUtil {

    /**
     * 二维数组转为稀疏数组
     */
    public static int[][] sparseArray(int[][] array) {

        int total = 0;
        for (int[] row : array) {
            for (int data : row) {
                if (data != 0) {
                    total += 1;
                }
            }
        }

        int[][] sparseArray = new int[total+1][3];
        sparseArray[0][0] = array.length;
        sparseArray[0][1] = array[0].length;
        sparseArray[0][2] = total;
        int index = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0) {
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = array[i][j];
                    index += 1;
                }
            }
        }
        return sparseArray;
    }

    /**
     * 稀疏数组转二维数组
     */
    public static int[][] array(int[][] sparseArray) {
        /**
         * 1.根据稀疏数组第一行确定二维数组的大小
         */
        int[][] array = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i <= sparseArray[0][2]; i++) {
            array[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return array;
    }

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
}
