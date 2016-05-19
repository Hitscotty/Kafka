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

public class Reset extends GameObject{
	


	public Reset(float x, float y, ObjectId id) 
	{
		super(x, y, id);

	}

	public void tick(LinkedList<GameObject> object) 
	{
	}

	public void render(Graphics g) 
	{
		
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int) y,  32, 32);

	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y , 80, 65);
	}

}
