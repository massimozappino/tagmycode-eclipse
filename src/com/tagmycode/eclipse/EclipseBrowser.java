package com.tagmycode.eclipse;

import com.tagmycode.plugin.IBrowser;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

enum EnumOS {
    linux, macos, solaris, unknown, windows;

    public boolean isLinux() {

        return this == linux || this == solaris;
    }

    public boolean isMac() {

        return this == macos;
    }

    public boolean isWindows() {

        return this == windows;
    }
}

public class EclipseBrowser implements IBrowser {

    private final EnumOS os;

    public EclipseBrowser() {
        os = guessOS();
    }

    @Override
    public boolean openUrl(String url) {
        try {
            return openUrl(new URI(url));
        } catch (URISyntaxException e) {
            return false;
        }
    }

    private boolean openUrl(URI uri) {
    	// Use of Desktop on Linux systems can cause this error:
		// #
		// # A fatal error has been detected by the Java Runtime Environment:
		// #
		// #  SIGSEGV (0xb) at pc=0x00007f7e8132d2b7, pid=12157, tid=140181336876800
		// #
		// # JRE version: Java(TM) SE Runtime Environment (8.0_66-b17) (build 1.8.0_66-b17)
		// # Java VM: Java HotSpot(TM) 64-Bit Server VM (25.66-b17 mixed mode linux-amd64 compressed oops)
		// # Problematic frame:
		// # C  [libgdk-x11-2.0.so.0+0x4e2b7]  gdk_display_open+0x57

        if (os != EnumOS.linux && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                return false;
            }
        } else {
            Rule rule = getOsBasedRule();
            return rule.browse(uri);
        }
        return false;
    }


    private Rule getOsBasedRule() {
        if (os.isLinux()) {
            return new LinuxRule();
        }

        if (os.isMac()) {
            return new MacRule();
        }

        if (os.isWindows()) {
            return new WindowsRule();
        }

        return new UnknownOsRule();

    }


    private EnumOS guessOS() {
        String s = System.getProperty("os.name").toLowerCase();

        if (s.contains("win")) {
            return EnumOS.windows;
        }

        if (s.contains("mac")) {
            return EnumOS.macos;
        }

        if (s.contains("solaris")) {
            return EnumOS.solaris;
        }

        if (s.contains("sunos")) {
            return EnumOS.solaris;
        }

        if (s.contains("linux")) {
            return EnumOS.linux;
        }

        if (s.contains("unix")) {
            return EnumOS.linux;
        } else {
            return EnumOS.unknown;
        }
    }

    private class MacRule extends Rule {
        @Override
        public boolean browse(URI uri) {
            return runCommand("open", "%s", uri.toString());
        }
    }

    private class LinuxRule extends Rule {
        @Override
        public boolean browse(URI uri) {
            return runCommand("xdg-open", "%s", uri.toString());
        }
    }

    private class WindowsRule extends Rule {
        @Override
        public boolean browse(URI uri) {
            return runCommand("explorer", "%s", uri.toString());
        }
    }

    private class UnknownOsRule extends Rule {
        @Override
        public boolean browse(URI uri) {
            return false;
        }
    }
}

abstract class Rule {
    public abstract boolean browse(URI uri);

    public boolean runCommand(String command, String args, String file) {
        String[] parts = prepareCommand(command, args, file);

        try {
            Process p = Runtime.getRuntime().exec(parts);
            if (p == null) return false;

            try {
                return p.exitValue() == 0;
            } catch (IllegalThreadStateException e) {
                return true;
            }
        } catch (IOException e) {
            return false;
        }
    }

    private String[] prepareCommand(String command, String args, String file) {

        java.util.List<String> parts = new ArrayList<String>();
        parts.add(command);

        if (args != null) {
            for (String s : args.split(" ")) {
                s = String.format(s, file);

                parts.add(s.trim());
            }
        }

        return parts.toArray(new String[parts.size()]);
    }

}
