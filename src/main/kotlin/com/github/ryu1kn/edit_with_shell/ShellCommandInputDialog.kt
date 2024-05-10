package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

class ShellCommandInputDialog(project: Project, private val context: PreSelectionContext) : DialogWrapper(project) {
    private val publisher = project.messageBus.syncPublisher(FinalSelectionAware.CHANGE_ACTION_TOPIC)

    private var confirmedCommand = context.command.commandString

    init {
        title = "Command Confirmation"
        init()
    }

    override fun createCenterPanel(): JComponent = panel {
        row {
            label("Please confirm a command before execute")
        }
        row {
            textField().bindText(::confirmedCommand).focused()
        }
    }

    override fun show() {
        super.show()
        if (isOK) notifyFinalSelection()
    }

    private fun notifyFinalSelection() {
        publisher.onPublished(context.finalise(confirmedCommand))
    }
}
