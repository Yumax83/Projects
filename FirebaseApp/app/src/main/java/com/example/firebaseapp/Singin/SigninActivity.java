package com.example.firebaseapp.Singin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebaseapp.HomeActivity;
import com.example.firebaseapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    EditText etSignInEmail, etSignInPassword;
    Button btnSignInUser;
    TextView tvVerifyEmail, tvForgotPassword;

    // для авторизации офлайн, при отсутствии сети
    SharedPreferences sharedPref;   // для сохранения данных
    SharedPreferences.Editor editor; // для изменения данных


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();


        // инициализация sharedPref
        sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE); // user prefs - название файла,  MODE_PRIVATE - доступ только для текущего приложения
        editor = sharedPref.edit(); // для изменения данных

        // проверка на авторизацию
        String cachedUid = sharedPref.getString("user_uid", null);
        if (cachedUid != null) { // если есть сохраненный uid
            Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        etSignInEmail = findViewById(R.id.et_signin_email);
        etSignInPassword = findViewById(R.id.et_signin_password);
        btnSignInUser = findViewById(R.id.btn_signin_user);
        tvVerifyEmail = findViewById(R.id.tv_verify_email);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);


        if (mAuth.getCurrentUser() != null) {
            mAuth.signOut();
        }

        btnSignInUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSignInEmail.getText().toString().isEmpty()) {
                    etSignInEmail.setError("Please Enter Email");
                } else if (etSignInPassword.getText().toString().isEmpty()) {
                    etSignInPassword.setError("Please Enter Password");
                } else {
                    mAuth.signInWithEmailAndPassword(etSignInEmail.getText().toString(), etSignInPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser(); // получение текущего пользователя
                                if (user.isEmailVerified()) {  // проверка на верификацию
                                    //  сохранение данных в sharedPref
                                    editor.putString("user_uid", user.getUid()); // сохранение uid в sharedPref
                                    editor.putString("user_email", user.getEmail()); // сохранение email в sharedPref
                                    editor.apply();

                                    Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                                    startActivity(intent);
   //                                 finish(); // завершение активности
                                } else {
                                    Toast.makeText(SigninActivity.this, "Please Verify Your Email", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SigninActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        // для верификации
        tvVerifyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser() == null) {

                    Toast.makeText(SigninActivity.this, "Please Sign In First", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SigninActivity.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSignInEmail.getText().toString().isEmpty()) {
                    etSignInEmail.setError("Please Enter Email");
                } else {
                    mAuth.sendPasswordResetEmail(etSignInEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SigninActivity.this, "Password Reset Email Sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
    }
}