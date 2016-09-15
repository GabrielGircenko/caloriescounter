package com.gircenko.gabriel.calcounter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends FirebaseAuthActivity {

    public static final int REQUEST_SIGNUP = 101;
    public static final int SIGNED_UP = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP && resultCode == SIGNED_UP) {
            this.finish();
        }
    }

    @OnClick(R.id.btn_login)
    public void login() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please, fill all fields", Toast.LENGTH_SHORT).show();

        } else {
            progressDialog.setMessage("Logging in... Please, wait.");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    } else {
                        Toast.makeText(LoginActivity.this, "Couldn't login. Please, try again.", Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }
            });
        }
    }

    @OnClick(R.id.tv_signup)
    public void signup() {
        startActivityForResult(new Intent(this, SignUpActivity.class), REQUEST_SIGNUP);
    }
}
