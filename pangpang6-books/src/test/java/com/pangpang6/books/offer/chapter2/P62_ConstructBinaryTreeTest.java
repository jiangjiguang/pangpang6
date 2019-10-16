package com.pangpang6.books.offer.chapter2;

import com.pangpang6.books.offer.structure.BinaryTreeNode;
import com.pangpang6.books.offer.structure.TreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class P62_ConstructBinaryTreeTest {
    TreeNode<Integer> root = new TreeNode<>(10);

    @Before
    public void before() {
        root.right = new TreeNode<>(14);
        root.left = new TreeNode<>(6);

        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(8);

        root.right.left = new TreeNode<>(12);
        root.right.right = new TreeNode<>(16);

    }

    @Test
    public void constructTest() {
        //前序遍历
        List<Integer> pre = P60_TraversalOfBinaryTree.preorderRecursively(root);
        //中序遍历
        List<Integer> in = P60_TraversalOfBinaryTree.inorderRecursively(root);

        int[] preArr = listToArr(pre);
        int[] inArr = listToArr(in);

        BinaryTreeNode root = b06_重建二叉树.constructBinaryTree(preArr, inArr);
        System.out.println(root);
//有问题代码
//        TreeNode treeNode = P62_ConstructBinaryTree.construct(preArr, inArr);
//        System.out.println(treeNode);
//        System.out.println(inArr);
    }

    private int[] listToArr(List<Integer> list) {
        int[] arr = new int[list.size()];
        int index = 0;
        for (int i : list) {
            arr[index++] = i;
        }
        return arr;
    }
}
