package projetopg.eduardo;

import java.util.ArrayList;
import java.awt.*;

public class CurvasDeBezier {
	private double t;
	private ArrayList<Point> pontos;
	private Graphics g;
	
	public CurvasDeBezier(Graphics g, ArrayList<Point> pontos, double t) {
		this.t = t;
		this.pontos = pontos;
		this.g= g;
	}
	
	public void desenhaCurva() {
		
	}
	
	public void limpaPontos() {
		this.pontos = new ArrayList<Point>();
	}
	public void derivadaDeBezier() {
		
	}
}
