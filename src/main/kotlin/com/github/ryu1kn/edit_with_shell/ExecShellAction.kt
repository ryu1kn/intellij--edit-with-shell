package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.popup.JBPopupFactory

class ExecShellAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        // Editor and Project were verified in update(), so they are not null.
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project = e.getRequiredData(CommonDataKeys.PROJECT)

        // Get them from somewhere real
        // XXX: Can't proceed if it's for the first time. i.e. empty list
        val historicalCommands = listOf("echo hi", "echo hey", "ls")

        // Playing with a popup to enter a shell command
        val popupFactory = JBPopupFactory.getInstance()
        val listPopup = popupFactory.createListPopup(MyListPopupStep(project, editor, historicalCommands))
        listPopup.showInBestPositionFor(editor)
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        // Set visibility and enable only in case of existing project and editor and if a selection exists
        e.presentation.setEnabledAndVisible(project != null && editor != null)
    }
}
