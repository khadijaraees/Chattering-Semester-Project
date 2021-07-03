package com.fahadmehmood.chattering.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.fahadmehmood.chattering.databinding.ActivityPhoneNumberBinding;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneNumber extends AppCompatActivity {
    ActivityPhoneNumberBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPhoneNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            Intent intent=new Intent(PhoneNumber.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        binding.PhoneBox.requestFocus(); //this is automatically open keypad when we click on edittext of type numbers
        binding.ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PhoneNumber.this,OTPActivity.class);
                intent.putExtra("phoneNumber",binding.PhoneBox.getText().toString());//To pass data while going from one activity to another
                startActivity(intent);

            }
        });
    }
}