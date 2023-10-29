package com.app.netflix;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.netflix.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

            binding.button2.setOnClickListener(v -> {
                if(binding.editTextText.getText().toString().isEmpty() || binding.editTextText2.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please fill your username and password",Toast.LENGTH_SHORT).show();
                } else if (binding.editTextText.getText().toString().equals("test") && binding.editTextText2.getText().toString().equals("test")) {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"Wrong credentials",Toast.LENGTH_SHORT).show();
                }
            });
    }
}