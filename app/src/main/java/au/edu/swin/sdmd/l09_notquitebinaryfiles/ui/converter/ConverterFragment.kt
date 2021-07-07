package au.edu.swin.sdmd.l09_notquitebinaryfiles.ui.converter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import au.edu.swin.sdmd.l09_notquitebinaryfiles.R
import au.edu.swin.sdmd.l09_notquitebinaryfiles.data.HistoryFile

class ConverterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_converter, container, false)
        val bConvert: Button = root.findViewById(R.id.bConvert)
        bConvert.setOnClickListener {
            val etDecimal: EditText = root.findViewById(R.id.etDecimal)
            val sDecimal = etDecimal.text.toString()
            if (sDecimal != "") {
                val iDecimal = sDecimal.toInt()
                val sBinary = Integer.toBinaryString(iDecimal)
                val tvResult = root.findViewById<TextView>(R.id.tvBinary)
                tvResult.text = sBinary

                /**
                 * This is where we update our files.
                 */
                updateSharedPrefs(sDecimal)
                updateHistory(sDecimal, sBinary)
            } else {
                val tvBinary: TextView = root.findViewById(R.id.tvBinary)
                tvBinary.text = "No number entered"
            }
        }
        return root
    }

    private fun updateSharedPrefs(sDecimal: String?) {
        /**
         * For writing to a shared preference file, we need to name
         * the file first.
         */
        val sharedPref = requireContext().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        /**
         * Using an Editor to add/update values in the file before committing.
         */
        val editor = sharedPref?.edit()
        editor?.putString(getString(R.string.last_input), sDecimal)
        editor?.apply()
    }

    private fun updateHistory(sDecimal: String, sBinary: String) {
        context?.let {
            HistoryFile.appendInput(it, "$sDecimal = $sBinary")
        }
    }
}