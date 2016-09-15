package com.gircenko.gabriel.calcounter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 13-Sep-16.
 */
public abstract class FirebaseAuthActivity extends AppCompatActivity {

    @BindView(R.id.et_email)
    protected EditText et_email;
    @BindView(R.id.et_password)
    protected EditText et_password;

    protected ProgressDialog progressDialog;

    protected FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }
}
