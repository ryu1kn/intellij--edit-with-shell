package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.layout.panel
import javax.swing.JComponent

class ShellCommandInputDialog(project: Project, selectedCommand: ShellCommand) : DialogWrapper(project) {
    private var confirmedCommand = selectedCommand.commandString

    init {
        title = "Command Confirmation"
        init()
    }

    override fun createCenterPanel(): JComponent = panel {
        row {
            label("Please confirm a command before execute")
        }
        row {
            textField({ confirmedCommand }, { confirmedCommand = it }).focused()
        }
    }

    fun command(): String? = if (isOK) confirmedCommand else null
}
