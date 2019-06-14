package quanth.vn.com.tv_kids.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import quanth.vn.com.tv_kids.R;
import quanth.vn.com.tv_kids.adapter.MenuItemAdapter;
import quanth.vn.com.tv_kids.fragments.HomeFragment;
import quanth.vn.com.tv_kids.model.MenuItem;
import quanth.vn.com.tv_kids.utils.SavePrefs;

public class MainActivity extends BaseActivity implements DrawerLayout.DrawerListener, View.OnClickListener, MenuItemAdapter.IOnMenuItemClickListener {


    public enum MENU_ITEM {MENU_HOME, MENU_LOGOUT}

    private ImageView imgAvatar;
    private ImageView mImvBack;
    private DrawerLayout mDrawerLayout;
    private TextView tvUsername;
    private MENU_ITEM mCurrentMenu, mMenuBefore;
    private Fragment mCurrentFragment;
    private View mLayoutSlideMenu;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponents() {
        imgAvatar = findViewById(R.id.imv_avatar);
        mImvBack = findViewById(R.id.imv_nav_left);
        mImvBack.setImageResource(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        tvUsername = findViewById(R.id.tv_fullname);
        mLayoutSlideMenu = findViewById(R.id.layout_left_menu);

        //instance menu
        RecyclerView mRecyclerViewMenu = findViewById(R.id.recyclerview_menu);
        mRecyclerViewMenu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(MENU_ITEM.MENU_HOME, R.drawable.ic_home, getString(R.string.home)));
        menuItems.add(new MenuItem(MENU_ITEM.MENU_LOGOUT, R.drawable.ic_logout, getString(R.string.logout)));
        MenuItemAdapter menuAdapter = new MenuItemAdapter(this, menuItems);
        menuAdapter.setItemListener(this);
        mRecyclerViewMenu.setAdapter(menuAdapter);
        mCurrentMenu = MENU_ITEM.MENU_HOME;
        menuAdapter.setItemSelected(MENU_ITEM.MENU_HOME);
        mDrawerLayout.addDrawerListener(this);

        //instance HomeFragment
        setTitle(getString(R.string.home));
        setNewPage(new HomeFragment());
        loadData();
    }

    private void loadData() {
        tvUsername.setText(SavePrefs.getUsername(this));
//        imgAvatar.setImageURI(Uri.parse(SavePrefs.getUriAva(this)));
        Glide.with(this).load(SavePrefs.getUriAva(this))
                .placeholder(R.mipmap.logo_app)
                .error(R.mipmap.logo_app)
                .into(imgAvatar);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void addListener() {
        mImvBack.setOnClickListener(this);
    }

    @Override
    public void onDrawerSlide(@NonNull View view, float v) {

    }

    @Override
    public void onDrawerOpened(@NonNull View view) {

    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        if (mCurrentMenu == null || mMenuBefore == null) {
            return;
        }
        mMenuBefore = null;
        switch (mCurrentMenu) {
            case MENU_HOME:
//                setTitle(getString(R.string.home));
//                mCurrentFragment = HomeFragment.newInstance("");
//                setNewPage(mCurrentFragment);
                break;
        }
    }

    @Override
    public void onDrawerStateChanged(int i) {

    }

    @Override
    public void onItemClick(MENU_ITEM menuId, MENU_ITEM currentMenu) {
        if (currentMenu == MENU_ITEM.MENU_LOGOUT) {
            mMenuBefore = null;
            showPopupLogout();
        } else {
            mCurrentMenu = menuId;
            mMenuBefore = menuId;
        }
        mDrawerLayout.closeDrawer(mLayoutSlideMenu);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imv_nav_left) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }
    }

    private void showPopupLogout() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(getString(R.string.txt_are_you_sure_logout))
                .setPositiveButton(R.string.txt_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        showLoading(true);
//                        new LogoutTask(MainActivity.this, MainActivity.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        logout();
                    }
                })
                .setNegativeButton(R.string.txt_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private void logout() {
        SavePrefs.clearSharePrefs(this);
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                startActivity(new Intent(MainActivity.this, SplashActivity.class));
                finish();
            }
        });
    }
}