package com.lv.wanandroid.main

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.hjq.toast.ToastUtils
import com.lv.wanandroid.R
import com.lv.wanandroid.base.BaseActivity
import com.lv.wanandroid.main.adapter.VpAdapter
import com.lv.wanandroid.main.mvp.MainContract
import com.lv.wanandroid.main.mvp.MainPresenter
import com.lv.wanandroid.module.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun createPresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    override fun bindView() {

        initTabLayout()

        initNav()
    }

    private fun initNav() {
        main_menu_iv.setOnClickListener {
            if (main_drawerLayout.isDrawerOpen(main_navigation)) {
                main_drawerLayout.closeDrawer(main_navigation)
            } else {
                main_drawerLayout.openDrawer(main_navigation)
            }
        }
        main_navigation.setNavigationItemSelectedListener {
            main_drawerLayout.closeDrawer(main_navigation)
            when (it.itemId) {
                R.id.nav_collect -> {
                    ToastUtils.show("我的收藏")
                }
                R.id.nav_integral -> {
                    ToastUtils.show("我的积分")
                }
                R.id.nav_settings -> {
                    ToastUtils.show("设置")
                }
                R.id.nav_regard -> {
                    ToastUtils.show("关于")
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun initTabLayout() {

        main_tab.setupWithViewPager(main_viewpager)
        main_viewpager.offscreenPageLimit = 4
        main_viewpager.adapter =
            VpAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        main_search_iv.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
//        val tabText = arrayOf("首页", "项目", "体系", "干货")
//        main_viewpager.adapter = VpAdapter(this)
//        TabLayoutMediator(main_tab, main_viewpager,
//            object : TabLayoutMediator.OnConfigureTabCallback {
//                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
//                    tab.text = tabText[position]
//                }
//            }).attach()
    }

    override fun showDialog(result: String) {
        Toast.makeText(this, "弹出 Dialog $result", Toast.LENGTH_LONG).show()
    }


}

