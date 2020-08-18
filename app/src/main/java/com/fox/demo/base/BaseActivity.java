package com.fox.demo.base;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.fox.demo.utlis.ProgressDialogUtils;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    /**
     * Navigate to new activity
     * @param classname
     */
    protected void navigateActivity(Class classname) {
        Intent intent = new Intent(this, classname);
        startActivity(intent);
    }

    /**
     * Navigate to new activity
     * @param classname
     * @param option
     */
    protected void navigateActivity(Class classname, int option) {
        Intent intent = new Intent(this, classname);
        intent.setFlags(option);
        startActivity(intent);
    }

    /**
     * Navigate to new activity
     * @param classname
     * @param k
     * @param v
     * @param option
     */
    protected void navigateActivity(Class classname, String k, int v, int option) {
        Intent intent = new Intent(this, classname);
        intent.putExtra(k, v);
        intent.setFlags(option);
        startActivity(intent);
    }

    /**
     * Navigate to new activity for result
     * @param classname
     * @param request_code
     */
    protected void navigateActivityForResult(Class classname, int request_code) {
        Intent intent = new Intent(this, classname);
        startActivityForResult(intent, request_code);
    }

    /**
     * Show progress dialog
     */
    protected void onShowLoading() {
        onHideLoading();
        progressDialog = ProgressDialogUtils.showLoadingDialog(this);
    }

    /**
     * Hide progress dialog
     */
    protected void onHideLoading() {
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            //progressDialog.cancel();
        }
    }

}
