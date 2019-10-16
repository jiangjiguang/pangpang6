package com.pangpang6.books.offer.chapter2;

import com.pangpang6.books.offer.structure.TreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class P60_TraversalOfBinaryTreeTest {

    TreeNode<Integer> root = new TreeNode<>(10);

    @Before
    public void before() {
        root.left = new TreeNode<>(6);
        root.right = new TreeNode<>(14);

        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(8);

        root.right.left = new TreeNode<>(12);
        root.right.right = new TreeNode<>(16);

    }


    //后续遍历 递归版
    @Test
    public void postorderRecursivelyTest() {
        System.out.println(P60_TraversalOfBinaryTree.postorderRecursively(root));
    }

    //后续遍历 循环版
    @Test
    public void postorderIterativelyTest() {
        System.out.println(P60_TraversalOfBinaryTree.postorderIteratively(root));

    }

    @Test
    public void levelorderTest() {
        System.out.println(P60_TraversalOfBinaryTree.levelorder(root));

    }


    //前序遍历 递归版
    @Test
    public void preorderRecursivelyTest() {
        List<Integer> result = P60_TraversalOfBinaryTree.preorderRecursively(root);
        System.out.println(result);
    }

    //前序遍历 循环版
    @Test
    public void preorderIterativelyTest() {
        List<Integer> result = P60_TraversalOfBinaryTree.preorderIteratively(root);
        System.out.println(result);
    }
}
