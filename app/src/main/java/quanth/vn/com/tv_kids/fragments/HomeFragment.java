package quanth.vn.com.tv_kids.fragments;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import quanth.vn.com.tv_kids.R;
import quanth.vn.com.tv_kids.activities.CommunicationActivity;
import quanth.vn.com.tv_kids.activities.CommunityActivity;
import quanth.vn.com.tv_kids.adapter.CategoryAdapter;
import quanth.vn.com.tv_kids.models.Category;

public class HomeFragment extends BaseFragment {
    private CategoryAdapter mAdapter;
    private ArrayList<Category> mData = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initComponents() {
        RecyclerView rcCategory = mView.findViewById(R.id.rcCategory);
        rcCategory.setLayoutManager(new GridLayoutManager(mContext, 3));
        mData.add(new Category(R.drawable.demo1, "Fairy tail"));
        mData.add(new Category(R.drawable.demo2, "Duck"));
        mData.add(new Category(R.drawable.demo3, "Doraemon"));
        mData.add(new Category(R.drawable.demo4, "Cartoon"));
        mData.add(new Category(R.drawable.demo5, "Ben 10"));
        mData.add(new Category(R.drawable.demo6, "Community"));
        mAdapter = new CategoryAdapter(mContext, mData);
        rcCategory.setAdapter(mAdapter);
    }

    @Override
    protected void addListener() {
        mAdapter.setItemListener(new CategoryAdapter.IOnMenuItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                if (mData.get(pos).getNameCategory().equals("Community")) {
                    startActivity(new Intent(mContext, CommunityActivity.class));
                }
            }
        });
    }
}
