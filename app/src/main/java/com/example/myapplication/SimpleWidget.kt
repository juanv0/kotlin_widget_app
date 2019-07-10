package com.example.myapplication

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

class SimpleWidget: AppWidgetProvider()  {
    fun onCreate(){

    }
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            println("Nuevo Update")
            println(appWidgetId.toString())
            SimpleWidget.updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
    companion object {

        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            var cupoDBHelper = CupoDBHelper(context)
            //val widgetText = context.getString(R.string.widgetLabelT)
            val widgetText = cupoDBHelper.getCupo()
            print("Entro en widgetUpdate")
            println(widgetText)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.simple_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
    fun getId(context:Context):Int{

        return this.getId(context)
    }
}