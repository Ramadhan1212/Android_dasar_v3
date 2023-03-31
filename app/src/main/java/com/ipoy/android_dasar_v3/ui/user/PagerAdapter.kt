package com.ipoy.android_dasar_v3.ui.user

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ipoy.android_dasar_v3.R

class PagerAdapter(private val context: Context, fm: FragmentManager, data: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var fB: Bundle

    init {
        fB = data
    }

    @StringRes
    private val TAB = intArrayOf(R.string.titles_followers, R.string.titles_following)

    override fun getCount(): Int {
        val tab = 2
        return tab
    }

    override fun getItem(position: Int): Fragment {
        var page: Fragment? = null
        when(position){
            0 -> page = FollowersFragment()
            1 -> page = FollowingFragment()
        }
        page?.arguments = this.fB
        return page as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB[position])
    }

}