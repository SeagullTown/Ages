package Ages.entity.mob;

import Ages.entity.Entity;
import Ages.graphics.Sprite;

public abstract class Mob extends Entity {
	protected Sprite sprite;
	//Direction the mob is looking at.
	protected int dir = 0;
	
	protected boolean moving = false;
	
	public void move() {
		
	}
	
	public void update() {
		
	}
	
	private boolean collision() {
		return false;
	}

}
