package Ages.graphics;

/*
 * Sprite class for each sprite in the game, handles different sizes.
 */

public class Sprite {

	public final int SIZE;
	private int x, y;
	private SpriteSheet sheet;
	public int[] pixels;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16,0x1B87E0);
	//public static Sprite mountain = new Sprite(16,16,0,SpriteSheet.tiles);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();

	}
	public Sprite(int size, int color) {
		SIZE  =size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	public void setColor(int color) {
		for(int i =0;i< SIZE*SIZE;i++) {
			pixels[i] = color;
		}
	}

	private void load() {

		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y)
						* sheet.SIZE];
			}
		}
	}

}
