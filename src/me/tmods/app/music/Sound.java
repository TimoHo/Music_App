package me.tmods.app.music;

import me.tmods.app.MusicApp;

public class Sound{
	private String name;
	private Integer option;
	private Integer note;
	public Sound(String name) {
		this.name = name;
		this.option = 0;
		this.note = MusicApp.getNotePos(name);
    }
	public Sound(String name,Integer option) {
		this.name = name;
		this.option = option;
		this.note = MusicApp.getNotePos(name);
	}
    public String getName() {
    	return this.name;
	}
    public void play() {
    	if (this.name == "B") {
    		try {
				Thread.sleep(this.option);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	} else {
    		if (this.name == "X") {
    			MusicApp.midiChannel.allSoundOff();
    		} else {
            	if (this.name == "K") {
            		MusicApp.midiChannel.allNotesOff();
            	} else {
            		MusicApp.playMidi(this.note);
            	}
    		}
    	}
    }
    
    @Override
    public String toString() {
    	return this.name + ":" + this.option;
    }
}