package com.tagmycode.eclipse.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

import com.tagmycode.sdk.model.Snippet;

public class AddSnippet extends TagMyCodeAction {

	public AddSnippet() {
		super();
	}

	public void run(IAction action) {
		IEditorPart editorPart = getEditorPart();

		Snippet snippet = new Snippet();
		snippet.setCode(getCode(editorPart));
		snippet.setTitle(editorPart.getTitle());
        if (!getFramework().canOperate()) {
            return;
        }
		getFramework().showSnippetDialog(snippet);
	}

	private String getCode(IEditorPart editorPart) {
		String selectedText = null;

		ITextSelection iTextSelection = retrieveITextSelection(editorPart);
		if (iTextSelection != null) {
			if (!iTextSelection.isEmpty() && iTextSelection.getLength() > 0) {
				selectedText = ((ITextSelection) iTextSelection).getText();
			} else {
				ITextEditor ite = (ITextEditor) editorPart;
				selectedText = ite.getDocumentProvider()
						.getDocument(ite.getEditorInput()).get();
			}
		}

		return selectedText;
	}

}
