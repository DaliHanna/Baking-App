package com.example.android.bakingapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.bakingapp.Adapters.RecyclerRecipesAdapter;
import com.example.android.bakingapp.Model.Recipe;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utilities.NetworkUtils;

import java.util.ArrayList;
import java.util.List;


public class RecipesFragment extends Fragment implements
        RecyclerRecipesAdapter.RecipesOnClickListener, LoaderManager.LoaderCallbacks<List<Recipe>> {

    private static final String TAG = RecipesFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerRecipesAdapter recyclerRecipesAdapter;
    OnRecipeClickListener onRecipeClickListener;
    private static final int LOADER_ID = 1;
    private ProgressBar progressBar;
    private List<Recipe> recipes;

    public interface OnRecipeClickListener {
        void onRecipe(Recipe recipeClicked);
    }

    public RecipesFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onRecipeClickListener = (OnRecipeClickListener) context;
        } catch (Exception e) {
            Log.d(TAG, "onAttach error  : " + e);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipes_list, container, false);
        progressBar = rootView.findViewById(R.id.progress_bar_recipes);
        progressBar.setVisibility(View.GONE);

        recyclerView = rootView.findViewById(R.id.recycler_recipes);
        int orientation = getResources().getConfiguration().orientation;

        if (orientation == 2) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else if (orientation == 1) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
        }
        recyclerView.setHasFixedSize(true);
        recyclerRecipesAdapter = new RecyclerRecipesAdapter(getContext(), this);
        recyclerView.setAdapter(recyclerRecipesAdapter);
        loadData();
        return rootView;
    }

    private void loadData() {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    public void onClick(Recipe recipeClicked) {
        onRecipeClickListener.onRecipe(recipeClicked);
    }

    @Override
    public Loader<List<Recipe>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<List<Recipe>>(getContext()) {
            @Override
            protected void onStartLoading() {
                progressBar.setVisibility(View.VISIBLE);
                forceLoad();

            }

            @Override
            public List<Recipe> loadInBackground() {
                recipes = new ArrayList<>();
                try {
                    String json = NetworkUtils.makeHttpRequest(NetworkUtils.createUrl());
                    recipes = NetworkUtils.getJsonRecipes(getContext(), json);
                } catch (Exception e) {
                    Log.e(TAG, "error loading in background: " + e.getMessage());
                }
                return recipes;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<Recipe>> loader, List<Recipe> data) {
        recyclerRecipesAdapter.setData(data);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {
        recyclerRecipesAdapter.setData(null);
    }

}
