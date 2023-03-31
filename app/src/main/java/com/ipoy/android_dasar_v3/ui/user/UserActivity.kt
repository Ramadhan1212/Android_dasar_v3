package com.ipoy.android_dasar_v3.ui.user

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ipoy.android_dasar_v3.R
import com.ipoy.android_dasar_v3.databinding.ActivityUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var vm: UserViewModel
    private lateinit var repoUrl: String

    companion object{
        const val UN = "un"
        const val ID = "id"
        const val AVATAR = "avatar_url"
        const val HTML = "html_url"
        const val TYPE = "type"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val un = intent.getStringExtra(UN).toString()
        val id = intent.getIntExtra(ID, 0)
        val avatar = intent.getStringExtra(AVATAR).toString()
        val link = intent.getStringExtra(HTML).toString()
        val type = intent.getStringExtra(TYPE).toString()

        val bundle = Bundle()
        bundle.putString(UN, un)


        vm = ViewModelProvider(this).get(UserViewModel::class.java)
        vm.set(un)
        vm.get().observe(this) {
            if (it != null) {
                binding.apply {
                    Glide.with(this@UserActivity)
                        .load(it.avatar)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(avatarImg)
                    tvFn.text = it.name
                    tvUn.text = it.username
                    if (it.workplace == null){
                        tvWorkplace.text = getString(R.string.nullwp)
                    } else {
                        tvWorkplace.text = it.workplace
                    }
                    if (it.home == null){
                        tvHome.text = getString(R.string.nullhome)
                    } else {
                        tvHome.text = it.home
                    }
                    tvFollowersCount.text = it.followers_count
                    tvFollowingCount.text = it.following_count
                    tvRepocount.text = it.repository
                    repoUrl = "https://github.com/${it.username}?tab=repositories"

                    binding.fabRepo.setOnClickListener{
                        val i = Intent(Intent.ACTION_VIEW)
                        i.data = Uri.parse(repoUrl)
                        startActivity(i)
                        val test = Toast.makeText(this@UserActivity, repoUrl, Toast.LENGTH_LONG)
                        test.show()
                    }
                }
            }
        }


        var _isCheck = false
        CoroutineScope(Dispatchers.IO).launch {
            val c = vm.check(id)
            withContext(Dispatchers.Main) {
                if (c != null){
                    if (c > 0){
                        binding.tbUser.isChecked = true
                        _isCheck = true
                    }
                    else {
                        binding.tbUser.isChecked = false
                        _isCheck = false
                    }
                }
            }
        }

        binding.tbUser.setOnClickListener {
            _isCheck = !_isCheck
            if (_isCheck) {
                vm.add(un, id, avatar, link, type)
            }
            else {
                vm.remove(id)
            }
            binding.tbUser.isChecked = _isCheck
        }

        val pager = PagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            vpFoll.adapter = pager
            tlFoll.setupWithViewPager(vpFoll)
        }
    }
}