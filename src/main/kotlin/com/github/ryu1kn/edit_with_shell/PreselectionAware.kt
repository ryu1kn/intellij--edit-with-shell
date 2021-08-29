package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.editor.Editor
import com.intellij.util.messages.Topic

interface PreselectionAware {
    fun onPreselected(context: PreselectedContext)

    companion object {
        val CHANGE_ACTION_TOPIC = Topic.create("custom name", PreselectionAware::class.java)
    }
}

data class PreselectedContext(val command: String?, val editor: Editor)
