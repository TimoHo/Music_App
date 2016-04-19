package me.tmods.app.music;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class DisplaySound extends Canvas{
	private List<Integer> sounds = new ArrayList<Integer>();
	private List<Integer> pos = new ArrayList<Integer>();
	public DisplaySound() {}
	public DisplaySound update() throws InstantiationException, IllegalAccessException {
		for (int i = 0;i<sounds.size();i++) {
			pos.set(i,pos.get(i) + 5);
			if (pos.get(i) > this.getWidth()) {
				sounds.remove(i);
				pos.remove(i);
			}
		}
		return this;
	}
	public DisplaySound add(Integer height) {
		sounds.add(height * 2);
		pos.add(0);
		return this;
	}
	@Override
	public void paint(Graphics g) {
		for(int i = 0;i<sounds.size();i++) {
			if (i != 0) {
				g.drawLine(this.getWidth() - pos.get(i-1), this.getHeight() - sounds.get(i-1),this.getWidth() - pos.get(i), this.getHeight() - sounds.get(i));
			}
		}
	}
}
