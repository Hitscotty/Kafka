package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

import framework.AudioPlayer;
import framework.KeyInput;
import framework.ObjectId;
import framework.Texture;

public class Game extends Canvas implements Runnable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7492659545089075909L;
	
	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	
	public BufferedImage level1 = null, cave = null;;
	//object
	Handler handler;
	Camera cam;
	static Texture tex;
	
	private AudioPlayer bgMusic;
	
	Random rand = new Random();
		
	private void init()
	{
		WIDTH   = getWidth();
		HEIGHT  = getHeight();
		
		tex = new Texture();
	
   
		
	//	debugging levels	
		BufferedImageLoader loader = new BufferedImageLoader();
		cave 					   = loader.loadImage("/cave.png");
 		cam     				   = new Camera(0,0);
 		handler 				   = new Handler(cam);
 		handler.startLevel();
 		//handler.addObject(new Player(100,100, handler, ObjectId.Player));
 		
		//handler.createLevel();
		this.addKeyListener(new KeyInput(handler));
		

		// music
		bgMusic = new AudioPlayer("/Music/bg.mp3");
		bgMusic.play();

	}
	
	public synchronized void start()
	{
		//fail safe error checkking 
		if(running) return;
		
		running = true;
		thread  = new Thread(this);
		thread.start();
	}
	
	
	public void run()
	{

		init();
		
		this.requestFocus();
		long lastTime 		 = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns      	     = 1000000000/ amountOfTicks;
		double delta 		 = 0;
		long timer   		 = System.currentTimeMillis();
		int updates  		 = 0;
		int frames   		 = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer  += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames  = 0;
				updates = 0;
			}
		}
	}
	
	//update stuff
	private void tick()
	{
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++)
		{
			if(handler.object.get(i).getId() == ObjectId.Player)
			{
				cam.tick(handler.object.get(i));
			}
		}
	}
	
	//graphical stuff
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy(); // this = canvas 
		if(bs == null)
		{
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		////////////////////////////////
		// Draw our game here
		g.setColor(Color.black);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(cave, 0, 50, this);

		
		g2d.translate(cam.getX(), cam.getY()); // begin cam
		
			handler.render(g); // adffected my cam
		
		g2d.translate(-cam.getX(),-cam.getY()); // end of cam

		///////////////////////////////
		g.dispose();
		bs.show();
	}
		
	public static Texture getInstance()
	{
		return tex;
	}
	
	public static void main(String [] args)
	{
		new Window(1366, 768, "Kafka on the Shore", new Game());
	}
}
