import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;

//import javafx.scene.paint.Color;

//import javafx.scene.paint.Color;


/**
 *
 */

/**
 * @author Orly
 *
 */
public class Game {

	public static int width;
	public static int height;
	public static int worldWidth;
	public static int worldHeight;
//	public static Menu gameMenu;
	public static Graphics graphics;
	public static JFrame window;
	public static Renderer mainGameRenderer;
	public static InterfaceController userInterface = new InterfaceController();
	public static ObjectMap objectMap;
	public static World gameWorld;
	public static ArrayList<String> nameList;
	public static SettingsHandler settingsControl;
	public static final int xOffset = 3;
	public static final int yOffset = 26;
	public static Semaphore sem = new Semaphore(1);

	public enum STATE{
		Menu,
		Exiting,
		Game
	}
	public static STATE currentState;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		mainHUD.displayCityOnHUD();

		settingsControl = new SettingsHandler();
		settingsControl.loadSettings();
		currentState = STATE.Menu;
//		worldWidth = tileWidth *isoDims.width;
//		 worldHeight = tileHeight *isoDims.height;
		objectMap = new ObjectMap();
//		Image icon = new ImageIcon("assets/testImage.png").getImage();
//		Image clickableImage = new ImageIcon("assets/click.png").getImage();


		//Loading all image assets
//		objectMap.addImage("border", "assets/border.png");
		objectMap.addImage("border", "assets/border_draft.png");
//		objectMap.addImage("uibuttonsmall", "assets/uibutton1.png");
		objectMap.addImage("2x2hover", "assets/2x2hover.png");
		objectMap.addImage("hover", "assets/hovertile.png");
		objectMap.addImage("cityhover", "assets/hovercity.png");
		objectMap.addImage("click", "assets/click.png");
		objectMap.addImage("roadtile","assets/road.png");
		objectMap.addImageSheet("grasstile","assets/grasstiles.png", new Dimension(64,32), 4);
		objectMap.addImageSheet("watertile","assets/watertiles.png", new Dimension(64,32), 3);
		objectMap.addImageSheet("treetile", "assets/foresttiles.png", new Dimension(64,40), 3);
		objectMap.addImageSheet("citytile", "assets/City1.png", new Dimension(192,112), 3);
		objectMap.addImage("cube", "assets/placeholder.png");
		objectMap.addImage("ironore", "assets/iron.png");
		objectMap.addImage("teststructure", "assets/structuretest.png");
		objectMap.addImage("redOwnedTile", "assets/redOwnedTile.png");
		objectMap.addImage("hudbutton01", "assets/hudbutton01.png");
		objectMap.addImage("menuButton1", "assets/menuButton1.png");
		objectMap.addImage("ironhut","assets/ironhut.png");
		objectMap.addImage("menuBackground", "assets/menuBackground.png");
		objectMap.addImageSheet("road", "assets/roadTiles.png", new Dimension(64,32), 11);
		objectMap.addImageSheet("redowned", "assets/redBorder.png", new Dimension(64,32), 16);
		objectMap.addImageSheet("blueowned", "assets/blueBorder.png", new Dimension(64,32), 16);
		objectMap.addImageSheet("uibuttonsmall","assets/uibutton1.png",new Dimension(64,32),2);
		objectMap.addImageSheet("uibuttonmedium","assets/uibutton2.png",new Dimension(128,32),2);
		objectMap.addImageSheet("topbarbtn","assets/topbarbutton.png",new Dimension(96,24),2);
		//Adding fonts
		objectMap.addFont("smallbuttonfont", "Calibri",Font.BOLD,10);
		objectMap.addFont("mediumbuttonfont", "Calibri", Font.BOLD, 13);
		objectMap.addFont("citytitlefont", "Calibri",Font.BOLD,15);
		objectMap.addFont("primarygamefont", "Arial",Font.PLAIN, 11);
		objectMap.addFont("topbarfont","Times New Roman",Font.BOLD, 15);
//		objectMap.getFont("topbarfont").
		
		
		Dimension dim = new Dimension (width, height);
		window = new JFrame("Draggable");
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setPreferredSize(dim);
		window.setMaximumSize(dim);
		window.setMinimumSize(dim);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setBackground(Color.DARK_GRAY);

		graphics = window.getGraphics();
		Renderer mainGameRenderer = new Renderer("mainGameRenderer", window);
//		Font gameFont = new Font("Arial", Font.PLAIN,11);

		graphics.setFont(objectMap.getFont("primarygamefont"));


