class Node {
    int value;
    Node left, right;

    public Node(int item) {
        value = item;
        left = right = null;
    }
}


public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // 1. Insertion: Adds a value while maintaining BST properties
    void insert(int key) {
        root = insertRecursive(root, key);
    }

    private Node insertRecursive(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.value) {
            root.left = insertRecursive(root.left, key);
        } else if (key > root.value) {
            root.right = insertRecursive(root.right, key);
        }
        return root;
    }

    // 2. Deletion: Handles three cases: no child, one child, or two children
    void delete(int key) {
        root = deleteRecursive(root, key);
    }

    private Node deleteRecursive(Node root, int key) {
        if (root == null) return root;

        if (key < root.value) {
            root.left = deleteRecursive(root.left, key);
        } else if (key > root.value) {
            root.right = deleteRecursive(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Node with two children: Get inorder successor (smallest in right subtree)
            root.value = minValue(root.right);
            // Delete the inorder successor
            root.right = deleteRecursive(root.right, root.value);
        }
        return root;
    }

    // 2. Searching: Checks if a key exists in O(log n) average time
    boolean search(int key) {
        return searchRecursive(root, key) != null;
    }

    private Node searchRecursive(Node root, int key) {
        if (root == null || root.value == key) {
            return root;
        }
        if (root.value > key) {
            return searchRecursive(root.left, key);
        }
        return searchRecursive(root.right, key);
    }

    

    private int minValue(Node root) {
        int minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }
        return minv;
    }

    // Helper: Inorder traversal to print the tree (should be sorted)
    void inorder() {
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(Node root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.value + " ");
            inorderRecursive(root.right);
        }
    }

    //pre-order and post-order
    //graphs
    // Sample demonstration
    //jalkeres algorithm
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert values
        bst.insert(50);
        bst.insert(20);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Inorder traversal after insertions:");
        bst.inorder();  // Expected: 20 30 40 50 60 70 80

        // Delete a leaf node (20)
        System.out.println("\nDeleting 20 (leaf node):");
        bst.delete(20);
        bst.inorder();  // Expected: 30 40 50 60 70 80

        // Delete a node with one child (70 → has only left child 60)
        System.out.println("\nDeleting 70 (one child):");
        bst.delete(70);
        bst.inorder();  // Expected: 30 40 50 60 80

        // Delete a node with two children (50)
        System.out.println("\nDeleting 50 (two children):");
        bst.delete(50);
        bst.inorder();  // Expected: 30 40 60 80 (60 becomes new root, successor of 50 was 60)
    }
}


// Root: 50
//     L--- 30
//         L--- 20
//         R--- 40
//     R--- 70
//         L--- 60
//         R--- 80
