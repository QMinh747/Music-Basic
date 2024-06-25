package com.example.music.adapter;

import android.view.LayoutInflater;
import android.view.RoundedCorner;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.music.databinding.CategoryItemRecyclerRowBinding;
import com.example.music.models.CategoryModel;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import com.example.music.ListActivity;
import com.example.music.databinding.CategoryItemRecyclerRowBinding;
import com.example.music.models.CategoryModel;
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private List<CategoryModel> categoryList;

    public CategoryAdapter(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CategoryItemRecyclerRowBinding binding;

        public MyViewHolder(CategoryItemRecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(CategoryModel category) {
            binding.nameTextView.setText(category.getName());
            Glide.with(binding.coverImageView).load(category.getCoverUrl())
                    .apply(new RequestOptions().transform(new RoundedCorners(32)))
                    .into(binding.coverImageView);

            Context context = binding.getRoot().getContext();
            binding.getRoot().setOnClickListener(v -> {
                ListActivity.category = category;
                context.startActivity(new Intent(context, ListActivity.class));
            });
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemRecyclerRowBinding binding = CategoryItemRecyclerRowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
