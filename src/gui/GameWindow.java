package gui;


import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import logic.Game;
import logic.Game.GameState;
import logic.GameMap;

public class GameWindow {

	static JFrame frmMazeGame;
	static MenuPanel pnlMenu;
	static VictoryPanel pnlVictory;
	static DefeatPanel pnlDefeat;
	static GameBar pnlGameBar;

	static MapEditor pnlCustomMap;
	static JLayeredPane layeredPane;

	static Game g = new Game(0, "Rookie");

	// Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frmMazeGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.

	public GameWindow() {
		initialize();
	}

	// Initialize the contents of the frame.

	private void initialize() {

		frmMazeGame = new JFrame();
		frmMazeGame.setResizable(false);
		frmMazeGame.setTitle("Maze Game");
		frmMazeGame.setBounds(100, 100, 549, 607);

		frmMazeGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMazeGame.getContentPane().setLayout(null);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 549, 620);
		frmMazeGame.getContentPane().add(layeredPane);
		layeredPane.setLayout(null);

		pnlVictory = new VictoryPanel();
		pnlVictory.setBounds(0, 0, 550, 620);
		pnlVictory.setVisible(false);
		layeredPane.add(pnlVictory);

		pnlDefeat = new DefeatPanel();
		pnlDefeat.setBounds(0, 0, 550, 620);
		pnlDefeat.setVisible(false);
		layeredPane.add(pnlDefeat);

		pnlMenu = new MenuPanel();
		pnlMenu.setBounds(0, 0, 550, 584);
		layeredPane.add(pnlMenu);

		pnlMenu.setLayout(null);

		layeredPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { pnlMenu, pnlCustomMap, pnlVictory, pnlDefeat, pnlGameBar }));

		pnlGameBar = new GameBar();
		pnlGameBar.setBounds(0, 0, 550, 584);
		pnlGameBar.setVisible(false);
		layeredPane.add(pnlGameBar);

	}

	public static void EndGame() throws InterruptedException {

		GameState state = g.getState();


		pnlGameBar.setVisible(false);

		switch (state) {
		case LOST:
			pnlDefeat.setVisible(true);
			break;
		case WON:
			pnlVictory.setVisible(true);
			break;
		default:
			break;
		}
    

	}

	public static GameMap getMap() {
		return g.getMap();
	}

	public static void setGame(Game game) {
		g = game;

	}

	public static Game getGame() {
		return g;
	}

	public static void createEditorPanel(int size) {

		pnlCustomMap = new MapEditor(size);
		pnlCustomMap.setBounds(0, 0, 539, 609);
		layeredPane.add(pnlCustomMap);

	}
}
