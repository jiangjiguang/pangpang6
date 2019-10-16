package com.pangpang6.books.offer.chapter3;


import com.pangpang6.books.offer.structure.ListNode;

/**
 * o(1)时间删除链表的节点
 */
public class P119_DeleteNodeInList {
    public static ListNode<Integer> deleteNode(ListNode<Integer> head, ListNode<Integer> node) {
        if (node == head) {
            //删除的是头结点
            return head.next;
        } else if (node.next != null) {
            //删除中间节点
            node.val = node.next.val;
            node.next = node.next.next;
            return head;
        } else {
            //删除尾节点
            ListNode<Integer> temp = head;
            //判断的是下一个节点是否是被删除节点
            //考虑删除节点不再连表之内，那么就不会删除节点
            while (temp.next != node) {
                temp = temp.next;
            }
            temp.next = null;
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        head.next = node2;
        node2.next = node3;
        System.out.println(head);
        head = deleteNode(head, node3);
        System.out.println(head);
        head = deleteNode(head, head);
        System.out.println(head);
    }
}
