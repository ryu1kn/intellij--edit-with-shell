package com.github.ryu1kn.edit_with_shell

import com.github.ryu1kn.edit_with_shell.MyNotifier.Companion.CHANGE_ACTION_TOPIC
import com.intellij.openapi.project.Project

class MyPublisher {
    fun publish(project: Project) {
        val publisher = project.messageBus.syncPublisher(CHANGE_ACTION_TOPIC)
        val context = EventContext("foobar")

        publisher.beforeAction(context)
        try {
            // Do action
            // ...
        } finally {
            publisher.afterAction(context)
        }
    }
}
