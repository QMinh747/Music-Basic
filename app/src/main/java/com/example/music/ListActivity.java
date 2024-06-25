package com.example.music;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.music.databinding.ActivityListBinding;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.music.adapter.ListAdapter;
import com.example.music.databinding.ActivityListBinding;
import com.example.music.models.CategoryModel;

import com.example.music.models.CategoryModel;

public class ListActivity extends AppCompatActivity {

    public static CategoryModel category;

    private ActivityListBinding binding;
    private ListAdapter songsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nameTextView.setText(category.getName());
        Glide.with(binding.coverImageView.getContext())
                .load(category.getCoverUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(32)))
                .into(binding.coverImageView);

        setupSongsListRecyclerView();
    }

    private void setupSongsListRecyclerView() {
        songsListAdapter = new ListAdapter(category.getSongs());
        binding.songsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.songsListRecyclerView.setAdapter(songsListAdapter);
    }
}