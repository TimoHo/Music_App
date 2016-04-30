package me.tmods.app.music;

import java.util.Timer;
import java.util.TimerTask;

import me.tmods.app.MusicApp;

public class Sound{
	private String name;
	private Integer option;
	private Integer note;
	private Integer begin;
	private Integer end;
	private Integer channel;
	public Sound(String name,Integer begin,Integer end,Integer channel) {
		this.name = name;
		this.option = 0;
		this.note = SoundConstructor.getNote(name);
		this.begin = begin;
		this.end = end;
		this.channel = channel;
    }
	public Sound(String name,Integer option,Integer begin,Integer end,Integer channel) {
		this.name = name;
		this.option = option;
		this.note = -1;
		this.begin = begin;
		this.end = end;
		this.channel = channel;
	}
	public Integer getBegin() {
		return this.begin;
	}
	public Integer getEnd() {
		return this.end;
	}
    public String getName() {
    	return this.name;
	}
    public void play() {
    	if (this.name == "B") {
    		MusicApp.beat[this.channel] = this.option;
    	} else {
    		if (this.name == "X") {
    			MusicApp.midiChannel[this.channel].setMute(true);
    			MusicApp.midiChannel[this.channel].allNotesOff();
    			MusicApp.midiChannel[this.channel].allSoundOff();
    			MusicApp.midiChannel[this.channel].setMute(false);
    		} else {
            	if (this.name == "K") {
            		MusicApp.midiChannel[this.channel].allNotesOff();
            	} else {
            		if (this.name == "I") {
            			MusicApp.midiChannel[this.channel].programChange(this.option);
            		} else {
            			if (this.name == "V") {
            				MusicApp.volume[this.channel] = this.option;
            			} else {
                    		if (this.name == "O") {
                    			MusicApp.timeOut[channel] = this.option;
                    		} else {
                    			MusicApp.playMidi(this.note,this.channel);
                        		TimerTask off = new TimerTask() {
    								@Override
    								public void run() {
    									MusicApp.stopMidi(note, channel);
    								}	
                        		};
                        		Timer timeOut = new Timer();
                        		timeOut.schedule(off, MusicApp.timeOut[channel]);
                    		}
            			}
            		}
            	}
    		}
    	}
    }
    public Integer getChannel() {
    	return this.channel;
    }
    @Override
    public String toString() {
    	return this.name + ":" + this.option;
    }
    
    public Integer getHeight() {
    	return SoundConstructor.getNote(name);
    }
}