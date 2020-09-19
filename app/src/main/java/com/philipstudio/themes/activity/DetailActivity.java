package com.philipstudio.themes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.philipstudio.themes.R;
import com.philipstudio.themes.adapter.CategoryAdapter;
import com.philipstudio.themes.adapter.WallpaperAdapter;
import com.philipstudio.themes.model.Category;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    RecyclerView rVListCategorySuggestion, rVListWallpaper;
    TextView txtNameCategory;
    ImageView imgFavorite;
    LinearLayout linearLayout;

    BottomSheetBehavior<LinearLayout> bottomSheetBehavior;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference dataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        txtNameCategory.setText(name);

        setUpRecyclerViewListWallpaper(name);

        setUpRecyclerViewlistCategory();

    }

    private void setUpRecyclerViewlistCategory() {
        rVListCategorySuggestion.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rVListCategorySuggestion.setLayoutManager(layoutManager);
        dataRef = firebaseDatabase.getReference().child("Category");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Category> arrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Category category = dataSnapshot.getValue(Category.class);
                    arrayList.add(category);
                }

                CategoryAdapter adapter = new CategoryAdapter(arrayList, DetailActivity.this);
                rVListCategorySuggestion.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setUpRecyclerViewListWallpaper(String name){
        rVListWallpaper.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rVListWallpaper.setLayoutManager(layoutManager);

        dataRef.child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> arrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String wallpaper = dataSnapshot.getValue(String.class);
                    arrayList.add(wallpaper);
                }

                WallpaperAdapter adapter = new WallpaperAdapter(arrayList, DetailActivity.this);
                rVListWallpaper.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView(){
        imgFavorite = findViewById(R.id.image_view_favorite);
        rVListWallpaper = findViewById(R.id.recyclerview_list_wallpaper);
        txtNameCategory = findViewById(R.id.text_view_name_category);
        rVListCategorySuggestion = findViewById(R.id.recyclerview_list_category_suggestion);
        linearLayout = findViewById(R.id.linear_layout);

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        firebaseDatabase = FirebaseDatabase.getInstance();
        dataRef = firebaseDatabase.getReference().child("Wallpaper");
    }
}