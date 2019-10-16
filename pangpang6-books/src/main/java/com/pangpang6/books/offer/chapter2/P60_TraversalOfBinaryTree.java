package com.pangpang6.books.offer.chapter2;


import com.google.common.collect.Lists;
import com.pangpang6.books.offer.structure.TreeNode;

import java.util.*;

/**
 * 二叉树的遍历：
 * 前序（递归，非递归），中序（递归，非递归），后序（递归，非递归），层序
 */
public class P60_TraversalOfBinaryTree {
    //前序遍历递归版
    public static List<Integer> preorderRecursively(TreeNode<Integer> node) {
        List<Integer> list = Lists.newArrayList();
        if (node == null) {
            return list;
        }
        list.add(node.val);
        list.addAll(preorderRecursively(node.left));
        list.addAll(preorderRecursively(node.right));
        return list;
    }

    //中序遍历递归版
    public static List<Integer> inorderRecursively(TreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null)
            return list;
        list.addAll(inorderRecursively(node.left));
        list.add(node.val);
        list.addAll(inorderRecursively(node.right));
        return list;
    }

    //后序遍历递归版
    public static List<Integer> postorderRecursively(TreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null)
            return list;
        list.addAll(postorderRecursively(node.left));
        list.addAll(postorderRecursively(node.right));
        list.add(node.val);
        return list;
    }

    //前序遍历循环版
    //关键点就是 入栈当前节点 左遍历 出站节点取右节点
    public static List<Integer> preorderIteratively(TreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        Stack<TreeNode<Integer>> stack = new Stack<>();
        List<Integer> list = Lists.newArrayList();
        TreeNode<Integer> cur = node;

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                //获取当前节点值
                list.add(cur.val);
                //当前节点入栈
                stack.push(cur);
                //左遍历
                cur = cur.left;
            } else {
                cur = stack.pop().right;
            }
        }
        return list;
    }

    //中序遍历非递归版
    public static List<Integer> inorderIteratively(TreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = node;
        List<Integer> list = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                //当前节点入栈
                stack.push(cur);
                cur = cur.left;
            } else {
                list.add(stack.peek().val);
                cur = stack.pop().right;
            }
        }
        return list;
    }

    //后续遍历非递归版
    public static List<Integer> postorderIteratively(TreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        //prevVisted用于区分是从左子树还是右子树返回的
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = node;

        //防止右边节点 被重复遍历
        TreeNode<Integer> prevVisited = null;

        List<Integer> list = Lists.newArrayList();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                //当前节点入栈
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.peek().right;
                if (cur != null && cur != prevVisited) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    prevVisited = stack.pop();
                    list.add(prevVisited.val);
                    cur = null;
                }
            }
        }
        return list;
    }

    //层序遍历
    //考察队列的使用
    public static List<Integer> levelorder(TreeNode<Integer> node) {
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        TreeNode<Integer> temp;
        if (node == null) {
            return list;
        }
        //首先是添加根节点
        queue.add(node);

        while (!queue.isEmpty()) {
            temp = queue.poll();
            list.add(temp.val);
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }
        return list;
    }

    public static void main(String[] args) {
        //            1
        //              \
        //               2
        //              /
        //             3
        //pre->123  in->132   post->321  level->123
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.right = new TreeNode<Integer>(2);
        root.right.left = new TreeNode<Integer>(3);
        List<Integer> list_preorderRecursively = preorderRecursively(root);
        System.out.print("preorderRecursively: " + '\t');
        System.out.println(list_preorderRecursively.toString());

        List<Integer> list_inorderRecursively = inorderRecursively(root);
        System.out.print("inorderRecursively: " + '\t');
        System.out.println(list_inorderRecursively.toString());

        List<Integer> list_postorderRecursively = postorderRecursively(root);
        System.out.print("postorderRecursively: " + '\t');
        System.out.println(list_postorderRecursively.toString());
        System.out.println();


        List<Integer> list_preorderIteratively = preorderIteratively(root);
        System.out.print("preorderIteratively: " + '\t');
        System.out.println(list_preorderIteratively.toString());

        List<Integer> list_inorderIteratively = inorderIteratively(root);
        System.out.print("inorderIteratively: " + '\t');
        System.out.println(list_inorderIteratively.toString());

        List<Integer> list_postorderIteratively = postorderIteratively(root);
        System.out.print("postorderIteratively: " + '\t');
        System.out.println(list_postorderIteratively.toString());
        System.out.println();

        List<Integer> list_levelorder = levelorder(root);
        System.out.print("levelorder: " + '\t');
        System.out.println(list_levelorder.toString());
    }
}
