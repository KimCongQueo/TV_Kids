package quanth.vn.com.tv_kids.activities;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import quanth.vn.com.tv_kids.R;
import quanth.vn.com.tv_kids.models.User;
import quanth.vn.com.tv_kids.utils.Constants;
import quanth.vn.com.tv_kids.utils.SavePrefs;

public class SplashActivity extends BaseActivity implements View.OnClickListener {
    private SignInButton btnLogin;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 1001;

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initComponents() {
        btnLogin = findViewById(R.id.btnLogin);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void addListener() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            showLoading(true);
            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                User user = new User();
                user.setDisplayName(account.getDisplayName());
                user.setPhotoUrl(account.getPhotoUrl());
                SavePrefs.saveUsername(this, account.getDisplayName());
                SavePrefs.saveUriAva(this, account.getPhotoUrl() + "");
                SavePrefs.saveTokenGg(this, account.getIdToken());
                SavePrefs.saveIdGg(this, account.getId());
                showLoading(false);
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra(Constants.USER, user);
                startActivity(i);
                finish();
            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            showDialogError(e.getStatusCode() + "");
//            updateUI(null);
        }
    }
}
