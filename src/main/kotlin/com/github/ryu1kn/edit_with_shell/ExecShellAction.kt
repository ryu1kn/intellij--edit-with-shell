package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ExecShellAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        println("Hello World!")
    }
}
