package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project

class FinalSelectionHandler(private val project: Project) : FinalSelectionAware {

    override fun onPublished(context: FinalSelectionContext) = pipeEditorText(context)

    private fun pipeEditorText(context: FinalSelectionContext) {
        val document = context.editor.document
        val primaryCaret = context.editor.caretModel.primaryCaret
        WriteCommandAction.runWriteCommandAction(project) {
            document.replaceString(
                primaryCaret.selectionStart,
                primaryCaret.selectionEnd,
                ProcessBuilder(listOf("bash", "-c", context.command ?: ": No op"))
                    .start().inputStream.bufferedReader().readText()
            )
        }
        primaryCaret.removeSelection()
    }
}
