package me.tmods.app;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;

import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MidiReader {
    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = {"C-", "C#", "D-", "D#", "E-", "F-", "F#", "G-", "G#", "A-", "A#", "H-"};
    public static int lastTick = 0;
    public static int bpm = 100;
    public static int ppqn;
    public static Writer textOutput;
    public static PrintWriter wrt;
    public static boolean add;
    public static double speed;
    public static boolean finished = false;
    public static void read(String file, Integer bpm, Integer speed, Boolean add) throws Exception {
    	MidiReader.add = add;
    	if (MidiReader.add) {
    		MidiReader.speed = speed;
    	} else {
    		MidiReader.speed = (((double)speed) / ((double) 100) + 1);
    	}
    	textOutput = new StringWriter();
    	wrt = new PrintWriter(textOutput);
    	wrt.println("[O: 1000]");
    	MidiReader.bpm = bpm;
        Sequence sequence = MidiSystem.getSequence(new File(file));
        ppqn = sequence.getResolution();
        for (Track track:sequence.getTracks()) {
            for (int i=0; i < track.size(); i++) { 
            	MidiEvent event = track.get(i);
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    if (sm.getCommand() == NOTE_ON) {
                    	MidiReader.lastTick = (int) event.getTick();
                    	int x;
                    	if (track.size() > i+1) {
                    		for (x=i+1;x<track.size();x++) {
                    			if (x < track.size()) {
                    				if (track.get(x).getMessage() instanceof ShortMessage) {
                        				if (((ShortMessage) track.get(x).getMessage()).getCommand() == NOTE_ON) {
                        					break;
                        				}
                        			}
                    			}
                    		}
                    		if (x < track.size()) {
                        		wrt.println("[B: " + MidiReader.getDeltaTime((int) track.get(x).getTick()) + "](" + sm.getChannel() + ")");
                    		}
                    	}
                        int key = sm.getData1();
                        int octave = (key / 12)-1;
                        int note = key % 12;
                        String noteName = NOTE_NAMES[note];
                        wrt.println("[" + noteName + octave + "](" + sm.getChannel() + ")");
                    } else {
                    	if (sm.getCommand() == 192 || sm.getCommand() == 193) {
                    		wrt.println("[I: " + sm.getData1() + "](" + sm.getChannel() + ")");
                    	} else {
                    		if (sm.getCommand() == 248) {
                    			bpm = sm.getData1();
                    		}
                    	}
                    }
                } else {
                	if (message instanceof MetaMessage) {
                		MetaMessage mm = (MetaMessage) message;
                		if (mm.getStatus() == 248) {
                			byte[] arr = mm.getData();
                			ByteBuffer wrapped = ByteBuffer.wrap(arr);
                			short num = wrapped.getShort();
                			MidiReader.bpm = (int) num;
                		}
                	}
                }
            }
        }
        wrt.flush();
        finished = true;
    }
    public static int getDeltaTime(int tick) {
    	int deltaTicks = (tick - MidiReader.lastTick);
    	int deltaTime = 60000 / (bpm*ppqn);
    	int deltams = (deltaTime*deltaTicks);
    	int deltatime;
    	if (MidiReader.add) {
    		deltatime = (int) (deltams + MidiReader.speed);
    	} else {
    		deltatime = (int) (deltams * MidiReader.speed);
    	}
    	return Math.max(deltatime, 0);
    }
}