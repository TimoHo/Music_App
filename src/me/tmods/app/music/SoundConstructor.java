package me.tmods.app.music;

import java.util.ArrayList;
import java.util.List;

public class SoundConstructor {
	public static List<Sound> fromText(String text) {
		List<Sound> list = new ArrayList<Sound>();
		for (int i = 0; i < text.length();i++) {
			if (text.charAt(i) == '[') {
				i++;
				if (text.charAt(i) == 'B') {
					i++;
					i++;
					i++;
					String s = "";
					while(text.charAt(i) != ']') {
						s = s + text.charAt(i);
						i++;
					}
					list.add(new Sound("B",Integer.valueOf(s)));
				} else {
					if (text.charAt(i) == 'V') {
						i++;
						i++;
						i++;
						String s = "";
						while(text.charAt(i) != ']') {
							s = s + text.charAt(i);
							i++;
						}
						list.add(new Sound("V",Integer.valueOf(s)));
					} else {
						if (text.charAt(i) == 'I') {
							i++;
							i++;
							i++;
							String s = "";
							while(text.charAt(i) != ']') {
								s = s + text.charAt(i);
								i++;
							}
							list.add(new Sound("I",Integer.valueOf(s)));
						} else {
							String s = "";
							while(text.charAt(i) != ']') {
								s = s + text.charAt(i);
								i++;
							}
							list.add(new Sound(s));
						}
					}
				}
			}
		}
		System.out.println(list.toString());
		return list;
	}
	public static Integer getNote(String code) {
		if (code.length() != 3) {
			return -1;
		}
		char name = code.charAt(0);
		char half = code.charAt(1);
		System.out.println(code.charAt(2));
		Integer octave = Integer.valueOf(code.charAt(2) + "");
		Integer noteInOctave = -1;
		switch (name) {
		case('C'): noteInOctave = 1; break;
		case('D'): noteInOctave = 3; break;
		case('E'): noteInOctave = 5; break;
		case('F'): noteInOctave = 6; break;
		case('G'): noteInOctave = 8; break;
		case('A'): noteInOctave = 10; break;
		case('H'): noteInOctave = 12; break;
		}
		noteInOctave += (half == '#' ? 1 : (half == 'b' ? -1 : 0));
		return noteInOctave + (octave * 12);
	}
}
