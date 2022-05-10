package com.xll.linkedList;

/**
 * @Author xulele
 * @Date: 2022/05/08/23:31
 * @Description: Josefu问题: 设编号为1,2,...n的个人围坐一圈,约定编号为k,(1<=k<=n)的人从1开始报数,数到m的那个人出列,它的下一位又从1开始报数,数到m的那个人又出列,以此类推, 2->4-1>5->3
 * 知道所有人都出列,由此产生一个出队编号的序列
 * <p>
 * 根据用户的输入:生成一个小孩出圈的顺序
 * 1.需求创建一个辅助变量helper,事先指向环形链表的最后这个节点
 * 补充: 报数前,先让first和helper移动k-1次数
 * 2.当小孩报数时,让first和helper同时移动 m-1 次
 * 3.这时就可以将first指向的小孩节点出圈
 * a.first  = first.next
 * b.helper.next = first
 * 原来需要被删除的节点就会被GC回收
 */

/**构建一个单向的环形链表思路
 * 1.先创建第一个节点,让first指向该节点,并形成环形
 * 2.后面每创建一个节点,就将该节点加入到已有的环形链表中即可
 *
 * 遍历环形链表:
 * 1.先让一个辅助变量指向first,
 * 2.通过while循环遍历该环形链表即可 curBoy.next = first (即遍历的是最后一个)
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();

        //加入五个小孩节点
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试出圈
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建一个环形单向链表
class CircleSingleLinkedList {
    //创建一个first节点
    private Boy first = new Boy(-1);

    /**
     * 根据用户的输入,计算出出圈顺序
     * @param startNo 从第几个开始
     * @param CountNo 数几下
     * @param nums 最初有多少小孩在圈
     */
    public void countBoy(int startNo, int CountNo, int nums) {

        //校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误");
            return;
        }

        //辅助变量,帮助完成出圈
        Boy helper = first;
        //事先将辅助变量指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        for (int j = 0; j < startNo - 1; j++){

            first = first.getNext();
            helper = helper.getNext();
        }
        //循环 直到只剩一个节点
        while (true) {
            if (helper == first) { //说明只有一人
                break;
            }
            //first和helper移动countNo - 1 次
            for (int j = 0; j < CountNo - 1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //first指向的就是要出去的节点
            System.out.println(first.getNo() + " 号出列");
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("留下来的是 " + first.getNo());

    }

    //添加节点,构建环形链表
    public void addBoy(int nums) {
        //校验节点个数
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        //创建环形链表
        Boy curBoy = null; //辅助变量 帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建节点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first); //构成一个环状
                curBoy = first; //让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy() {
        //是否为空链表
        if (first == null || first.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动,所以需要一个辅助变量,完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号: " + curBoy.getNo());
            if (curBoy.getNext() == first) { //说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }
}

//先创建一个Boy,表示一个节点
class Boy {
    private int no;
    private Boy next;//下一个节点

    public Boy(int no) {
        this.no = no;
    }

    public Boy() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
