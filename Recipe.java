import java.util.List;

public class Recipe {

    private String name;
    private List<String> ingredients;
    private String instructions;


    public Recipe(String name, List<String> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName(){
        return name;
    }

    public List<String> getIngredients(){
        return ingredients;
    }


    @Override
    public String toString(){
        return "Recipe: " + name + "\nIngredients: " + ingredients;
    }

}
