package Ages.level.tile;

import Ages.graphics.Screen;
import Ages.graphics.Sprite;

public class Tile {
	
	public int x,y;
	public Sprite sprite;
	
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	//public static Tile mountain = new MountainTile(Sprite.mountain);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y,Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}
}
