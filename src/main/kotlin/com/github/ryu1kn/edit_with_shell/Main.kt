package com.github.ryu1kn.edit_with_shell

import com.github.ryu1kn.edit_with_shell.ui.InputDialog
import com.github.ryu1kn.edit_with_shell.ui.SearchableListPicker

class Main(
    private val listPicker: SearchableListPicker,
    private val inputDialog: InputDialog,
    private val editorWrapper: EditorWrapper
) {
    fun run() {
        val historicalCommands = listOf("echo hi", "echo hey", "ls")

        listPicker.show(historicalCommands) {
            inputDialog.show(it, editorWrapper::pipeText)
        }
    }
}
