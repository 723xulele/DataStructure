package com.xll.linkedList;


import java.util.Stack;

/**
 * @Author xulele
 * @Date: 2022/04/20/23:16
 * @Description: 单链表
 * <p>
 * 1. 链表是以节点的方式来存储,是链式存储
 * 2. 每个节点包含data域,next域,指向下一个节点
 * 3. 链表的每个节点不一定是连续存放
 * 4. 链表分带头节点的链表和没有头节点的链表  根据实际的需求确定
 * <p>
 * * head节点:
 * 1. 不存放具体的数据
 * 2. 作用就是单链表的头
 * 3. next域指向下一个节点
 * <p>
 * <p>
 * 添加:
 * 1.先创建一个head节点,作用就是标识单链表的头
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        ////按编号
        //singleLinkedList.addByOrder(hero1);
        //singleLinkedList.addByOrder(hero4);
        //singleLinkedList.addByOrder(hero2);
        //singleLinkedList.addByOrder(hero3);
        //
        ////测试修改节点
        ////HeroNode heroNode5 = new HeroNode(2, "小鹿", "...");
        ////singleLinkedList.update(heroNode5);
        //
        ////测试删除节点
        ////singleLinkedList.delete(3);
        //
        ////测试一下 求单链表的节点个数
        //System.out.println("有效节点个数为: " + getLength(singleLinkedList.getHead()));
        //
        ////测试,倒数第k个节点
        //System.out.println(findLastNodeIndex(singleLinkedList.getHead(), 3));
        //
        ////显示链表
        singleLinkedList.list();

        //reverseList(singleLinkedList.getHead());

        //singleLinkedList.list();

        //测试逆序打印单链表
        reversePrint(singleLinkedList.getHead());
    }
    /**
     * 合并两个有序的单向链表
     */
    //todo 合并两个有序的单向链表

    /**
     * 获取单链表的节点的个数(如果是带头节点的链表,需要不统计头节点)
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义辅助变量 没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 将单链表进行反转
     * 思路:
     * 1.先定义一个节点,reverseHead = new HeroNode();
     * 2.从头到尾遍历原来的链表,每遍历一个节点,就将其取出并放在新的链表的最前端
     * 3.原来的链表的head.next = reverseHead.next
     */
    public static void reverseList(HeroNode heroNode) {
        //如果当前链表为空活活只有一个节点,无需反转,直接返回
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        //定义一个辅助变量,帮助我们遍历原来的链表
        HeroNode cur = heroNode.next;
        HeroNode next = null;//指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");

        //遍历原来的链表,每遍历一个节点,就将其取出,并放在新的链表的最前端

        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的一个节点,因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点,指向新的链表的最前端
            reverseHead.next = cur;//将cur连接到新的链表上
            cur = next;//cur后移
        }
        //将heroNode.next指向reverseHead.next,实现单链表的反转
        heroNode.next = reverseHead.next;
    }

    /**
     * 从尾到头打印单链表
     * 思路:
     * 1.要求就是逆序打印单链表
     * 2.方式1: 将单链表进行反转操作,再遍历,这样做的问题是会破坏原来的单链表的结构(不建议)
     * 3.方式2: 利用栈这个数据结构,将各个节点压入到栈中,然后利用栈的先进后出特点,实现逆序打印效果
     * 举例使用方式2
     */
    public static void reversePrint(HeroNode heroNode) {
        if (heroNode.next == null) {
            return; //空链表 不能打印
        }
        //创建一个栈 将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = heroNode.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点打印 pop出栈
        while (stack.size() > 0 ) {
            System.out.println(stack.pop());//stack的特点是先进后出
        }
    }

    /**
     * 查找单链表中的倒数第K个节点[新浪面试题]
     * 思路:
     * 1.编写一个方法,接收head节点,同时接收一个index
     * 2.index标识第index个节点
     * 3.先把链表从头到尾遍历,得到链表的总长度(getLength),
     * 4.得到size后,从链表的第一个开始遍历(size - index)个
     * 5.如果找打则返回该节点 否则返回空
     */
    public static HeroNode findLastNodeIndex(HeroNode heroNode, Integer index) {
        //如果链表为空 则返回null
        if (heroNode.next == null) {
            return null;
        }
        //第一次遍历 得到链表的节点个数
        int size = getLength(heroNode);
        //第二次遍历 size - index位置,就是倒数第K个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义一个辅助变量 for循环定位到倒数的index
        HeroNode cur = heroNode.next;

        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }

        return cur;
    }
}

