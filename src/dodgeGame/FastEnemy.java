package dodgeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject {
	private Handler handler;
	
	public FastEnemy(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 2;
		velY = 9;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//set boundary for baic enmey unit
		if(y<= 0|| y>= Game.HEIGHT - 32) velY *= -1;
		if(x<= 0|| x>= Game.WIDTH -16) velX *= -1;
		
		handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.pink,16,16,0.03f,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}
