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

public class Flag extends GameObject{
	
	private Animation portal;
	Texture tex = Game.getInstance();


	public Flag(float x, float y, ObjectId id) 
	{
		super(x, y, id);
		portal = new Animation(12, tex.portal[0], tex.portal[1], tex.portal[2], tex.portal[3]);

	}

	public void tick(LinkedList<GameObject> object) 
	{
		portal.runAnimation();
	}

	public void render(Graphics g) 
	{
		/**
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int) y,  32, 32);*/
		portal.drawAnimation(g, (int) x, (int) y, 80, 65);

	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y , 80, 65);
	}

}
