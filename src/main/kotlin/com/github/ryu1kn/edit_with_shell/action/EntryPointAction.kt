package com.github.ryu1kn.edit_with_shell.action

import com.github.ryu1kn.edit_with_shell.Main
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class EntryPointAction : AnAction() {

    override fun getActionUpdateThread() = ActionUpdateThread.BGT

    override fun actionPerformed(e: AnActionEvent) {
        // Editor and Project were verified in update(), so they are not null.
        Main().run(
            project = e.getRequiredData(CommonDataKeys.PROJECT),
            editor = e.getRequiredData(CommonDataKeys.EDITOR)
        )
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        // Set visibility and enable only in case of existing project and editor and if a selection exists
        e.presentation.setEnabledAndVisible(project != null && editor != null)
    }
}
