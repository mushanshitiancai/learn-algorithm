package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉查找树
 * <p>
 * 参考：《算法（第四版）》250页
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private int N;
        private Node left;
        private Node right;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.value;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;

        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Key min() {
        Node min = min(root);
        return min == null ? null : min.key;
    }

    private Node min(Node node) {
        if (node == null) return null;
        if (node.left == null) return node;
        else return min(node.left);
    }

    public Key max() {
        Node max = max(root);
        return max == null ? null : max.key;
    }

    private Node max(Node node) {
        if (node == null) return null;
        if (node.right == null) return node;
        else return max(node.right);
    }

    public Key floor(Key key) {
        return floor(root, key);
    }

    private Key floor(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return key;
        else if (cmp < 0) return floor(node.left, key);

        Key right = floor(node.right, key);
        if (right == null) return node.key;
        else return right;
    }

    public Key select(int k) {
        return select(root, k);
    }

    private Key select(Node node, int k) {
        if (node == null) return null;
        int size = size(node.left);
        if (size == k) return node.key;
        if (size > k) return select(node.left, k);
        return select(node.right, k - size - 1);
    }

    public void deleteMin() {
        root = deleteMin(root); // 修改操作会涉及到节点替换，root节点要注意
    }

    private Node deleteMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return node.right;  // 要考虑最小节点右子树存在的情况
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node == null) return null;
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node nextNode = min(node.right);
            nextNode.left = node.left;
            nextNode.right = deleteMin(node.right);
            node = nextNode;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Iterable<Key> keys() {
        LinkedList<Key> list = new LinkedList<Key>();
        keys(root, min(), max(), list);
        return list;
    }

    public Iterable<Key> keys(Key l, Key r) {
        LinkedList<Key> list = new LinkedList<Key>();
        keys(root, l, r, list);
        return list;
    }

    private void keys(Node node, Key l, Key r, List<Key> list) {
        if (node == null) return;

        int cmpL = l.compareTo(node.key);
        int cmpR = r.compareTo(node.key);

        if (cmpL < 0) keys(node.left, l, r, list);
        if (cmpL <= 0 && cmpR >= 0) list.add(node.key);
        if (cmpR > 0) keys(node.right, l, r, list);
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    }
}
