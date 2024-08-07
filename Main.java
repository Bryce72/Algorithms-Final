import java.util.List;

public class Main {

    public static void main(String[] args) {

        //instantiate rbt

        RedBlackTree rbTree = new RedBlackTree();

        //recipe
        Recipe beefWellington = new Recipe("Beef Wellington", List.of("Beef", "Flour", "Eggs"));


        rbTree.put(beefWellington.getName().hashCode(), beefWellington);



    }
}
