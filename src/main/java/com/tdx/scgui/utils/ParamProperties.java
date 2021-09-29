package com.tdx.scgui.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ParamProperties extends Properties {

	private static final long serialVersionUID = 1L;



	protected ParamProperties() {

	}

	private Integer getIntProperty(String property) {
		String value = this.getProperty(property, null);
		if (value == null)
			return null;
		return Integer.parseInt(value);
	}
	private Long getLongProperty(String property) {
		String value = this.getProperty(property, null);
		if (value == null)
			return null;
		return Long.parseLong(value);
	}

	private Float getFloatProperty(String property) {
		String value = this.getProperty(property, null);
		if (value == null)
			return null;
		return Float.parseFloat(value);
	}
	private Double getDoubleProperty(String property) {
		String value = this.getProperty(property, null);
		if (value == null)
			return null;
		return Double.parseDouble(value);
	}
	private Short getShortProperty(String property) {
		String value = this.getProperty(property, null);
		if (value == null)
			return null;
		return Short.parseShort(value);
	}



	protected String doSetProperty(String name, String value) {
		if (name == null)
			return null;

		String oldValue;

		synchronized (this) {
			if (value == null) {
				super.remove(name);
				return null;
			}
			oldValue = (String) super.setProperty(name, value);
		}

		return oldValue;
	}
	/**
	 * Adds a property definition in the form key=value Lines starting with #
	 * are ignored Lines that do not contain a = character are ignored Any text
	 * after a # sign in the value is ignored
	 */
	public void addPropertyDefinition(String line) {
		if (line == null)
			return;
		if (line.trim().length() == 0)
			return;
		if (line.startsWith("#"))
			return;
		int pos = line.indexOf("=");
		if (pos == -1)
			return;
		String key = line.substring(0, pos);
		String value = line.substring(pos + 1);
		pos = value.indexOf('#');
		if (pos > -1) {
			value = value.substring(0, pos);
		}
		this.setProperty(key, value.trim());
	}

	/**
	 * Read the content of the file into this properties object. This method
	 * does not support line continuation, but supports an encoding (as opposed
	 * to the original properties class)
	 * 
	 */
	public void loadTextFile(String filename)
			throws IOException {
		File f = new File(filename);
		if (!f.exists()) {
		    return;
        }
        FileInputStream inputStream = null;
		try {
		    inputStream = new FileInputStream(f);
		    load(inputStream);
		} finally {
		    if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th) {
                }
            }
		}
	}

	public synchronized void saveToFile(String filename) throws IOException {
		FileOutputStream out = null;
		File fileObj = new File(filename);
		if (!fileObj.getParentFile().exists()) {
            fileObj.getParentFile().mkdirs();
        }
		try {
			out = new FileOutputStream(fileObj);
			this.store(out, new Date().toString());
		} finally {
		    if (out != null) {
		        try {
                    out.close();
                } catch (Exception ex){}
            }
		}
	}

	private String getSections(String aString, int aNum) {
		int pos = aString.indexOf(".");
		String result = null;
		for (int i = 1; i < aNum; i++) {
			int pos2 = aString.indexOf('.', pos + 1);
			if (pos2 > -1) {
				pos = pos2;
			} else {
				if (i == (aNum - 1)) {
					pos = aString.length();
				}
			}
		}
		result = aString.substring(0, pos);
		return result;
	}
}
