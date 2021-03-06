package com.example.android.bakingapp.Widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.example.android.bakingapp.Model.Ingredient;
import com.example.android.bakingapp.R;

import java.util.List;

public class WidgetAdapter implements RemoteViewsFactory {
    private List<Ingredient> ingredients;
    private Context context;

    public WidgetAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        ingredients = WidgetProvider.getIngredientList(context);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (null == ingredients) return 0;
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Ingredient ingredient = ingredients.get(position);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.list_item_widget);
        remoteViews.setTextViewText(R.id.ingredient_name_widget, ingredient.getIngredient());
        remoteViews.setTextViewText(
                R.id.ingredient_quantity_widget,
                ingredient.getQuantity() + " " + ingredient.getMeasure());
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
