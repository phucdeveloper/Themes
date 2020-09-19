package com.philipstudio.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.philipstudio.themes.fragment.SignInFragment;
import com.philipstudio.themes.fragment.SignInPhoneNumberFragment;
import com.philipstudio.themes.fragment.UserFragment;
import com.philipstudio.themes.utils.UserUtil;

public class LoginActivity extends AppCompatActivity implements SignInFragment.OnButtonSignInClickListener {

    FrameLayout frameLayout;

    UserUtil userUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        frameLayout = findViewById(R.id.frame_layout_container);
        userUtil = new UserUtil(this);

        if (TextUtils.isEmpty(userUtil.getUserUtil())) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout_container, new SignInFragment())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout_container, new UserFragment())
                    .commit();
        }


    }

    @Override
    public void onButtonClick(int number) {
        if (number == 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout_container, new SignInPhoneNumberFragment())
                    .commit();
        }
    }
}