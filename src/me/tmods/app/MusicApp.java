package me.tmods.app;


import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

import me.tmods.app.music.Sound;
import me.tmods.app.music.Window;

public class MusicApp {
	public static Synthesizer synth;
	public static Instrument midiIntrument;
	public static MidiChannel midiChannel;
	public static List<Sound> sounds = new ArrayList<Sound>();
	public static void main(String[] args) {
		sounds = getAllSounds();
		Window.main(args);
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			midiIntrument = synth.getAvailableInstruments()[0];
			midiChannel = synth.getChannels()[0];
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	public static synchronized void playMidi(final Integer soundName) {
		midiChannel.noteOn(soundName, 100);
	}
	/*
	public static synchronized void playSound(final String soundName) {
		 new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("resources/notes/" + soundName));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		}).start();
	}
	*/
	public static List<Sound> getAllSounds() {
		List<Sound> list = new ArrayList<Sound>();
		list.add(new Sound("C"));
		list.add(new Sound("D"));
		list.add(new Sound("E"));
		list.add(new Sound("F"));
		list.add(new Sound("G"));
		list.add(new Sound("A"));
		list.add(new Sound("H"));
		
		list.add(new Sound("C1"));
		list.add(new Sound("D1"));
		list.add(new Sound("E1"));
		list.add(new Sound("F1"));
		list.add(new Sound("G1"));
		list.add(new Sound("A1"));
		list.add(new Sound("H1"));
		
		list.add(new Sound("C2"));
		list.add(new Sound("D2"));
		list.add(new Sound("E2"));
		list.add(new Sound("F2"));
		list.add(new Sound("G2"));
		list.add(new Sound("A2"));
		list.add(new Sound("H2"));
		
		list.add(new Sound("X"));
		list.add(new Sound("K"));
		return list;
	}
	public static Sound getSound(String s) {
		for (Sound snd:sounds) {
			if (snd.getName().equalsIgnoreCase(s)) {
				return snd;
			}
		}
		return null;
	}
	public static Integer getNotePos(String name) {
		switch(name) {
		case("C"): return 5;
		case("D"): return 6;
		case("E"): return 7;
		case("F"): return 8;
		case("G"): return 9;
		case("A"): return 10;
		case("H"): return 11;
		
		case("C1"): return 12;
		case("D1"): return 13;
		case("E1"): return 14;
		case("F1"): return 15;
		case("G1"): return 16;
		case("A1"): return 17;
		case("H1"): return 18;
		
		case("C2"): return 19;
		case("D2"): return 20;
		case("E2"): return 21;
		case("F2"): return 22;
		case("G2"): return 23;
		case("A2"): return 24;
		case("H2"): return 25;
		default: return null;
		}
	}
}
