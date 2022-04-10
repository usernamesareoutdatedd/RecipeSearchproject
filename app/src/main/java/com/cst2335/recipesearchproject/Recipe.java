package com.cst2335.recipesearchproject;

public class Recipe {
    private  String  id;
    private String NameOfRecipe;


    public Recipe(String  id, String recipe) {
        this.id = id;
        this.NameOfRecipe = recipe;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfRecipe() {
        return NameOfRecipe;
    }

    public void setRecipe(String NameOfRecipe) {
        this.NameOfRecipe = NameOfRecipe;
    }
}
