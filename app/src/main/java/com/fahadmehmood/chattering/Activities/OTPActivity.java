package com.fahadmehmood.chattering.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.fahadmehmood.chattering.databinding.ActivityOTPBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mukesh.OnOtpCompletionListener;

import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {

    ActivityOTPBinding binding;
    ProgressDialog dialog;
    FirebaseAuth auth;
    String VerificationId;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOTPBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog=new ProgressDialog(this);
        dialog.setMessage("Sending OTP..."); //jab tak code a na jay load ho rha ho tabb tk ya messege show ho ga
        dialog.setCancelable(false); //loading kay darmoyan cancelleable nae hai
        dialog.show();
        auth=FirebaseAuth.getInstance();
        String phoneNumber=getIntent().getStringExtra("phoneNumber");
        binding.phonelabel.setText("Verify"+" "+ phoneNumber);
        PhoneAuthOptions options=PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS) //verification timeout timmer
                .setActivity(OTPActivity.this).
                        setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {//Incase if verification fails

                    }

                            @Override
                            public void onCodeSent(@NonNull String VerifyId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(VerifyId, forceResendingToken);
                                dialog.dismiss();
                                VerificationId=VerifyId;
                    }
                        }).build();
        binding.otpView.requestFocus();
        PhoneAuthProvider.verifyPhoneNumber(options); //This line will sent the code
        binding.otpView.setOtpCompletionListener(new OnOtpCompletionListener() { //Iss function say continue button pai click nae karna pary ga khud he verify ho jy ga
            @Override
            public void onOtpCompleted(String otp) {
                PhoneAuthCredential credential=PhoneAuthProvider.getCredential(VerificationId,otp);
                auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(OTPActivity.this,SetupProfileActivity.class);
                            startActivity(intent);
                            finishAffinity();//finishAffinity: jitni b activities open hoe hai sab ko finish kar day ga
                            //finish():sirf .this activity ko khtm kary ga
                        }else{
                            Toast.makeText(OTPActivity.this,"Fail to login",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        }
    }
