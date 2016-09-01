package com.tagmycode.eclipse;

import com.tagmycode.plugin.IPasswordKeyChain;


public class PasswordKeyChain extends HashMapToFile implements IPasswordKeyChain {
    public PasswordKeyChain()
    {
        super(new SaveFilePath().getPath("eclipse_tagmycode_secrets.ser"));
    }
}
