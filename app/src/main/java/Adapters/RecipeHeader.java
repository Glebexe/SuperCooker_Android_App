package Adapters;

public class RecipeHeader {
    private String name;

    public RecipeHeader(){}

    public RecipeHeader(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
