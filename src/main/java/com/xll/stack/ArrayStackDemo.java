package com.xll.stack;

/**
 * @Author xulele
 * @Date: 2022/05/11/6:42
 * @Description:
 */
/**
 * 栈的介绍:
 * 1.栈的英文为(stack)
 * 2.栈是一个先进后出的有序列表
 * 3.栈式限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表,允许插入和删除的一端,为变化的一端,称为栈顶,另一端为固定的一端,成为栈底
 * 4.根据栈的定义可知,最先放入栈中元素在栈底,最后放入的元素在栈顶,而删除元素刚好相反,最后放入的元素最先删除,最先放入的元素最后删除
 */

import java.util.Scanner;

/**
 * 栈的应用场景
 * 1.子程序的调用: 在跳往子程序前,会先将下一个指令的地址存在堆栈中,知道子程序执行完后再将地址取出,以回到原来的程序中
 * 2.处理递归调用: 和子程序的调用类似,只是除了存储下一次指定的地址外,也将参数.区域变量等数据存储堆栈中
 * 3.表达式的转换[中缀表达式转后缀表达式]与求值
 * 4.二叉树的遍历
 * 5.图形的深度优先搜索法
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        //测试栈
        //创建一个ArrayStack对象
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 标识显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 添加数据到栈  入栈");
            System.out.println("pop: 从栈取出数据  出栈");
            System.out.println("请输入选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);;
                    break;
                case "pop":
                    try {
                        int res = stack.pop();;
                        System.out.printf("出栈的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出");


    }
}
/** 标识栈结构 */
class ArrayStack {

    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈,数据就放在该数组
    private int top = -1;//栈顶   没有数据的时候,初始化为-1


    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize -  1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈 push
    public void push(int value) {
        //先判断是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈,将栈顶的收据返回
    public int pop(){
        //判断是否空
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈 遍历时需要从栈顶开始显示数据
    public void list() {
        //判空
        if (isEmpty()) {
            System.out.println(("栈空"));
        }
        for (int i = top;i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //todo 用单向链表模拟实现栈
}
