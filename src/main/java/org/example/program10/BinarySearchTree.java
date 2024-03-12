package org.example.program10;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Binary Search Tree (BST) that stores data of a generic type.
 * The type must extend Comparable, as the BST uses the compareTo method to order the nodes.
 * The BST supports standard operations such as add, remove, contains, and size.
 * It also supports tree traversals (in-order, pre-order, post-order), and other operations like getting the depth of the tree.
 *
 * @param <ItemType> the type of the data to be stored in the BST
 */
public class BinarySearchTree<ItemType extends Comparable<ItemType>> {

    /**
     * The root of the binary search tree.
     */
    private Node root;

    /**
     * Checks if the binary tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Gets the root of the binary search tree.
     *
     * @return the root of the tree
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Adds a new item to the tree.
     *
     * @param newItem The value to add to the tree.
     * @return true if the item was added successfully
     */
    public boolean add(ItemType newItem) {
        root = add(newItem, root);
        return true;
    }

    /**
     * The add method adds a value to the search tree.
     *
     * @param bstree The root of the binary search tree.
     * @return The root of the resulting binary search tree.
     * @newItem the value to add.
     */
    private Node add(ItemType newData, Node bstree) {
        if (bstree == null)
            return new Node(newData);

        if (newData.compareTo(bstree.value) < 0) {
            bstree.left = add(newData, bstree.left);
        } else {
            bstree.right = add(newData, bstree.right);
        }
        return bstree;
    }

    /**
     * Removes a value from the binary search tree.
     *
     * @param removalTarget The value to remove.
     * @return true if removalTarget was removed, false if removalTarget not found.
     */
    public boolean remove(ItemType removalTarget) {
        RemovalResult result = remove(root, removalTarget);
        if (result == null)
            return false;
        else {
            root = result.tree;
            return true;
        }
    }

    /**
     * The remove method removes a value from the binary search tree.
     *
     * @param deleteValue The value to remove.
     * @param bTree       The binary search tree.
     * @return The result of removing the value.
     */
    private RemovalResult remove(Node bTree, ItemType deleteValue) {
        if (bTree == null)
            return null;

        if (deleteValue.compareTo(bTree.value) < 0) {
            RemovalResult result = remove(bTree.left, deleteValue);
            if (result == null)
                return null;
            bTree.left = result.tree;
            result.tree = bTree;
            return result;
        }

        if (deleteValue.compareTo(bTree.value) > 0) {
            RemovalResult result = remove(bTree.right, deleteValue);
            if (result == null)
                return null;
            bTree.right = result.tree;
            result.tree = bTree;
            return result;
        }

        if (bTree.right == null && bTree.left == null)
            return new RemovalResult(bTree, null);

        if (bTree.right != null && bTree.left != null) {
            RemovalResult remResult = removeLargest(bTree.left);
            Node newRoot = remResult.node;
            newRoot.left = remResult.tree;
            newRoot.right = bTree.right;

            bTree.left = null;
            bTree.right = null;
            return new RemovalResult(bTree, newRoot);
        }

        Node tree;
        if (bTree.left != null)
            tree = bTree.left;
        else
            tree = bTree.right;

        bTree.left = null;
        bTree.right = null;
        return new RemovalResult(bTree, tree);
    }

    /**
     * The removeLargest method removes the largest node from a binary search
     * tree.
     *
     * @param bTree The binary search tree.
     * @return The result of removing the largest node.
     */
    private RemovalResult removeLargest(Node bTree) {
        if (bTree == null)
            return null;

        if (bTree.right == null) {
            // Root is the largest node
            Node tree = bTree.left;
            bTree.left = null;
            return new RemovalResult(bTree, tree);
        } else {
            // Remove the largest node from the right subtree.
            RemovalResult remResult = removeLargest(bTree.right);
            bTree.right = remResult.tree;
            remResult.tree = bTree;
            return remResult;
        }
    }

    /**
     * Checks to see if a value is in the binary tree.
     *
     * @param searchTarget The value to check for.
     * @return true if searchTarget is in the tree, false otherwise.
     */
    public boolean contains(ItemType searchTarget) {
        return contains(searchTarget, root);
    }

    /**
     * The contains method checks to see if a value is in the binary tree.
     *
     * @param searchItem The value to check for.
     * @param bstree     The binary search tree.
     * @return true if searchItem is in the tree, false otherwise.
     */
    private boolean contains(ItemType searchItem, Node bstree) {
        if (bstree == null)
            return false;

        if (searchItem.compareTo(bstree.value) == 0)
            return true;
        if (searchItem.compareTo(bstree.value) < 0)
            return contains(searchItem, bstree.left);
        else
            return contains(searchItem, bstree.right);
    }

