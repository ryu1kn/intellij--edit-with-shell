package com.github.ryu1kn.edit_with_shell

import com.github.ryu1kn.edit_with_shell.ui.SearchableListPickerImpl
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class Main {

    fun run(project: Project, editor: Editor) {
        val historicalCommands = listOf("echo hi", "echo hey", "ls")

        if (historicalCommands.isEmpty()) {
            // This way we can reuse the same input dialog
            val publisher = project.messageBus.syncPublisher(PreSelectionAware.CHANGE_ACTION_TOPIC)
            publisher.onPublished(PreSelectionContext(ShellCommand(""), editor))
            return
        }

        // With project, I can access my services
        project.service<MyProjectService>().doSomething()

        SearchableListPickerImpl().show(historicalCommands, editor) { selectedValue ->
            val publisher = project.messageBus.syncPublisher(PreSelectionAware.CHANGE_ACTION_TOPIC)
            publisher.onPublished(PreSelectionContext(ShellCommand(selectedValue), editor))
        }
    }
}

data class ShellCommand(val commandString: String)
