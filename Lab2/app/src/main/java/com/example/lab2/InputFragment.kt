package com.example.lab2

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class InputFragment : Fragment() {

    interface OnDataSendListener {
        fun onDataSend(result: String)
    }

    private var listener: OnDataSendListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnDataSendListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        val edit1 = view.findViewById<EditText>(R.id.editText1)
        val edit2 = view.findViewById<EditText>(R.id.editText2)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val buttonOk = view.findViewById<Button>(R.id.buttonOk)

        buttonOk.setOnClickListener {

            val n1 = edit1.text.toString()
            val n2 = edit2.text.toString()
            val selected = radioGroup.checkedRadioButtonId

            if (n1.isEmpty() || n2.isEmpty() || selected == -1) {
                Toast.makeText(requireContext(),
                    getString(R.string.fill_all_fields),
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val num1 = n1.toDouble()
            val num2 = n2.toDouble()

            val result = when (selected) {
                R.id.radioAdd -> num1 + num2
                R.id.radioSubtract -> num1 - num2
                R.id.radioMultiply -> num1 * num2
                R.id.radioDivide -> {
                    if (num2 == 0.0) {
                        Toast.makeText(requireContext(),
                            getString(R.string.division_by_zero),
                            Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    num1 / num2
                }
                else -> 0.0
            }

            listener?.onDataSend(result.toString())
        }

        return view
    }
}