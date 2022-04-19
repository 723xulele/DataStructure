package com.xll.queue;

import java.util.Scanner;

/**
 * @Author xulele
 * @Date: 2022/04/19/22:55
 * @Description:
 *
 * 队列:
 *  1.队列的使用场景  银行排队窗口
 *  2.队列的介绍
 *      a.队列是一个有序列表,可以用数组或链表来实现
 *      b.遵循先入先出的原则,即线存入队列的数据,要先取出,后存入的后取出
 *  3. 问题分析并优化
 *      a.目前数组使用一次就不能使用,没有达到复用的效果
 *      b.将这个数组使用算法,改进成一个环形队列 取模的方式完成
 *
 *
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        //创建队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add):  添加数据到队列");
            System.out.println("g(get):  从队列取出数据");
            System.out.println("h(head):  查看队列头的数据");
            key = scanner.next().charAt(0); // 接收一个字符

            switch (key) {
                case 's' :
                    queue.showQueue();
                    break;
                case 'a' :
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g' :
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h' :
                    try {
                        int head = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n",head);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' :
                    scanner.close();
                    loop = false;
                    break;

            }
        }
        System.out.println("程序退出");

    }
}

// 使用数组模拟队列
class ArrayQueue {
    private int maxSize; //表示数组最大容量
    private int front; //指向队列头
    private int real; //指向队列尾
    private int[] arr; //该数组用于存放数据,模拟队列

    //创建队列构造器
    public ArrayQueue(int arrayMaxSize) {
        this.maxSize = arrayMaxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头部,分析出,front指向队列头的前一个位置
        real = -1; //指向队列尾部.指向队列尾的具体位置(即就是队列最后一个数据的下标)
    }


    //判断队列是否满
    public boolean isFull() {
        return real == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return real == front;
    }

    //添加数据到队列
    public void addQueue(int n) {

        /** 是否满 */
        if (isFull()) {
            System.out.println("队列满不能加入数据");
            return;
        }

        real++;
        arr[real] = n;
    }

    //数据出队列
    public int getQueue() {

        /** 是否空 */
        if (isEmpty()) {
            /** throw后会停止程序 */
            throw new RuntimeException("队列空,不能取数据");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历数组
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列的头数据,注意不是取出数据
    public int headQueue(){
        //判断是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空没有数据");
        }
        return arr[front+1];
    }

}