package com.tagmycode.eclipse.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.AbstractTextEditor;

import com.tagmycode.eclipse.Activator;
import com.tagmycode.plugin.Framework;

public abstract class TagMyCodeAction implements IObjectActionDelegate {

	@Override
	public abstract void run(IAction action);

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

	protected IEditorPart getEditorPart() {
		final IEditorPart editorPart = Activator.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		return editorPart;
	}

	protected Framework getFramework() {
		return Activator.getDefault().getFramework();
	}
	
	protected ITextSelection retrieveITextSelection(IEditorPart editorPart) {
		ITextSelection iTextSelection = null;
		if (editorPart instanceof AbstractTextEditor) {
			IEditorSite iEditorSite = editorPart.getEditorSite();
			if (iEditorSite != null) {
				ISelectionProvider selectionProvider = iEditorSite
						.getSelectionProvider();
				if (selectionProvider != null) {
					iTextSelection = (ITextSelection) selectionProvider
							.getSelection();
				}
			}
		}
		return iTextSelection;
	}
}
