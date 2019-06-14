package quanth.vn.com.tv_kids.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import quanth.vn.com.tv_kids.R;
import quanth.vn.com.tv_kids.views.RoundedConnerImageView;

public class CommunicationActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String YOUTUBE_APP_KEY = "AIzaSyDzsNBdJrDqFhHJSwQQgiUA8r0FYC4b0XU";
    private String VIDEO_ID;
    YouTubePlayerView youTubeView;
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_communi);

        RoundedConnerImageView img = findViewById(R.id.imgAvatar);
        Glide.with(this).load(R.drawable.demo1).into(img);
//        youTubeView = findViewById(R.id.youtube_view);
        txtName = findViewById(R.id.tvName);
        youTubeView.initialize(YOUTUBE_APP_KEY, CommunicationActivity.this);
        VIDEO_ID = "stUq527vvtQ";
        Toast.makeText(this, VIDEO_ID, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.setShowFullscreenButton(true);
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        String error = "Không thể load video! Kiểm tra Internet và ứng dụng Youtube trên máy của bạn!";
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
