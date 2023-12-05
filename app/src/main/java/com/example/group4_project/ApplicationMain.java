package com.example.group4_project;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ApplicationMain extends Application implements UpdateSelected {
    private static Context context;
    ArrayList<OrderListModel> orderListModels;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        orderListModels = new ArrayList<>();
    }

    public static Context getMyContext(){
        return context;
    }

    @Override
    public void addItems(String name, int price) {
        orderListModels.add(new OrderListModel());
    }

    @Override
    public List<OrderListModel> getItems() {
        return orderListModels;
    }
}
