package com.xll.queue;

import java.util.Scanner;

/**
 * @Author xulele
 * @Date: 2022/04/20/0:45
 * @Description: 使用数组模拟环形队列的思路分析
 * 1. front变量的含义做一个调整: front就指向队列的第一个元素,也就是说arr[front]就是队列的第一个元素 front的初始值为0
 * 2. real变量的含义做一个调整: real指向队列的最后一个元素的后一个位置,因为希望空出一个空间作为约定 real的初始值为0
 * 3. 当队列满时,条件是 (real + 1) % maxSize =  front
 * 4. 队列为空的条件,real = front 空
 * 5. 当这样分析后,队列中有效的数据的个数 (real + maxSize - front) % maxSize
 * 6. 再原来的队列上修改,得到环形队列
 */
public class CircleArrrayQueueDemo {

    public static void main(String[] args) {

        //创建队列
        CircleArray queue = new CircleArray(4); //实际队列有效数据最大为3
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
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", head);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;

            }
        }
        System.out.println("程序退出");

    }
}

// 使用数组模拟队列
class CircleArray {
    private int maxSize; //表示数组最大容量
    private int front; //front就指向队列的第一个元素
    private int real; //real指向队列的最后一个元素的后一个位置
    private int[] arr; //该数组用于存放数据,模拟队列

    //创建队列构造器
    public CircleArray(int arrayMaxSize) {
        this.maxSize = arrayMaxSize;
        arr = new int[maxSize];
    }


    //判断队列是否满
    public boolean isFull() {
        return (real + 1) % maxSize == front;
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

        arr[real] = n;
        //将real后移 必须考虑取摸
        real = (real + 1) % maxSize;
    }

    //数据出队列
    public int getQueue() {

        /** 是否空 */
        if (isEmpty()) {
            /** throw后会停止程序 */
            throw new RuntimeException("队列空,不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1. 先把front对应的值保存到一个临时变量
        //2. 将front后移 考虑取模
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;

    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历数组
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        // 从front开始遍历  遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (real + maxSize - front) % maxSize;
    }

    //显示队列的头数据,注意不是取出数据
    public int headQueue() {
        //判断是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空没有数据");
        }
        return arr[front];
    }

}
