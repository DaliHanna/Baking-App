package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.android.bakingapp.Fragments.DetailFragment;
import com.example.android.bakingapp.Fragments.IngredientsFragment;
import com.example.android.bakingapp.Fragments.IngredientsViewPagerFragment;
import com.example.android.bakingapp.Fragments.StepsListFragment;
import com.example.android.bakingapp.Model.Step;

import java.util.ArrayList;
import java.util.List;

public class DetailsRecipeActivity extends AppCompatActivity implements StepsListFragment.OnStepClickListener {
    private static final String TAG = DetailsRecipeActivity.class.getSimpleName();
    private int minSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_recipe);
        minSize = getResources().getConfiguration().smallestScreenWidthDp;
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("recipe_name");
        if (savedInstanceState == null) {
            if (minSize < 600) {
                IngredientsViewPagerFragment ingredientsViewPagerFragment = new IngredientsViewPagerFragment();
                ingredientsViewPagerFragment.setRecipeId(id);
                ingredientsViewPagerFragment.setRecipeName(name);
                getSupportFragmentManager().beginTransaction().add(R.id.ingredients_steps_fragment, ingredientsViewPagerFragment).commit();
            } else {
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                ingredientsFragment.setRecipeId(id);
                ingredientsFragment.setRecipeName(name);
                getSupportFragmentManager().beginTransaction().add(R.id.ingredients_7, ingredientsFragment).commit();

                StepsListFragment stepsListFragment = new StepsListFragment();
                stepsListFragment.setRecipeId(Integer.valueOf(id));
                getSupportFragmentManager().beginTransaction().add(R.id.stepsss, stepsListFragment).commit();
            }
        }

    }


    @Override
    public void onStepClickListener(Step step, List<Step> steps) {

        if (minSize < 600 ) {
            Intent intent = new Intent(this, StepDetailActivity.class);
            intent.putExtra("shortDescription", step.getShortDescription());
            intent.putExtra("description", step.getDescription());
            intent.putExtra("videoUrl", step.getVideoUrl());
            intent.putExtra("id", step.getStepId());
            intent.putParcelableArrayListExtra("steps_extra", (ArrayList<? extends Parcelable>) steps);
            startActivity(intent);
        } else {
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setStepList(steps);
            detailFragment.setStepData(step.getStepId());
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_step_7, detailFragment).commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
