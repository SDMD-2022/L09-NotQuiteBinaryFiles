package au.edu.swin.sdmd.l09_notquitebinaryfiles.ui.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import au.edu.swin.sdmd.l09_notquitebinaryfiles.R
import au.edu.swin.sdmd.l09_notquitebinaryfiles.data.HistoryFile

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_history, container, false)

        /**
         * check if something is stored in the shared preferences file to show,
         * and update the TextView on screen.
         */
        val sHistory: String? = checkSharedPrefs()
        sHistory?.let {
            val tvHistory: TextView = root.findViewById(R.id.history)
            tvHistory.text = String.format("Last value converted was %s", it)
        }

        /**
         * The detailed retrieval of data from the file has moved to another
         * class; this gets a list of the strings in the file and provides them
         * to the list adapter. This is just using the old style list widget for
         * simplicity.
         */
        context?.let { context ->
            val numbers = HistoryFile.getFileContents(context)
            val lvNumbers: ListView = root.findViewById(R.id.history_list)
            lvNumbers.adapter = ArrayAdapter<Any?>(
                context,
                android.R.layout.simple_list_item_1, numbers
            )
        }
        return root
    }

    private fun checkSharedPrefs(): String? {
        /**
         * For reading shared preference files, simply find the correct file
         * and find the value using the key. A default value can also be
         * specified.
         */
        val sharedPref = requireContext().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        return sharedPref?.getString(getString(R.string.last_input), "???")
    }
}