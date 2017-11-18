package com.tagmycode.eclipse.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

import com.tagmycode.plugin.gui.IDocumentInsertText;

public class SearchSnippet extends TagMyCodeAction {

	public SearchSnippet() {
		super();
	}

	public void run(IAction action) {

		final IEditorPart editorPart = getEditorPart();
        if (!getFramework().canOperate()) {
            return;
        }
		getFramework().showSearchDialog(createDocumentInsertText(editorPart));
	}

	private IDocumentInsertText createDocumentInsertText(final IEditorPart editorPart) {
		return new IDocumentInsertText() {

			@Override
			public void insertText(final String text) {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						ITextSelection iTextSelection = retrieveITextSelection(editorPart);
						if (iTextSelection != null) {
							ITextEditor ite = (ITextEditor) editorPart;
							IDocument document = ite.getDocumentProvider().getDocument(ite.getEditorInput());
							try {
								document.replace(iTextSelection.getOffset(), iTextSelection.getLength(), text);
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
						}
					}
				});
			}
		};
	}
}
