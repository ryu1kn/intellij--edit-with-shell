package com.github.ryu1kn.edit_with_shell

import com.github.ryu1kn.edit_with_shell.services.MyProjectService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.service
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.ui.ListSpeedSearch
import com.intellij.ui.SpeedSearchBase
import com.intellij.ui.TreeUIHelper
import com.intellij.ui.components.JBList
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.table.JBListTable

class ExecShellAction : AnAction() {
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

        // Playing with a popup to enter a shell command
        val list = JBList(historicalCommands).also { TreeUIHelper.getInstance().installListSpeedSearch(it) }

        JBPopupFactory.getInstance().createListPopupBuilder(list)
            .setFilterAlwaysVisible(true)
            .setItemChosenCallback { selectedValue ->
                val publisher = project.messageBus.syncPublisher(PreSelectionAware.CHANGE_ACTION_TOPIC)
                publisher.onPublished(PreSelectionContext(ShellCommand(selectedValue.toString()), editor))
            }
            .createPopup()
            .showInBestPositionFor(editor)
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        // Set visibility and enable only in case of existing project and editor and if a selection exists
        e.presentation.setEnabledAndVisible(project != null && editor != null)
    }

    data class ShellCommand(val commandString: String)
}
