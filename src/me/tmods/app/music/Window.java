package me.tmods.app.music;

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

public class Window {
	public static JTextArea instruments;
	public static JFrame frame;
	private JTextField textField;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 692, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea text = new JTextArea();
		text.setText("Noten:\r\n\r\n[C-1] (C in der ersten Oktave)\r\n[C#2] (C in der zweiten Oktave mit #)\r\n\r\nPausen:\r\n\r\n[B: 100] (100ms Pause)\r\n\r\nInstrumente:\r\n\r\n[I: 1] (Wechsel auf instrument 1)\r\n\r\nLautst\u00E4rke\r\n\r\n[V: 50] (Lautst\u00E4rke auf 50%)\r\n\r\nAbbruch:\r\n\r\n[X] (Sofortiger Abbruch)\r\n[K] (Taste wird nichtmer gedr\u00FCckt aber klingt nach)");
		text.setRows(10);
		text.setBounds(10, 11, 483, 347);
		frame.getContentPane().add(text);
		
		JTextArea txtpnInstrumente = new JTextArea();
		txtpnInstrumente.setEditable(false);
		txtpnInstrumente.setToolTipText("");
		txtpnInstrumente.setText("Instrumente:\r\n");
		txtpnInstrumente.setBounds(503, 11, 163, 347);
		frame.getContentPane().add(txtpnInstrumente);
		
		instruments = txtpnInstrumente;
		
		JScrollPane scrollPane = new JScrollPane(txtpnInstrumente);
		scrollPane.setBounds(503, 10, 163, 347);
		frame.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(text);
		scrollPane_1.setBounds(10, 11, 483, 347);
		frame.getContentPane().add(scrollPane_1);
		
		
		JButton button = new JButton("Abspielen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Sound> sounds = SoundConstructor.fromText(text.getText());
				for (Sound s:sounds) {
					if (s != null)  {
						s.play();
					}
				}
			}
		});
		button.setBounds(10, 364, 325, 44);
		frame.getContentPane().add(button);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"[C-]", "[C#]", "[Db]", "[D-]", "[D#]", "[Eb]", "[E-]", "[F-]", "[F#]", "[Gb]", "[G-]", "[G#]", "[Ab]", "[A-]", "[A#]", "[Hb]", "[H-]", "[B: ]", "[I: ]", "[K]", "[X]", "[V: ]"}));
		comboBox.setBounds(507, 368, 60, 20);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(507, 388, 60, 20);
		frame.getContentPane().add(textField);
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
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNoteaktion = new JLabel("Note/Aktion:");
		lblNoteaktion.setBounds(419, 371, 90, 14);
		frame.getContentPane().add(lblNoteaktion);
		
		JLabel lblOktaveoption = new JLabel("Oktave/Option:");
		lblOktaveoption.setBounds(419, 391, 90, 14);
		frame.getContentPane().add(lblOktaveoption);
	}
}
