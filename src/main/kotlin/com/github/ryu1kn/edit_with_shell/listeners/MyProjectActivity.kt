package com.github.ryu1kn.edit_with_shell.listeners

import com.github.ryu1kn.edit_with_shell.services.MyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

internal class MyProjectActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        project.service<MyProjectService>()
    }
}
