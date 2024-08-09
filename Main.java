import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try (FileWriter writer = new FileWriter("result.csv")) {
            // Write the CSV header
            writer.append("Iteration,RedBlackTree Time (ns),NormalBinaryTree Time (ns)\n");

            // Loop with varying values of i
            for (int i = 10; i <= 50000; i += 200) {
                // Instantiate the trees for each iteration
                RedBlackTree rbTree = new RedBlackTree();
                normalBinaryTree shittyTree = new normalBinaryTree();

                // Recipe
                Recipe beefWellington = new Recipe("Beef Wellington", List.of("Beef", "Flour", "Eggs"));

                // Measure time for RedBlackTree
                long rbTreeStartTime = System.nanoTime();
                for (int j = 0; j < i; j++) {
                    rbTree.put(Recipe.getName().hashCode() + j, beefWellington);
                }
                long rbTreeEndTime = System.nanoTime();
                long rbTreeDuration = rbTreeEndTime - rbTreeStartTime;

                // Measure time for normalBinaryTree
                long shittyTreeStartTime = System.nanoTime();
                for (int j = 0; j < i; j++) {
                    shittyTree.put(Recipe.getName().hashCode() + j, beefWellington);
                }
                long shittyTreeEndTime = System.nanoTime();
                long shittyTreeDuration = shittyTreeEndTime - shittyTreeStartTime;

                // Write the iteration and durations to the CSV file
                writer.append(i + "," + rbTreeDuration + "," + shittyTreeDuration + "\n");

                // Optional: Print progress to console
                System.out.println("Iteration " + i + " completed.");
            }

            System.out.println("Results written to result.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
