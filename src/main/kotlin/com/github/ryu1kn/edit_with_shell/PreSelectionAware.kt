package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.editor.Editor
import com.intellij.util.messages.Topic

interface PreSelectionAware {
    fun onPublished(context: PreSelectionContext)

    companion object {
        val CHANGE_ACTION_TOPIC = Topic.create("custom name", PreSelectionAware::class.java)
    }
}

data class PreSelectionContext(val command: MainAction.ShellCommand, val editor: Editor) {
    fun finalise(command: String) = FinalSelectionContext(command, editor)
}
