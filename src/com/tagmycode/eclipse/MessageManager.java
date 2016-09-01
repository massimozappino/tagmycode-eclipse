package com.tagmycode.eclipse;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.tagmycode.plugin.IMessageManager;

public class MessageManager implements IMessageManager {

	private Shell shell;

	public MessageManager(Shell shell) {
		this.shell = shell;
		// TODO Auto-generated constructor stub
	}

	public void error(String message) {
		MessageBox dialog = 
				  new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				dialog.setText("My info");
				dialog.setMessage(message);

				// open dialog and await user selection
				dialog.open();

	}

}
