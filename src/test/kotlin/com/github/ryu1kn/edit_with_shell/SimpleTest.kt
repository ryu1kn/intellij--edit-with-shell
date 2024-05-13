package com.github.ryu1kn.edit_with_shell

import com.github.ryu1kn.edit_with_shell.ui.InputDialog
import com.github.ryu1kn.edit_with_shell.ui.SearchableListPicker
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SimpleTest {
    @Test
    fun testSum() {
        assertEquals(42, 40 + 2)
    }

    @Test
    fun testSum2() {
        val listPicker = object : SearchableListPicker {
            override fun show(items: List<String>, handler: (String) -> Unit) {
                if (items == listOf("echo hi", "echo hey")) handler("echo hi")
                else throw IllegalArgumentException("Unexpected items: $items")
            }
        }
        val dialog = object : InputDialog {
            override fun show(prepopulatedText: String, handler: (String) -> Unit) {
                if (prepopulatedText == "echo hi") handler("echo hi")
                else throw IllegalArgumentException("Unexpected prepopulatedText: $prepopulatedText")
            }
        }
        val editorWrapper = object : EditorWrapper {
            override fun pipeText(commandString: String?) {
                if (commandString == "echo hi") return
                else throw IllegalArgumentException("Unexpected commandString: $commandString")
            }
        }

        Main(listPicker, dialog, editorWrapper).run()
    }
}
