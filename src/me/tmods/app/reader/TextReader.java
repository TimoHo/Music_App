package me.tmods.app.reader;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import me.tmods.app.music.Window;

public class TextReader {

	private static JFrame frame;
	private static JTextField txtDatei;
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void open() {
		initialize();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 120, 120);
		frame.getContentPane().setLayout(null);
		
		txtDatei = new JTextField();
		txtDatei.setText("datei");
		txtDatei.setBounds(10, 11, 89, 20);
		frame.getContentPane().add(txtDatei);
		txtDatei.setColumns(10);
		
		JButton btnNewButton = new JButton("lesen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Window.updateText(readStream(new FileInputStream(txtDatei.getText())));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 47, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
	public static String readStream(InputStream file) {
	    StringBuilder sb = new StringBuilder(512);
	    try {
	        Reader r = new InputStreamReader(file, "UTF-8");
	        int c = 0;
	        while ((c = r.read()) != -1) {
	            sb.append((char) c);
	        }
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	    return sb.toString();
	}
}
