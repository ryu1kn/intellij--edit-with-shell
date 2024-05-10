package com.github.ryu1kn.edit_with_shell.services

import com.github.ryu1kn.edit_with_shell.MyBundle
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
