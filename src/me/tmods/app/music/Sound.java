package me.tmods.app.music;

import me.tmods.app.MusicApp;

public class Sound{
	private String name;
	private Integer option;
	private Integer note;
	private Integer begin;
	private Integer end;
	public Sound(String name,Integer begin,Integer end) {
		this.name = name;
		this.option = 0;
		this.note = SoundConstructor.getNote(name);
		this.begin = begin;
		this.end = end;
    }
	public Sound(String name,Integer option,Integer begin,Integer end) {
		this.name = name;
		this.option = option;
		this.note = -1;
		this.begin = begin;
		this.end = end;
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
    		MusicApp.beat = this.option;
    	} else {
    		if (this.name == "X") {
    			MusicApp.midiChannel.setMute(true);
    			MusicApp.midiChannel.allNotesOff();
    			MusicApp.midiChannel.allSoundOff();
    			MusicApp.midiChannel.setMute(false);
    		} else {
            	if (this.name == "K") {
            		MusicApp.midiChannel.allNotesOff();
            	} else {
            		if (this.name == "I") {
            			MusicApp.midiChannel.programChange(this.option);
            		} else {
            			if (this.name == "V") {
            				MusicApp.volume = this.option;
            			} else {
                    		MusicApp.playMidi(this.note);
            			}
            		}
            	}
    		}
    	}
    }
    
    @Override
    public String toString() {
    	return this.name + ":" + this.option;
    }
    
    public Integer getHeight() {
    	return SoundConstructor.getNote(name);
    }
}