package org.derithium;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * The main class of the client.
 * @author Stephen
 */
public class Client {

	private static final int WIDTH = 765;
	private static final int HEIGHT = 503;
	
	/**
	 * A game object.
	 */
	private static Game game;
	
	private static final int FRAMERATE = 60;
	
	public static void main(String[] args) {
		initDisplay();
		initGL();
		initGame();
		gameLoop();
		cleanUp();
	}

	private static void initGame() {
		game = new Game();
	}
	
	private static void getInput() {
		game.getInput();
	}
	
	private static void update() {
		game.update();
	}
	
	private static void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		
		game.render();
		
		Display.update();
		Display.sync(FRAMERATE);
	}
	
	private static void gameLoop() {
		while (!Display.isCloseRequested()) {
			getInput();
			update();
			render();
		}
	}
	
	private static void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		glMatrixMode(GL_MODELVIEW);
		
		glClearColor(0, 0, 0, 1);
		
		glDisable(GL_DEPTH_TEST);
	}
	
	private static void cleanUp() {
		Display.destroy();
	}
	
	private static void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
