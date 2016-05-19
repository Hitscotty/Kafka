package window;

import framework.GameObject;

public class Camera {
	
	private float x, y;
	private float offsetMaxX, offsetMaxY, offsetMinX, offsetMinY;
	
	public Camera(float x, float y)
	{
		this.x = x;
		this.y = y;
		offsetMaxX = (Game.WIDTH/2) ;
		offsetMaxY = (Game.HEIGHT/2) ;
	}
	
	public void tick(GameObject player)
	{
		
		
		float xTarg = -player.getX() + Game.WIDTH/2;
		float yTarg = -player.getY() + Game.HEIGHT/2;
		x += (xTarg - x) * 0.05;
		y += (yTarg - y) * 0.05;

//		y = -player.getY() + offsetMaxY;
		//y = -player.getY() + offsetMaxY;
		//x += ((-player.getX() - x)/20);
		//y += ((-player.getY() - y)/20);
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}

}
