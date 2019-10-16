package com.pangpang6.books.offer.chapter3;


import com.pangpang6.books.offer.structure.ListNode;

/**
 * 链表中环的入口节点
 */
public class P139_EntryNodeInListLoop {
    public static ListNode<Integer> meetingNode(ListNode<Integer> head) {
        if (head == null) {
            return null;
        }
        //两个节点 一快一慢
        ListNode<Integer> slow = head, fast = head;

        while (true) {
            //用来确定是否包含环
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            //满节点 一次移动一个
            slow = slow.next;
            //快节点 一次移动两个
            fast = fast.next.next;
            //快节点和慢节点 指向同一个
            if (slow == fast) {
                break;
            }
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        //    1->2->3->4->5->6
        //          ↑_______↓
        ListNode<Integer> head = new ListNode<>(1);
        head.next = new ListNode<>(2);
        ListNode<Integer> node = new ListNode<>(3);
        head.next.next = node;
        node.next = new ListNode<>(4);
        node.next.next = new ListNode<>(5);
        node.next.next.next = new ListNode<>(6);
        node.next.next.next.next = node;
        ListNode<Integer> meet = meetingNode(head);
        if (meet == null) {
            System.out.println("没有环");
        } else {
            System.out.println("环的入口值:" + meet.val);
        }
    }
}
