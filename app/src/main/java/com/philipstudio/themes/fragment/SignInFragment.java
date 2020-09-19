package com.philipstudio.themes.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.philipstudio.themes.R;
import com.philipstudio.themes.model.User;
import com.philipstudio.themes.utils.BlurBuilder;
import com.philipstudio.themes.utils.PhoneNumberUtil;
import com.philipstudio.themes.utils.UserUtil;

public class SignInFragment extends Fragment {

    UserUtil userUtil;
    PhoneNumberUtil phoneNumberUtil;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dataUserRef;
    OnButtonSignInClickListener onButtonSignInClickListener;

    EditText edtEmail, edtPassword;
    Button btnSignIn, btnSignInFacebook, btnSignInPhone, btnSignInGoogle;
    TextView txtForgotPassword;
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        initView(view);

        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        Bitmap blurredBitmap = BlurBuilder.blur(getContext(), originalBitmap);
        linearLayout.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

        btnSignIn.setOnClickListener(listener);
        btnSignInPhone.setOnClickListener(listener);
        btnSignInGoogle.setOnClickListener(listener);
        btnSignInFacebook.setOnClickListener(listener);


        return view;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_sign_in:
                    String email = edtEmail.getText().toString();
                    String pass = edtPassword.getText().toString();
                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                        Toast.makeText(getContext(), "Data is not null", Toast.LENGTH_SHORT).show();
                    } else {
                        onSignInWithEmailAndPassword(email, pass);
                    }
                    break;
                case R.id.button_sign_in_with_phone_number:
                    if (onButtonSignInClickListener != null) {
                        onButtonSignInClickListener.onButtonClick(0);
                    }
                    break;
            }
        }
    };

    private void onSignInWithEmailAndPassword(final String text1, final String text2) {
        firebaseAuth.createUserWithEmailAndPassword(text1, text2).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser firebaseUser = authResult.getUser();
                if (firebaseUser != null) {
                    firebaseAuth.signInWithEmailAndPassword(text1, text2).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (authResult != null){
                                String idUser = authResult.getUser().getUid();
                                userUtil.setUserUtil(idUser);
                                String nickname = authResult.getUser().getDisplayName();
                                User user = new User(idUser, text1, text2, nickname, nickname);
                                dataUserRef.child(user.getIdUser()).setValue(user);
                                Toast.makeText(getContext(), "Sign in successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView(View view) {
        edtEmail = view.findViewById(R.id.edit_text_email);
        edtPassword = view.findViewById(R.id.edit_text_password);
        btnSignIn = view.findViewById(R.id.button_sign_in);
        btnSignInFacebook = view.findViewById(R.id.button_sign_in_with_facebook_account);
        btnSignInGoogle = view.findViewById(R.id.button_sign_in_with_google_account);
        btnSignInPhone = view.findViewById(R.id.button_sign_in_with_phone_number);
        txtForgotPassword = view.findViewById(R.id.text_view_forgot_password);
        linearLayout = view.findViewById(R.id.linear_layout);

        userUtil = new UserUtil(getContext());
        phoneNumberUtil = new PhoneNumberUtil(getContext());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dataUserRef = firebaseDatabase.getReference().child("User");
    }

    public interface OnButtonSignInClickListener {
        void onButtonClick(int number);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        onButtonSignInClickListener = (OnButtonSignInClickListener) context;
    }
}
