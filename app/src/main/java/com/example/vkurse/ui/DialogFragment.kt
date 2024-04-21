package com.example.vkurse.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.vkurse.R
import com.example.vkurse.ui.gallery.University

class UniversityInfoDialogFragment : DialogFragment() {

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_INFO = "info"

        fun newInstance(university: University): UniversityInfoDialogFragment {
            val args = Bundle()
            args.putString(ARG_NAME, university.name)
            args.putString(ARG_INFO, university.info)

            val fragment = UniversityInfoDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.university_info_layout, null)
        view.findViewById<TextView>(R.id.universityNameTextView).text = arguments?.getString(ARG_NAME)
        view.findViewById<TextView>(R.id.universityInfoTextView).text = arguments?.getString(ARG_INFO)

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
    }
}