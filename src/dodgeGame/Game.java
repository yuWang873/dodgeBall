package dodgeGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4265891881552621074L;
	//algorithm to create a good aspect ratio
	public static final int WIDTH =640, HEIGHT = WIDTH/12*9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE{
		MENU,
		GAME,
		HELP,
		END;
	}
	
	public static STATE gameState = STATE.MENU;
	
	//constructor of game class, sets the game window, and title
	public Game() {
		handler = new Handler();//initialize the handler, need to be first before window created
		hud = new HUD();
		menu = new Menu(this,handler,hud);
		
		//initialize the key listener to get input from key board
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		new Window(WIDTH,HEIGHT,"Pang Game!",this);
		spawner = new Spawn(handler, hud);
		r = new Random();
		
		
		/*//create 50 player object at random places
		for(int i = 0;i<50;i++) {
			
			handler.addObject(new Player(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.Player));
		}
		*/
		
		//if(gameState == STATE.GAME)
		//add a player object
		//handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player,handler));
		//handler.addObject(new Player(WIDTH/2+64,HEIGHT/2-32,ID.Player2));
		//handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.basicEnemy,handler));
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();//kill the thread
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		thread.start();
	}
	
	public void run() {
		this.requestFocus();//allow for keyboard control right away without clicking the window first
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta --;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		if(gameState == STATE.GAME) {
			hud.tick();
			spawner.tick();
	
			
			if(hud.HEALTH <= 0) {
	
				gameState = STATE.END;
				handler.clearEnemy();
				HUD.HEALTH =100;
				hud.setLevel(1);
				hud.score(0);
			
			}
		}
		else if(gameState == STATE.MENU||gameState == STATE.END) {
			menu.tick();
		}
		
	}
	
	private void render() {
		//limits the fps
		BufferStrategy bs = this.getBufferStrategy();
		if(bs ==null) {
			this.createBufferStrategy(3);//create three buffers
			return;
		}
		//draw the background
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gameState == STATE.GAME) {
			hud.render(g);
		}
		
		else if(gameState == STATE.MENU || gameState == STATE.HELP||gameState == STATE.END) {
			menu.render(g);
		}
		
	
		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return min;
		else
			return var;
	}
	
	public static void main(String args[]) {
		//create new instance of game class
		new Game();
	}
	
}
