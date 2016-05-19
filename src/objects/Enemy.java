package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.LinkedList;

import window.Animation;
import window.Camera;
import window.Game;
import window.Handler;
import framework.AudioPlayer;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

public class Enemy extends GameObject{

	private float width = 32, height = 32;
	private float gravity = 0;
	private final float MAX_SPEED = 10;
	
	private Handler handler;
	private Camera cam;
	
	Texture tex = Game.getInstance();
	
	private Animation playerWalkRight;
	private Animation playerWalkLeft;
	private HashMap<String, AudioPlayer> sfx;
	private int velocityX = 4;




	
	public Enemy(float x, float y, Handler handler, ObjectId id)
	{
		super(x, y, id);
		this.handler    = handler;
		playerWalkRight = new Animation(3, tex.enemyRight[0], tex.enemyRight[1], tex.enemyRight[2]);
		playerWalkLeft  = new Animation(3, tex.enemyLeft [0], tex.enemyLeft [1], tex.enemyLeft [2]);
		
		sfx = new HashMap <String, AudioPlayer>();
		sfx.put("enemy",  new AudioPlayer("/Music/enemyhit.mp3"));


	}
	
	public void tick(LinkedList<GameObject> object) {
		x += velocityX;
		y += velocityY;
		
		if(velocityX < 0) 
		{
			facing = -1;
		}
		
		else if(velocityX > 0) 
		{
			facing = 1;
		}
		
		Collision(object);
		
		playerWalkRight.runAnimation();
		playerWalkLeft.runAnimation();
		
		
	}

	//checks my deviously made invisible rectangles around objects 
	// if they intersect stop movement
	private void Collision(LinkedList<GameObject> object)
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			
			if(tempObject.getId() == ObjectId.Block)
			{

				//Right
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX() - width;
					velocityX = -4;
				} 
				
				//Left
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX() + 35;
					velocityX = 4;
				} 
			
			} 
			
			if(tempObject.getId() == ObjectId.Bullet)
			{
				//Top
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{
					sfx.get("enemy").play();

					handler.object.remove(this);
				} 
				//Bottom
				if(getBounds().intersects(tempObject.getBounds()))
				{
					sfx.get("enemy").play();

					handler.object.remove(this);

				}
				
				//Right
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{
					sfx.get("enemy").play();

					handler.object.remove(this);

				} 
				
				//Left
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{
					sfx.get("enemy").play();

					handler.object.remove(this);
					
				} 
			
			} 
		
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		if(velocityX != 0)
		{
			if(facing == 1)
				playerWalkRight.drawAnimation(g, (int) x, (int) y, 32, 32);
			else 
				playerWalkLeft.drawAnimation(g, (int) x, (int) y, 32, 32);
		}
		else 
		{
			if(facing == 1)
			{
				g.drawImage(tex.playerRight[0], (int) x, (int) y, 32, 32, null);
			}
			else if(facing == -1)
			{
				g.drawImage(tex.playerLeft[0], (int) x, (int) y, 32, 32, null);

			}
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + (width/2) - (width/4)), (int) ((int) y + (height/2)), (int) width/2, (int) height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + (width/2) - (width/4)), (int) y, (int) width/ 2, (int) height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}
	

}
