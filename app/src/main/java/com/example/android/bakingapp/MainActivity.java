package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.Fragments.RecipesFragment;
import com.example.android.bakingapp.Model.Recipe;

public class MainActivity extends AppCompatActivity implements
        RecipesFragment.OnRecipeClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            RecipesFragment recipesFragment = new RecipesFragment();
            fragmentManager.beginTransaction().add(R.id.recipe_list, recipesFragment).commit();
        }
    }

    @Override
    public void onRecipe(Recipe recipeClicked) {
        Intent intent = new Intent(this, DetailsRecipeActivity.class);
        intent.putExtra("id", recipeClicked.getId());
        intent.putExtra("recipe_name",recipeClicked.getRecipeName());
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

}
