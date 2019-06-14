package quanth.vn.com.tv_kids.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import quanth.vn.com.tv_kids.R;

public class CustomCmtDialog extends Dialog implements View.OnClickListener {
    private TextView tvEdit;
    private TextView tvDelete;
    private View viewDevider;
    private ConstraintLayout layoutEdit;
    private EditText etEdit;
    private TextView btnDone;
    private TextView btnCancel;
    private Context mContext;
    private IOnItemClickedListener mIOnItemClickedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_comment);
        Window window = this.getWindow();
        assert window != null;
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.getWindow().setAttributes(wlp);

        init();
        addListener();
    }

    public CustomCmtDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public void setmIOnItemClickedListener(IOnItemClickedListener mIOnItemClickedListener) {
        this.mIOnItemClickedListener = mIOnItemClickedListener;
    }

    private void init() {

    }

    private void addListener() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    public interface IOnItemClickedListener {
        void onItemClickDelete(boolean isDelete);
        void onItemEdit(String edit);
    }
}
