package com.philipstudio.themes.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.philipstudio.themes.R;
import com.philipstudio.themes.utils.PhoneNumberUtil;
import com.philipstudio.themes.utils.UserUtil;

public class SignInFragment extends Fragment {

    UserUtil userUtil;
    PhoneNumberUtil phoneNumberUtil;

    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        initView(view);


        return view;
    }

    private void initView(View view) {
        userUtil = new UserUtil(getContext());

        firebaseAuth = FirebaseAuth.getInstance();

        phoneNumberUtil = new PhoneNumberUtil(getContext());
    }
}
