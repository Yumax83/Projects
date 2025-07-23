package com.example.dzlogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailSignUp, passwordSignUp, passwordConfirmSignUp;
    private Button signUpButton;
    private DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        emailSignUp = findViewById(R.id.signUpEmail);
        passwordSignUp = findViewById(R.id.signUpPassword);
        passwordConfirmSignUp = findViewById(R.id.singUpConfirmPassword);

        signUpButton = findViewById(R.id.signUpButton);

        myDB = new DataBaseHelper(this);
        insertUser();

    }

    private void insertUser() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailSignUp.getText().toString();
                String password = passwordSignUp.getText().toString();
                String confirmPassword = passwordConfirmSignUp.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "All field are mandatory \n (Все поля являются обязательными для заполнения)", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirmPassword)) {
                        boolean checkUserEmail = myDB.checkEmail(email);
                    } else {
                        Toast.makeText(SignUpActivity.this, "Invalid Password \n (Пароли не совпадают)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}