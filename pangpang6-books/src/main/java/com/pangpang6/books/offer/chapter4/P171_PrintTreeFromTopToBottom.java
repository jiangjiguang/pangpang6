package com.pangpang6.books.offer.chapter4;


import com.pangpang6.books.offer.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 从上到下打印二叉树(层序遍历)
 */
public class P171_PrintTreeFromTopToBottom {

    //循环打印
    public static void printFromTopToBottom(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode<Integer> temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp.val);
            System.out.print('\t');
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }
    }

    //递归打印
    public static void printFromTopToBottom2(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val);
        printFromTopToBottom2(root.left);
        printFromTopToBottom2(root.right);

    }

    public static void main(String[] args) {
        //            1
        //          /   \
        //         2     3
        //       /  \   / \
        //      4    5 6   7
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.left = new TreeNode<Integer>(2);
        root.right = new TreeNode<Integer>(3);
        root.left.left = new TreeNode<Integer>(4);
        root.left.right = new TreeNode<Integer>(5);
        root.right.left = new TreeNode<Integer>(6);
        root.right.right = new TreeNode<Integer>(7);
        printFromTopToBottom(root);

        printFromTopToBottom2(root);

    }
}
