import java.util.List;

public class Main {

    public static void main(String[] args) {

        //instantiate rbt

        RedBlackTree rbTree = new RedBlackTree();
        normalBinaryTree shittyTree = new normalBinaryTree();

        //recipe
        Recipe beefWellington = new Recipe("Beef Wellington", List.of("Beef", "Flour", "Eggs"));

        for(int i = 0; i < 10; i ++){
            rbTree.put(beefWellington.getName().hashCode() + i, beefWellington);
            shittyTree.put(beefWellington.getName().hashCode() + i, beefWellington);
        }




        //System.out.println(rbTree.find(0));
        //rbTree.printTree();
        shittyTree.printTree();


    }
}
