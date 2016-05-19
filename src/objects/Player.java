package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import window.Animation;
import window.Camera;
import window.Game;
import window.Handler;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

public class Player extends GameObject{

	private float width = 32, height = 32;
	private float gravity = 0.3f;
	private final float MAX_SPEED = 10;
	public int shots = 0;
	
	private Handler handler;
	private Camera cam;
	
	Texture tex = Game.getInstance();
	
	private Animation playerWalkRight;
	private Animation playerWalkLeft;
	private Animation playerIdle;
	


	
	public Player(float x, float y, Handler handler, Camera cam, ObjectId id)
	{
		super(x, y, id);
		this.cam 		= cam;
		this.handler    = handler;
		playerWalkRight = new Animation(3, tex.playerRight[0], tex.playerRight[1], tex.playerRight[2]);
		playerWalkLeft  = new Animation(3, tex.playerLeft [0], tex.playerLeft [1], tex.playerLeft [2]);
		playerIdle      = new Animation(3, tex.playerIdle [0], tex.playerIdle [1], tex.playerIdle [2]);
		

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
		
		if(falling || jumping)
		{
			velocityY += gravity;
			
			if(velocityY > MAX_SPEED) velocityY = MAX_SPEED;
		}
		
		
		Collision(object);
		
		playerWalkRight.runAnimation();
		playerWalkLeft.runAnimation();
		playerIdle.runAnimation();
		
		
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
				//Top
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() + 32;
					velocityY = 0;
				} 
				//Bottom
				if(getBounds().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() - height;
					velocityY = 0;
					falling   = false;
					jumping   = false;
				} else {
					falling = true;
				}
				
				//Right
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX() - width;
				} 
				
				//Left
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX() + 35;
				} 
			} 
			else if (tempObject.getId() == ObjectId.Flag)
			{
				//switch levels
				if(getBounds().intersects(tempObject.getBounds()))
				{
					handler.switchLevel();

				}
			}
			else if (tempObject.getId() == ObjectId.Reset)
			{
				//switch levels
				if(getBounds().intersects(tempObject.getBounds()))
				{
					handler.LEVEL = 1;
					handler.switchLevel();

				}
			}
			else if (tempObject.getId() == ObjectId.Enemy)
			{
				//switch levels
				if(getBounds().intersects(tempObject.getBounds()))
				{
					handler.resetLevel();

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
		
		/** debugging collision 
		for(int i = 0; i < tex.player.length; i++)
		{
			g.drawImage(tex.player[i], (int) x, (int) y, null);

		}
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
		*/
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
