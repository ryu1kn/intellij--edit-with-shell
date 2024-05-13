package com.github.ryu1kn.edit_with_shell.ui

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.ui.TreeUIHelper
import com.intellij.ui.components.JBList

interface SearchableListPicker {
    fun show(items: List<String>, handler: (String) -> Unit)
}

class SearchableListPickerImpl(private val editor: Editor) : SearchableListPicker {

    override fun show(items: List<String>, handler: (String) -> Unit) {
        val list = JBList(items).also { TreeUIHelper.getInstance().installListSpeedSearch(it) }

        JBPopupFactory.getInstance().createListPopupBuilder(list)
            .setFilterAlwaysVisible(true)
            .setItemChosenCallback(handler)
            .createPopup()
            .showInBestPositionFor(editor)
    }
}
