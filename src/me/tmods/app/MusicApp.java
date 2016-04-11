package me.tmods.app;


import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import me.tmods.app.music.Sound;
import me.tmods.app.music.Window;

public class MusicApp {
	public static Synthesizer synth;
	public static Instrument[] midiInstruments;
	public static MidiChannel midiChannel;
	public static List<Sound> sounds = new ArrayList<Sound>();
	public static Integer volume = 100;
	public static void main(String[] args) {
		Window.main(args);
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			midiInstruments = synth.getAvailableInstruments();
			midiChannel = synth.getChannels()[0];
			for (int i = 0;i < midiInstruments.length;i++) {
				Window.addInstrument(midiInstruments[i].getName(), i);
			}
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	public static synchronized void playMidi(final Integer sound) {
		if (sound != -1) {
			midiChannel.noteOn(sound, volume);
		}
	}
}
