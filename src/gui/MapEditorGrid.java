package gui;

import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import logic.Character;
import logic.GameMap;
import logic.KeepMap;

public class MapEditorGrid extends Map {

	char[][] map;
	int size;

	MapEditorGrid(int width, int height, int size) {

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(size, size));
		this.setSize(width, height);
		this.setBackground(java.awt.Color.BLACK);
		this.setVisible(true);
		this.size = size;

		loadImages();

		map = new char[size][size];

		resetMap();

		super.update(map);

	}

	public void setNewChar(int x, int y, char c) {
		map[y][x] = c;

	}

	public void update() {
		super.update(map);
	}


	public boolean mapIsValid() {
		if (checkHero())
			if (checkKeys())
				if (checkBorder())
					if(checkClub())
					if (checkExitReachable())
										return true;

		return false;

	}

	// verifica que existe 1 e 1 só herói

	public boolean checkHero() {

		int hero_cnt = 0;

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) 
				if (map[i][j] == 'H')
					hero_cnt++;
			

		if (hero_cnt == 1)
			return true;


		return false;
	}

	// verifica que existe pelo menos uma chave

	public boolean checkKeys() {

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (map[i][j] == 'k')
					return true;

		return false;

	}
	
	public boolean checkClub() {

		int club_cnt = 0;

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) 
				if (map[i][j] == '*')
					club_cnt++;
			

		if (club_cnt == 1)
			return true;

		return false;

	}

	public ArrayList<Point> getOgresPositions() {

		ArrayList<Point> ogres_positions = new ArrayList<Point>();
		
	

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (map[i][j] == 'O'){
					ogres_positions.add(new Point(j,i));

				}
					

		return ogres_positions;
	}

	// verificar a moldura de parede

	public boolean checkBorder() {

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (i == 0 || j == 0 || i == size - 1 || j == size - 1)
					if (map[i][j] != 'X' && map[i][j] != 'S')
						return false;

		return true;

	}



	public boolean checkExitReachable() {

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				if (map[i][j] == 'S') {
					char[][] m = deepClone(map);

					visit(m, i, j);

					for (int k = 0; k < m.length; k++)
						for (int l = 0; l < m.length; l++) {
							if (m[k][l] != 'X' && m[k][l] != 'V')
								return false;

						}

				}
			}

		return true;

	}

	public void visit(char[][] m, int i, int j) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length)
			return;
		if (m[i][j] == 'X' || m[i][j] == 'V')
			return;
		m[i][j] = 'V';
		visit(m, i - 1, j);
		visit(m, i + 1, j);
		visit(m, i, j - 1);
		visit(m, i, j + 1);
	}

	public char[][] deepClone(char[][] m) {
		char[][] c = m.clone();
		for (int i = 0; i < m.length; i++)
			c[i] = m[i].clone();
		return c;
	}

	public char[][] getMap() {
		return map;
	}

	public void normalizeMap() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				if (map[i][j] == 'O')
					map[i][j] = ' ';
				if (map[i][j] == 'S')
					map[i][j] = 'I';
			}
	}

	public void resetMap() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == size - 1 || j == 0 || j == size - 1)

					map[i][j] = 'X';
				else
					map[i][j] = ' ';

			}
		}

	}


}
