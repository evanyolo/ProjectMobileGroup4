package com.example.group4_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Advertising2 extends Fragment implements View.OnClickListener {

    @Override
    @Nullable
    public View onCreateView(@Nullable LayoutInflater inflater, @NonNull ViewGroup containers, @NonNull Bundle savedInstancesState){
        View v =inflater.inflate(R.layout.activity_advertising2, containers, false);
        WebView webview = v.findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://pages.sejasa.com/jasa-pertukangan/?utm_content=aplikasi%20tukang%20bangunan&utm_term=b&utm_campaign=[SEM-01004099]:%20Handyman&utm_medium=cpc&utm_source=google&gad_source=1&gclid=Cj0KCQiA67CrBhC1ARIsACKAa8THHuD6e1xXICyhtsycPkziMa_r97UyW6I0hIb6xY4H6Hakrf6gYr0aAqU0EALw_wcB");
        return v;
    }

    @Override
    public void onClick(View view) {

    }
}