package window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;

import objects.Block;
import objects.Enemy;
import objects.Flag;
import objects.Player;
import objects.Reset;
import framework.AudioPlayer;
import framework.GameObject;
import framework.ObjectId;

public class Handler 
{

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	private Camera cam;
	
	private BufferedImage level1 = null, level2 = null, level3 = null, level4 = null, level5 = null;

	private HashMap<String, AudioPlayer> sfx = new HashMap <String, AudioPlayer>();

	private BufferedImage currentLevel;

	public int LEVEL;

	
	public Handler(Camera cam)
	{
		this.cam = cam;
		LEVEL    = 1;
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 					   = loader.loadImage("/level1.png"); // loading the level
		level2 					   = loader.loadImage("/level2.png"); // loading the level
		level3					   = loader.loadImage("/level3.png"); // loading the level
		level4					   = loader.loadImage("/level4.png"); // loading the level
		level5 		               = loader.loadImage("/level5.png");
		currentLevel		       = level1;

		
		
		sfx.put("teleport",  new AudioPlayer("/Music/teleport.mp3"));
		sfx.put("hit",  new AudioPlayer("/Music/playerhit.mp3"));


		
	}
	
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void LoadImageLevel(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		
	// debuggin System.out.println("width, height " + w + " " + h );
		for(int xx = 0; xx < h; xx++)
		{
			for(int yy = 0; yy < w; yy++)
			{
				int pixel = image.getRGB(xx, yy);
				int red   = (pixel >> 16) & 0xff; 
				int green = (pixel >> 8)  & 0xff;
				int blue  = (pixel)       & 0xff;
				
				//white pixel color
				if(red == 255 && green == 255 && blue == 255) 
					addObject(new Block(xx * 32, yy * 32, 1, ObjectId.Block));
				// grey 
				if(red == 102 && green == 102 && blue == 102) 
					addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block));
				//blue pixel color for player
				if(red == 0 && green == 0 && blue == 255) 
					addObject(new Player(xx * 32, yy * 32,this, cam, ObjectId.Player));
				//yellow for new level
				if(red == 255 && green == 255 && blue == 0) 
					addObject(new Flag(xx * 32, yy * 32,ObjectId.Flag));
				//color for red
				if(red == 255 && green == 0 && blue == 0)
					addObject(new Reset(xx * 32, yy * 32,ObjectId.Reset));
				//greyer
				
				if(red == 51 && green == 51 && blue == 51)
					addObject(new Block(xx * 32, yy * 32,2,ObjectId.Block));
				
				if(red == 255 && green == 51 && blue == 153)
					addObject(new Block(xx * 32, yy * 32, 2,ObjectId.Block));
				if(red == 255 && green == 51 && blue == 153)
					addObject(new Block(xx * 32, yy * 32, 2,ObjectId.Block));
				if(red == 102 && green == 255 && blue == 102) 
					addObject(new Enemy(xx * 32, yy * 32, this, ObjectId.Enemy));
			}
		}
	}
	
	public void switchLevel()
	{
		sfx.get("hit").play();
		clearLevel();
		cam.setX(0);
		cam.setY(0);
		switch(LEVEL)
		{
		case 1:
			currentLevel = level1;
			LoadImageLevel(currentLevel);
			break;
		case 2:
			currentLevel = level2;
			LoadImageLevel(currentLevel);
			break;
		case 3:
			currentLevel = level3;
			LoadImageLevel(currentLevel);
			break;
		case 4:
			currentLevel = level4;
			LoadImageLevel(currentLevel);
			break;
		case 5:
			currentLevel = level5;
			LoadImageLevel(currentLevel);
			break;
		case 6:
			currentLevel = level1;
			LEVEL = 0;
			LoadImageLevel(currentLevel);
			break;
		}
		LEVEL++;
	}
	
	public void startLevel()
	{
		clearLevel();
		cam.setX(0);
		cam.setY(0);
		LoadImageLevel(currentLevel);
	}
	
	public void resetLevel()
	{
		clearLevel();
		cam.setX(0);
		cam.setY(0);
		LoadImageLevel(currentLevel);
	}
	
	private void clearLevel()
	{
		object.clear();
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
	/** level debug
	public void createLevel()
	{
		//floor
		for(int yy = 0; yy < Game.HEIGHT+32; yy+=32)
			addObject(new Block(0, yy, ObjectId.Block));
		
		//right side
		for(int xx = 0; xx < Game.WIDTH*2; xx+=32)
			addObject(new Block(xx, Game.HEIGHT-32, ObjectId.Block));
		
		//middle
		for(int xx = 200; xx < 600; xx+=32)
			addObject(new Block(xx, 400, ObjectId.Block));
	}
	*/
}
