package edu.cscc;
/* prakash Parasuram
    Java 2 Binary Tree
 */
public class BinaryTree {

    private Node root;
    /* constructor and getter and setter */
    /*
        BinaryTree() Construct an empty BinaryTree object.
     */
    public BinaryTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    /*
        int size() Return the size (number of items) in this BinaryTree.
        do in order traversal of tree to get size
     */
    public int size (){
        int size = 0;
        return(traverseTreeForSize(root, size));
    }

    private int traverseTreeForSize(Node nextNode, int size) {
        if (nextNode != null) {
                size++;
                size = traverseTreeForSize (nextNode.getLeftChild(),size);
                size = traverseTreeForSize (nextNode.getRightChild(),size);
        }
        return size;
    }
    /*
        boolean isEmpty() Return true if this BinaryTree has no items. (This is the same as
        the size equal to zero.) Return false if the size is greater than zero.
     */
    public boolean isEmpty(){
        return (root == null);
    }
    /*
        void add(int value) Add the given element, value, to the tree.
     */
    public void add(int value) {
        if (root == null) {
            Node node = new Node(value, null, null);
            root = node;
            return;
        }
        traverseTreeForAdd(root, value);
    }
    private void traverseTreeForAdd(Node nextNode, int value) {
        if (value > nextNode.getData() && nextNode.getRightChild() == null) {
            // insert here
            Node node = new Node(value, null, null);
            nextNode.setRightChild(node);
        } else {
            if (value <= nextNode.getData() && nextNode.getLeftChild() == null) {
                Node node = new Node(value, null, null);
                nextNode.setLeftChild(node);
            } else { // end of if left is empty...
                if (value > nextNode.getData())
                    traverseTreeForAdd(nextNode.getRightChild(), value);
                else
                    traverseTreeForAdd(nextNode.getLeftChild(), value);
            }  //end of inner else
        } // end of else
    }
    /*
        bool exists(int value) Return true if the element exists in the tree, otherwise return false.
     */
    public boolean exists(int value) {
        return(traverseTreeForExists(root, value));
    } // exists ()
    private boolean traverseTreeForExists(Node nextNode, int value) {
        if (nextNode == null)
            return false;
        if (value == nextNode.getData())
            return true;
        if (value > nextNode.getData())
            return (traverseTreeForExists(nextNode.getRightChild(), value));
        else
            return (traverseTreeForExists(nextNode.getLeftChild(), value));
    } // end of traverseTreeForExists
    /*
        Integer max() Return the largest element in the tree.
     */
    public Integer max() {
        Integer max = null;
        if (root == null)
            return max;
        else {
            max = root.getData();
            return traverseTreeForMax(root, max);
        }
    }
    public Integer traverseTreeForMax(Node nextNode, Integer max){
        if (nextNode == null)
                return max;
        else {
            max = nextNode.getData();
            max = traverseTreeForMax(nextNode.getRightChild(), max);
            return max;
        }
    }
    /*
            Integer min() Return the smallest element in the tree.
         */
    public Integer min() {
        Integer min = null;
        if (root == null)
            return min;
        else {
            min = root.getData();
            min = traverseTreeForMin(root, min);
            return min;
        }
    }
    public Integer traverseTreeForMin(Node nextNode, Integer min){
        if (nextNode == null)
            return min;
        else {
            min = nextNode.getData();
            min = traverseTreeForMin(nextNode.getLeftChild(), min);
            return min;
        }

    }

    // ------------------ InOrder traversal-------------------
    protected void inorder(Node theRootNode) {
        if (theRootNode != null) {
            inorder(theRootNode.leftChild);
            theRootNode.show();
            inorder(theRootNode.rightChild);
        }
    }

    /*      Class Node
            We are including an inside class to keep everything in one file because the requirements did not
            clarify, this allows us to keep everything in one file
    */
    public class Node {
        private int data;
        private Node rightChild;
        private Node leftChild;

        public Node(int data, Node rightChild, Node leftChild) {
            this.data = data;
            this.rightChild = rightChild;
            this.leftChild = leftChild;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }
        public void show() {
            //calls the show method of the Integer
            System.out.print(data + " ");
        }
    }

}
