package Ages.level.tile;

import Ages.graphics.Screen;
import Ages.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		// rendering will be done here
		screen.renderTile(x << 4, y << 4, this);
	}
}
