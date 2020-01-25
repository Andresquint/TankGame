package Bullet;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import MyTank.Tank;
import SoundEffect.Sound;
import Game.tankgame;
import Wall.Wall;

public class TankBullet extends Bullet {
	private int bulletDMG, angle;
	public TankBullet(Image img, int speed, Tank tank, int sideSpeed, int damage) {
		super(img, tank.x+((tank.getWidth()/2)-(img.getWidth(null)/120)), tank.y+(img.getHeight(null)/2), speed, sideSpeed);
		bulletDMG= damage;
		sizeX = img.getWidth(null)/60;
		sizeY = img.getHeight(null);
		myTank = tank;
		angle = myTank.getAngle();
		show = true;
	}
	public void destroyBullet(){
		show = false;
	}
	
	@Override
	public void update(){
		
		x+=speed*Math.cos(Math.toRadians(6*angle));
		y-=speed*Math.sin(Math.toRadians(6*angle));

		if(tankgame.p1.collision(x, y, sizeX, sizeY)&&show&&myTank != tankgame.p1&&!tankgame.p1.isRespawning()) {
            show = false;
        	tankgame.p1.enemyBulletDmg(bulletDMG);
        	tankgame.p2.setScore(30);
        }else if(tankgame.p2.collision(x, y, sizeX, sizeY)&&show&&myTank != tankgame.p2&&!tankgame.p2.isRespawning()) {
            show = false;
        	tankgame.p2.enemyBulletDmg(bulletDMG);
        	tankgame.p1.setScore(30);
        }
        else{
        	for(int i = 0; i < tankgame.getTankGame().getWall().size()-1; i++){
				Wall tempWall = tankgame.getTankGame().getWall().get(i);
        		if(tempWall.getWallRectangle().intersects(x, y, width/60, height)&&tempWall.isRespawning()){
        			if(tempWall.isDestroyAble()){
        				tempWall.breakWall();
    		    		addExplosion(tankgame.getTankGame().getWallExplosionImg(),tempWall.x,tempWall.y);
    		    		Sound.player("snd_explosion1.wav", false);
        			}
		    		show=false;
        		}
        	}
       }
		if(!show){
    		addExplosion(tankgame.getTankGame().getWallExplosionImg(),x,y);
    		Sound.player("snd_explosion1.wav", false);
		}
	}
	
	@Override
	public void draw(ImageObserver obs, Graphics2D g) {
        g.drawImage(img, x, y, x+(img.getWidth(null)/60), y+img.getHeight(null), angle*24, 0, angle*24+24, img.getHeight(null), obs);
        update();
	}
	
}