    /**
     * Searches for a value in the binary tree.
     *
     * @param searchTarget The value to search for.
     * @return The value if found, null otherwise.
     */
    public ItemType search(ItemType searchTarget) {
        Node resultNode = search(root, searchTarget);
        return resultNode != null ? resultNode.value : null;
    }

    /**
     * The search method searches for a value in the binary tree.
     *
     * @param searchTarget The value to search for.
     * @param bstree       The binary search tree.
     * @return The node containing the value if found, null otherwise.
     */
    private Node search(Node bstree, ItemType searchTarget) {
        if (bstree == null) {
            return null;
        }

        if (searchTarget.compareTo(bstree.value) == 0) {
            return bstree;
        } else if (searchTarget.compareTo(bstree.value) < 0) {
            return search(bstree.left, searchTarget);
        } else {
            return search(bstree.right, searchTarget);
        }
    }

    /**
     * Counts the number of elements in the binary search tree.
     *
     * @return The number of nodes in the tree.
     */
    public int size() {
        return count(root);
    }

    /**
     * The count method counts the number of nodes in the binary search tree.
     *
     * @param tree The binary search tree.
     * @return The number of nodes in the tree.
     */
    int count(Node tree) {
        if (tree == null)
            return 0;
        else
            return count(tree.left) + count(tree.right) + 1;
    }

    /**
     * Gets the depth of the tree.
     *
     * @return The depth of the tree.
     */
    public int treeDepth() {
        return getDepth(root) - 1;
    }

    /**
     * The getDepth method gets the depth of a binary tree.
     *
     * @param tree The binary tree.
     * @return The depth of the tree.
     */
    private int getDepth(Node tree) {
        if (tree == null)
            return 0;
        else {
            int leftDepth = getDepth(tree.left);
            int rightDepth = getDepth(tree.right);

            if (leftDepth > rightDepth)
                return leftDepth + 1;
            else
                return rightDepth + 1;
        }
    }

    /**
     * Performs an in-order traversal of the tree.
     */
    public void traverseInOrder() {
        System.out.println("IN-ORDER");

        inorder(root);

        System.out.println("\n\n");
    }

    /**
     * Performs an in-order traversal of the tree.
     */
    public void inorder(Node btree) {
        if (btree != null) {
            inorder(btree.left);
            System.out.print(btree.value + " ");
            inorder(btree.right);
        }
    }

    /**
     * Performs a pre-order traversal of the tree.
     */
    public List<ItemType> inorderTraversal() {
        List<ItemType> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    /**
     * Performs an in-order traversal of the tree.
     */
    private void inorder(Node btree, List<ItemType> result) {
        if (btree != null) {
            inorder(btree.left, result);
            result.add(btree.value);
            inorder(btree.right, result);
        }
    }

    /**
     * Performs a pre-order traversal of the tree.
     */
    public void traversePreOrder() {
        System.out.println("PRE-ORDER");

        preorder(root);

        System.out.println("\n\n");
    }

    /**
     * Performs a pre-order traversal of the tree.
     */
    private void preorder(Node btree) {
        if (btree != null) {
            System.out.print(btree.value + " ");
            preorder(btree.left);
            preorder(btree.right);
        }
    }

    /**
     * Performs a post-order traversal of the tree.
     */
    public void traversePostOrder() {
        System.out.println("POST-ORDER");

        postorder(root);

        System.out.println("\n\n");
    }

    /**
     * Performs a post-order traversal of the tree.
     */
    private void postorder(Node btree) {
        if (btree != null) {
            postorder(btree.left);
            postorder(btree.right);
            System.out.print(btree.value + " ");
        }
    }

    /**
     * This class represents the result of removing a node from a binary tree.
     * It contains the removed node and the remaining tree after the removal.
     */
    private class RemovalResult {
        /**
         * The removed node.
         */
        Node node;
        /**
         * The remaining tree after the removal.
         */
        Node tree;

        RemovalResult(Node node, Node tree) {
            this.node = node;
            this.tree = tree;
        }
    }

    /**
     * This class represents a node in the binary search tree.
     * Each node contains a value, and references to its left and right children.
     */
    public class Node {
        /**
         * The value of the node.
         */
        ItemType value;

        /**
         * The left child of the node.
         */
        Node left, right;

        /**
         * Constructor for the Node class.
         * Initializes the node with the given value and null children.
         *
         * @param val the value of the node
         */
        Node(ItemType val) {
            value = val;
            left = null;
            right = null;
        }

        /**
         * Constructor for the Node class.
         * Initializes the node with the given value and children.
         *
         * @param val        the value of the node
         * @param leftChild  the left child of the node
         * @param rightChild the right child of the node
         */
        Node(ItemType val, Node leftChild, Node rightChild) {
            value = val;
            left = leftChild;
            right = rightChild;
        }
    }

}