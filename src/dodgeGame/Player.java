package dodgeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
    Random r= new Random();
    Handler handler;
    
	public Player(int x, int y, ID id, Handler hanlder) {
		super(x, y, id);
		this.handler = hanlder;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//ensure the player move inside the game window
		x = Game.clamp((int)x, 0, Game.WIDTH-34);
		y = Game.clamp((int)y, 0, Game.HEIGHT-58);
		
		collision();
		
	}
	
	private void collision() {
		for(int i = 0; i<handler.object.size();i++) {
			//get each instance of game object
			GameObject tempObj = handler.object.get(i);
			
			//if the object is a basic enemy and intersect with palyer's bounds
			if(tempObj.getId() == ID.basicEnemy||tempObj.getId() == ID.fastEnemy||tempObj.getId() == ID.smartEnemy||tempObj.getId() == ID.bossEnemy) {
				if(getBounds().intersects(tempObj.getBounds())) {
					HUD.HEALTH -=2;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		//create a 32*32 white box representing player
		if(id == ID.Player)
			g.setColor(Color.white);
		//else if(id == ID.Player2) g.setColor(Color.blue);
		g.fillRect((int)x,(int)y, 32, 32);
		
	}
	
	
	
	

}
