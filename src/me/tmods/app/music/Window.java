package me.tmods.app.music;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import me.tmods.app.MusicApp;

public class Window {
	public static JTextArea instruments;
	public static JFrame frmSmlSynthesizer;
	public static boolean init = false;
	private JTextField textField;
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
		text.setBounds(3, 3, 488, 341);
		frmSmlSynthesizer.getContentPane().add(text);
		
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
		canvas.setBounds(10, 11, 178, 347);
		frmSmlSynthesizer.getContentPane().add(canvas);
		
		JScrollPane scrollPane_1 = new JScrollPane(text);
		scrollPane_1.setBounds(214, 11, 279, 347);
		frmSmlSynthesizer.getContentPane().add(scrollPane_1);

		JButton button = new JButton("Abspielen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MusicApp.playThread != null) {
					if (MusicApp.playThread.isAlive()) {
						return;
					}
				}
				List<Sound> sounds = SoundConstructor.fromText(text.getText());
				Runnable r = new Runnable() {
					@Override
					public void run() {
						for (Sound s:sounds) {
							if (s != null)  {
								canvas.add(s.getHeight());
								canvas.repaint();
								try {
									canvas.update();
								} catch (InstantiationException e1) {
									e1.printStackTrace();
								} catch (IllegalAccessException e1) {
									e1.printStackTrace();
								}
								s.play();	
								if (!s.getName().equalsIgnoreCase("B")) {
									try {
										Thread.sleep(MusicApp.beat[s.getChannel()]);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				};
				Thread t = new Thread(r);
				MusicApp.playThread = t;
				t.start();
			}
		});
		button.setBounds(36, 364, 203, 44);
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
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if (MusicApp.playThread != null) {
					if (MusicApp.playThread.isAlive()) {
						MusicApp.playThread.stop();
						text.repaint();
					}
				}
			}
		});
		btnNewButton_1.setBounds(249, 366, 141, 41);
		frmSmlSynthesizer.getContentPane().add(btnNewButton_1);
	}
}
