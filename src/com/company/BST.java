package com.company;

public class BST <K extends Comparable<K>, V>{
    private Node root;

    class Node {
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V value) {
        root = putBST(key, value, root);
    }

    public V get(K key) {
        return getBST(key, root);
    }

    public void delete(K key) {
        deleteBST(key, root);
    }

    private Node putBST(K key, V val, Node node) {
        if (node == null) {
            return new Node(key, val);
        }
        int compare = root.key.compareTo(key);

        if (compare < 0) {
            putBST(key, val, node.left);
        }
        else if (compare > 0) {
            putBST(key, val, node.right);
        } else {
            node.val = val;
        }
        return node;
    }

    private void deleteBST(K key, Node node) {
        if (node == null) {
            return;
        }
        int compare = root.key.compareTo(key);
        if (compare < 0) {
            deleteBST(key, node.left);
        }
        else if (compare > 0) {
            deleteBST(key, node.right);
        } else {
            node.val = (V) "";
            node.key = (K) "";
        }
    }

    private V getBST(K key, Node node) {
        if (key == null || node == null) return null;
        int compare = key.compareTo(node.key);
        if (compare < 0) return getBST(key, node.left);
        if (compare > 0) return getBST(key, node.right);
        return node.val;
    }

    public Iterable<K> iterator() {
        return null;
    }
}