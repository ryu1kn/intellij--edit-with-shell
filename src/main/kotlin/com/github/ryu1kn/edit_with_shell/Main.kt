package com.github.ryu1kn.edit_with_shell

import com.github.ryu1kn.edit_with_shell.ui.SearchableListPickerImpl
import com.github.ryu1kn.edit_with_shell.ui.ShellCommandInputDialog
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class Main {

    fun run(project: Project, editor: Editor) {
        val historicalCommands = listOf("echo hi", "echo hey", "ls")

        if (historicalCommands.isEmpty()) {
            ShellCommandInputDialog(project).show("") {
                EditorWrapper(project, editor).pipeText(it)
            }
            return
        }

        // With project, I can access my services
        project.service<MyProjectService>().doSomething()

        SearchableListPickerImpl().show(historicalCommands, editor) { selectedValue ->
            ShellCommandInputDialog(project).show(selectedValue) {
                EditorWrapper(project, editor).pipeText(it)
            }
        }
    }
}
