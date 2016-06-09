package it.polimi.ingsw.cg23.gui;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.view.XmlInterface;

/**
 * redirect the console out to the gui logger
 * http://stackoverflow.com/questions/342990/create-java-console-inside-a-gui-panel
 */
public class TextAreaOutputStream extends OutputStream{
	private byte[] oneByte; // array per scrivere
	private Appender appender; // most recent action
	private static Logger logger;//logger

	/**
	 * costructor
	 * @param txtarea, the text area to write on
	 */
	public TextAreaOutputStream(JTextArea txtarea) {
		this(txtarea, 1000);

		//configurazione logger
		logger = Logger.getLogger(XmlInterface.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

	}

	/**
	 * @param txtara
	 * @param maxlin
	 */
	public TextAreaOutputStream(JTextArea txtara, int maxlin) {
		if(maxlin<1) { 
			throw new IllegalArgumentException("TextAreaOutputStream maximum lines must be positive (value="+maxlin+")"); 
		}
		oneByte=new byte[1];
		appender=new Appender(txtara,maxlin);

		//configurazione logger
		logger = Logger.getLogger(XmlInterface.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logge
	}

	/** Clear the current console text area. */
	public synchronized void clear() {
		if(appender!=null) { 
			appender.clear(); 
		}
	}

	@Override
	public synchronized void close() {
		appender=null;
	}


	/**
	 * empty method
	 */
	@Override
	public synchronized void flush() {
		/*
		 * empty method
		 */
	}

	@Override
	public synchronized void write(int val) {
		oneByte[0]=(byte)val;
		write(oneByte,0,1);
	}

	@Override
	public synchronized void write(byte[] ba) {
		write(ba,0,ba.length);
	}

	@Override
	public synchronized void write(byte[] ba,int str,int len) {
		if(appender!=null) { 
			appender.append(bytesToString(ba,str,len)); }
	}

	private static  String bytesToString(byte[] ba, int str, int len) {
		try { 
			return new String(ba,str,len,"UTF-8"); 
		} catch(UnsupportedEncodingException e) {
			logger.error("errore", e);
			return new String(ba,str,len); 
		} // all JVMs are required to support UTF-8
	}

	// *************************************************************************************************
	// STATIC MEMBERS
	// *************************************************************************************************

	static class Appender implements Runnable
	{
		private static final String         EOL1="\n";
		private static final String         EOL2=System.getProperty("line.separator",EOL1);

		private final JTextArea             textArea;
		private final int                   maxLines;                                                   // maximum lines allowed in text area
		private final LinkedList<Integer>   lengths;                                                    // length of lines within text area
		private final List<String>          values;                                                     // values waiting to be appended

		private int                         curLength;                                                  // length of current line
		private boolean                     clear;
		private boolean                     queue;

		Appender(JTextArea txtara, int maxlin) {
			textArea=txtara;
			maxLines=maxlin;
			lengths=new LinkedList<>();
			values=new ArrayList<>();

			curLength=0;
			clear=false;
			queue=true;
		}

		synchronized void append(String val) {
			values.add(val);
			if(queue) { 
				queue=false; 
				EventQueue.invokeLater(this); 
			}
		}

		synchronized void clear() {
			clear=true;
			curLength=0;
			lengths.clear();
			values.clear();
			if(queue) { 
				queue=false; 
				EventQueue.invokeLater(this); 
			}
		}

		// MUST BE THE ONLY METHOD THAT TOUCHES textArea!
		@Override
		public synchronized void run() {
			if(clear) { 
				textArea.setText(""); 
			}
			for(String val: values) {
				curLength+=val.length();
				if(val.endsWith(EOL1) || val.endsWith(EOL2)) {
					if(lengths.size()>=maxLines) { 
						textArea.replaceRange("",0,lengths.removeFirst()); 
					}
					lengths.addLast(curLength);
					curLength=0;
				}
				textArea.append(val);
			}
			values.clear();
			clear =false;
			queue =true;
		}
	}
}