package com.lgtm.emoji_diary.data.source

import com.lgtm.emoji_diary.data.Diary

interface DiaryDataSource {

    suspend fun getDiaries(): Result<List<Diary>>

}