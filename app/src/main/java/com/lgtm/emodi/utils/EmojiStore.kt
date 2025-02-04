package com.lgtm.emodi.utils

import android.content.Context
import com.lgtm.emodi.R
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat


// TODO : Hardcoded Emoji Store :(
object EmojiStore {
    val emojiDrawableResMap = mapOf<Int, EmojiInfo>(
        0 to EmojiInfo(0L, R.drawable.smile),
        1 to EmojiInfo(1L, R.drawable.angry),
        2 to EmojiInfo(2L, R.drawable.cool),
        3 to EmojiInfo(3L, R.drawable.cry),
        4 to EmojiInfo(4L, R.drawable.sad),
        5 to EmojiInfo(5L, R.drawable.scared),
        6 to EmojiInfo(6L, R.drawable.soso),
        7 to EmojiInfo(7L, R.drawable.shocked),
        8 to EmojiInfo(8L, R.drawable.sleepy),
        9 to EmojiInfo(9L, R.drawable.heart),
        10 to EmojiInfo(10L, R.drawable.party),
        11 to EmojiInfo(11L, R.drawable.hungry),
        12 to EmojiInfo(12L, R.drawable.joy),
        13 to EmojiInfo(13L, R.drawable.thinking),
        14 to EmojiInfo(14L, R.drawable.sweat),
    )

    fun getEmojiDrawable(context: Context, emojiId: Long): Drawable? {
        return emojiDrawableResMap[emojiId.toInt()]?.let {
            ResourcesCompat.getDrawable(context.resources, it.drawableInt, null)
        } ?: run {
            ResourcesCompat.getDrawable(context.resources, R.drawable.emoji_select, null)
        }
    }
}

data class EmojiInfo(
    val id: Long,
    val drawableInt: Int,
)
