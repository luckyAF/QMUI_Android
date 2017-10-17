package com.qmuiteam.qmuidemo.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.QMUITopBar
import com.qmuiteam.qmuidemo.R

/**
 * 沉浸式状态栏的调用示例。
 * Created by Kayo on 2016/12/12.
 */

class TranslucentActivity : AppCompatActivity() {
    private var mTopBar: QMUITopBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        if (intent != null) {
            val isTranslucent = intent.getBooleanExtra(ARG_CHANGE_TRANSLUCENT, true)
            if (isTranslucent) {
                QMUIStatusBarHelper.translucent(this) // 沉浸式状态栏
            }
        }

        val root = LayoutInflater.from(this).inflate(R.layout.activity_translucent, null)
        mTopBar = root.findViewById(R.id.topbar) as QMUITopBar
        initTopBar()
        setContentView(root)

    }

    private fun initTopBar() {
        mTopBar!!.setBackgroundColor(ContextCompat.getColor(this, R.color.app_color_theme_4))
        mTopBar!!.addLeftBackImageButton().setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right)
        }

        mTopBar!!.setTitle("沉浸式状态栏示例")
    }

    companion object {

        private val ARG_CHANGE_TRANSLUCENT = "ARG_CHANGE_TRANSLUCENT"
        private val ARG_STATUSBAR_MODE = "ARG_STATUSBAR_MODE"

        fun createActivity(context: Context, isTranslucent: Boolean): Intent {
            val intent = Intent(context, TranslucentActivity::class.java)
            intent.putExtra(ARG_CHANGE_TRANSLUCENT, isTranslucent)
            return intent
        }
    }
}
