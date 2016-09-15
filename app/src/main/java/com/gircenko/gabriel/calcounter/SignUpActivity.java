package com.gircenko.gabriel.calcounter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gabriel Gircenko on 13-Sep-16.
 */
public class SignUpActivity extends FirebaseAuthActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.btn_signup)
    public void signup() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (    email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please, fill all fields", Toast.LENGTH_SHORT).show();

        } else {
            progressDialog.setMessage("Signing up... Please, wait.");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        setResult(LoginActivity.SIGNED_UP, null);
                        finish();
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));

                    } else {
                        Toast.makeText(SignUpActivity.this, "Couldn't register the user. Please, try again.", Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }
            });
        }
    }
}
