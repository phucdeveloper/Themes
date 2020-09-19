package com.philipstudio.themes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.philipstudio.themes.R;

public class SignInPhoneNumberFragment extends Fragment {

    Button btnSignIn;
    EditText edtInputPhoneNumber;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in_phone_number, container, false);

        initView(view);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_container, new SignInFragment())
                        .commit();
            }
        });

        return view;
    }

    private void initView(View view) {
        btnSignIn = view.findViewById(R.id.button_sign_in);
        toolbar = view.findViewById(R.id.toolbar);
        edtInputPhoneNumber = view.findViewById(R.id.edit_text_input_phone_number);
    }
}
