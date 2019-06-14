package quanth.vn.com.tv_kids.fragments;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import quanth.vn.com.tv_kids.R;
import quanth.vn.com.tv_kids.models.Video;

public class YouTubeAdapter extends RecyclerView.Adapter<YouTubeAdapter.VideoViewHolder> {
    private List<Video> videos;
    private IOnItemClickedListener mIOnItemClickedListener;
    private IFragmentManager fragmentManager;
    private Context mContext;

    YouTubeAdapter(Context mContext, IFragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.mContext = mContext;
    }

    void setOnItemClickListener(IOnItemClickedListener listener) {
        mIOnItemClickedListener = listener;
    }

    void setVideos(List<Video> videos) {
        if (this.videos == null) {
            this.videos = new ArrayList<>(videos.size());
            this.videos.addAll(videos);
            return;
        }
        this.videos.clear();
        this.videos.addAll(videos);
    }

    @NotNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_communi, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull VideoViewHolder holder, int position) {

    }

    @Override
    public void onViewAttachedToWindow(@NotNull YouTubeAdapter.VideoViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getAdapterPosition();
        Video video = videos.get(position);
        video.binder.prepare();
        video.binder.bind(holder, fragmentManager);
    }

    @Override
    public void onViewDetachedFromWindow(@NotNull YouTubeAdapter.VideoViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        int position = holder.getAdapterPosition();
        Video video = videos.get(position);
        video.binder.unBind(holder, fragmentManager);
    }

    @Override
    public int getItemCount() {
        return this.videos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView image;
        //    public TextView title;
        public TextView tvCaption;
        TextView tvCreatedAt;
        TextView tvName;
        TextView tvLike;
        TextView tvBookmark;
        public FrameLayout videoContainer;
        ImageView imgAvatar;
        ImageView imgLike;
        ImageView imgBookmark;
        public RelativeLayout layoutVideo;
        ConstraintLayout layoutRoot;
        ConstraintLayout layoutLike;
        ConstraintLayout layoutBookmark;
        ConstraintLayout layoutComment;

        VideoViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgBookmark = itemView.findViewById(R.id.imgBookmark);
            layoutLike = itemView.findViewById(R.id.layoutLike);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvName = itemView.findViewById(R.id.tvName);
            layoutRoot = itemView.findViewById(R.id.layoutRoot);
            layoutBookmark = itemView.findViewById(R.id.layoutBookmark);
            layoutComment = itemView.findViewById(R.id.layoutComment);
            imgLike = itemView.findViewById(R.id.imgLike);
            tvLike = itemView.findViewById(R.id.tvLike);
            tvBookmark = itemView.findViewById(R.id.tvBookmark);
//        title = (TextView) itemView.findViewById(R.id.title);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            videoContainer = itemView.findViewById(R.id.video_container);
            layoutVideo = itemView.findViewById(R.id.youtube_view1);
            layoutRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIOnItemClickedListener != null) {
                        mIOnItemClickedListener.onItemClick(videos.get(getLayoutPosition()).getVideoId());
                    }
                }
            });
            layoutComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIOnItemClickedListener != null) {
                        mIOnItemClickedListener.onItemClickComment(videos.get(getLayoutPosition()).getVideoId());
                    }
                }
            });
            layoutLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (videos.get(getLayoutPosition()).getLike()) {
                        videos.get(getLayoutPosition()).setLike(false);
                        imgLike.setImageResource(R.drawable.ic_like);
                        tvLike.setTextColor(Color.parseColor("#414141"));
                    } else {
                        videos.get(getLayoutPosition()).setLike(true);
                        imgLike.setImageResource(R.drawable.ic_like_sl);
                        tvLike.setTextColor(Color.parseColor("#0f9ac4"));
                    }
                }
            });
            layoutBookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (videos.get(getLayoutPosition()).getBookmark()) {
                        videos.get(getLayoutPosition()).setBookmark(false);
                        imgBookmark.setImageResource(R.drawable.ic_bookmark);
                        tvBookmark.setTextColor(Color.parseColor("#414141"));
                    } else {
                        videos.get(getLayoutPosition()).setBookmark(true);
                        imgBookmark.setImageResource(R.drawable.ic_bookmark_sl);
                        tvBookmark.setTextColor(Color.parseColor("#0f9ac4"));
                    }
                }
            });
        }
    }

    public interface IOnItemClickedListener {
        void onItemClick(String id);
        void onItemClickComment(String id);
    }
}
