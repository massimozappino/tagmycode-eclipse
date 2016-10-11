package com.tagmycode.eclipse;

import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;

import com.tagmycode.plugin.IBrowser;

public class EclipseBrowser implements IBrowser {

	private Display display;

	public EclipseBrowser(Display display) {
		this.display = display;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean openUrl(String url) {
		System.out.println("url: " + url);
		return Program.launch(url);
	}

}
