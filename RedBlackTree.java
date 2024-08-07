/*
 * This is the class to implement Red-Black Tree
 * 
 */


public class RedBlackTree {
    
    enum Color{
        RED, BLACK;
    }

    private Node root;

    private static class Node {
        
        int key;
        Object value;
        Node left;
        Node right;
        Node parent; // parent node
        Color color = Color.RED; // RED or BLACK   

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        // check if current nod is left child
        boolean isLeftChild(){
            return parent != null && parent.left == this;
        }

        // found uncle node
        Node uncle(){
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        // found brother node
        Node sibling(){
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    // check red and black node
    boolean isRed(Node node){
        // red node can't be null
        return node != null && node.color == Color.RED;
    }

    boolean isBlack(Node node){
        // not red then it's black
        return !isRed(node);
    }

    // right rotate
    private void rightRotate(Node node){
        Node parent = node.parent;
        Node newRoot = node.left;
        Node temp = newRoot.right;
        if (temp != null) {
            temp.parent = node;
        }
        // handle parent node when rotate
        newRoot.right = node;
        newRoot.parent = parent;
        node.left = temp;
        node.parent = newRoot;
        if (parent == null) {
            // set new root if no parent
            root = newRoot;
        } else if (parent.left == node) {
            parent.left = newRoot;
        } else {
            parent.right = newRoot;
        }
    }

        // left rotate
    private void leftRotate(Node node){
        Node parent = node.parent;
        Node newRoot = node.right;
        Node temp = newRoot.left;
        if (temp != null) {
            temp.parent = node;
        }
        // handle parent node when rotate
        newRoot.left = node;
        newRoot.parent = parent;
        node.right = temp;
        node.parent = newRoot;
        if (parent == null) {
            // set new root if no parent
            root = newRoot;
        } else if (parent.right == node) {
            parent.right = newRoot;
        } else {
            parent.left = newRoot;
        }
    }
    
    // found empty space then put node into the tree
    public void put(int key, Object value){
        // set pointer to loop whole tree
        Node p = root;
        Node parent = null; // parent is null at beginning
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                p.value = value; // update value when found position
                return;
            }
        }
        Node insertedNode = new Node(key, value);
        if (parent == null) {
            root = insertedNode;
        } else if (key < parent.key) {
            parent.left = insertedNode;
            // set partner node
            insertedNode.parent = parent;
        } else {
            parent.right = insertedNode;
            // set partner node
            insertedNode.parent = parent;
        }
        fixRedRed(insertedNode);
    }

    // fix case when two red nodes connected
    void fixRedRed(Node x){
        // if inserted node is root, then change to black
        if (x == root) {
            x.color = Color.BLACK;
            return;
        }
        //if node parent is black, no need to fix
        if (isBlack(x.parent)) {
            return;
        }
        // fix case when uncle is red
        // get colors of parent, uncle, and grandparent
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;
        if (isRed(uncle)) {
            // change parent to black
            parent.color = Color.BLACK;
            // change uncle to black
            uncle.color = Color.BLACK;
            // set grandparent to red
            grandparent.color = Color.RED;
            // recursively check grandparent
            fixRedRed(grandparent);
            return;
        }
        // fix case when uncle is black
        // for parent is left and inserted node is left
        if (parent.isLeftChild() && x.isLeftChild()) {
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            rightRotate(grandparent);
        } else if (parent.isLeftChild() && !x.isLeftChild()) { // parent is left and inserted node is right
            leftRotate(parent);
            x.color = Color.BLACK;
            grandparent.color = Color.RED;
            rightRotate(grandparent);
        } else if (!parent.isLeftChild() && !x.isLeftChild()) { //  parent is right and inserted node is right
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            leftRotate(grandparent);  
        } else { // parent is right and inserted node is left
            rightRotate(parent);
            x.color = Color.BLACK;
            grandparent.color = Color.RED;
            leftRotate(grandparent);
        }

    }

    // found node
    Node find(int key){
        Node p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    // save the children node of deleted a node
    Node findReplaced(Node deleted){
        if (deleted.left == null && deleted.right == null) { // when deleted node is leaf node
            return null;
        }
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        Node s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }

    public void remove(int key){
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted); 
    }

    // deleted node and left node is all black, handle case that less black after remove
    // can't change the left node to black since it's already black (if left is red, then we can re-balance by changing red to black)
    private void fixDoubleBlack(Node x){
        if (x == root) {
            return;
        }
        Node parent = x.parent; // get fix node parent
        Node sibling = x.sibling();
        // when sibling is red
        if (isRed(sibling)) {
            if (x.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = Color.RED;
            sibling.color = Color.BLACK;
            fixDoubleBlack(x);
            return;
        }
        if (sibling != null) {
            // sibling is black and its children are blacks
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = Color.RED;
                if (isBlack(parent)) {
                    parent.color = Color.BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
            } 
            // sibling is black, child has red
            else {
                if (sibling.isLeftChild() && isRed(sibling.left)) { // LL
                    rightRotate(parent);
                    sibling.left.color = Color.BLACK;
                    sibling.color = parent.color;
                }
                else if (sibling.isLeftChild() && isRed(sibling.right)) { // LR
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                else if (!sibling.isLeftChild() && isRed(sibling.left)) { // RL
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                else {
                    leftRotate(parent); // RR
                    sibling.right.color = Color.BLACK;
                    sibling.color = parent.color;
                }
                parent.color = Color.BLACK;
            }
        } else {
             fixDoubleBlack(parent);
        }
    }

    private void doRemove(Node deleted){
        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;
        if (replaced == null) { // no child
            if (deleted == root) {
                root = null; // empty tree;
            } else {
                if (isBlack(deleted)) {
                    // re-balance
                    fixDoubleBlack(deleted);
                } else {
                    // red
                }
                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }
        if (deleted.left == null || deleted.right == null) { // only one child
            if (deleted == root) {
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null; // empty root children
            } else {
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = null;
                deleted.right = null;
                deleted.parent = null;
                // deleted is black and left node is black
                if (isBlack(deleted) && isBlack(replaced)) {
                    fixDoubleBlack(replaced);
                } else {
                    // change to black
                    replaced.color = Color.BLACK;
                }
            }
            return;
        }
        // two children case, convert to one child or no child case
        // exchange value and key with deleted noted and replace node
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;
        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;
        doRemove(replaced);
    }

    public void printTree(){
        printTree(root,"",true);
    }
    private void printTree(Node node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            String color= node.color == Color.RED ? "RED" : "BLACK";
            System.out.println(Recipe.getName() + "(" + color + ")");
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }
}
