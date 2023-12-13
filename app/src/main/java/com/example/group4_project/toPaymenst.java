package com.example.group4_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class toPaymenst extends AppCompatActivity {
    TextView method;
    ImageView img;
    Button btnPays,uploadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_paymenst);
        btnPays = findViewById(R.id.btnMakePayment);
        btnPays.setOnClickListener(view -> {
            AlertDialog.Builder ad = new AlertDialog.Builder(toPaymenst.this);
            ad.setTitle("Payments");
            ad.setMessage("Are you sure want to make Payment?");
            ad.setPositiveButton("Yes", (dialogInterface, i) -> {
                Toast.makeText(this,"Thank You for the Payment",Toast.LENGTH_SHORT).show();
                Intent a = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(a);
            });
            ad.setNegativeButton("No",(dialogInterface, i) -> {
                Toast.makeText(this,"Canceled",Toast.LENGTH_SHORT).show();
            });
            ad.show();
        });
        show();
    }
    public void show(){
        method = findViewById(R.id.methodPay);
        img = findViewById(R.id.paymenDraw);

        method.setText(paymentcons.getMethod_payment());
        Glide.with(this).load(paymentcons.getPicPayment()).into(img);
    }
}