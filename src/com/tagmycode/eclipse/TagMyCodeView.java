package com.tagmycode.eclipse;

import java.awt.Frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.tagmycode.plugin.Framework;
import com.tagmycode.plugin.FrameworkConfig;
import com.tagmycode.plugin.gui.CenterLocation;
import com.tagmycode.plugin.gui.CenterLocationType;
import com.tagmycode.sdk.authentication.TagMyCodeApiProduction;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class TagMyCodeView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.tagmycode.eclipse.TagMyCodeView";

	/**
	 * The constructor.
	 */
	public TagMyCodeView() {
		CenterLocation.setCenterType(CenterLocationType.CENTER_SCREEN);
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		Frame frame = frameFromComposite(parent);
		final FrameworkConfig frameworkConfig = new FrameworkConfig(
				new PasswordKeyChain(), new Storage(), new MessageManager(
						parent.getShell()), new TaskFactory(), new EclipseBrowser(), frame);
		Framework framework = new Framework(new TagMyCodeApiProduction(),
				frameworkConfig, new Secret());

		frame.removeAll();
		frame.add(framework.getMainFrame());

		try {
			framework.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Activator.getDefault().setFramework(framework);
	}

	private Frame frameFromComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.EMBEDDED
				| SWT.NO_BACKGROUND);
		return SWT_AWT.new_Frame(composite);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}