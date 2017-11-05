package projetopg.eduardo;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

/**
 * Classe que realiza operações com as curvas de bezier.
 * @author Eduardo Luiz
 */

public class CurvasDeBezier {
	private double t;
	private ArrayList<Point> pontos;
	private Graphics graphics;
	
	/**
	 * Construtor da Classe
	 * @param graphics
	 * @param pontos
	 * @param t
	 */
	public CurvasDeBezier(Graphics graphics, ArrayList<Point> pontos, double t) {
		this.t = t;
		this.pontos = pontos;
		this.graphics= graphics;
	}
	
	/**
	 * Método que desenha a curva de bezier
	 */
	public void desenhaCurva() {
		double x = 0, y = 0;  
		//cor da curva
        graphics.setColor(geraCorAleatoria());  
        //arraylist com pontos a serem ligados   
        ArrayList<int[]> pontos2 = new ArrayList<>();  
  
        for (double u = 0; u <= 1; u += t) {    
            for (int k = 0; k < pontos.size(); k++) {  
                double b = deCasteljau(u, pontos.size() - 1, k);  
                x += pontos.get(k).getX() * b;  
                y += pontos.get(k).getY() * b;  
            }  
            
            pontos2.add(new int[]{(int) x, (int) y});  
            graphics.drawLine((int) x, (int) y, (int) x, (int) y); //desenha ponto calculado  
  
            if (pontos2.size() > 1) {  
            	//desenha reta que vai do ponto calculado ao seu antecessor
                graphics.drawLine(pontos2.get(pontos2.size() - 2)[0], pontos2.get(pontos2.size() - 2)[1], (int) x, (int) y); 
                pontos2.remove(0);  
            }  
  
            x = y = 0;  
        }  
        //desenha o final da curva
        graphics.drawLine(pontos2.get(pontos2.size() - 1)[0],pontos2.get(pontos2.size() - 1)[1], (int) pontos.get(pontos.size() - 1).getX(), 
        		(int) pontos.get(pontos.size() - 1).getY());
		
	}
	
	/**
	 * reinicia o arraylist de pontos
	 */
	public void limpaPontos() {
		this.pontos = new ArrayList<Point>();
	}
	
	/**
	 * Calcula a derivada da curva de bezier e retorna a norma acumulada dos vetores
	 * (usada para o gráfico)
	 * @param t
	 * @return
	 */
	public double derivadaDeBezier(double t) {
		double x=0; double y=0;
		double norma=0;
		ArrayList<Point> deltas=geraDeltas(pontos);
		for (double u = 0; u <= 1; u += t) {
			for (int i = 0; i <deltas.size() ; i++) {
				double b = deCasteljau(u, deltas.size()-1, i);
				x+=deltas.get(i).getX() * b;
				y+=deltas.get(i).getY() * b;
			}
			norma+=calculaNorma(x, y);
			x=y=0;
		}
		System.out.println("" + norma);
		return norma;
	}
	/**
	 * gera os vetores deltas para o calculo da derivada
	 * @param pontos
	 * @return deltas
	 */
	private ArrayList<Point> geraDeltas(ArrayList<Point> pontos) {
		ArrayList<Point> deltas = new ArrayList<Point>();
		for (int i = 0; i+1 < pontos.size(); i++) {
			Point p = new Point((int)(pontos.get(i+1).getX() - pontos.get(i).getX()), (int) (pontos.get(i+1).getY() - pontos.get(i).getY()));
			deltas.add(p);
		}
		System.out.println("Deltas derivada: " +deltas);
		return deltas;
	}
	/**
	 * Calcula o b por meio de Casteljau
	 * @param s
	 * @param n
	 * @param k
	 * @return valor de b
	 */
	private double deCasteljau(double s, int n, int k) {
		return ((fatorial(n)/(fatorial(k)*fatorial(n-k)))*Math.pow(s, k)* Math.pow(1-s, n-k));
	}
	/**
	 * calcula o fatorial 
	 * @param d
	 * @return fatorial de d
	 */
	private double fatorial(double d) {
		double fatorial = 1;
        if (d == 0 || d == 1) 
            return fatorial;
        else {
            for (int i = 2; i <= d; i++) {
                fatorial *= i; 
            }
            return fatorial;
        }
	}
	/**
	 * gera cor aleatoria
	 * (usado no desenho da curva)
	 * @return
	 */
private Color geraCorAleatoria() {
		Color cor = null; Random rc = new Random();
		int codCor = rc.nextInt(6);
		switch (codCor) {
		case 0:
			cor = Color.BLUE;
			break;
		case 1:
			cor = Color.CYAN;
			break;
		case 2:
			cor = Color.RED;
			break;
		case 3:
			cor = Color.GREEN;
			break;
		case 4:
			cor = Color.MAGENTA;
			break;
		case 5:
			cor = Color.ORANGE;
			break;
		}
		return cor;
	}
	public double calculaNorma(double x, double y) {
		return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
	}
}
