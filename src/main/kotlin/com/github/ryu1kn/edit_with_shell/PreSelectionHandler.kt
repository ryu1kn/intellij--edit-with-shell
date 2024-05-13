package com.github.ryu1kn.edit_with_shell

import com.github.ryu1kn.edit_with_shell.ui.ShellCommandInputDialog
import com.intellij.openapi.project.Project

class PreSelectionHandler(private val project: Project) : PreSelectionAware {

    override fun onPublished(context: PreSelectionContext) = confirmSelection(context)

    private fun confirmSelection(context: PreSelectionContext) {
        ShellCommandInputDialog(project).show(context.command.commandString) { confirmedCommand ->
            val publisher = project.messageBus.syncPublisher(FinalSelectionAware.CHANGE_ACTION_TOPIC)
            publisher.onPublished(context.finalise(confirmedCommand))
        }
    }
}
