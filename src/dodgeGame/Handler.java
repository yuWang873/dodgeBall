package dodgeGame;

import java.awt.Graphics;
import java.util.LinkedList;

//handle every objects in the game, update and render to the screen
public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();

	//let every game object execute tick function
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			//get the id of the current object, i runs the entire linked list of game objects
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	//render every game object to the  game screen
	public void render(Graphics g) {
		for (int i = 0; i<object.size();i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	//add a new game object to the linked list
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	//remove one game object from linked list
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearEnemy() {
		for (int i = 0; i<object.size();i++) {
			GameObject tempObject = object.get(i);
			if(Game.gameState !=Game.STATE.END) {
				if(tempObject.getId()!=ID.Player) {
				this.removeObject(tempObject);
				i--;
				}
			}
			else {
				object.clear();
				
			}
		}
	}
	

}
