package com.gircenko.gabriel.calcounter.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.gircenko.gabriel.calcounter.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 13-Sep-16.
 */
public abstract class CredentialsActivity extends AppCompatActivity {

    @BindView(R.id.et_email)
    protected EditText et_email;
    @BindView(R.id.et_password)
    protected EditText et_password;

    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
    }
}