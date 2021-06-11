package dodgeGame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x,y;//x,y coordinates
	protected ID id;//identify player,enemy
	protected float velX, velY;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return id;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public float getVelY() {
		return velY;
	}
}
