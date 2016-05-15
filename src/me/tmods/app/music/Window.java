package me.tmods.app.music;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import me.tmods.app.MidiReader;
import me.tmods.app.MusicApp;
import me.tmods.app.reader.ReaderWindow;
import me.tmods.app.reader.TextReader;

public class Window {
	public static JTextArea instruments;
	public static JFrame frmSmlSynthesizer;
	public static boolean init = false;
	public static DisplaySound canvas;
	private JTextField textField;
	private static JTextPane txt;
	private static JTextField length;
	private JTextField textField_1;
	@SuppressWarnings("static-access")
	public static void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmSmlSynthesizer.setVisible(true);
					init = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void errorMessage(String message,Throwable t) {
		final Integer sel = JOptionPane.showOptionDialog(null, message, "Error", 1, MessageType.ERROR.ordinal(), null, new String[]{"Continue","Show Message"}, "Error");
		if (sel == 1) {
			PrintWriter w = null;
			StringWriter wr = new StringWriter();
			w = new PrintWriter(wr);
			t.printStackTrace(w);
			final Integer stsel = JOptionPane.showOptionDialog(null, wr.toString(), "StackTrace", 1, MessageType.NONE.ordinal(), null, new String[]{"Ok","Exit"}, 0);
			if (stsel == 1) {
				System.exit(0);
			}
		}
	}
	public static void addInstrument(String name,Integer number) {
		instruments.setText(instruments.getText() + number + ": " + name + "\n");
		frmSmlSynthesizer.repaint();
	}
	public Window() {
		initialize();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmSmlSynthesizer = new JFrame();
		frmSmlSynthesizer.setResizable(false);
		frmSmlSynthesizer.setBounds(100, 100, 692, 450);
		frmSmlSynthesizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSmlSynthesizer.getContentPane().setLayout(null);
		
		JTextPane text = new JTextPane();
		text.setBackground(Color.WHITE);
		text.setBounds(1, 29, 277, 317);
		frmSmlSynthesizer.getContentPane().add(text);
		txt = text;
		
		JTextArea txtpnInstrumente = new JTextArea();
		txtpnInstrumente.setEditable(false);
		txtpnInstrumente.setToolTipText("");
		txtpnInstrumente.setText("Instrumente:\r\n");
		txtpnInstrumente.setBounds(503, 11, 163, 347);
		frmSmlSynthesizer.getContentPane().add(txtpnInstrumente);
		
		instruments = txtpnInstrumente;
		
		JScrollPane scrollPane = new JScrollPane(txtpnInstrumente);
		scrollPane.setBounds(503, 11, 163, 347);
		frmSmlSynthesizer.getContentPane().add(scrollPane);
		
		DisplaySound canvas = new DisplaySound();
		canvas.setForeground(Color.BLACK);
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(10, 40, 178, 318);
		frmSmlSynthesizer.getContentPane().add(canvas);
		Window.canvas = canvas;
		
		JScrollPane scrollPane_1 = new JScrollPane(text);
		scrollPane_1.setBounds(214, 11, 279, 347);
		frmSmlSynthesizer.getContentPane().add(scrollPane_1);

		JButton button = new JButton("Abspielen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window.playAll(text.getText());
			}
		});
		button.setBounds(128, 364, 141, 44);
		frmSmlSynthesizer.getContentPane().add(button);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"[C-]", "[C#]", "[Db]", "[D-]", "[D#]", "[Eb]", "[E-]", "[F-]", "[F#]", "[Gb]", "[G-]", "[G#]", "[Ab]", "[A-]", "[A#]", "[Hb]", "[H-]", "[B: ]", "[I: ]", "[K]", "[X]", "[V: ]", "[O: ]"}));
		comboBox.setBounds(507, 368, 60, 20);
		frmSmlSynthesizer.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(507, 388, 60, 20);
		frmSmlSynthesizer.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Hinzuf\u00FCgen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().toString().equalsIgnoreCase("[X]") || comboBox.getSelectedItem().toString().equalsIgnoreCase("[K]")) {
					text.setText(text.getText() + "\r\n" + comboBox.getSelectedItem().toString());
				} else {
					text.setText(text.getText() + "\r\n" + comboBox.getSelectedItem().toString().replace("]", textField.getText()+ "]"));
				}
			}
		});
		btnNewButton.setBounds(566, 368, 100, 40);
		frmSmlSynthesizer.getContentPane().add(btnNewButton);
		
		JLabel lblNoteaktion = new JLabel("Note/Aktion:");
		lblNoteaktion.setBounds(419, 371, 90, 14);
		frmSmlSynthesizer.getContentPane().add(lblNoteaktion);
		
		JLabel lblOktaveoption = new JLabel("Oktave/Option:");
		lblOktaveoption.setBounds(419, 391, 90, 14);
		frmSmlSynthesizer.getContentPane().add(lblOktaveoption);
		
		JButton btnNewButton_1 = new JButton("Abbr.");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
		btnNewButton_1.setBounds(268, 366, 141, 41);
		frmSmlSynthesizer.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("txt");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextReader.open();
			}
		});
		btnNewButton_2.setBounds(10, 13, 79, 23);
		frmSmlSynthesizer.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("mid");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MidiReader.finished = false;
				ReaderWindow.open();
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						while(!MidiReader.finished) {
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						text.setText(MidiReader.textOutput.toString());
						ReaderWindow.close();
					}
				});
				t.start();
			}
		});
		btnNewButton_3.setBounds(99, 13, 70, 23);
		frmSmlSynthesizer.getContentPane().add(btnNewButton_3);
		
		JLabel lblLnge = new JLabel("Dauer:");
		lblLnge.setBounds(10, 371, 54, 14);
		frmSmlSynthesizer.getContentPane().add(lblLnge);
		
		textField_1 = new JTextField();
		textField_1.setText("0:00");
		textField_1.setEditable(false);
		textField_1.setBounds(10, 388, 100, 33);
		frmSmlSynthesizer.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		length = textField_1;
	}
	public static void playAll(String sheet) {
		if (MusicApp.playThread != null) {
			if (MusicApp.playThread.isAlive()) {
				return;
			}
		}
		List<Sound> sounds = SoundConstructor.fromText(sheet);
		Integer mins = (int) ((SoundConstructor.prevLength / 1000)/60);  
		Integer secs = (int) ((SoundConstructor.prevLength / 1000)%60);
		length.setText(mins + ":" + (secs < 10 ? ("0" + secs):(secs)));
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (Sound s:sounds) {
					if (s != null)  {
						try {
							Window.canvas.update();
						} catch (InstantiationException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						}
						s.play();
						if (s.getNote() != -1) {
							try {
								Thread.sleep(MusicApp.beat[s.getChannel()]);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							Window.canvas.add(s.getHeight());
							Window.canvas.repaint();
						}
					}
				}
			}
		};
		Thread t = new Thread(r);
		MusicApp.playThread = t;
		t.setUncaughtExceptionHandler(new ExceptionHandler());
		t.start();
	}
	@SuppressWarnings("deprecation")
	public static void stop() {
		if (MusicApp.playThread != null) {
			if (MusicApp.playThread.isAlive()) {
				MusicApp.playThread.stop();
			}
		}
	}
	public static void updateText(String s) {
		txt.setText(s);
	}
}
