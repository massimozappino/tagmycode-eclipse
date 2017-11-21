package com.tagmycode.eclipse;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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

	@Override
	public void errorLog(String message) {
		IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.ERROR, message, new Exception());
		ILog log = Activator.getDefault().getLog();
        log.log(status);
	}

	@Override
	public void errorDialog(final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				dialog.setText("TagMyCode Error");
				dialog.setMessage(message);
				dialog.open();
			}
		});

	}

}
