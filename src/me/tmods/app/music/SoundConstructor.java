package me.tmods.app.music;

import java.util.ArrayList;
import java.util.List;

import me.tmods.app.MusicApp;

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
					String s = "";
					while(text.charAt(i) != ']') {
						s = s + text.charAt(i);
						i++;
					}
					list.add(MusicApp.getSound(s));
				}
			}
		}
		System.out.println(list.toString());
		return list;
	}
}
