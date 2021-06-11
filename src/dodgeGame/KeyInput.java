package dodgeGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private boolean[] keyDown = new boolean[4];

	public KeyInput(Handler handler) {
		this.handler = handler;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();//get the letter value corresponding to the key pressed
		
		//loop all the game object, and find player object
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			//controls for player 1
			if(tempObject.getId()==ID.Player) {
				if(key == KeyEvent.VK_W) {//up ---- W
					tempObject.setVelY(-6);keyDown[0] = true;}
				if(key == KeyEvent.VK_S) {//down ----S
					tempObject.setVelY(6);keyDown[1] = true;}
				if(key == KeyEvent.VK_A) {//left ---- A
					tempObject.setVelX(-6);keyDown[2] = true;}
				if(key == KeyEvent.VK_D) {//right ----D
					tempObject.setVelX(6);keyDown[3] = true;}
			}
			if(key == KeyEvent.VK_ESCAPE) System.exit(1);
			/*
			if(tempObject.getId() == ID.Player2) {
				if(key == KeyEvent.VK_I) //up ---- I
					tempObject.setVelY(-3);
				if(key == KeyEvent.VK_K)// down ---- K
					tempObject.setVelY(3);
				if(key == KeyEvent.VK_J)//left ---- J
					tempObject.setVelX(-3);
				if(key == KeyEvent.VK_L)//right ----L
					tempObject.setVelX(3);
			}
			*/
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		//stop the motion when key released
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			//controls for player 1
			if(tempObject.getId()==ID.Player) {
				if(key == KeyEvent.VK_W)//up ---- W
					keyDown[0] = false;
				if(key == KeyEvent.VK_S)//down ----S
					keyDown[1] = false;
				if(key == KeyEvent.VK_A)//left ---- A
					keyDown[2] = false;
				if(key == KeyEvent.VK_D)//right ----D
					keyDown[3] = false;
			
			
			//vertical movement
			if(!keyDown[0]&&!keyDown[1]) tempObject.setVelY(0);
			
			//horizontal movement
			if(!keyDown[2]&&!keyDown[3]) tempObject.setVelX(0);
			}
			/*
			if(tempObject.getId() == ID.Player2) {
				if(key == KeyEvent.VK_I) //up ---- I
					tempObject.setVelY(0);
				if(key == KeyEvent.VK_K)// down ---- K
					tempObject.setVelY(0);
				if(key == KeyEvent.VK_J)//left ---- J
					tempObject.setVelX(0);
				if(key == KeyEvent.VK_L)//right ----L
					tempObject.setVelX(0);
			}
			*/
		}
	}
}
