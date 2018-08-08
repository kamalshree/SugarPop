package com.android.bakingapp.Widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.android.bakingapp.R;


public class SugarPopWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sugar_pop_widget);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String recipeName, ingredientsList;

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sugar_pop_widget);
        SharedPreferences sharedPref = context.getSharedPreferences("RecipeDetails", Context.MODE_PRIVATE);

        recipeName = sharedPref.getString("SharedPrefrecipeName", "");
        ingredientsList = sharedPref.getString("SharedPrefingredientslist", "");

        views.setTextViewText(R.id.tv_widget_recipe_name, recipeName);
        views.setTextViewText(R.id.tv_widget_ingredientslist, ingredientsList);

        AppWidgetManager.getInstance(context).updateAppWidget(
                new ComponentName(context, SugarPopWidget.class), views);

    }
}