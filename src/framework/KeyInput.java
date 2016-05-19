package framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javazoom.jl.player.Player;
import objects.Bullet;
import window.Handler;

public class KeyInput implements KeyListener{
	
	Handler handler;
	private HashMap<String, AudioPlayer> sfx = new HashMap <String, AudioPlayer>();
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
		//sounds
		sfx.put("shoot",  new AudioPlayer("/Music/playerattack.mp3"));
		sfx.put("jump",  new AudioPlayer("/Music/playerjump.mp3"));


	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_RIGHT) {
					tempObject.setvelocityX(4);
				}

				if(key == KeyEvent.VK_LEFT){
					tempObject.setvelocityX(-4);
				}

				if(key == KeyEvent.VK_SPACE && !tempObject.isJumping())
					{
					    tempObject.setJumping(true);
						tempObject.setvelocityY(-10);
						sfx.get("jump").play();
					}
				if(key == KeyEvent.VK_W)
				{
					handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, tempObject.getFacing() * 10));
					sfx.get("shoot").play();
				}
				
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE)
		{
			System.exit(1);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_RIGHT) tempObject.setvelocityX(0);
				if(key == KeyEvent.VK_LEFT) tempObject.setvelocityX(0);
				
			}

		}
		
	}
}
