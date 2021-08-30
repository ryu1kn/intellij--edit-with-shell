package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.editor.Editor
import com.intellij.util.messages.Topic

interface FinalSelectionAware {
    fun onPublished(context: FinalSelectionContext)

    companion object {
        val CHANGE_ACTION_TOPIC = Topic.create("custom name", FinalSelectionAware::class.java)
    }
}

data class FinalSelectionContext(val command: String?, val editor: Editor)
