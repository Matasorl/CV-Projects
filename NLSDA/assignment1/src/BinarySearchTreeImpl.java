package assignment;

import java.util.LinkedList;

public class BinarySearchTreeImpl<T> {
    protected static class Node<T> {
        public Node(int key, T value)
        {
            this.key = key;
            this.value = value;
        }
        public int key;
        public T value;
        public Node<T> parent = null;
        public Node<T> left = null;
        public Node<T> right = null;
    }
    protected Node<T> root = null;

    protected void insert(Node<T> x, int key, T value) {
        // If the root is null set node to root
        if (root == null) {
            root = new Node<>(key, value);
            return;
        }

        // Search tree while node is not null
        while (x != null) {
            // If key of the current node is equal to the given node x's key set value to x.value
            if (key == x.key) {
                x.value = value;
                return;
            }
            // Else if the key of the current node is smaller than the key of the x node
            else if (key < x.key) {
                // If the left child of the x node is null set that to the x node
                if (x.left == null) {
                    x.left = new Node<>(key, value);
                    x.left.parent = x;
                    return;
                }
                // Go to the left child node
                x = x.left;
            }
            // Else if the key of the current node is bigger than the key of the x node
            else {
                // If the right child of the x node is null set that to be the x node
                if (x.right == null) {
                    x.right = new Node<>(key, value);
                    x.right.parent = x;
                    return;
                }
                // Go to the right child node
                x = x.right;
            }
        }
    }

    protected LinkedList<T> inorderTreeWalk(Node<T> x) {
        // Initialize result list
        LinkedList<T> resultLinkedList = new LinkedList<>();
        // If the node is not null
        if (x != null) {
            // Recursively call the left child of the x node and add the result
            resultLinkedList.addAll(inorderTreeWalk(x.left));
            // Add the value of the x node to the list
            resultLinkedList.add(x.value);
            // Recursively call the right child of the x node and add the result
            resultLinkedList.addAll(inorderTreeWalk(x.right));
        }
        return resultLinkedList;
    }

    protected Node<T> search(Node<T> x, int key) {
        // If the x node is null or the key of the current node is equal to the key of the x node return x
        if (x == null || key == x.key) {
            return x;
        }

        // If the current node's key is smaller than recursively search the left subtree
        if (key < x.key) {
            return search(x.left, key);
        }

        // Else recursively search the right subtree
        else {
            return search(x.right, key);
        }
    }

    protected int depth(Node<T> x) {
        // If the node given is null return -1
        if(x == null) {
            return -1;
        }

        // Get depth of the left subtree using recursion of the left child of the x node
        int leftDepth = depth(x.left);
        //Get the depth of the right subtree using recursion of the right child of the x node
        int rightDepth = depth(x.right);

        // If the left depth is greater than the right depth return the left depth + 1
        if (leftDepth > rightDepth) {
            return leftDepth + 1;
        }
        // If the right depth is greater than the left depth return the right depth + 1
        else {
            return rightDepth + 1;
        }
    }

    protected Node<T> minimum(Node<T> x) {
        // While the left child of the x node is not null go down the left subtree
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    protected Node<T> maximum(Node<T> x) {
        // While the right child of the x node is not null go down the right subtree
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    protected Node<T> successor(Node<T> x) {
        // If the right child of the x node is not null find the minimum of the right subtree
        if (x.right != null) {
            return minimum(x.right);
        }
        // Create node y that is the parent of x
        Node<T> y = x.parent;

        // While y in not null and the x node is the right child of the y node
        while (y != null && x == y.right) {
            // Set the x node to y
            x = y;
            // Set y to the parent of y
            y = y.parent;
        }

        return y;

    }

    protected Node<T> predecessor(Node<T> x) {
        // If the left child of the x node is not null find the maximum of the left subtree
        if (x.left != null) {
            return maximum(x.left);
        }

        // Create node y that is the parent of x
        Node<T> y = x.parent;

        // While y is not null and x node is the left child of the y node
        while (y != null && x == y.left) {
            // Set the x node to y
            x = y;
            // Set y to the parent of y
            y = y.parent;
        }

        return y;
    }

    // Helper method to handle moving of nodes in delete function
    protected void transplant(Node<T> u, Node<T> v) {
        // If parent of u node is null (meaning u is the root), set root to v
        if (u.parent == null) {
            root = v;
        }

        // If parent of u node is not null
        else {
            // If the u node is the left child of its parent node set the left child of the parent node of the u node to the v node
            if (u == u.parent.left) {
                u.parent.left = v;
            }
            // If the u node is the right child of its parent node set the right child of the parent node of the u node to the v node
            else {
                u.parent.right = v;
            }
        }

        // If the v node is not null set the parent of the v node to the parent of the u node
        if (v != null) {
            v.parent = u.parent;
        }
    }

    protected void delete(Node<T> z) {
        // Case : (1 Null Node) Left child is null thus transplant z with the right child of the z node
        if (z.left == null) {
            transplant(z, z.right);
        }
        // Case : (1 Null Node) Right child is null thus transplant z with the left child of the z node
        else if (z.right == null) {
            transplant(z, z.left);
        }
        // Case : (0 Null Nodes) Has both left and right child thus find successor and replace z
        else {
            // Find the minimum of the right subtree and set it to the y node
            Node<T> y = minimum(z.right);

            // If the y node isn't the right child of the z node
            if (y != z.right) {
                // Transplant y with the right child of the y node
                transplant(y, y.right);
                // Set the right child of the y node to the right child of the z node
                y.right = z.right;
                // If the right child of the y node is not null set y.right.parent to be y
                if (y.right != null) {
                    y.right.parent = y;
                }
            }

            // Attach z's left subtree to y before transplanting
            y.left = z.left;
            if (y.left != null) {
                y.left.parent = y;
            }

            // Transplant z with y
            transplant(z, y);


        }
    }


}
