package com.pangpang6.books.offer.chapter2;

import com.pangpang6.books.offer.structure.TreeNode;

/**
 * 二叉树的下一个节点
 * (此二叉树节点不仅有两个孩子节点指针，还包括一个父节点指针)
 */
public class P65_NextNodeInBinaryTrees {

    public static TreeNode getNext(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getLeft(node.right);
        }

        if (node.father != null && node == node.father.left) {
            return node.father;
        }

        if (node.father != null && node == node.father.right) {
            TreeNode father = node.father;

            while (true) {
                if (father.father == null) {
                    return null;
                }
                if (father == father.father.left) {
                    break;
                }
                father = father.father;
            }
            return father.father;
        }
        return null;
    }

    public static TreeNode getLeft(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return getLeft(node.left);
    }
}
