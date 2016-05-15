package me.tmods.app.music;

import java.lang.Thread.UncaughtExceptionHandler;

import me.tmods.app.MusicApp;

@SuppressWarnings("deprecation")
public class ExceptionHandler implements UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		if (MusicApp.playThread != null) {
			if (MusicApp.playThread.isAlive()) {
				MusicApp.playThread.stop();
			}
		}
		Window.errorMessage("Error in Thread " + t.getName(),e);
	}
}
