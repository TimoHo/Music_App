package me.tmods.app.music;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Window {
	public static JTextArea instruments;
	public static JFrame frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void addInstrument(String name,Integer number) {
		instruments.setText(instruments.getText() + number + ": " + name + "\n");
		frame.repaint();
	}
	public Window() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 692, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea text = new JTextArea();
		text.setText("Noten:\r\n\r\n[C-0] (C in der ersten Oktave)\r\n[C#1] (C in der zweiten Oktave mit #)\r\n\r\nPausen:\r\n\r\n[B: 100] (100ms Pause)\r\n\r\nInstrumente:\r\n\r\n[I: 1] (Wechsel auf instrument 1)\r\n\r\nLautst\u00E4rke\r\n\r\n[V: 50] (Lautst\u00E4rke auf 50%)\r\n\r\nAbbruch:\r\n\r\n[X] (Sofortiger Abbruch)\r\n[K] (Taste wird nichtmer gefr\u00FCckt aber klingt nach)");
		text.setRows(10);
		text.setBounds(10, 11, 483, 347);
		frame.getContentPane().add(text);
		
		Button button = new Button("Abspielen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Sound> sounds = SoundConstructor.fromText(text.getText());
				for (Sound s:sounds) {
					if (s != null) {
						s.play();
					}
				}
			}
		});
		button.setBounds(161, 364, 365, 44);
		frame.getContentPane().add(button);
		
		JTextArea txtpnInstrumente = new JTextArea();
		txtpnInstrumente.setEditable(false);
		txtpnInstrumente.setToolTipText("");
		txtpnInstrumente.setText("Instrumente:\r\n");
		txtpnInstrumente.setBounds(503, 11, 163, 347);
		frame.getContentPane().add(txtpnInstrumente);
		
		instruments = txtpnInstrumente;
	}
}
