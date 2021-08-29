package com.github.ryu1kn.edit_with_shell

import com.intellij.util.messages.Topic

interface MyNotifier {
    fun beforeAction(context: EventContext)
    fun afterAction(context: EventContext)

    companion object {
        val CHANGE_ACTION_TOPIC = Topic.create("custom name", MyNotifier::class.java)
    }
}

data class EventContext(val name: String)
