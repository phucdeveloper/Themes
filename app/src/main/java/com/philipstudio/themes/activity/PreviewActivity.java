package com.philipstudio.themes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.philipstudio.themes.R;

import java.io.IOException;

public class PreviewActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnSetWallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_preview);
        imageView = findViewById(R.id.image_view);
        btnSetWallpaper = findViewById(R.id.button_set_wallpaper);

        Intent intent = getIntent();
        if (intent != null){
            String wallpaper = intent.getStringExtra("data");
            Glide.with(this).load(wallpaper).into(imageView);
        }

        imageView.setOnClickListener(listener);
        btnSetWallpaper.setOnClickListener(listener);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_set_wallpaper:
                    setWallpaper();
                    break;
                case R.id.image_view:
                    Toast.makeText(PreviewActivity.this, "Hello world", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    private void setWallpaper() {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        WallpaperManager manager = WallpaperManager.getInstance(this);
        try {
            manager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBack(View view) {
        finish();
    }
}