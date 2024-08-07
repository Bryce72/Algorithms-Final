public class normalBinaryTree {
    private Node root;


    private static class Node{
        int key;
        Object value;
        Node left, right;

        //constructor
        public Node(int key, Object value){
            this.key = key;
            this.value = value;
        }
    }

    public void put(int key, Object value){
        root = put(root, key, value);
    }

    private Node put(Node node, int key, Object value){
        if(node == null){
            return new Node(key, value);
        }
        if (key < node.key){
            node.left = put(node.left, key, value);
        }else if (key > node.key){
            node.right = put(node.right, key, value);
        }else{
            node.value = value;
        }
        return node;
    }



    public Object find(int key){
        return find(root, key);
    }

    private Object find(Node node, int key){
        if(node == null){
            return null;
        }
        if(key < node.key){
            return find(node.left, key);
        }else if(key > node.key){
            return find(node.right, key);
        }else{
            return node.value;
        }
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
            System.out.println(Recipe.getName());
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }

}
