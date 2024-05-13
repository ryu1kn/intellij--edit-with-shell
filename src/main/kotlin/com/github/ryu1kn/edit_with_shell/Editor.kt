package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

interface EditorWrapper {
    fun pipeText(commandString: String?)
}

class EditorWrapperImpl(private val project: Project, private val editor: Editor) : EditorWrapper {

    override fun pipeText(commandString: String?) {
        val document = editor.document
        val primaryCaret = editor.caretModel.primaryCaret
        WriteCommandAction.runWriteCommandAction(project) {
            document.replaceString(
                primaryCaret.selectionStart,
                primaryCaret.selectionEnd,
                ProcessBuilder(listOf("bash", "-c", commandString ?: ": No op"))
                    .start().inputStream.bufferedReader().readText()
            )
        }
        primaryCaret.removeSelection()
    }
}
