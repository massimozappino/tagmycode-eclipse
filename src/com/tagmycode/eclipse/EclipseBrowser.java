package com.tagmycode.eclipse;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.tagmycode.plugin.IBrowser;


public class EclipseBrowser implements IBrowser {

    @Override
    public boolean openUrl(String url) {
    	try {
			PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(url));
		} catch (PartInitException | MalformedURLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
    }
}