		gameWorld = new World();
		nameList = gameWorld.populateNameList();
		gameWorld.initialiseTileMap();
		gameWorld.initialiseEntityMap();
//		gameMenu = new Menu();
//		gameWorld.

//		Game.objectMap.get

//		UserInterfaceObject testButton = new UserInterfaceElement(ObjectType.DEFAULT,UserInterfaceElement.UIElementType.SMALL,new Point(400,400),"newgame");
		userInterface.createUIContainer("mainmenu",new Point(200,600), new Point(0,50));
		userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.SMALL,"mainmenu", "newgamebutton","newgame","Start");
		userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.SMALL,"mainmenu", "exitbutton","exit","Quit");
		userInterface.enableInterfaceContainer("mainmenu");
//		objectMap.put("testbutton", testButton);
//		objectMap.addObject(ObjectType.DEFAULT, "testbutton", testButton);
		
		userInterface.createUIContainer("topmenubar", new Point(384,4), new Point(128,0));
		Game.userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.TOPBAR, "topmenubar", "topbarlabour", "hello", "Labour");
		Game.userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.TOPBAR, "topmenubar", "topbarconstruction", "constructionmenu", "Construction");
		Game.userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.TOPBAR, "topmenubar", "topbarcities", "citiesmenu", "Cities");

		userInterface.createUIContainer("citymanager", new Point(1450,120), new Point(0,50));
		Game.userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.SMALL, "citymanager", "hellobtn", "hello", "Hello");
		Game.userInterface.addInterfaceTextObject(UserInterfaceObject.UIElementType.TEXT, "citymanager","citytitle","undefined","primarygamefont",Color.WHITE,new Point (1430,60));
//		Game.userInterface.enableInterfaceContainer("cityinterface");
		
		
		userInterface.createUIContainer("constructionmenu",new Point(1450,200), new Point(0,50));
		userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.MEDIUM,"constructionmenu", "buildironmine","buildironmine","Iron Mine");
		
		userInterface.createUIContainer("citiesmenu",new Point(1450,200), new Point(0,40));
		for(City city : gameWorld.cityList) {
			userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.MEDIUM,"citiesmenu", city.name+"citiesmenu","citybtn",city.name,city);
		}


		userInterface.createUIContainer("resourcestructure", new Point(1450,120), new Point(0,30));
		Game.userInterface.addInterfaceObject(UserInterfaceObject.UIElementType.SMALL, "resourcestructure", "addworkerbtn", "addWorkerToResourceStructure", "Add");
		Game.userInterface.addInterfaceTextObject(UserInterfaceObject.UIElementType.TEXT, "resourcestructure","structuretitle","undefined","primarygamefont",Color.WHITE,new Point (1430,60));
		Game.userInterface.addInterfaceTextObject(UserInterfaceObject.UIElementType.TEXT, "resourcestructure","resourcestoredlabel","undefined","primarygamefont",Color.WHITE,new Point (1430,250));
		Game.userInterface.addInterfaceTextObject(UserInterfaceObject.UIElementType.TEXT, "resourcestructure","resourcestoredvalue","undefined","primarygamefont",Color.WHITE,new Point (1510,250));
		Game.userInterface.addInterfaceTextObject(UserInterfaceObject.UIElementType.TEXT, "resourcestructure","workerslabel","undefined","primarygamefont",Color.WHITE,new Point (1430,300));
		Game.userInterface.addInterfaceTextObject(UserInterfaceObject.UIElementType.TEXT, "resourcestructure","workersvalue","undefined","primarygamefont",Color.WHITE,new Point (1510,300));

		//Border
		GameObject border = new GameObject(ObjectType.DEFAULT);
		Game.objectMap.addObject(ObjectType.DEFAULT,  "border", border);
		Game.objectMap.getObject("border").setProperties(new Dimension(1600,900), new Point(0,0),"border");


//		//UI Background
//		GameObject uibackground = new GameObject(ObjectType.DEFAULT);
//		objectMap.addObject(ObjectType.DEFAULT,  "uibackground", uibackground);
//		objectMap.getObject("uibackground").setProperties(new Dimension(200,300), new Point(1289,110),"uibackground");

		//Mouse data text objects
		TextObject globalMousePosText = new TextObject(ObjectType.DEFAULT);
		globalMousePosText.setTextProperties("Global mouse position:",Game.objectMap.getFont("primarygamefont"),Color.WHITE,new Point(xOffset+5, 70));
		objectMap.addObject(ObjectType.DEFAULT, "globalMousePosText", globalMousePosText);

		TextObject worldMousePosText = new TextObject(ObjectType.DEFAULT);
		worldMousePosText.setTextProperties("World mouse position:",Game.objectMap.getFont("primarygamefont"),Color.WHITE,new Point(xOffset+5, 85));
		objectMap.addObject(ObjectType.DEFAULT, "worldMousePosText", worldMousePosText);