//定义SingleLinkedList 管理英雄
class SingleLinkedList {
    //初始化一个头节点,头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路: 当不考虑编号的顺序时 按顺序添加
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode) {

        //因为head节点不能动,因此需要一个辅助变量 temp
        HeroNode temp = head;
        //遍历链表 找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没找到最后,将temp后移
            temp = temp.next;
        }
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    //第二种添加英雄时 根据排名添加到指定位置
    //如果这个排名有英雄 则添加失败

    /**
     * 需要按照编号的顺序添加
     * 1.首先找到新添加的节点的位置,是通过一个辅助变量来找,通过遍历
     * 2.新的节点.next = temp.next
     * 3.将temp.next = 新的节点
     */
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动,因此需要一个辅助变量来帮助找到添加的位置
        //因为单链表,因此我们找的这个temp是位于添加位置的前一个节点,否则插入不了
        HeroNode temp = head;
        boolean flag = false;//flag标志添加的编号是否存在 默认为false

        while (true) {
            if (temp.next == null) { //说明temp在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到 就在temp的后面
                break;
            } else if (temp.next.no == heroNode.no) { //说明希望添加的编号已经存在
                System.out.println("编号存在");
                break;
            }
            temp = temp.next; //后移,遍历当前链表
        }
        if (flag) { //不能添加,编号存在
            System.out.println("编号已经存在,不能加入" + heroNode.no);
        } else {
            //插入到链表,temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;

        }
    }

    /**
     * 修改节点的信息 根据编号修改,no不能变
     * 1.根据新节点的no修改
     */
    public void update(HeroNode heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        //找到需要修改的节点
        while (true) {
            if (temp == null) {
                System.out.println("链表的最后");
                break;//已经遍历完链表了
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else { //没有找到该节点
            System.out.println("没有找到编号" + heroNode.no);
        }
    }

    /**
     * 删除节点
     * 1.需要一个辅助变量 找到待删除节点的前一个节点
     * 2.temp.next = temp.next.next
     * 3.被删除的节点将不会有其他引用指向,会被垃圾回收机制回收
     */
    public void delete(int no) {

        HeroNode temp = head;
        boolean flag = false; //表示是否找到该节点
        //找到需要修改的节点
        while (true) {
            if (temp.next == null) {
                System.out.println("链表的最后");
                break;//已经遍历完链表了
            }
            if (temp.next.no == no) {
                //找到删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else { //没有找到该节点
            System.out.println("没有找到编号" + no);
        }
    }


    //显示链表 遍历
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动,所以需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //是否到链表的最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp.toString());
            //将temp后移,一定需要后移
            temp = temp.next;

        }
    }
}

//定义HeroNode 每个HeadNode就是一个节点

class HeroNode {

    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一个域


    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode() {
    }

    /**
     * 为了显示方便 重写toString()方法
     */
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
    /**
     * 双向链表的遍历,添加,修改,删除的思路
     * 1.遍历: 方式和单链表一样,只是可以向前,也可以向后查找
     * 2.添加: (添加到双向链表的最后)
     *  a.先找到双向链表的最后一个节点
     *  b.temp.next = new HeroNode()
     *  c.new HeroNode().pre = temp
     * 3.修改 思路和单向链表一样
     * 4.删除
     *  a.因为是双向链表,因此可以实现自我删除某个节点
     *  b.直接找到要删除的这个节点,比如temp
     *  c.temp.pre.next = temp.next
     *  d.temp.next.pre = temp.pre
     */














































}
