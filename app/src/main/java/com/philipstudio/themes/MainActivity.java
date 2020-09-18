package com.philipstudio.themes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.github.abdularis.civ.CircleImageView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.philipstudio.themes.adapter.CategoryAdapter;
import com.philipstudio.themes.model.Category;
import com.philipstudio.themes.utils.UserUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rVListCategory;
    LinearLayout linearLayout;
    BottomSheetBehavior<LinearLayout> bottomSheetBehavior;
    CircleImageView circleImageView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference dataRef;

    UserUtil userUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setUpRecyclerView();

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(userUtil.getUserUtil())){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void setUpRecyclerView(){
        rVListCategory.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rVListCategory.setLayoutManager(layoutManager);

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Category> arrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Category category = dataSnapshot.getValue(Category.class);
                    arrayList.add(category);
                }

                CategoryAdapter adapter = new CategoryAdapter(arrayList, MainActivity.this);
                rVListCategory.setAdapter(adapter);

                adapter.setOnItemCategoryClickListener(new CategoryAdapter.OnItemCategoryClickListener() {
                    @Override
                    public void onItemClick(int pos) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView(){
        rVListCategory = findViewById(R.id.recyclerview_list_category);
        linearLayout = findViewById(R.id.linear_layout);
        circleImageView = findViewById(R.id.circle_image_view);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        firebaseDatabase = FirebaseDatabase.getInstance();
        dataRef = firebaseDatabase.getReference().child("Category");

        userUtil = new UserUtil(this);
    }
}