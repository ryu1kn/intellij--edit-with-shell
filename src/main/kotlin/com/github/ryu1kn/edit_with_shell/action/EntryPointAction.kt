package com.github.ryu1kn.edit_with_shell.action

import com.github.ryu1kn.edit_with_shell.EditorWrapperImpl
import com.github.ryu1kn.edit_with_shell.Main
import com.github.ryu1kn.edit_with_shell.MyProjectService
import com.github.ryu1kn.edit_with_shell.ui.SearchableListPickerImpl
import com.github.ryu1kn.edit_with_shell.ui.ShellCommandInputDialog
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.service

class EntryPointAction : AnAction() {

    override fun getActionUpdateThread() = ActionUpdateThread.BGT

    override fun actionPerformed(e: AnActionEvent) {
        // Editor and Project were verified in update(), so they are not null.

        val project = e.getRequiredData(CommonDataKeys.PROJECT)
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)

        // With project, I can access my services
        project.service<MyProjectService>().doSomething()

        Main(
            SearchableListPickerImpl(editor),
            ShellCommandInputDialog(project),
            EditorWrapperImpl(project, editor)
        ).run()
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        // Set visibility and enable only in case of existing project and editor and if a selection exists
        e.presentation.setEnabledAndVisible(project != null && editor != null)
    }
}
