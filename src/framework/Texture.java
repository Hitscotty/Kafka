package framework;

import java.awt.image.BufferedImage;

import window.BufferedImageLoader;

public class Texture {
	
	SpriteSheet bs, ps, gs, prt,em;
	
	private BufferedImage blockSheet  = null;
	private BufferedImage playerSheet = null;
	private BufferedImage gunSheet    = null;
	private BufferedImage portalSheet = null;
	
	public BufferedImage [] block       = new BufferedImage[5];
	public BufferedImage [] playerRight = new BufferedImage[3];
	public BufferedImage [] playerLeft  = new BufferedImage[3];
	public BufferedImage [] playerIdle  = new BufferedImage[3];
	public BufferedImage [] enemyRight  = new BufferedImage[3];
	public BufferedImage [] enemyLeft   = new BufferedImage[3];
	
	public BufferedImage [] bullet      = new BufferedImage[1];
	public BufferedImage [] portal      = new BufferedImage[4];


	
	public Texture()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			blockSheet  = loader.loadImage("/blockSheet.png");
			playerSheet = loader.loadImage("/playerSheet.png");
			gunSheet    = loader.loadImage("/gunSheet.png");
			portalSheet = loader.loadImage("/portal.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		bs  = new SpriteSheet(blockSheet);
		ps  = new SpriteSheet(playerSheet);
		gs  = new SpriteSheet(gunSheet);
		prt = new SpriteSheet(portalSheet);
 		
		getTextures();
	}
	
	private void getTextures()
	{
		block[0] = bs.grabImage(15, 1, 32, 32); //dirtblock
		block[1] = bs.grabImage(5, 1, 32, 32); //grassblock
		block[2] = bs.grabImage(5, 1, 32, 32); //grassblock
		block[3] = bs.grabImage(15, 4, 32, 32); //grassblock
		block[4] = bs.grabImage(5, 15, 32, 32); //grassblock



		// player 
		playerRight[0] = ps.grabImage(7, 3, 32, 32); 
		playerRight[1] = ps.grabImage(8, 3, 32, 32); 
		playerRight[2] = ps.grabImage(9, 3, 32, 32); 
		
		playerLeft[0] = ps.grabImage(7, 2, 32, 32);
		playerLeft[1] = ps.grabImage(8, 2, 32, 32);
		playerLeft[2] = ps.grabImage(9, 2, 32, 32);
		
		playerIdle[0] = ps.grabImage(7, 1, 32, 32);
		playerIdle[1] = ps.grabImage(8, 1, 32, 32);
		playerIdle[2] = ps.grabImage(9, 1, 32, 32);
		
		
		//enemy
		enemyRight[0] = ps.grabImage(1, 3, 32, 32); 
		enemyRight[1] = ps.grabImage(2, 3, 32, 32); 
		enemyRight[2] = ps.grabImage(3, 3, 32, 32); 
		
		enemyLeft[0] = ps.grabImage(1, 2, 32, 32);
		enemyLeft[1] = ps.grabImage(2, 2, 32, 32);
		enemyLeft[2] = ps.grabImage(3, 2, 32, 32);
		
	
	
		bullet[0]     = gs.grabImage(24, 2, 32, 32);// 24 2
		
		portal[0]     = prt.grabImage(1, 1, 80, 64);
		portal[1]     = prt.grabImage(1, 2, 80, 64);
		portal[2]     = prt.grabImage(1, 3, 80, 64);
		portal[3]     = prt.grabImage(1, 4, 80, 64);




 	}
	

}
