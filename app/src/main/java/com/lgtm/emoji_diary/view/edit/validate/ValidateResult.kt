package com.lgtm.emoji_diary.view.edit.validate

data class ValidateResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)