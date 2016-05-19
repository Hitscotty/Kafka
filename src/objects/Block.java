package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import window.Game;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;

public class Block extends GameObject
{
	Texture tex = Game.getInstance();
	private int type;
	
	public Block(float x, float y, int type, ObjectId id)
	{
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		
		if(type == 0) // dirt block
			g.drawImage(tex.block[0],(int) x,(int) y, null);
		if(type == 1) // grass block
			g.drawImage(tex.block[1],(int) x,(int) y, null);
		if(type == 2) // grass block
			g.drawImage(tex.block[2],(int) x,(int) y, null);

		 //debugging images
	//	g.setColor(Color.WHITE);
	//	g.drawRect((int) x, (int) y, 32, 32);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
