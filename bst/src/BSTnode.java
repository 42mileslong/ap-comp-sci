import java.util.ArrayList;

/**
 * Binary Search Tree Node
 * Code written by Rafi Long
 *
 * See code for specific documentation
 * Assignment fulfilling http://paleyontology.com/AP_CS/BST.html requirements
 *
 * This class has data that extends comparable, because in a binary
 *   search tree the left child is smaller than the parent,
 *   while the right is larger.
 */
public class BSTnode<T extends Comparable> {
    /** The node's data */
    private T datum;

    /** The left child, which is smaller than the parent */
    private BSTnode<T> left;
    /** The right child, which is larger than the parent */
    private BSTnode<T> right;

    /**
     * A constructor for a node
     * @param datum the data for the node
     */
    public BSTnode(T datum) {
        this.datum = datum;
        this.left = null;
        this.right = null;
    }

    /**
     * Returns the node's datum
     * @return the node's datum
     */
    public T getDatum() {
        return datum;
    }

    /**
     * Returns the left node
     * @return the left node
     */
    public BSTnode<T> getLeft() {
        return left;
    }

    /**
     * Returns the right node
     * @return the right node
     */
    public BSTnode<T> getRight() {
        return right;
    }

    /**
     * Returns whether the node is a leaf or not
     * The node is a leaf if it has no children, or in this case if
     *   the children are both null
     *
     * @return true if the node is a leaf
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Inserts the datum, keeping the tree a binary search tree.
     * This function compares the datum to be added to the current nodes datum,
     *   and then inserts it into the left side if it is smaller than the current node
     *   or to the right if it is larger. This is not the best way to insert a node, as it
     *   always requires at least O(log n) time, as the datum will be put at the bottom even
     *   if it could be inserted in the middle. However, the end result will always be a binary
     *   search tree.
     * @param datum the datum to be inserted
     */
    public void insert(T datum) {
        // if the datum is less than the current node than it goes on the right
        if (datum.compareTo(this.datum) < 0) {
            // set left to the datum if left is null
            if (left == null) {
                setLeft(datum);
            // insert it on the left side it is not
            } else {
                left.insert(datum);
            }
        // if the datum is great than the current node than it goes on the right
        } else {
            // set right to the datum if right is null
            if (right == null) {
                setRight(datum);
            // insert it on the right side if it is not
            } else {
                right.insert(datum);
            }
        }
    }

    /**
     * Removes the old left and creates a new object
     * NOTE: this removes all of the left child's children as well. This seems to be
     *   standard procedure, and the assignment did not specify exactly what
     *   this method what supposed to do
     *
     * @param datum the datum to replace left
     */
    public void setLeft(T datum) {
        left = new BSTnode<T>(datum);
    }

    /**
     * Removes the old right and creates a new object
     * NOTE: this removes all of the right child's children as well. This seems to be
     *   standard procedure, and the assignment did not specify exactly what
     *   this method what supposed to do
     *
     * @param datum the datum to replace right
     */
    public void setRight(T datum) {
        right = new BSTnode<T>(datum);
    }

    /**
     * Returns the depth of the tree
     * This works by recursive calling: if it is a leaf the return is one,
     *   and if it is not the return is the max of the left and right plus one.
     *   This means that depth() is called for every node on the tree, all the way down,
     *   and then works its way up, on each step disregarding the side that has less nodes
     *   and passing up the number of nodes on the other side plus one.
     *
     * @return the depth of the tree
     */
    public int depth() {
        if (this.isLeaf()) return 1;
        if (this.left == null) return right.depth();
        if (this.right == null) return left.depth();
        return Math.max(left.depth(), right.depth()) + 1;
    }

    /**
     * Prints the tree in infix notation
     * This means that the tree is printed in order, with values in
     *   ascending order.
     * This is done by recursively calling print on the left subtree, then printing the current node, then
     *   recursively calling print on the right subtree.
     */
    public void printTree() {
        if (this.left != null) this.left.printTree();
        System.out.println(this);
        if (this.right != null) this.right.printTree();
    }

    /**
     * Calls datum.toString
     * In the assignment it was not specified what toString was supposed to do.
     *   Because there is already a method printTree, I thought the highest
     *   probably use case for this method would be printing the data in the node.
     *   This method is in fact implicitly called in printTree.
     *
     * @return datum.toString
     */
    public String toString() {
        return datum.toString();
    }

    /**
     * NOTE: This is not the printTree method you are looking for. This prints the tree in graphic notation, but
     *   has errors when there are holes in the tree
     *
     * Prints the binary search tree from the current node down.
     * This is done by having an ArrayList with the current levels nodes. It then iterates
     *   for the depth of the tree, at each level iterating across on the nodes at that level,
     *   printing the value of each node and adding it's children to nextLevelNodes. At the end
     *   of the iteration through the levels nodes it then prints a new line and sets the current
     *   levels children to levelNodes.
     */
    public void printGraphicTree() {
        // the current levels nodes
        ArrayList<BSTnode<T>> levelNodes = new ArrayList<BSTnode<T>>();
        // the children of this levels nodes
        ArrayList<BSTnode<T>> nextLevelNodes = new ArrayList<BSTnode<T>>();

        // adding the root to the first occurage of levelNodes
        levelNodes.add(this);

        // iterating through the depth of the tree
        for (int i = 0; i < depth(); i++) {
            // iterating through the elements of the current level's nodes
            for (BSTnode<T> node : levelNodes) {
                // printing the current nodes values
                if (node == null) {
                    System.out.print("n ");
                } else {
                    System.out.print(node + " ");
                }

                // adding the children to the next level's node list
                if (node != null) {
                    nextLevelNodes.add(node.getLeft());
                    nextLevelNodes.add(node.getRight());
                }
            }

            // new line
            System.out.println();
            // clearing the current nodes
            levelNodes.clear();
            // setting the current level's nodes to the current level's nodes' children
            levelNodes.addAll(nextLevelNodes);
            // clearing the list of children
            nextLevelNodes.clear();
        }
    }
}
