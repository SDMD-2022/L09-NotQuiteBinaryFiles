package au.edu.swin.sdmd.l09_notquitebinaryfiles.data

import android.content.Context
import java.io.*

object HistoryFile {
    private val filename = "decimal_history"

    fun appendInput(context: Context, sDecimal: String) {
        context.openFileOutput(filename, Context.MODE_APPEND).use {
            it.write(sDecimal.toByteArray())
            it.write("\n".toByteArray())
        }
    }

    fun getFileContents(context: Context): List<String> {
        // if file exists, read data and reverse it
        return if (File(context.filesDir.absolutePath, filename).exists()) {
            val history = context.openFileInput(filename)
                .bufferedReader().useLines {
                    it.toList()
                }
            history.asReversed()
        } else { // if file does not exist, return an empty list
            emptyList()
        }
    }

    fun deleteFile(context: Context) {
        context.deleteFile(filename)
    }
}