package com.example.firebaseapp.Singin;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebaseapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    private EditText etRegisterEmail, etRegisterPassword,  etRegisterUname;
    private Button btnRegisterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance(); // get instance of firebase auth

        etRegisterEmail = findViewById(R.id.et_register_email);
        etRegisterPassword = findViewById(R.id.et_register_password);
        btnRegisterUser = findViewById(R.id.et_register_user);
        etRegisterUname = findViewById(R.id.et_register_uname);

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etRegisterUname.getText().toString().isEmpty()) {
                    etRegisterUname.setError("Please Enter User Name"); // set error
                } else if (etRegisterEmail.getText().toString().isEmpty()) {
                    etRegisterEmail.setError("Please Enter Email ID"); // set error
                } else if (etRegisterPassword.getText().toString().isEmpty()) {
                    etRegisterPassword.setError("Please Enter Password"); // set error
                } else {
                     mAuth.createUserWithEmailAndPassword(etRegisterEmail.getText().toString(),
                             etRegisterPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() { //
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) { //
                             if (task.isSuccessful()) {
                                 UserProfileChangeRequest profilechangeRequest = new UserProfileChangeRequest.Builder().
                                         setDisplayName(etRegisterUname.getText().toString()).
                                         setPhotoUri(Uri.parse("https://i.pinimg.com/736x/0d/86/f6/0d86f6bff1cac189715bdba7990727cb.jpg")).build(); // set display name
                                 mAuth.getCurrentUser().updateProfile(profilechangeRequest)
                                         .addOnCompleteListener(new OnCompleteListener<Void>() {
                                             @Override
                                             public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegisterActivity.this, "User registered", Toast.LENGTH_SHORT).show();
                                                }
                                             }
                                         });
                                         // / show toast
                             } else {
                                 Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show(); // show toast
                             }
                         }
                     }); // create user with email and password
                }
            }
        });
    }
}