public class BST {
    Node root;

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public Node find(int val) {
        return findNode(root, val);
    }

    private Node findNode(Node node, int val) {
        if (node == null || node.val == val) {
            return node;
        }

        if (val < node.val) {
            return findNode(node.left, val);
        } else {
            return findNode(node.right, val);
        }
    }


    public void insert(int val) {
        root = insertNode(root, val);
    }

    private Node insertNode(Node node, int val) {
        if (node == null) {
            return new Node(val);
        }

        if (val < node.val) {
            node.left = insertNode(node.left, val);
        } else if (val > node.val) {
            node.right = insertNode(node.right, val);
        }

        return node;
    }


    private void bubbleUp(Node node, int val) {
        while (node != null && node.val != val) {
            if (val < node.val) {
                swap(node, node.left);
                node = node.left;
            } else {
                swap(node, node.right);
                node = node.right;
            }
        }
    }

    public void remove(int val) {
        root = removeNode(root, val);
    }

    private Node removeNode(Node node, int val) {
        if (node == null) {
            return null;
        }

        if (val < node.val) {
            node.left = removeNode(node.left, val);
            bubbleDown(node, node.left);
        } else if (val > node.val) {
            node.right = removeNode(node.right, val);
            bubbleDown(node, node.right);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.val = findMinimumValue(node.right);
            node.right = removeNode(node.right, node.val);
        }

        return node;
    }

    private void bubbleDown(Node node, Node child) {
        while (child != null) {
            if (child.val < node.val) {
                swap(node, child);
                node = child;
                child = node.left;
            } else {
                swap(node, child);
                node = child;
                child = node.right;
            }
        }
    }

    private void swap(Node parent, Node child) {
        if (parent == null || child == null) {
            return;
        }

        int temp = parent.val;
        parent.val = child.val;
        child.val = temp;
    }

    private int findMinimumValue(Node node) {
        int minVal = node.val;
        while (node.left != null) {
            minVal = node.left.val;
            node = node.left;
        }
        return minVal;
    }
}
