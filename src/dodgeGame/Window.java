package dodgeGame;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3387175331397768703L;
	
	public Window(int width, int height, String title, Game game) {
		//create Jframe with the title name
		JFrame frame = new JFrame(title);

		//sets the window size
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		//click on the exit button allow to end the thread
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);//start the window in the middle, OTW starts at top left corner
		frame.add(game);//add game class to the frame
		frame.setVisible(true);//see the frame
		game.start();
	}
}
