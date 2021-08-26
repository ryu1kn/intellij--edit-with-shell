package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.layout.panel
import javax.swing.JComponent

class ShellCommandInput : DialogWrapper(true) {
    override fun createCenterPanel(): JComponent {
        return panel {
            row {
                label("Hello World!")
            }
        }
    }

    init {
        title = "Foobar"
        init()
    }
}