//		
		TextObject isoMousePosText = new TextObject(ObjectType.DEFAULT);
		isoMousePosText.setTextProperties("Iso mouse position:",Game.objectMap.getFont("primarygamefont"),Color.WHITE,new Point(xOffset+5, 100));
		objectMap.addObject(ObjectType.DEFAULT, "isoMousePosText", isoMousePosText);
		
//		TextObject isoMousePosText = new TextObject(ObjectType.DEFAULT,objectMap.getFont("primarygamefont"), Color.WHITE);
		
//		TextObject worldMousePosText = new TextObject(ObjectType.DEFAULT,objectMap.getFont("primarygamefont"), Color.WHITE);
//		worldMousePosText.setProperties("World mouse position:",Color.WHITE, new Point(xOffset+5, 85));
//		objectMap.addObject(ObjectType.DEFAULT, "worldMousePosText", worldMousePosText);
//
//		TextObject isoMousePosText = new TextObject(ObjectType.DEFAULT,objectMap.getFont("primarygamefont"), Color.WHITE);
//		isoMousePosText.setProperties("Iso mouse position:",Color.WHITE, new Point(xOffset+5, 100));
//		objectMap.addObject(ObjectType.DEFAULT, "isoMousePosText", isoMousePosText);


		//Test unit
		Unit cube;
		cube = new Unit(new Point(6,8));
		objectMap.addObject(ObjectType.WORLD, "placeholder", cube);
		objectMap.addEntity("placeholder", cube,8);
		cube.setProperties(new Dimension(64,32), new Point(600,200),"cube");
		cube.setDestination(new Point(41,55));
		Game.gameWorld.addTickingObject(cube);


		Unit cube2;
		cube2 = new Unit(new Point(20,37));
		objectMap.addObject(ObjectType.WORLD, "placeholder2", cube2);
		objectMap.addEntity("placeholder2", cube2,8);
		cube2.setProperties(new Dimension(64,32), new Point(600,200),"cube");
		cube2.setDestination(objectMap.getTile(new Point(41,19)).getEntityOnTile().getClosestNeighbour(cube2.isoPoint));
//		gameWorld.getNeighbours(centreTile)
		Game.gameWorld.addTickingObject(cube2);

		//Initialise input handler
		InputHandler inputControl = new InputHandler();
		window.getContentPane().addMouseListener(inputControl);
		window.getContentPane().addMouseMotionListener(inputControl);


//		gameWorld.setTile(new Point(0,1), IsometricTile.TILESET.grass);
		objectMap.updateMainDisplayObjects();
		gameWorld.updateDisplay();
		
		//Sleep to stop concurrent modification exception
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mainGameRenderer.start();


		// testing tile ownership
//		for (int x = 36; x < 54; x++) {
//			for (int y = 15; y < 27; y++) {
//				objectMap.getTile(new Point(x,y)).setOwner(IsometricTile.OWNERSET.red);
//			}
//		}
//		for (int x = 25; x < 36; x++) {
//			for (int y = 15; y < 27; y++) {
//				objectMap.getTile(new Point(x,y)).setOwner(IsometricTile.OWNERSET.blue);
//			}
//		}
//		objectMap.getTile(new Point(45,27)).setOwner(IsometricTile.OWNERSET.red);
//		objectMap.getTile(new Point(40,26)).setOwner(IsometricTile.OWNERSET.none);
//		objectMap.getTile(new Point(35,20)).setOwner(IsometricTile.OWNERSET.red);
//		objectMap.getTile(new Point(36,24)).setOwner(IsometricTile.OWNERSET.none);
//		objectMap.getTile(new Point(45,14)).setOwner(IsometricTile.OWNERSET.red);
//		objectMap.getTile(new Point(40,15)).setOwner(IsometricTile.OWNERSET.none);
//		objectMap.getTile(new Point(54,20)).setOwner(IsometricTile.OWNERSET.red);
//		objectMap.getTile(new Point(53,24)).setOwner(IsometricTile.OWNERSET.none);
//		objectMap.getTile(new Point(36,21)).setOwner(IsometricTile.OWNERSET.blue);
//		objectMap.getTile(new Point(37,21)).setOwner(IsometricTile.OWNERSET.blue);
//		objectMap.getTile(new Point(37,20)).setOwner(IsometricTile.OWNERSET.blue);

//
//		System.out.println(objectMap.getTile(new Point(18,15)).getEntityOnTile().getClosestNeighbour(new Point(15,18)));
//		
//		System.out.println(objectMap.getTile(new Point(54,20)).toString());
		
		
		
		// temporary "tick" loop
		while(true) {
			try {
				Thread.sleep(0);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(currentState == Game.STATE.Game) {
				gameWorld.tick();


				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
