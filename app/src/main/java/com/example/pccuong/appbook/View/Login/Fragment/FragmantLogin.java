package com.example.pccuong.appbook.View.Login.Fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pccuong.appbook.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by PCCuong on 12/31/2016.
 */

public class FragmantLogin  extends Fragment  implements View.OnClickListener{
    Button btnLogin,btnLoginGoogle;
    CallbackManager callbackManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragmant_login, container, false);
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
        Log.d("kiemtra"," thanh cong");
            }

            @Override
            public void onCancel() {
                Log.d("kiemtra"," thoat");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("kiemtra"," Erro");
            }
        });
        btnLogin = (Button) view.findViewById(R.id.btnLoginFacebook);
        btnLoginGoogle  = (Button) view.findViewById(R.id.btnloginGoogle);
        btnLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
LoginManager.getInstance().logInWithReadPermissions(FragmantLogin.this, Arrays.asList("public_profile"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}