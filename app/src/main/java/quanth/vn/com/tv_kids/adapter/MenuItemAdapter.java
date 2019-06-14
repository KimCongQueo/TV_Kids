package quanth.vn.com.tv_kids.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import quanth.vn.com.tv_kids.R;
import quanth.vn.com.tv_kids.activities.MainActivity;
import quanth.vn.com.tv_kids.model.MenuItem;

/**
 * Created by Admin on 11/14/16.
 */

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {
    private List<MenuItem> mData;
    private Context mContext;
    private IOnMenuItemClickListener mOnClickListener;
    private MainActivity.MENU_ITEM mCurrentMenu;

    public MenuItemAdapter(Context context, List<MenuItem> data) {
        this.mData = data;
        this.mContext = context;
    }

    public void setItemListener(IOnMenuItemClickListener listener) {
        mOnClickListener = listener;
    }

    public void setItemSelected(MainActivity.MENU_ITEM itemId) {
        this.mCurrentMenu = itemId;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        MenuItem menu = mData.get(position);
        holder.tvName.setText(menu.getName());
        holder.imvImage.setImageResource(menu.getResId());
        if (menu.getId() == MainActivity.MENU_ITEM.MENU_LOGOUT) {
            holder.viewDividerTop.setVisibility(View.VISIBLE);
        } else {
            holder.viewDividerTop.setVisibility(View.GONE);
        }
        if (mCurrentMenu != null && mCurrentMenu == menu.getId() && menu.getId() != MainActivity.MENU_ITEM.MENU_LOGOUT) {
            holder.menuGroup.setSelected(true);
        } else {
            holder.menuGroup.setSelected(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mData.get(position).getId() != MainActivity.MENU_ITEM.MENU_LOGOUT) {
                    mCurrentMenu = mData.get(position).getId();
                }
                if (mOnClickListener != null) {
                    mOnClickListener.onItemClick(mCurrentMenu, mData.get(position).getId());
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imvImage;
        private View menuGroup;
        private View viewDividerTop;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_item);
            tvName.setSelected(true);
            imvImage = view.findViewById(R.id.imv_item);
            menuGroup = view.findViewById(R.id.menu_group);
            viewDividerTop = view.findViewById(R.id.view_divider_top);
        }
    }


    public interface IOnMenuItemClickListener {
        void onItemClick(MainActivity.MENU_ITEM menuId, MainActivity.MENU_ITEM currentMenu);
    }
}
