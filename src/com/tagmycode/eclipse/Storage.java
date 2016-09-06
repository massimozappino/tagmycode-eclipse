package com.tagmycode.eclipse;

import com.tagmycode.plugin.IStorage;

import java.io.IOException;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;


public class Storage implements IStorage {

    private IEclipsePreferences prefs;

    public Storage() {   
         prefs = InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID);
    }

    @Override
    public String read(String key) throws IOException {
        return prefs.get(key, "");
    }

    @Override
    public void write(String key, String value) throws IOException {
    	prefs.put(key, value);
    	flush();
    }

    @Override
    public void unset(String key) throws IOException {
        prefs.remove(key);
        flush();
    }

	private void flush() {
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			Activator.getDefault().getFramework().getMessageManager().error(e.getMessage());
		}
	}

}
