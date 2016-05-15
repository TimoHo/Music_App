package me.tmods.app.music;

import java.util.ArrayList;
import java.util.List;

public class SoundConstructor {
	public static Integer prevLength = 0;
	public static List<Sound> fromText(String text) {
		Integer length = 0;
		Integer[] breaks = new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		List<Sound> list = new ArrayList<Sound>();
		for (int i = 0; i < text.length();i++) {
			if (text.charAt(i) == '[') {
				Integer channel = 0;
				final Integer begin = i;
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
					if (text.length() > i+1) {
						if (text.charAt(i+1) == '(') {
							i++;
							i++;
							String chn = "";
							while (text.charAt(i) != ')') {
								chn = chn + text.charAt(i); i++;
							}
							channel = Integer.valueOf(chn);
						}
					}
					breaks[channel] = Integer.valueOf(s);
					list.add(new Sound("B",Integer.valueOf(s),begin,i,channel));
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
						if (text.length() > i+1) {
							if (text.charAt(i+1) == '(') {
								i++;
								i++;
								String chn = "";
								while (text.charAt(i) != ')') {
									chn = chn + text.charAt(i); i++;
								}
								channel = Integer.valueOf(chn);
							}
						}
						list.add(new Sound("V",Integer.valueOf(s),begin,i,channel));
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
							if (text.length() > i+1) {
								if (text.charAt(i+1) == '(') {
									i++;
									i++;
									String chn = "";
									while (text.charAt(i) != ')') {
										chn = chn + text.charAt(i); i++;
									}
									channel = Integer.valueOf(chn);
								}
							}
							list.add(new Sound("I",Integer.valueOf(s),begin,i,channel));
						} else {
							if (text.charAt(i) == 'O') {
								i++;
								i++;
								i++;
								String s = "";
								while(text.charAt(i) != ']') {
									s = s + text.charAt(i);
									i++;
								}
								if (text.length() > i+1) {
									if (text.charAt(i+1) == '(') {
										i++;
										i++;
										String chn = "";
										while (text.charAt(i) != ')') {
											chn = chn + text.charAt(i); i++;
										}
										channel = Integer.valueOf(chn);
									}
								}
								list.add(new Sound("O",Integer.valueOf(s),begin,i,channel));
							} else {
								String s = "";
								while(text.charAt(i) != ']') {
									s = s + text.charAt(i);
									i++;
								}
								if (text.length() > i+1) {
									if (text.charAt(i+1) == '(') {
										i++;
										i++;
										String chn = "";
										while (text.charAt(i) != ')') {
											chn = chn + text.charAt(i); i++;
										}
										channel = Integer.valueOf(chn);
									}
								}
								length += breaks[channel];
								list.add(new Sound(s,begin,i,channel));
							}
						}
					}
				}
			}
		}
		prevLength  = length;
		return list;
	}
	public static Integer getNote(String code) {
		if (code.length() != 3) {
			return -1;
		}
		char name = code.charAt(0);
		char half = code.charAt(1);
		Integer octave = Integer.valueOf(code.charAt(2) + "") + 1;
		Integer noteInOctave = -1;
		switch (name) {
		case('C'): noteInOctave = 0; break;
		case('D'): noteInOctave = 2; break;
		case('E'): noteInOctave = 4; break;
		case('F'): noteInOctave = 5; break;
		case('G'): noteInOctave = 7; break;
		case('A'): noteInOctave = 9; break;
		case('H'): noteInOctave = 11; break;
		}
		noteInOctave += (half == '#' ? 1 : (half == 'b' ? -1 : 0));
		return noteInOctave + (octave * 12);
	}
}
