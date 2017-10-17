package com.qmuiteam.qmuidemo.fragment

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

import com.qmuiteam.qmui.util.QMUIPackageHelper
import com.qmuiteam.qmui.widget.QMUITopBar
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import com.qmuiteam.qmuidemo.R
import com.qmuiteam.qmuidemo.base.BaseFragment

import java.text.SimpleDateFormat
import java.util.Locale

import butterknife.BindView
import butterknife.ButterKnife

/**
 * 关于界面

 * Created by Kayo on 2016/11/18.
 */
class QDAboutFragment : BaseFragment() {

    @BindView(R.id.topbar) internal var mTopBar: QMUITopBar? = null
    @BindView(R.id.version) internal var mVersionTextView: TextView? = null
    @BindView(R.id.about_list) internal var mAboutGroupListView: QMUIGroupListView? = null
    @BindView(R.id.copyright) internal var mCopyrightTextView: TextView? = null

    override fun onCreateView(): View {
        val root = LayoutInflater.from(activity).inflate(R.layout.fragment_about, null)
        ButterKnife.bind(this, root)

        initTopBar()

        mVersionTextView!!.text = QMUIPackageHelper.getAppVersion(context)

        QMUIGroupListView.newSection(context)
                .addItemView(mAboutGroupListView!!.createItemView(resources.getString(R.string.about_item_homepage))) {
                    val url = "http://qmuiteam.com/android/page/index.html"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                }
                .addItemView(mAboutGroupListView!!.createItemView(resources.getString(R.string.about_item_github))) {
                    val url = "https://github.com/QMUI/QMUI_Android"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                }
                .addTo(mAboutGroupListView)

        val dateFormat = SimpleDateFormat("yyyy", Locale.CHINA)
        val currentYear = dateFormat.format(java.util.Date())
        mCopyrightTextView!!.setText(String.format(resources.getString(R.string.about_copyright), currentYear))

        return root
    }

    private fun initTopBar() {
        mTopBar!!.addLeftBackImageButton().setOnClickListener { popBackStack() }

        mTopBar!!.setTitle(resources.getString(R.string.about_title))
    }
}
