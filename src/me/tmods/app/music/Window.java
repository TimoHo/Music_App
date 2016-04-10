package me.tmods.app.music;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Window {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 692, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea text = new JTextArea();
		text.setText("Normale Noten schnell hintereinander gespielt:\r\n[C][D][E][F][G][A][H][B: 100][X]\r\n[C1][D1][E1][F1][G1][A1][H1][B: 100][X]\r\n[C2][D2][E2][F2][G2][A2][H2][B: 100][X]\r\nNoten m\u00FCssen in eckigen klammern geschrieben werden\r\nPausen:\r\n[B: 100] (100ms pause)\r\n\r\nAbbruch:\r\n[X] (Bricht den gesamten ton ab)");
		text.setRows(10);
		text.setBounds(10, 11, 656, 347);
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
	}
}
