package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep

class MyListPopupStep(val project: Project, val editor: Editor) : BaseListPopupStep<ShellCommand>("Shell commands", listOf(ShellCommand("echo hi"), ShellCommand("echo hey"))) {
    private lateinit var selectedCommand: ShellCommand

    override fun getTextFor(value: ShellCommand): String = value.commandString

    override fun onChosen(selectedValue: ShellCommand, finalChoice: Boolean): PopupStep<*>? {
        selectedCommand = selectedValue
        return null
    }

    override fun getFinalRunnable(): Runnable = Runnable {
        val dialog = ShellCommandInputDialog(project, selectedCommand).apply { show() }

        val publisher = project.messageBus.syncPublisher(PreselectionAware.CHANGE_ACTION_TOPIC)
        val context = PreselectedContext(dialog.command(), project, editor)
        publisher.onPreselected(context)
    }
}

data class ShellCommand(val commandString: String)
