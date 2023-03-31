package com.ipoy.android_dasar_v3.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipoy.android_dasar_v3.data.User
import com.ipoy.android_dasar_v3.databinding.FragmentHomeBinding
import com.ipoy.android_dasar_v3.ui.user.UserActivity

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: HomeViewModel
    private lateinit var adapter: Adapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        adapter = Adapter()
        adapter.notifyDataSetChanged()

        adapter.setOnClick(object : Adapter.OnClick {
            override fun onClicked(data: User) {
                Intent(requireContext(), UserActivity::class.java).also {
                    it.putExtra(UserActivity.UN, data.username)
                    it.putExtra(UserActivity.ID, data.userid)
                    it.putExtra(UserActivity.AVATAR, data.avatar)
                    it.putExtra(UserActivity.HTML, data.link)
                    it.putExtra(UserActivity.TYPE, data.type)
                    startActivity(it)
                }
            }
        })

        vm = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)

        binding.apply {
            rvSearch.setHasFixedSize(true)
            rvSearch.layoutManager = LinearLayoutManager(activity)
            rvSearch.adapter = adapter

            btnSearch.setOnClickListener {
                search()
            }

            etQuery.setOnKeyListener { v, i, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                    search()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        vm.get().observe(viewLifecycleOwner){
            if (it != null){
                adapter.setList(it)
                showLoading(false)
            }
        }

    }

    private fun search(){
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            vm.set(query)
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.pbHome.visibility = View.VISIBLE
        } else {
            binding.pbHome.visibility = View.GONE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}