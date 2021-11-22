
import processing.core.PApplet;

public class minesweeper extends PApplet{

	public static void main(String[] args) {
		PApplet.main("minesweeper");
	}

	boolean[][] mines = new boolean[30][30];
	boolean[][] flagged = new boolean[30][30];
	boolean[][] exposed = new boolean[30][30];

	public void settings() {
		size(750,750);
	}

	public void setup() {
		background(0);
		textSize(20);
		rectMode(CENTER);
		initializeMines();
	}

	public void draw() {
		for(int i = 0; i < 30; i++)
			for(int j = 0; j < 30; j++) {
				if(exposed[i][j]) {
					fill(255);
					rect(25*i + 13, 25*j + 13, 25, 25);
					if(numberOfAdjacentMines(i, j) > 0) {
						fill(0,255,0);
						text(numberOfAdjacentMines(i, j), 25*i + 7, 25*j + 20);
					}
					else
						clearAdjacent(i, j);
				}
				else {
					fill(200);
					rect(25*i + 13, 25*j + 13, 25, 25);
				}

				if(flagged[i][j] && !exposed[i][j]) {
					fill(255,0,0);
					rect(25*i + 13, 25*j + 13, 25, 25);
				}
			} 

	}

	public void mouseReleased() {
		if(mouseButton == LEFT)
			if(isBomb(mouseX, mouseY))
				loseGame();
			else
				exposed[mouseX/25][mouseY/25] = true;
		else if(mouseButton == RIGHT)
			flagged[mouseX/25][mouseY/25] = !flagged[mouseX / 25][mouseY / 25];
	}

	void initializeMines() {
		for(int i = 0; i < 100; i++)
			mines[(int)(30 * Math.random())][(int)(30 * Math.random())] = true;
	}

	int numberOfAdjacentMines(int i, int j) {
		int adjacentMines = 0;
		for(int a = i-1; a <= i+1; a++)
			for(int b = j-1; b <= j+1; b++)
				if(a >= 0 && a <= 29 && b >= 0 && b <= 29 && mines[a][b])
					adjacentMines++;
		return adjacentMines;
	}

	boolean isBomb(int xmouse, int ymouse){
		return mines[xmouse / 25][ymouse / 25];
	}

	void loseGame() {
		for(int i = 0; i < 30; i++)
			for(int j = 0; j < 30; j++)
				if(mines[i][j]) {
					fill(0,0,255);
					rect(25*i + 13, 25*j + 13, 25, 25);
				}
		delay(100);
		exit();
	}

	void clearAdjacent(int i, int j) {
		for(int a = i-1; a <= i+1; a++)
			for(int b = j-1; b <= j+1; b++)
				if(a >= 0 && a <= 29 && b >= 0 && b <= 29)
					exposed[a][b] = true;
	}
}
