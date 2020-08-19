package com.book.store.stock.bookstore.pages

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.viewpager.widget.ViewPager
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.base_ui.BaseMainPageDaggerFragment
import com.book.store.stock.bookstore.databinding.MainActivityBinding
import com.book.store.stock.bookstore.utility.AppSharedPreferences
import com.book.store.stock.bookstore.utility.Updatable
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemReselectedListener,
    NavController.OnDestinationChangedListener {


    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences

    lateinit var binding: MainActivityBinding

    private val backStack = Stack<Int>()

    private val fragments = listOf(
        BaseMainPageDaggerFragment.newInstance(
            R.layout.content_settings_base,
            R.id.toolbar_setting,
            R.id.nav_host_settings
        ),
        BaseMainPageDaggerFragment.newInstance(
            R.layout.content_shop_base,
            R.id.toolbar_order,
            R.id.nav_host_order
        ),
        BaseMainPageDaggerFragment.newInstance(
            R.layout.content_dashboard_base,
            R.id.toolbar_dashboard,
            R.id.nav_host_dashboard
        )
    )


    private val indexToPage = mapOf(0 to R.id.setting, 1 to R.id.shop, 2 to R.id.dashboard)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        binding.mainPager.addOnPageChangeListener(this)
        binding.mainPager.adapter = ViewPagerAdapter()
        binding.mainPager.post(this::checkDeepLink)
        binding.mainPager.offscreenPageLimit = fragments.size

        binding.navigation.setOnNavigationItemSelectedListener(this)
        binding.navigation.setOnNavigationItemReselectedListener(this)
        setItem(2)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position = indexToPage.values.indexOf(item.itemId)
        if (main_pager.currentItem != position) setItem(position)
        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        val position = indexToPage.values.indexOf(item.itemId)
        val fragment = fragments[position]
        fragment.popToRoot()
    }

    override fun onBackPressed() {
        val fragment = fragments[main_pager.currentItem]
        val hadNestedFragments = fragment.onBackPressed()
        if (!hadNestedFragments) {
            if (backStack.size > 1) {
                backStack.pop()
                main_pager.currentItem = backStack.peek()

            } else
                super.onBackPressed()
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

    override fun onPageSelected(page: Int) {
        val itemId = indexToPage[page] ?: R.id.dashboard
        if (binding.navigation.selectedItemId != itemId) binding.navigation.selectedItemId = itemId
    }

    private fun setItem(position: Int) {
        main_pager.currentItem = position
        fragments[position].navController?.addOnDestinationChangedListener(this)
    }

    private fun checkDeepLink() {

    }

    inner class ViewPagerAdapter :
        FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size
        override fun getItemPosition(`object`: Any): Int {
            return POSITION_NONE
        }

    }


    fun setStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
    }


    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (destination.id == R.id.dashBoardFragment || destination.id == R.id.settingFragment || destination.id == R.id.orderFragment) {
            if (binding.navigation.visibility != View.VISIBLE) {
                YoYo.with(Techniques.SlideInUp).duration(500).playOn(binding.navigation)
                binding.navigation.visibility = View.VISIBLE
            }
        } else {
            YoYo.with(Techniques.SlideOutDown).duration(500).playOn(binding.navigation)
            Handler().postDelayed({ binding.navigation.visibility = View.GONE }, 300)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (destination.id == R.id.dashBoardFragment) {
                setStatusBarColor(R.color.colorPrimaryDark)
            } else {
                setStatusBarColor(R.color.grayDark)
            }
        }
    }


    fun reCreatePagerFragment(popToRoot: Boolean = true) {
        fragments.forEach {
            if (popToRoot)
                it.popToRoot()
            val fragment = it.childFragmentManager.fragments[0].childFragmentManager.fragments[0]
            fragment?.let {
                if (fragment is Updatable)
                    fragment.refresh()
            }
        }
    }
}
