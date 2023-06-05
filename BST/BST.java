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

    /**
     * Finds a node with the given value in the binary search tree.
     *
     * @param val The value to search for.
     * @return The node with the given value, or null if not found.
     */
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

    /**
     * Inserts a new node with the given value into the binary search tree.
     *
     * @param val The value to insert.
     */
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

    /**
     * Performs the bubble-up operation starting from the given node and value.
     *
     * @param node The starting node.
     * @param val  The value to bubble up.
     */
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

    /**
     * Removes a node with the given value from the binary search tree.
     *
     * @param val The value to remove.
     */
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

    /**
     * Performs the bubble-down operation starting from the given node and child.
     *
     * @param node  The starting node.
     * @param child The child node to bubble down.
     */
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

    /**
     * Swaps the values between the parent and child nodes.
     *
     * @param parent The parent node.
     * @param child  The child node.
     */
    private void swap(Node parent, Node child) {
        if (parent == null || child == null) {
            return;
        }

        int temp = parent.val;
        parent.val = child.val;
        child.val = temp;
    }

    /**
     * Finds the minimum value in the binary search tree rooted at the given node.
     *
     * @param node The root node of the tree.
     * @return The minimum value in the tree.
     */
    private int findMinimumValue(Node node) {
        int minVal = node.val;
        while (node.left != null) {
            minVal = node.left.val;
            node = node.left;
        }
        return minVal;
    }
}
