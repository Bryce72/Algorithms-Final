import java.util.List;

public class Recipe {

    private static String name;
    private static List<String> ingredients;



    public Recipe(String name, List<String> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }

    public static String getName(){
        return name;
    }

    public static List<String> getIngredients(){
        return ingredients;
    }


    @Override
    public String toString(){
        return "Recipe: " + name + "\nIngredients: " + ingredients;
    }

}
