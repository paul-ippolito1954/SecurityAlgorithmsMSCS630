package com.example.aesdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goEncrypt(View view){
        Intent intent = new Intent(this, EncryptActivity.class);
        startActivity(intent);
    }

    public void goDecrypt (View view){
        Intent intent = new Intent(this, DecryptActivity.class);
        startActivity(intent);
    }
}
