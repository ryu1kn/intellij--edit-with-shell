package com.github.ryu1kn.edit_with_shell.services

import com.github.ryu1kn.edit_with_shell.MyBundle
import com.intellij.openapi.components.Service

@Service
class MyApplicationService {

    init {
        println(MyBundle.message("applicationService"))
    }
}
