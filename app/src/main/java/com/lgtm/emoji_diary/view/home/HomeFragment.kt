package com.lgtm.emoji_diary.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.lgtm.emoji_diary.R
import com.lgtm.emoji_diary.ViewPagerFragmentStateAdapter
import com.lgtm.emoji_diary.databinding.FragmentHomeBinding
import com.lgtm.emoji_diary.delegate.viewBinding
import com.lgtm.emoji_diary.utils.CalendarUtil
import com.lgtm.emoji_diary.utils.setChildFragmentResultListener
import com.lgtm.emoji_diary.view.detail.DetailFragmentDirections
import com.lgtm.emoji_diary.view.edit.getCurrentSimpleDate
import com.lgtm.emoji_diary.view.home.calendar.CalendarFragment
import com.lgtm.emoji_diary.view.home.timeline.TimelineFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding : FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()

        setupNavigation()

        setupFragmentResultListener()
    }

    private fun setupViewPager() {
        ViewPagerFragmentStateAdapter(
            FRAGMENT_LIST,
            childFragmentManager,
            lifecycle
        ).also {
            binding.viewPager.adapter = it
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = TAB_NAME[position]
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.viewPager.isUserInputEnabled = FRAGMENT_LIST[position] !is CalendarFragment
            }
        })
    }

    private fun setupNavigation() {
        val navController = findNavController()
        binding.fab.setOnClickListener {
            val lastSelectedDate = viewModel.selectedDate.value ?: getCurrentSimpleDate()
            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(lastSelectedDate)
            navController.navigate(action)
        }

    }

    private fun setupFragmentResultListener() {
        setChildFragmentResultListener(HomeFragmentResult.KEY_TIMELINE_ON_CLICK) { _, result ->
            val diaryId = result.getLong(HomeFragmentResult.KEY_DIARY_ID)
            // viewModel.onDiaryClick(diaryId)
            moveToDetailPage(diaryId)
        }

        setChildFragmentResultListener(HomeFragmentResult.KEY_CALENDAR_ON_CLICK) { _, result ->
            val diaryId = result.getLong(HomeFragmentResult.KEY_DIARY_ID)
            // viewModel.onDiaryClick(diaryId)
            // do something
            // 고민되는 부분이다... 클릭된 날짜에 대한 정보를 Home 에서도 갱신해 놓을지...
        }
    }

    private fun moveToDetailPage(diaryId: Long) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(diaryId)
        findNavController().navigate(action)
    }


    companion object {
        val FRAGMENT_LIST = arrayListOf(
            CalendarFragment.newInstance(),
            TimelineFragment.newInstance(),
        )

        val TAB_NAME = arrayListOf(
            "캘린더",
            "타임라인",
        )
    }
}