package com.xll.stack;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author xulele
 * @Date: 2022/06/06/21:32
 * @Description:
 */
public class PolandNotation {

    public static void main(String[] args) {

        // 完成一个将中缀表达式转成后缀表达式的功能
        // 因为直接对字符串进行操作不太方便,因此先将字符串转成一个中缀表达式对应的list

        String expression = "1+((2+3)*4)-5";
        List<String> stringList = toInFixExpression(expression);

        List<String> stringList1 = parseSuffixExpressionList(stringList);
        System.out.println(stringList1); //[1, 2, 3, +, 4, *, +, 5, -]


        // 先定义一个逆波兰表达式(用空格隔开):
        String suffixExpression = "3 4 + 5 * 6 - ";

        //思路
        //1.先将suffixExpression放入ArrayList中
        //2.将ArrayList传递给一个方法 ,遍历配合栈完成计算

        List<String> listString = getListString(suffixExpression);

        int calculate = calculate(stringList1);
        System.out.println(calculate);
    }

    //将中缀表达式对应的list转成后缀表达式对应的list\
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //初始化两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //Stack<String> s2 = new Stack<>();//存贮中间结果的栈 因为S2在整个转换过程中没有pop操作,而且还需要i逆序输出,所以不用Stack<String> 此处用list
        List<String> s2 = Lists.newArrayList();


        //遍历ls
        for (String item : ls) {
            //如果是一个数,就加入到s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) { //如果是右括号 依次弹出s1栈顶运算符,并压入到s2,知道需要左括号,此时将一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将(小括号弹出s1  消除小括号
            } else {
                //当item的优先级 <= s1栈顶的运算符的优先级 将s1栈顶的运算符弹出并押入到s2
                //缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item押入到s1
                s1.push(item);
            }
        }
        //将s1中剩余的元素加入到s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        //因为是存放到list中 本身就有序 所以直接输出即可
        return s2;
    }


    //将逆波兰表达式放入list
    public static List<String> getListString(String suffixExpression) {
        return Arrays.asList(suffixExpression.split(" "));

    }

    //将中缀表达式转为list
    public static List<String> toInFixExpression(String s) {
        List<String> ls = Lists.newArrayList();
        int i = 0;//这是一个指针,用于遍历  中缀表达式字符串
        String str;// 对多位数的拼接
        char c;//每遍历到一个字符 就放入到c
        do {
            //如果是一个非数字,加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {// 如果是一个数字 则需要考虑是否为多位数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;

    }

    public static int calculate(List<String> list) {
        //创建栈  只需要一个栈
        Stack<String> stack = new Stack();
        for (String s : list) {
            //使用正则表达式匹配数
            if (s.matches("\\d+")) { //匹配的是多位数
                //入栈
                stack.push(s);
            } else {
                //pop出两个数并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (s.equals("+")) {
                    res = num1 + num2;
                } else if ("-".equals(s)) {
                    res = num1 - num2;
                } else if ("*".equals(s)) {
                    res = num1 * num2;
                } else if ("/".equals(s)) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有问题");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
//比较优先级高低的方法
class Operation {

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法返回对应的优先数字

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println(("不存在该运算符"));
                break;

        }
        return result;
    }
}
