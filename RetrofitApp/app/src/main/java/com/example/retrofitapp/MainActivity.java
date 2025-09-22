package com.example.retrofitapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    JSONPlaceHolder jsonPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolder = retrofit.create(JSONPlaceHolder.class);

        //getPost();
     //getComments();

        createPost();

    }

    public void createPost(){
        Post post = new Post("18", "First title", "First text");
        Call<Post> call = jsonPlaceHolder.createPost(post);
        call.enqueue(new Callback<Post>() {


            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                 if (!response.isSuccessful()) {
                     Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                     return;
                 }
                 List<Post> postList = new ArrayList<>();
                 postList.add(response.body());

                 PostAdapter postAdapter = new PostAdapter(MainActivity.this, postList);
                 recyclerView.setAdapter(postAdapter);
                 Toast.makeText(MainActivity.this, response.code() + "Response", Toast.LENGTH_SHORT).show();
                 }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getComments(){

        Call<List<Comment>> call = jsonPlaceHolder.getComments(2);

         call.enqueue(new Callback<List<Comment>>() {
             @Override
             public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                 if (!response.isSuccessful()) {
                     Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                     return;
                 } else {
                     List<Comment> commentList = response.body();
                     CommentAdapter adapter = new CommentAdapter(MainActivity.this, commentList);
                     recyclerView.setAdapter(adapter);
                 }
             }

             @Override
             public void onFailure(Call<List<Comment>> call, Throwable t) {
                 Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
             }


             public void getPost() {
                 Call<List<Post>> call = jsonPlaceHolder.getPost();
                 call.enqueue(new Callback<List<Post>>() {
                     @Override
                     public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                         if (!response.isSuccessful()) {
                             Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                             return;
                         } else {
                             List<Post> postList = response.body();
                             PostAdapter adapter = new PostAdapter(MainActivity.this, postList);
                             recyclerView.setAdapter(adapter);
                         }
                     }

                     @Override
                     public void onFailure(Call<List<Post>> call, Throwable t) {
                         Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 });
             }
         });
    }
}