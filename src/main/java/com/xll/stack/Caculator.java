package com.xll.stack;

/**
 * @Author xulele
 * @Date: 2022/05/17/21:55
 * @Description:
 */

/**
 * 使用栈完成计算一个表达式的结果
 * 思路:
 * 1.通过一个index(索引),来遍历表达式
 * 2.如果发现是一个数字,就直接入数栈
 * 3,如果发现扫描到的是一个运算符,就分如下情况
 * a.如果发现当前的符号栈为空,就直接入栈
 * b.如果符号栈有操作符,就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符 ,就需要从数栈中pop出两个数,再从符号栈中pop出一个符号,进行运算,将得到的元素结果再入数栈,,然后将当前的操作符入符号栈;
 * 如果当前的操作符的优先级大于栈中的操作符 ,就直接入符号栈;
 * 4.当表达式扫描完毕,就顺序的从数栈和符号栈中pop出相应的数和符号,并运算
 * 5.最后数栈只有与1个数字,就是表达式的结果
 */
public class Caculator {
    public static void main(String[] args) {
        //完成表达式的运算
        String expression = "7*2*2-5+1-5+3-4";
        //创建两个栈  数栈  符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义需要的相关变量
        int index = 0; //扫描表达式
        int num1, num2, oper, res = 0;
        char ch = ' '; //将每次扫描得到的char保存到ch
        String keepNum = ""; //用于拼接多位数
        //循环扫描表达式
        while (true) {
            //依次得到表达式的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断是那种类型
            if (operStack.isOper(ch)) {
                //当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    //不为空 需要判断优先级问题
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //将计算结果push到数栈
                        numStack.push(res);
                        //当前操作符入符号栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else {
                //1.当处理多位数时,不能发现是一个数字就立即入栈,因为可能是多位数
                //2.在处理数时,需要向表达式的后面在判断一下是否时数,如果是数就继续扫描
                //3.需要定义一个字符串变量用于拼接
                //numStack.push(ch - 48);
                keepNum += ch;
                //判断下一个字符是不是数字,如果是数字就继续扫描,如果是运算符 则入数栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                    numStack.push(Integer.parseInt(keepNum));
                    //清空keepNum
                    keepNum = "";
                }

            }
            //index后移  判断是否是最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (true) {
            //符号栈为空 则计算已经结束  数栈中只有一个数字 就是结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //将计算结果push到数栈
            numStack.push(res);
        }
        System.out.println("表达式的值为: " + numStack.pop());

    }
}

//先创建一个栈
class ArrayStack2 {

    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈,数据就放在该数组
    private int top = -1;//栈顶   没有数据的时候,初始化为-1


    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
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
    public int pop() {
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
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回栈顶的元素 但是不是出栈
    public int peek() {

        return stack[top];
    }

    //返回运算符的优先级,优先级由程序员定,优先级使用数字表示,数字越大则优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假设只有加减符号
        }
    }

    //是否是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;//注意顺序
                break;
            default:
                break;

        }
        return res;
    }
}
