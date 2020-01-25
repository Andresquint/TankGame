package PowerUp;

import java.awt.Image;

import Game.tankgame;

public class HealthUp extends CarePackageAbstract{

	public HealthUp(Image img, int x, int y, int speed) {
		super(img, x, y, speed);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Checking if it collide with the player plane.
	 */
	public void update() {
		y+=speed;
		if(tankgame.p1.collision(x, y, width, height)){
			tankgame.p1.healthUp();;
			System.out.println("health up ");
			show = false;
		}
		else if(tankgame.p2.collision(x, y, width, height)){
			tankgame.p2.healthUp();
			show = false;
		}

	}

}
