package com.xll.linkedList;

/**
 * @Author xulele
 * @Date: 2022/05/08/22:23
 * @Description:
 *
 * 单向链表缺点分析:
 * 1.单向链表查找的方向只能是一个方向,而双向链表可以向前或向后查找
 * 2.单向链表不能自我删除,需要辅助节点(temp 是被删除节点的前一个节点),而双向链表,则可以自我删除(temp就是要删除的节点)
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
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        //todo 按照编号顺序添加,保证有序 (按照单链表的顺序添加方式稍作修改)

        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        //显示
        doubleLinkedList.list();

        //修改
        HeroNode2 hero5 = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(hero5);
        doubleLinkedList.list();

        //删除

        doubleLinkedList.delete(3);
        doubleLinkedList.list();


    }
}

class DoubleLinkedList {
    //初始化一个头节点,头节点不要动,不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //双向链表删除节点 对于双向链表,可以直接找到要删除的节点,找到后自我删除即可
    public void delete(int no) {

        if (head.next == null) {
            System.out.println("链表为空,无法删除");
            return;
        }

        HeroNode2 temp = head.next; //辅助变量
        boolean flag = false; //表示是否找到该节点
        //找到需要修改的节点
        while (true) {
            if (temp == null) {
                System.out.println("链表的最后");
                break;//已经遍历完链表了
            }
            if (temp.no == no) {
                //找到删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //这里需要作判空处理 如果是最后一个节点,就不需要执行下面这句,否则空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else { //没有找到该节点
            System.out.println("没有找到编号" + no);
        }
    }

    //修改一个节点的内容  双向链表的节点内容修改方法和单向链表一致
    public void update(HeroNode2 heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
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

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {

        //因为head节点不能动,因此需要一个辅助变量 temp
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //遍历双向链表 与单向链表遍历方法一致
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动,所以需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

//常见一个双向链表类
class HeroNode2 {

    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next; //指向下一个域,默认为null
    public HeroNode2 pre;//指向前一个节点,默认为null


    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode2() {
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
}
