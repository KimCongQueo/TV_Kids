package quanth.vn.com.tv_kids.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quanth.vn.com.tv_kids.R;
import quanth.vn.com.tv_kids.models.Image;
import quanth.vn.com.tv_kids.models.Video;
import quanth.vn.com.tv_kids.views.CustomCmtDialog;

public class MainActivityFragment extends Fragment implements IFragmentManager {
//    private static final String TAG = MainActivityFragment.class.getSimpleName();

    RecyclerView videos;
    List<Video> mList = new ArrayList<>();
    YouTubeAdapter adapter = new YouTubeAdapter(getContext(), this);
    float scale;

    public MainActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        scale = getResources().getDisplayMetrics().density;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_communication, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videos = view.findViewById(R.id.videos);
        videos.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();
        addListener();
    }

    private void addListener() {
        adapter.setOnItemClickListener(new YouTubeAdapter.IOnItemClickedListener() {
            @Override
            public void onItemClick(String id) {

            }

            @Override
            public void onItemClickComment(String id) {
                CustomCmtDialog dialog = new CustomCmtDialog(Objects.requireNonNull(getContext()));
                Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.DialogTheme;
                dialog.show();
            }
        });
    }

    private void initData() {
        Image img1 = new Image("https://i.ytimg.com/vi/ddaEtFOsFeM/hqdefault.jpg", 480, 360);
        Image img2 = new Image("https://i.ytimg.com/vi/L0NZW6pgSLc/hqdefault.jpg", 480, 360);
        Image img3 = new Image("https://i.ytimg.com/vi/2bUScL5ojlY/hqdefault.jpg", 480, 360);
        Image img4 = new Image("https://i.ytimg.com/vi/UA-_--OS6i0/hqdefault.jpg", 480, 360);
        Image img5 = new Image("https://i.ytimg.com/vi/KdrbBJNFwGw/hqdefault.jpg", 480, 360);
        mList.add(new Video("ddaEtFOsFeM",img1, "Đen ft. MIN - Bài Này Chill Phết (M/V)", "aaaaaaaaaa"));
        mList.add(new Video("L0NZW6pgSLc",img2, "Đen - Mười Năm ft. Ngọc Linh (M/V) (Lộn Xộn 3)", "aaaaaaaaaa"));
        mList.add(new Video("2bUScL5ojlY",img3, "Đen x Chi Pu x Lynk Lee - Nếu Mình Gần Nhau (Audio)", "aaaaaaaaaa"));
        mList.add(new Video("UA-_--OS6i0",img4, "Đen x JustaTee - Đố em biết anh đang nghĩ gì ft. Biên (M/V)", "aaaaaaaaaa"));
        mList.add(new Video("KdrbBJNFwGw",img5, "Đen - Anh Đếch Cần Gì Nhiều Ngoài Em ft. Vũ., Thành Đồng (M/V)", "aaaaaaaaaa"));
        adapter.setVideos(mList);
        videos.setAdapter(adapter);
    }

    @Override
    public FragmentManager getSupportFragmentManager() {
        return getFragmentManager();
    }

    @Override
    public Fragment getSupportFragment() {
        return this;
    }
}
