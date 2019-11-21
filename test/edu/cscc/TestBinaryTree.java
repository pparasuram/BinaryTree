package edu.cscc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBinaryTree {
    BinaryTree myBinaryTree = new BinaryTree();
    BinTree hisBinaryTree = new BinTree();
    @Test
    public void justTestBinaryTree(){
        // assertEquals(myBinaryTree.exists(20), (20 == hisBinaryTree.searchBST(3) ));
        populateBinaryTrees ();
        myBinaryTree.inorder(myBinaryTree.getRoot());
        hisBinaryTree.inorder(hisBinaryTree.getTheBTRootNode());
        assertEquals(myBinaryTree.exists(20), hisBinaryTree.searchBST(20));
    }
    @Test
    public void justTestBinaryTreeSize(){
        populateBinaryTrees();
        assertEquals(7, myBinaryTree.size());
    }
    @Test
    public void justTestBinaryTreeEmpty(){
        populateBinaryTrees();
        assertEquals(true, myBinaryTree.isEmpty());
    }
    @Test
    public void justTestBinaryTreeMax(){
        populateBinaryTrees();
        assertEquals(99, (long) myBinaryTree.max());
    }
    @Test
    public void justTestBinaryTreeMin(){
        populateBinaryTrees();
        assertEquals(99, (long) myBinaryTree.min());
    }
    @Test
    public void justTestBinaryTreeHeight(){
        populateBinaryTrees();
        printLevelOrder();
        assertEquals(8, (long) getHeight(myBinaryTree));
    }
    public void populateBinaryTrees (){
        myBinaryTree.add(5);
        myBinaryTree.add(6);
        myBinaryTree.add(7);
        myBinaryTree.add(8);
        myBinaryTree.add(4);
        myBinaryTree.add(100);
        myBinaryTree.add(10);
        myBinaryTree.add(20);
        myBinaryTree.add(11);
        myBinaryTree.add(21);
        myBinaryTree.add(12);
        myBinaryTree.add(22);
        myBinaryTree.add(14);
        myBinaryTree.add(24);
        myBinaryTree.add(15);
        myBinaryTree.add(25);
        hisBinaryTree.insertBST(5);
        hisBinaryTree.insertBST(6);
        hisBinaryTree.insertBST(7);
        hisBinaryTree.insertBST(8);
        hisBinaryTree.insertBST(4);
        hisBinaryTree.insertBST(100);
        hisBinaryTree.insertBST(10);
        hisBinaryTree.insertBST(20);
        hisBinaryTree.insertBST(11);
        hisBinaryTree.insertBST(21);
        hisBinaryTree.insertBST(12);
        hisBinaryTree.insertBST(22);
        hisBinaryTree.insertBST(14);
        hisBinaryTree.insertBST(24);
        hisBinaryTree.insertBST(15);
        hisBinaryTree.insertBST(25);
    }
    /* Compute the "height" of a tree -- the number of
    nodes along the longest path from the root node
    down to the farthest leaf node.*/
    public Integer getHeight(BinaryTree myBinaryTree){
        return height(myBinaryTree.getRoot());
    }
    int height(BinaryTree.Node root)
    {
        if (root == null)
            return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.getLeftChild());
            int rheight = height(root.getRightChild());

            /* use the larger one */
            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1);
        }
    }
    /* Print nodes at the given level */
    void printGivenLevel (BinaryTree.Node root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.getData() + " ");
        else if (level > 1)
        {
            printGivenLevel(root.getLeftChild(), level-1);
            printGivenLevel(root.getRightChild(), level-1);
        }
    }
    /* function to print level order traversal of tree*/
    void printLevelOrder()
    {
        int h = height(myBinaryTree.getRoot());
        int i;
        for (i=1; i<=h; i++)
            printGivenLevel(myBinaryTree.getRoot(), i);
    }
    public class BinTree {
        BNode theBTRootNode;

        public BinTree() // constructor
        {
            theBTRootNode = null;
        }

        public BNode getTheBTRootNode() {
            return theBTRootNode;
        }

        // ------------------ Addition of the node to the BST-------------------
        protected BNode insertAB(BNode theRootNode, BNode myNewNode) {
            if (theRootNode == null) {
                theRootNode = myNewNode;
                //checks if the username is smaller than
                //the root object, if smaller appends to the left
            } else if (myNewNode.myInt.compareTo(
                    theRootNode.myInt) < 0) {
                theRootNode.leftBNode = insertAB(theRootNode.leftBNode, myNewNode);
            } else {
                // else if bigger appends to the right
                theRootNode.rightBNode =
                        insertAB(theRootNode.rightBNode, myNewNode);
            }
            return theRootNode;
        }

        public void insertBST(Integer myInt) {
            BNode myIntBTNode = new BNode(myInt);
            //calls insert above
            theBTRootNode = insertAB(theBTRootNode, myIntBTNode);
        }

        // ------------------ InOrder traversal-------------------
        protected void inorder(BNode theRootNode) {
            if (theRootNode != null) {
                inorder(theRootNode.leftBNode);
                theRootNode.show();
                inorder(theRootNode.rightBNode);
            }
        }

        //calls the method to do in order
        public void inorderBST() {
            inorder(theBTRootNode);
        }

        // ----- Search for key name and  returns ref.
        //              to BNode or null if not found--------
        protected BNode search(BNode theRootNode, Integer keyName) {
            //if the root is null returns null
            if (theRootNode == null) {
                return null;
            } else {
                //checks if they are equal
                if (keyName.compareTo(theRootNode.myInt) == 0) {
                    return theRootNode;
                    //checks id the key is smaller than the current
                    //record  if smaller traverses to the left
                } else if (keyName.compareTo(theRootNode.myInt) < 0) {
                    return search(theRootNode.leftBNode, keyName);
                } else {
                    // if bigger traverses to the left
                    return search(theRootNode.rightBNode, keyName);
                }
            }
        }

        //returns null if no result else returns
        //the Integer object matched with the keyName
        public Integer searchBST(Integer keyName) {
            BNode temp = search(theBTRootNode, keyName);
            if (temp == null) {
                //noresults found
                return null;
            } else {
                //result found
                return temp.myInt;
            }
        }
    }
    public class BNode {

        public BNode leftBNode,  rightBNode; // the nodes
        public Integer myInt; //the Integer objext

        public BNode(Integer myInt ) {//constructor
            this.myInt= myInt;
            this.leftBNode = null;
            this.rightBNode = null;
        }

        public void show() {
            //calls the show method of the Integer
            System.out.print(myInt);
        }
    }

}
