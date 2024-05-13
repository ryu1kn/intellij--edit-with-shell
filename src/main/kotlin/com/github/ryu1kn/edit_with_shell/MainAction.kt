package com.github.ryu1kn.edit_with_shell

import com.github.ryu1kn.edit_with_shell.ui.SearchableListPickerImpl
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.service

class MainAction : AnAction() {

    override fun getActionUpdateThread() = ActionUpdateThread.BGT

    override fun actionPerformed(e: AnActionEvent) {
        // Editor and Project were verified in update(), so they are not null.
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project = e.getRequiredData(CommonDataKeys.PROJECT)

        // Get them from somewhere real
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

    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        // Set visibility and enable only in case of existing project and editor and if a selection exists
        e.presentation.setEnabledAndVisible(project != null && editor != null)
    }

    data class ShellCommand(val commandString: String)
}