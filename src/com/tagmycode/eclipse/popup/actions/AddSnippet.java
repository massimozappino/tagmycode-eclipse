package com.tagmycode.eclipse.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;

import com.tagmycode.eclipse.Activator;
import com.tagmycode.plugin.Framework;
import com.tagmycode.sdk.model.Snippet;

public class AddSnippet implements IObjectActionDelegate {

	public AddSnippet() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {

		// get editor
		IEditorPart editorPart = Activator.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		String code = getCode(editorPart);
		
		
		Framework framework = Activator.getDefault().getFramework();
		Snippet snippet = new Snippet();
		snippet.setCode(code);
		snippet.setTitle(editorPart.getTitle());
		framework.showNewSnippetDialog(snippet);
	}

	private String getCode(IEditorPart editorPart) {
		String selectedText = null;
		if (editorPart instanceof AbstractTextEditor) {
			IEditorSite iEditorSite = editorPart.getEditorSite();
			if (iEditorSite != null) {
				ISelectionProvider selectionProvider = iEditorSite
						.getSelectionProvider();
				if (selectionProvider != null) {
					ITextSelection iSelection = (ITextSelection) selectionProvider
							.getSelection();
					if (!iSelection.isEmpty() && iSelection.getLength() > 0) {
						selectedText = ((ITextSelection) iSelection).getText();
					} else {
						ITextEditor ite = (ITextEditor) editorPart;
						selectedText = ite.getDocumentProvider()
								.getDocument(ite.getEditorInput()).get();
					}
				}
			}
		}
		return selectedText;
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
