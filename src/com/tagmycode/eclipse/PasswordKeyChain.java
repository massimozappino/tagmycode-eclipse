package com.tagmycode.eclipse;

import java.io.IOException;

import com.tagmycode.plugin.IPasswordKeyChain;
import com.tagmycode.plugin.exception.TagMyCodeGuiException;

public class PasswordKeyChain implements IPasswordKeyChain {

	private Storage storage;

	public PasswordKeyChain() {
		storage = new Storage();
	}

	@Override
	public void deleteValue(String key) throws TagMyCodeGuiException {
		try {
			storage.unset(key);
		} catch (IOException e) {
			throw new TagMyCodeGuiException(e.getMessage());
		}
	}

	@Override
	public String loadValue(String key) throws TagMyCodeGuiException {
		String value = "";
		try {
			value = storage.read(key);
		} catch (IOException e) {
			throw new TagMyCodeGuiException(e.getMessage());
		}
		return value;
	}

	@Override
	public void saveValue(String key, String value)
			throws TagMyCodeGuiException {
		try {
			storage.write(key, value);
		} catch (IOException e) {
			throw new TagMyCodeGuiException(e.getMessage());
		}

	}

}
