package me.tmods.app;


import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.tmods.app.music.ExceptionHandler;
import me.tmods.app.music.Sound;
import me.tmods.app.music.Window;

public class MusicApp {
	public static Synthesizer synth;
	public static Instrument[] midiInstruments;
	public static MidiChannel[] midiChannel;
	public static List<Sound> sounds = new ArrayList<Sound>();
	public static Integer[] volume = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
	public static Thread playThread;
	public static Integer[] beat = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public static Integer[] timeOut = {1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000};
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		Window.open();
		while (!Window.init){try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			Soundbank sb = synth.getDefaultSoundbank();
			midiInstruments = sb.getInstruments();
			midiChannel = synth.getChannels();
			for (int i = 0;i < midiInstruments.length;i++) {
				Window.addInstrument(midiInstruments[i].getName(), i);
			}
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	public static synchronized void playMidi(final Integer sound,final Integer channel) {
		if (sound != -1) {
			midiChannel[channel].noteOn(sound, volume[channel]);
		}
	}
	public static synchronized void stopMidi(final Integer sound,final Integer channel) {
		if (sound != -1) {
			midiChannel[channel].noteOff(sound);
		}
	}
}
