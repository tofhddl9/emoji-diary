package com.lgtm.emodi.view.edit.picker

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lgtm.emodi.databinding.DialogFragmentEmojiBinding
import com.lgtm.emodi.view.edit.EditFragmentResult
import com.lgtm.emodi.widgets.EmojiPickerAdapter

class EmojiPickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val binding = DialogFragmentEmojiBinding.inflate(layoutInflater)
        with(binding) {
            emojiList.layoutManager = GridLayoutManager(requireContext(), 3)
            emojiList.adapter = EmojiPickerAdapter(::onItemClick)
        }

        val dialog = builder.setView(binding.root).create()

        return dialog
    }

    private fun onItemClick(emojiId: Long) {
        setFragmentResult(
            EditFragmentResult.KEY_EMOJI_PICKER,
            bundleOf(EditFragmentResult.KEY_EMOJI_ID to emojiId)
        )

        findNavController().popBackStack()
    }

}