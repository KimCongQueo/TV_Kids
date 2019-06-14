package quanth.vn.com.tv_kids.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.IOException;

import quanth.vn.com.tv_kids.R;
import quanth.vn.com.tv_kids.api.exception.ApiException;
import quanth.vn.com.tv_kids.views.DialogMessage;
import quanth.vn.com.tv_kids.views.LoadingProgress;


/**
 * Created by Admin on 3/22/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public ImageView mImvNavRight;
    private ImageView mImvNavLeft;
    private TextView mTvTitle;
    protected Fragment mFragment;
    protected LoadingProgress mProgressDialog;
    private MaterialDialog mAlertDialog;

    protected abstract int initLayout();

    protected abstract void initComponents();

    protected abstract void addListener();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = initLayout();
        if (layoutId != 0) {
            setContentView(layoutId);
        }
        mProgressDialog = new LoadingProgress(this);
        mProgressDialog.setCancelable(false);
        initNavigation();
        initComponents();
        addListener();
    }

    public void initNavigation() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        if (mTvTitle != null) {
            mImvNavLeft = (ImageView) findViewById(R.id.imv_nav_left);
            mImvNavRight = (ImageView) findViewById(R.id.imv_nav_right);
            mTvTitle.setSelected(true);
        }
    }

    public void showNavigation(ImageView imageView, int resId, View.OnClickListener listener) {
        if (imageView != null) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(resId);
            if (listener != null) {
                imageView.setOnClickListener(listener);
            }
        }
    }

    public void showNavLeft(int resId, View.OnClickListener listener) {
        showNavigation(mImvNavLeft, resId, listener);
    }

    public void showNavRight(int resId, View.OnClickListener listener) {
        showNavigation(mImvNavRight, resId, listener);
    }

    public void hiddenNavRight() {
        mImvNavRight.setVisibility(View.GONE);
    }

    public void setTitle(String title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void toast(int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show();
    }

    public DialogMessage showDialogError(String msg) {
        DialogMessage dialogMessage = new DialogMessage(this, msg);
        dialogMessage.showMsg();
        return dialogMessage;
    }

    public void showLoading(boolean isShow) {
        try {
            if (isShow) {
                mProgressDialog.show();
            } else {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Override
    public void finish() {
        hideKeyBoard();
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void setNewPage(Fragment fragment) {
        try {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_main, fragment, "currentFragment");
            transaction.commitAllowingStateLoss();
            if (mFragment != null)
                transaction.remove(mFragment);
            mFragment = fragment;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void hideKeyBoard() {
        try {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    try {
                        InputMethodManager inputManager = (InputMethodManager) BaseActivity.this
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(
                                BaseActivity.this.getCurrentFocus().getApplicationWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch (IllegalStateException e) {
                    } catch (Exception e) {
                    }
                }
            });

        } catch (IllegalStateException e) {
        } catch (Exception e) {
        }
    }

    public void showAlert(Exception e) {
        if (e instanceof ApiException)
            showAlert(((ApiException) e).getMessage(this));
        else if (e instanceof IOException)
            showAlert(R.string.err_network_available);
        else
            showAlert(R.string.err_unexpected_exception);
    }

    public void showAlert(int messageId) {
        showAlert(getString(messageId));
    }

    public void showAlert(String message) {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
        mAlertDialog = new MaterialDialog.Builder(this)
                .content(message)
                .positiveText(getString(R.string.txt_ok))
                .cancelable(false)
                .build();
        mAlertDialog.show();
    }
}