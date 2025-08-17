package com.example.contactapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class AddEditContact extends AppCompatActivity {

    private Toolbar toolbar;

    private ImageView profile;
    private EditText nameEdit, phoneEdit, emailEdit, noteEdit;
    private FloatingActionButton fab;

    private String name, phone,email,note;

    //подключаем репозиторий работы с фото
    ActivityResultLauncher<Intent> imagePickLauncher;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_edit_contact);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEditContact.this, MainActivity.class);
                startActivity(intent);
            }
        });

        profile = findViewById(R.id.profile);
        profile.setClipToOutline(true);

        nameEdit = findViewById(R.id.nameEdit);
        phoneEdit = findViewById(R.id.phoneEdit);
        emailEdit = findViewById(R.id.emailEdit);
        noteEdit = findViewById(R.id.noteEdit);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        //работа с репозиторием Image
        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data != null && data.getData() != null) {
                    selectedImageUri = data.getData();
                    setProfilePic(this, selectedImageUri, profile);
                }
            }
        });
        //настраиваем репозиторий Image
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AddEditContact.this).cropSquare().compress(512).maxResultSize(512,512).createIntent(new Function1<Intent, Unit>() {
                    @Override
                    public Unit invoke(Intent intent) {
                        imagePickLauncher.launch(intent);
                        return null;
                    }
                });
            }
        });
    }

    private void setProfilePic(AddEditContact addEditContact, Uri selectedImageUri, ImageView profile) {
        Glide.with(addEditContact).load(selectedImageUri).apply(RequestOptions.centerCropTransform()).into(profile);
    }

    private void saveData() {
        
        name = nameEdit.getText().toString();
        phone=phoneEdit.getText().toString();
        email=emailEdit.getText().toString();
        note=noteEdit.getText().toString();
        
        if (!name.isEmpty() || !phone.isEmpty() || !email.isEmpty() || !note.isEmpty()) {
            Toast.makeText(this, "Nothing to save...", Toast.LENGTH_SHORT).show();
        }
    }


}








