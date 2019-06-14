package quanth.vn.com.tv_kids.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import quanth.vn.com.tv_kids.R;
import quanth.vn.com.tv_kids.fragments.MainActivityFragment;
import quanth.vn.com.tv_kids.models.VideoBinder;

public class CommunityActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String FRAGMENT_TAG = MainActivityFragment.class.getSimpleName();
    private ImageView imgBack;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        init();
        Fresco.initialize(this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, new MainActivityFragment(), FRAGMENT_TAG)
                .commit();
    }

    private void init() {
        imgBack = findViewById(R.id.imv_nav_left);
        imgBack.setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(getString(R.string.community));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imv_nav_left) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (VideoBinder.youTubePlayer != null && VideoBinder.isFullScreen) {
            VideoBinder.youTubePlayer.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }
}
