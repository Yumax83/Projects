package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chatapp.utils.FirebaseUtil;
import com.example.chatapp.utils.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

public class LoginUsernameActivity extends AppCompatActivity {

    EditText usernameInput;
    Button letMeInBtn;
    ProgressBar progressBar;

    String phoneNumber;
    UserModel userModel;// юзер модель

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_username);

        usernameInput = findViewById(R.id.login_username);
        letMeInBtn = findViewById(R.id.login_let_me_in_btn);
        progressBar = findViewById(R.id.login_progress_bar);

        phoneNumber = getIntent().getExtras().getString("phone");// получаем телефон из интента
        getUsername();

        letMeInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUsername();
            }
        });
    }

    private void setUsername() {// метод для установки юзернейма
        String username = usernameInput.getText().toString();// получаем юзернейм
        if (username.isEmpty() || username.length() < 3) { // если юзернейм пуст или меньше 3 символов
            usernameInput.setError("Username length should be at least 3 chars"); // если юзернейм меньше 3 символов
            return; // возвращаемся
        }
        setInProgress(true); // устанавливаем прогресс
        if (userModel != null) {
            userModel.setUsername(username); // устанавливаем юзернейм
        } else {
            userModel = new UserModel(phoneNumber, username, Timestamp.now()); // создаем юзер модель
            FirebaseUtil.currentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() { // устанавливаем юзернейм
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    setInProgress(false);
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LoginUsernameActivity.this, MainActivity.class); // создаем интент
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // флаги
                        startActivity(intent);
                    }
                }
            });
        }

    }


    private void getUsername() {// метод для получения юзернейма
        setInProgress(true);
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {// получаем юзера из бд
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                setInProgress(false);
                if (task.isSuccessful()) {// если успешно
                    userModel = task.getResult().toObject(UserModel.class);// получаем юзер модель
                    if (userModel != null) {// если юзер модель не пуста
                        usernameInput.setText(userModel.getUsername());     // устанавливаем юзернейм
                    }
                }
            }
        });
    }

    private void setInProgress(boolean inProgress) {// метод для отображения прогресса
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            letMeInBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            letMeInBtn.setVisibility(View.VISIBLE);
        }
    }
}
