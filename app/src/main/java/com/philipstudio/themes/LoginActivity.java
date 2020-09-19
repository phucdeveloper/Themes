package com.philipstudio.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.philipstudio.themes.fragment.SignInFragment;
import com.philipstudio.themes.utils.BlurBuilder;

public class LoginActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        frameLayout = findViewById(R.id.frame_layout_container);

        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        Bitmap blurredBitmap = BlurBuilder.blur(this, originalBitmap);
        frameLayout.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_container, new SignInFragment())
                .commit();


    }
}