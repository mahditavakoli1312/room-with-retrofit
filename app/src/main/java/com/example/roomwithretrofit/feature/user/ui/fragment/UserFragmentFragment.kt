package com.example.roomwithretrofit.feature.user.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.roomwithretrofit.R
import com.example.roomwithretrofit.databinding.FragmentUserFragmentBinding
import com.example.roomwithretrofit.feature.user.ui.UserFragmentState
import com.example.roomwithretrofit.feature.user.ui.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragmentFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var binding : FragmentUserFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_user_fragment,
            container,
            false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentStateTV = view.findViewById<TextView>(R.id.tv_fragmentState_UserFragmen)
        val mListView = view.findViewById<ListView>(R.id.lv_usersList_userFragment)

        binding.apply {
            srlRefreshUserFragment.setOnRefreshListener {
                userViewModel.fetchUers()
                srlRefreshUserFragment.setRefreshing(false);

            }
        }
        userViewModel.users.observe(viewLifecycleOwner) {
            val users = it?.map {
                "${it.firstName} ${it.lastName}"
            }
            val arrayAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                users!!
            )
            mListView.adapter = arrayAdapter
        }
        userViewModel.fragmentState.observe(viewLifecycleOwner) { userFragmentState ->
            when (userFragmentState) {
                UserFragmentState.SUCCESS -> {
                    Toast.makeText(
                        requireContext(),
                        "Success",
                        Toast.LENGTH_SHORT
                    ).show()
                    fragmentStateTV.text = "fragment state is : Success"
                }

                UserFragmentState.FAILURE -> {
                    Toast.makeText(
                        requireContext(),
                        userViewModel.fragmentStateMessage.value,
                        Toast.LENGTH_SHORT
                    ).show()
                    fragmentStateTV.text = "$userFragmentState " +
                            "${userViewModel.fragmentStateMessage.value}"

                }

                UserFragmentState.APPERROR -> {
                    Toast.makeText(
                        requireContext(),
                        userViewModel.fragmentStateMessage.value,
                        Toast.LENGTH_SHORT
                    ).show()
                    fragmentStateTV.text = "$userFragmentState " +
                            "${userViewModel.fragmentStateMessage.value}"
                }

                UserFragmentState.INITIAL_STATE -> Toast.makeText(
                    requireContext(),
                    "INITIAL_STATE",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }
}