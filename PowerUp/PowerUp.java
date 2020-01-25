package PowerUp;

import java.awt.Image;

import Game.tankgame;

public class PowerUp extends CarePackageAbstract{

	public PowerUp(Image img, int x, int y, int speed) {
		super(img, x, y, speed);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Checking if it collide with the player plane.
	 */
	public void update() {
		y+=speed;
		if(tankgame.p1.collision(x, y, width, height)){
			tankgame.p1.oneUp();
			tankgame.p1.setScore(100);
			System.out.println("one up ");
			show = false;
		}
		else if(tankgame.p2.collision(x, y, width, height)){
			tankgame.p2.oneUp();
			tankgame.p2.setScore(100);
			show = false;
		}
	}
}
