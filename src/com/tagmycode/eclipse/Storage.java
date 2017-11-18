package com.tagmycode.eclipse;

import java.io.IOException;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;


public class Storage {

    private IEclipsePreferences prefs;

    public Storage() {   
         prefs = InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID);
    }

    public String read(String key) throws IOException {
        return prefs.get(key, "");
    }

    public void write(String key, String value) throws IOException {
    	prefs.put(key, value);
    	flush();
    }

    public void unset(String key) throws IOException {
        prefs.remove(key);
        flush();
    }

	private void flush() {
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			Activator.getDefault().getFramework().getMessageManager().errorLog(e.getMessage());
		}
	}

}
