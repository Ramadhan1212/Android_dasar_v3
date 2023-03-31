package com.ipoy.android_dasar_v3.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipoy.android_dasar_v3.data.User
import com.ipoy.android_dasar_v3.databinding.FragmentFavoriteBinding
import com.ipoy.android_dasar_v3.ui.home.Adapter
import com.ipoy.android_dasar_v3.ui.user.UserActivity

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val FavoriteVM = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.bind(view)

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

        binding.apply {
            rvFv.setHasFixedSize(true)
            rvFv.layoutManager = LinearLayoutManager(activity)
            rvFv.adapter = adapter
        }

        FavoriteVM.get()?.observe(viewLifecycleOwner) {
            if (it != null) {
                val list = map(it)
                adapter.setList(list)
            }
        }

    }

    private fun map(fave: List<FavoriteDC>): ArrayList<User> {
        val list = ArrayList<User>()
        for (user in fave) {
            val mapping = User(
                user.username,
                user.userid,
                user.avatar,
                user.link,
                user.type
            )
            list.add(mapping)
        }
        return list
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}