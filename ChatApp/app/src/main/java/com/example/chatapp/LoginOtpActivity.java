package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chatapp.utils.AndroidUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class LoginOtpActivity extends AppCompatActivity {

    String phoneNumber;
    Long timeoutSeconds = 60L; //время в секундах

    String verificationCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;

    EditText otpInput, otpEditText;
    Button nextBtn;
    ProgressBar progressBar;
    TextView resendOtpTextView;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_otp);

        otpInput = findViewById(R.id.login_otp);
        nextBtn = findViewById(R.id.login_next_btn);
        progressBar = findViewById(R.id.login_progress_bar);
        resendOtpTextView = findViewById(R.id.resend_otp_textview);


        phoneNumber = getIntent().getExtras().getString("phone"); //получаем номер телефона из интента

        sendOtp(phoneNumber, false);

        nextBtn.setOnClickListener(new View.OnClickListener() { //кнопка отправки кода
            @Override
            public void onClick(View v) {
                String enterOtp = otpInput.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, enterOtp);//получаем код из поля ввода и проверяем его
                signIn(credential);
                setInProgress(true);
            }
        });


//        Toast.makeText(this, phoneNumber, Toast.LENGTH_SHORT).show();
//
//
//        Map<String, String> data = new HashMap<>(); //создаем мапу
//        FirebaseFirestore.getInstance().collection("test").add(data); //добавляем в бд
    }



    private void sendOtp(String phoneNumber, boolean isResend) {
        startResendTimer();

        setInProgress(true);
        PhoneAuthOptions.Builder builder = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) { //
                        signIn(phoneAuthCredential);
                        setInProgress(false);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) { //событие ошибки
                        AndroidUtil.showToast(getApplicationContext(), "verify failed");
                        setInProgress(false);
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) { //событие отправки кода
                        super.onCodeSent(s, forceResendingToken);
                        verificationCode = s;
                        resendingToken = forceResendingToken;

                        AndroidUtil.showToast(getApplicationContext(), "code sent successfully");
                        setInProgress(false);
                    }
                });
        if (isResend) {
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        } else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }
    }
    private void startResendTimer() {

        otpInput.setVisibility(View.VISIBLE);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeoutSeconds--;
                resendOtpTextView.setText("Resend OTP " + timeoutSeconds + " seconds");
                if(timeoutSeconds <= 0){
                    timeoutSeconds = 30L;
                    timer.cancel();
                    runOnUiThread(() -> {

                        otpInput.setVisibility(View.GONE);

                        nextBtn.setText("Back");
                    });
                }
            }
        }, 0, 1000);
    }
//    private void startResendTimer() {
//        resendOtpTextView.setEnabled(false);
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                timeoutSeconds--;
//                resendOtpTextView.setText("Resend OTP " + timeoutSeconds + " seconds");
//                if(timeoutSeconds <= 0){
//                    timeoutSeconds = 60L;
//                    timer.cancel();
//                    runOnUiThread(() -> {
//                        resendOtpTextView.setEnabled(true);
//                    });
//                }
//            }
//        }, 0, 1000);
//    }



    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        setInProgress(true);
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() { // проверяем код
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                setInProgress(false);
                if (task.isSuccessful()) {
                    Intent intent = new Intent(LoginOtpActivity.this, LoginUsernameActivity.class);
                    intent.putExtra("phone", phoneNumber);
                    startActivity(intent);
                } else {
                    AndroidUtil.showToast(getApplicationContext(), "verify failed");
                }
            }
        });

    }

    private void setInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            nextBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            nextBtn.setVisibility(View.VISIBLE);
        }
    }
}

//
//        testing.setTextIsSelectable(true);
//
////        editText.setTextIsSelectable(true);
////        editText.setLongClickable(true);
//
////        testing.setText(Html.fromHtml("Enter, <font color='#FF0000'>otp sent to your phone number</font>!"));
//        testing.setText(Html.fromHtml(
//        "Enter, <font color='#FF0000'>otp</font> " +
//        "<font color='#00FF00'>sent to</font> " +
//        "<font color='#0000FF'>your phone number</font>!"
//));