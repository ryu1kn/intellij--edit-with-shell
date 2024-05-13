package com.github.ryu1kn.edit_with_shell.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent


interface InputDialog {
    fun show(prepopulatedText: String, handler: (String) -> Unit)
}

class ShellCommandInputDialog(private val project: Project) : InputDialog {

    override fun show(prepopulatedText: String, handler: (String) -> Unit) {
        object : DialogWrapper(project) {
            private var confirmedText = prepopulatedText

            init {
                title = "Command Confirmation"
                init()
            }

            override fun createCenterPanel(): JComponent = panel {
                row {
                    label("Please confirm a command before execute")
                }
                row {
                    textField().bindText(::confirmedText).focused()
                }
            }

            override fun show() {
                super.show()
                if (isOK) handler(confirmedText)
            }
        }.show()
    }
}
