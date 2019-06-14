package quanth.vn.com.tv_kids.views

import android.app.Dialog
import android.content.Context
import android.text.Spanned
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import quanth.vn.com.tv_kids.R


/**
 * Created by thaivuvo on 2018/04/24
 */

class DialogMessage : Dialog {

    internal var onButtonClick: OnButtonClick? = null
    private var countLoading = 0

    constructor(context: Context, msg: String?) : super(context) {
        initLoadingProgress()
        (findViewById<View>(R.id.txtMsg) as TextView).text = msg ?: ""
    }

    constructor(context: Context, msg: Spanned) : super(context) {
        initLoadingProgress()
        (findViewById<View>(R.id.txtMsg) as TextView).text = msg
    }

    /*public void setTextButtonOK(String message) {
        ((Button) findViewById(R.id.btnOk)).setText(message);
    }*/
    private fun initLoadingProgress() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.dialog_message)
        this.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(this.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        this.window!!.attributes = lp
        this.setCancelable(true)
        this.setCanceledOnTouchOutside(true)
        /*this.findViewById(R.id.btnOk).setOnClickListener(view -> {
            dismiss();
            if(onButtonClick != null){
                onButtonClick.onClick();
            }
        });*/
    }

    fun showMsg(): DialogMessage {
        if (countLoading == 0) {
            super.show()
        }
        countLoading++
        return this
    }

    fun dismissMsg() {
        countLoading--
        if (countLoading > 0) return
        super.dismiss()
    }

    fun forceDismissMsg() {
        countLoading = 0
        super.dismiss()
    }

    fun setOnButtonClick(onButtonClick: OnButtonClick) {
        this.onButtonClick = onButtonClick
    }

    interface OnButtonClick {
        fun onClick()
    }
}
