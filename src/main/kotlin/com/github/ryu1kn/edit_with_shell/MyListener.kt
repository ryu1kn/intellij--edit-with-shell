package com.github.ryu1kn.edit_with_shell

class MyListener : MyNotifier {
    override fun beforeAction(context: EventContext) {
        println("Received before action call (${context.name})")
    }

    override fun afterAction(context: EventContext) {
        println("Received after action call (${context.name})")
    }
}
