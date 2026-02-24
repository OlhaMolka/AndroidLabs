package com.example.lab2

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {

    interface OnCancelListener {
        fun onCancel()
    }

    private var listener: OnCancelListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnCancelListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val resultText = view.findViewById<TextView>(R.id.textResult)
        val cancelButton = view.findViewById<Button>(R.id.buttonCancel)

        resultText.text = arguments?.getString("result")

        cancelButton.setOnClickListener {
            listener?.onCancel()
        }

        return view
    }

    companion object {
        fun newInstance(result: String): ResultFragment {
            val fragment = ResultFragment()
            val bundle = Bundle()
            bundle.putString("result", result)
            fragment.arguments = bundle
            return fragment
        }
    }
}