import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import java.io.IOException;

public class MyGameCanvas
  extends GameCanvas implements Runnable {

	public MyGameCanvas() {
	  super(true);
	}

	public void start() {

	  try {

	    // create and load an image to the center

		pokemonImg = Image.createImage("/blinky.png");
		centerX = CENTER_X;
		centerY = CENTER_Y;

	  } 
	  catch(IOException ioex) {
		 System.err.println(ioex);
	   }

	  Thread forever = new Thread(this);
	  forever.start();

	}

	public void run() {

	  // the graphics object for this canvas
	  Graphics g = getGraphics();

	  while(true) { // infinite loop

  	    // based on the structure

  		// first verify game state
  		checkGameState();

  		// check user's input
  		checkUserInput();

  		// update screen
  		updateGameScreen(getGraphics());

		// and sleep, this controls
		// how fast refresh is done
		try {
		  Thread.currentThread().sleep(25);
		} catch(Exception e) {}

	  }

	}

	private void checkGameState() {

	}

	private void checkUserInput() {

	  // get the state of keys
	  int keyState = getKeyStates();

	  // calculate the position for x axis
	  calculateCenterX(keyState);

	}

	private void updateGameScreen(Graphics g) {

	  // the next two lines clear the background to white.
	  g.setColor(0xFFFFFF);
	  g.fillRect(0, 0, getWidth(), getHeight());

	  // draws the pokeman image in the current position
	  g.drawImage(pokemonImg, centerX, centerY, Graphics.HCENTER | Graphics.BOTTOM);

	  // this call paints off screen buffer to screen
	  flushGraphics();

	}

	private void calculateCenterX(int keyState) {

	  // determines which way to move and changes the
	  // x coordinate accordingly
	  if((keyState & LEFT_PRESSED) != 0) {
	    centerX -= dx;
	  }
	  else if((keyState & RIGHT_PRESSED) != 0) {
	    centerX += dx;
	  }

	}

	// the pokemon image
	private Image pokemonImg;

	// pokemon image coordinates
	private int centerX;
	private int centerY;

	// the distance to move in the x axis
	private int dx = 1;

	// the center of the screen
	public final int CENTER_X = getWidth()/2;
	public final int CENTER_Y = getHeight()/2;

}