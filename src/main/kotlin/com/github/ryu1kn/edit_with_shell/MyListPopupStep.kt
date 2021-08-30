package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep

class MyListPopupStep(project: Project, private val editor: Editor) : BaseListPopupStep<ShellCommand>("Shell commands", listOf(ShellCommand("echo hi"), ShellCommand("echo hey"))) {
    private val publisher = project.messageBus.syncPublisher(PreSelectionAware.CHANGE_ACTION_TOPIC)

    private lateinit var selectedCommand: ShellCommand

    override fun getTextFor(value: ShellCommand): String = value.commandString

    override fun onChosen(selectedValue: ShellCommand, finalChoice: Boolean): PopupStep<*>? {
        selectedCommand = selectedValue
        return null
    }

    override fun getFinalRunnable(): Runnable = Runnable {
        publisher.onPublished(PreSelectionContext(selectedCommand, editor))
    }
}

data class ShellCommand(val commandString: String)
