
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;

/**
 *
 */

/**
 * @author Orly
 *
 */
public class Structure extends Entity {


	public String name;
	/**
	 * @param type
	 * @param worldDimsIn
	 * @param worldPointIn
	 */
	public Structure(ArrayList<IsometricTile> tileList) {
		super(tileList);
		name = "undefined";
	}

	// public Structure(ArrayList<IsometricTile> tileList, Point masterTile,
	// StructureType type) {
	// super();
	// this.tileList = tileList;
	// this.masterTile = masterTile;
	// this.type = type;
	//
	// }
	//
	// public Structure(Point masterTile, ArrayList<IsometricTile> tileList) {
	// super();
	// this.tileList = tileList;
	// this.masterTile = masterTile;
	//
	//
	// }
//	public Structure(ArrayList<IsometricTile> tileList) {
//
//		this.tileList = tileList;
//		for (IsometricTile tile : tileList) {
//			tile.setStructureOnTile(this);
//		}
//		this.worldPoint = tileList.get(0).worldPoint;
//		this.children = new HashMap<GameObject, Pair<Integer, Integer>>();
//	}
//
//	public void addChild(GameObject child, Pair<Integer, Integer> positionOffset) {
//		this.children.put(child, positionOffset);
//
//	}
//
//	@Override
//	public void setPosition(Point worldPointIn, Point displayPanelPoint) {
//		this.coords.setLocation((displayPanelPoint.getX() + (this.worldPoint.getX() - worldPointIn.getX())),
//				((displayPanelPoint.getY() + (this.worldPoint.getY() - worldPointIn.getY()))));
//		if (children == null) {
//			return;
//		} else if (children.isEmpty()) {
//			return;
//		} else {
//			for (GameObject child : children.keySet()) {
//				child.coords.setLocation(
//						(displayPanelPoint.getX() + (this.worldPoint.getX() - worldPointIn.getX()))
//								+ children.get(child).getKey(),
//						((displayPanelPoint.getY() + (this.worldPoint.getY() - worldPointIn.getY()))
//								+ children.get(child).getValue()));
//			}
//		}
//	}
//
//	@Override
//	public void clickAction() {
//		// if(structureOnTile != null) {
//		// System.out.println("Clicked a structure containing tile");
//		// }else {
//		// System.out.println("Clicked a tile of type: " + this.tileset + ".
//		// Walkable:"+this.walkable);
//		// }
//
//		if (this.currentlyClicked == false) {
//			this.currentlyClicked = true;
//		} else {
//			this.currentlyClicked = false;
//		}
//	}
//
//	public void hoverAction() {G
//		// System.out.println("structure hovered");
//		this.currentlyHovered = true;
//
	
	
	@Override
	public void clickAction() {
//		System.out.println("Click action on: "+ this.name);
		this.currentlyClicked = true;
//		Game.userInterface.passCityToInterfaceContainer(this, "citymanager");
//		Game.userInterface.enableInterfaceContainer("citymanager");
//		Game.userInterface.createUIContainer("cityinterface", new Point(1410,36), new Point(0,50));
//		Game.userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.SMALL, "cityinterface", "hellobutton", "hello", "Hello");
//		Game.userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.SMALL, "cityinterface", "goodbyebutton", "goodbye", "Goodbye");
//		Game.userInterface.enableInterfaceContainer("cityinterface");
	}
//	}
//	@Override
//	public void render(Graphics g) {
//		g.drawImage(Game.objectMap.getImage(objectImage), coords.x + Game.xOffset, coords.y + Game.yOffset - this.structureOffset, null);
//		if(currentlyHovered || currentlyClicked) {
//			g.drawImage(Game.objectMap.getImage("cityhover"), coords.x + Game.xOffset, coords.y + Game.yOffset - this.structureOffset, null);
//		}
//		if (children == null) {
//			return;
//		} else if (children.isEmpty()) {
//			return;
//		} else {
//			for (GameObject child : children.keySet()) {
//				child.render(g);
//			}
//		}
//	}
}
