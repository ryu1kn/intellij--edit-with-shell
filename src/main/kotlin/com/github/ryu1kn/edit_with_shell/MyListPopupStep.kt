package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep

class MyListPopupStep(val project: Project, val editor: Editor) : BaseListPopupStep<ShellCommand>("Shell commands", listOf(ShellCommand("echo hi"), ShellCommand("echo hey"))) {
    private val document = editor.document

    private lateinit var selectedCommand: ShellCommand

    override fun getTextFor(value: ShellCommand): String = value.commandString

    override fun onChosen(selectedValue: ShellCommand, finalChoice: Boolean): PopupStep<*>? {
        selectedCommand = selectedValue
        return null
    }

    override fun getFinalRunnable(): Runnable = Runnable {
        val primaryCaret = editor.caretModel.primaryCaret
        val dialog = ShellCommandInputDialog(project, selectedCommand).apply { show() }

        WriteCommandAction.runWriteCommandAction(project) {
            document.replaceString(
                primaryCaret.selectionStart,
                primaryCaret.selectionEnd,
                ProcessBuilder(listOf("bash", "-c", dialog.command() ?: ": No op"))
                    .start().inputStream.bufferedReader().readText()
            )
        }
        primaryCaret.removeSelection()
    }
}

data class ShellCommand(val commandString: String)
