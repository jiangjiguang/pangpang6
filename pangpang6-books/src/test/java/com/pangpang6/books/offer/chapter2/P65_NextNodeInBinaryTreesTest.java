package com.pangpang6.books.offer.chapter2;

import com.pangpang6.books.offer.structure.TreeNode;
import org.junit.Before;
import org.junit.Test;

public class P65_NextNodeInBinaryTreesTest {
    TreeNode<Integer> root = new TreeNode<>(10);

    @Before
    public void before() {
        root.left = new TreeNode<>(6);
        root.right = new TreeNode<>(14);
        root.left.father = root;
        root.right.father = root;

        root.left.right = new TreeNode<>(8);
        root.left.left = new TreeNode<>(4);
        root.left.right.father = root.left;
        root.left.left.father = root.left;

        root.right.left = new TreeNode<>(12);
        root.right.right = new TreeNode<>(16);

        root.right.left.father = root.right;
        root.right.right.father = root.right;
    }

    @Test
    public void test() {
        //1.若该节点有右子树，那么就是右子树的最左节点
        TreeNode queryNode = root;
        System.out.println(queryNode);
        TreeNode treeNode = P65_NextNodeInBinaryTrees.getNext(queryNode);
        System.out.println(treeNode);

        //2.没有又子树，若节点是它父节点的左节点，那么就是父节点
        TreeNode queryNode2 = root.left.left;
        System.out.println(queryNode2);
        TreeNode treeNode2 = P65_NextNodeInBinaryTrees.getNext(queryNode2);
        System.out.println(treeNode2);

        //3.没有又子树，若节点是它父节点的右节点，那么沿着父节点往上找，直到该父节点是其父节点的左节点
        //TreeNode queryNode3 = root.left.right;
        TreeNode queryNode3 = root.right.right;
        System.out.println(queryNode3);
        TreeNode treeNode3 = P65_NextNodeInBinaryTrees.getNext(queryNode3);
        System.out.println(treeNode3);
    }
}
