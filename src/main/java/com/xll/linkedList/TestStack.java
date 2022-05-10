package com.xll.linkedList;



import java.util.Stack;

/**
 * @Author xulele
 * @Date: 2022/05/05/23:48
 * @Description:
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<String> strings = new Stack<>();

        //入栈
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");

        //出栈
        while (strings.size() > 0 ) {
            //将栈顶的数据去除
            System.out.println(strings.pop());
        }

    }
}
