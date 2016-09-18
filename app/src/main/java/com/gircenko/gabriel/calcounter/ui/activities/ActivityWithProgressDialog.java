package com.gircenko.gabriel.calcounter.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.gircenko.gabriel.calcounter.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 13-Sep-16.
 */
public abstract class ActivityWithProgressDialog extends AppCompatActivity {

    private ProgressDialog progressDialog;

    /**{@inheritDoc}*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
    }

    protected void showProgressDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void dismissProgressDialogAndShowToast(String toastMessage) {
        progressDialog.dismiss();
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
