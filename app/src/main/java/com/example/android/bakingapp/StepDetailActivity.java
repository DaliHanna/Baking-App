package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.Fragments.DetailFragment;
import com.example.android.bakingapp.Model.Step;

import java.util.List;

public class StepDetailActivity extends AppCompatActivity implements DetailFragment.ChangeStepClickListener {
    private static final String TAG = StepDetailActivity.class.getSimpleName();

    private int mStepId;
    private List<Step> stepsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        Intent intent = getIntent();
        mStepId = intent.getIntExtra("id", 0);
        stepsList = intent.getParcelableArrayListExtra("steps_extra");
        if (savedInstanceState == null) {
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setStepList(stepsList);
            detailFragment.setStepData(mStepId);
            getSupportFragmentManager().beginTransaction().add(R.id.rec, detailFragment).commit();
        }

    }

    @Override
    public void changeStepClickListener(int newInt) {
        int step = newInt + 1;
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setStepList(stepsList);
        detailFragment.setStepData(step);
        getSupportFragmentManager().beginTransaction().replace(R.id.rec, detailFragment).commit();

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
