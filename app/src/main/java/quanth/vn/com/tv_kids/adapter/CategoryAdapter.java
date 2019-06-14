package quanth.vn.com.tv_kids.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import quanth.vn.com.tv_kids.R;
import quanth.vn.com.tv_kids.models.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> mData;
    private Context mContext;
    private IOnMenuItemClickListener mOnClickListener;

    public CategoryAdapter(Context context, List<Category> data) {
        this.mData = data;
        this.mContext = context;
    }

    public void setItemListener(IOnMenuItemClickListener listener) {
        mOnClickListener = listener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Category item = mData.get(position);
        if (item != null) {
            Glide.with(mContext).load(item.getResId()).into(holder.imgCategory);
            holder.tvCategory.setText(item.getNameCategory());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        ImageView imgCategory;
        ConstraintLayout layoutCategory;

        ViewHolder(View view) {
            super(view);
            tvCategory = view.findViewById(R.id.tvCategory);
            imgCategory = view.findViewById(R.id.imgCategory);
            layoutCategory = view.findViewById(R.id.layoutCategory);
            layoutCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnClickListener != null){
                        mOnClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }


    public interface IOnMenuItemClickListener {
        void onItemClick(int pos);
    }
}
