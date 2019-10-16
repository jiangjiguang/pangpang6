package com.pangpang6.books.offer.chapter2;

import com.google.common.collect.Maps;
import com.pangpang6.books.offer.structure.BinaryTreeNode;

import java.util.Map;

/**
 * 根据前序和中序序列（不含有重复的数字），构建一棵二叉树
 * <p>
 */
public class b06_重建二叉树 {

    public static BinaryTreeNode constructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length || in.length <= 0) {
            return null;
        }

        //中序遍历的值 map 节点顺讯
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0, size = in.length; i < size; i++) {
            map.put(in[i], i);
        }
        return construct(pre, 0, pre.length - 1, 0, map);
    }


    public static BinaryTreeNode construct(int[] preArr, int preStart, int preEnd, int inStart, Map<Integer, Integer> inMap) {
        //开始位置大于结束位置，说明已经没有需要处理的元素了
        if (preStart > preEnd) {
            return null;
        }
        //取前序遍历的第一个数字，就是当前的根结点
        int rootValue = preArr[preStart];
        // 在中序遍历的数组中找根结点的位置
        int inRootIndex = inMap.get(rootValue);
        //创建当前根节点
        BinaryTreeNode head = new BinaryTreeNode(rootValue);
        //递归：
        //左边开始的节点=原开始节点+1
        int leftStart = preStart + 1;
        //左边结束的节点=原开始节点 + 中序根节点-中序开始节点
        int leftEnd = preStart + inRootIndex - inStart;

        //右边开始节点=原开始节点 + 1 + 中序根节点-中序开始节点
        int rightStart = preStart + 1 + inRootIndex - inStart;
        //右边结束节点=原结束节点
        int rightEnd = preEnd;

        head.left = construct(preArr, leftStart, leftEnd, inStart, inMap);
        head.right = construct(preArr, rightStart, rightEnd, inRootIndex + 1, inMap);
        return head;
    }

    public static void main(String[] args) {
        test1();
        System.out.println();
        test2();
        System.out.println();
        test3();
        System.out.println();
        test4();
        System.out.println();
        test5();
        System.out.println();
        test6();
        System.out.println();
//        test7();
    }


    // 普通二叉树  
    //              1  
    //           /     \  
    //          2       3  
    //         /       / \  
    //        4       5   6  
    //         \         /  
    //          7       8  
    private static void test1() {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = constructBinaryTree(pre, in);
        System.out.println(root);
    }

    // 所有结点都没有右子结点
    //            1  
    //           /  
    //          2  
    //         /  
    //        3  
    //       /  
    //      4  
    //     /  
    //    5  
    private static void test2() {
        int[] pre = {1, 2, 3, 4, 5};
        int[] in = {5, 4, 3, 2, 1};
        BinaryTreeNode root = constructBinaryTree(pre, in);
    }

    // 所有结点都没有左子结点
    //            1  
    //             \  
    //              2  
    //               \  
    //                3  
    //                 \  
    //                  4  
    //                   \  
    //                    5  
    private static void test3() {
        int[] pre = {1, 2, 3, 4, 5};
        int[] in = {1, 2, 3, 4, 5};
        BinaryTreeNode root = constructBinaryTree(pre, in);
    }

    // 树中只有一个结点
    private static void test4() {
        int[] pre = {1};
        int[] in = {1};
        BinaryTreeNode root = constructBinaryTree(pre, in);
    }

    // 完全二叉树
    //              1  
    //           /     \  
    //          2       3  
    //         / \     / \  
    //        4   5   6   7  
    private static void test5() {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};
        BinaryTreeNode root = constructBinaryTree(pre, in);
    }

    // 输入空指针
    private static void test6() {
        constructBinaryTree(null, null);
    }

    // 输入的两个序列不匹配
    private static void test7() {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 8, 1, 6, 3, 7};
        BinaryTreeNode root = constructBinaryTree(pre, in);
    }

}
