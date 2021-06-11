package dodgeGame;
import java.util.Random;
public class Spawn {

	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	
	public Spawn(Handler handler,HUD hud) {
		this.handler = handler;
		this.hud = hud;
		
	}
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 100) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel()+1);
			if(hud.getLevel()<10)
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.basicEnemy,handler));
			if (hud.getLevel()%6==0&&hud.getLevel()<10) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.fastEnemy,handler));
			}
			if (hud.getLevel()%4==0&&hud.getLevel()<10) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.smartEnemy,handler));
			}
			if (hud.getLevel()==10) {
				handler.clearEnemy();
				handler.addObject(new BossEnemy(Game.WIDTH/2 -48,-120,ID.smartEnemy,handler));
			}
			if(hud.getLevel()==20) {
				handler.clearEnemy();
			}
			
		}
	}
	
}
