package com.example.productsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent,false);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.overview.setText(product.getDescription());

        Glide.with(context).load(product.getPoster()).into(holder.imageView);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", product.getTitle());
                bundle.putString("overview", product.getDescription());
                bundle.putString("poster", product.getPoster());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
       ImageView imageView;
       TextView title, overview;
       ConstraintLayout constraintLayout;

       public ProductHolder(@NonNull View itemView) {
           super(itemView);

           imageView = itemView.findViewById(R.id.imageView);
           title = itemView.findViewById(R.id.title_tv);
           overview = itemView.findViewById(R.id.overview_tv);
           constraintLayout = itemView.findViewById(R.id.main_layout);

       }
   }
}
