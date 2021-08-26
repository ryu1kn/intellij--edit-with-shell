package com.github.ryu1kn.intellijeditwithshell.services

import com.github.ryu1kn.intellijeditwithshell.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
