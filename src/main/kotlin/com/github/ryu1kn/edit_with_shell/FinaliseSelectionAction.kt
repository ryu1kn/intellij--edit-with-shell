package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.project.Project

class FinaliseSelectionAction(private val project: Project) : PreSelectionAware {
    override fun onPublished(context: PreSelectionContext) {
        ShellCommandInputDialog(project, context).apply { show() }
    }
}
