package com.github.ryu1kn.edit_with_shell

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MyProjectService(project: Project) {

    init {
        println("projectService ${project.name}")
    }

    fun doSomething() {
        println("doSomething")
    }
}
