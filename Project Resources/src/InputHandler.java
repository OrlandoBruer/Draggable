import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;

import javafx.util.Pair;

/**
 *
 */

/**
 * @author Jasper
 *
 */
public class InputHandler implements MouseListener, MouseMotionListener {

	private Point mousePressPos;
	private boolean dragEnabled;



	public boolean checkContains(Pair<Dimension,Point> pairIn, Point mousePosition) {
			if(pairIn.getValue().getX() < mousePosition.getX() && pairIn.getValue().getY() < mousePosition.getY()
					&& pairIn.getValue().getX()+pairIn.getKey().getWidth() > mousePosition.getX()
					&& pairIn.getValue().getY()+pairIn.getKey().getHeight() > mousePosition.getY() ){

				return true;
			}
			return false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {


//		if(checkContains((new Pair<Dimension,Point>(new Dimension(1400,700), new Point(100,100))), e.getPoint())){
			if(dragEnabled == true && Game.currentState == Game.STATE.Game) {
				Game.gameWorld.offsetDisplay(mousePressPos,e.getPoint());
//			}

		}

	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseClicked(MouseEvent e) {

		String mouseButton = "not";
		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseButton = "left";
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			mouseButton = "middle";
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			mouseButton = "right";
		}

		System.out.println("Mouse clicked at: ("+ e.getX() + "," + e.getY()+ ") World position: " + Game.gameWorld.getWorldPosition(e.getPoint()));
		for(Map.Entry<String, GameObject> obj : Game.objectMap.entrySet()) {
			if(obj.getValue().isClickable()){
				if(checkContains(obj.getValue().getPosition(),e.getPoint())) {
					if(obj.getValue().clickTag == "mainmenustart") {
						Game.currentState = Game.STATE.Game;
						Game.gameWorld.updateDisplay();
						Game.gameWorld.updateDisplay();

					}else if (obj.getValue().clickTag == "tile") {
						System.out.println("test");
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

//		if (!(e.getButton() == MouseEvent.BUTTON3)) {
//			return;
//		}

		this.mousePressPos = e.getPoint();

		if(checkContains((new Pair<Dimension,Point>(new Dimension(1400,700), new Point(100,100))), e.getPoint())){
			dragEnabled = true;
		}

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		dragEnabled = false;
		mousePressPos = null;
		Game.gameWorld.staticWorldPoint = null;
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}
