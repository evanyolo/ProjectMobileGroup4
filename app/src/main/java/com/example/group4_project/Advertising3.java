package com.example.group4_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.zip.Inflater;

public class Advertising3 extends Fragment implements View.OnClickListener {

    @Override
    @Nullable
    public View onCreateView(@Nullable LayoutInflater inflater, @NonNull ViewGroup containers, @NonNull Bundle savedInstancesState){
        View m = inflater.inflate(R.layout.activity_advertising3, containers, false);
        WebView webview = m.findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.antaranews.com/berita/2951917/aplikasi-gravel-mudahkan-tukang-bangunan-dapat-kerja");
        return m;
    }

    @Override
    public void onClick(View view) {

    }
}