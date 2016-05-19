package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import window.Animation;
import window.Game;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

public class Bullet extends GameObject{
	
	private Animation bullet;
	Texture tex = Game.getInstance();


	public Bullet(float x, float y, ObjectId id, int velX) 
	{
		super(x, y, id);
		this.velocityX = velX;
		bullet = new Animation(1, tex.bullet[0]);

	}

	public void tick(LinkedList<GameObject> object) 
	{
		x += velocityX;
		bullet.runAnimation();
	}

	public void render(Graphics g) 
	{
		g.drawImage(tex.bullet[0], (int) x, (int) y, 32, 32, null);
		
		//debug
		//g.setColor(Color.red);
		//g.fillRect((int)x, (int) y,  16, 16);
	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y , 32, 32);
	}

}
