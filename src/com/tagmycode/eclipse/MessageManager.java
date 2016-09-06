package com.tagmycode.eclipse;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.tagmycode.plugin.IMessageManager;

public class MessageManager implements IMessageManager {

	private Shell shell;

	public MessageManager(Shell shell) {
		this.shell = shell;
	}

	public void error(final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR
						| SWT.OK);
				dialog.setText("TagMyCode Error");
				dialog.setMessage(message);
				dialog.open();
			}
		});

	}

}
