package au.edu.swin.sdmd.l09_notquitebinaryfiles.ui.process

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.text.Html
import au.edu.swin.sdmd.l09_notquitebinaryfiles.R

class ProcessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_process, container, false)

        val text = root.findViewById<TextView>(R.id.text_process)
        val process = resources.openRawResource(R.raw.binary_process)
            .bufferedReader().readText()
        text.text = Html.fromHtml(process, Html.FROM_HTML_MODE_COMPACT)
        return root
    }
}