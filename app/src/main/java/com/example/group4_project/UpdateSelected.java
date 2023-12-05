package com.example.group4_project;

import java.util.ArrayList;
import java.util.List;

public interface UpdateSelected {
    void addItems(String name, int price);

    List<OrderListModel> getItems();
}
