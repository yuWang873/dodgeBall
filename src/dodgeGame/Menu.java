package dodgeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import dodgeGame.Game.STATE;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game =game;
		this.handler = handler;
		this.hud = hud;
	}
	public void mousePressed(MouseEvent e) {
		//store mouse's position
		int mx = e.getX();
		int my = e.getY();
		if(game.gameState == STATE.MENU) {
			//play button
			if(mouseOver(mx,my,210,150,200,64)) {
				game.gameState = STATE.GAME;	
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
			}
			
			//quit button
			else if(mouseOver(mx,my,210,350,200,64)) {
				
					System.exit(1);
			}
			
			//help screen
			else if(mouseOver(mx,my,210,250,200,64)) {
				game.gameState = STATE.HELP;
			}
		}
		
		//back button
		if(game.gameState == STATE.HELP) {
			if(mouseOver(mx,my,210,350,200,64)) {
				game.gameState= STATE.MENU;
				return;
			}
		}
		
		//Try Again button
		if(game.gameState == STATE.END) {
			if(mouseOver(mx,my,210,350,200,64)) {
				game.gameState= STATE.GAME;
				return;
			}
		}
		
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y,int width,int height) {
		if(mx > x && mx < x+ width) {
			if(my>y && my < y+height) {
				return true;
			} else return false;
		}
		else 
			return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("arial",1,50);
		Font fnt2 = new Font("arial",1,30);
		Font fnt3 = new Font("arial",1,20);
		
		if(game.gameState == STATE.MENU) {
		
			g.setFont(fnt);g.setColor(Color.WHITE);
			g.drawString("Pang Game", 165, 90);
			
			g.setFont(fnt2);g.setColor(Color.WHITE);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);
			
			g.setFont(fnt2);g.setColor(Color.WHITE);
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);
			
			g.setFont(fnt2);g.setColor(Color.WHITE);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
		}
		else if(game.gameState == STATE.HELP) {
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("Using WASD to move and dodge enemies", 100, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		}
		else if(game.gameState == STATE.END) {
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 180, 70);
			
			g.setFont(fnt3);
			g.drawString("You LOST, SCORE: " + hud.getScore(), 200, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 245, 390);
		}
	}
}
