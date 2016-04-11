package me.tmods.app.music;

import me.tmods.app.MusicApp;

public class Sound{
	private String name;
	private Integer option;
	private Integer note;
	public Sound(String name) {
		this.name = name;
		this.option = 0;
		this.note = SoundConstructor.getNote(name);
    }
	public Sound(String name,Integer option) {
		this.name = name;
		this.option = option;
		this.note = -1;
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
}