package projetopg.eduardo;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

public class CurvasDeBezier {
	private double t;
	private ArrayList<Point> pontos;
	private Graphics graphics;
	
	public CurvasDeBezier(Graphics graphics, ArrayList<Point> pontos, double t) {
		this.t = t;
		this.pontos = pontos;
		this.graphics= graphics;
	}
	
	public void desenhaCurva() {
		double x = 0, y = 0;  
		
        graphics.setColor(geraCorAleatoria());  
          
        ArrayList<int[]> pontos2 = new ArrayList<>();  
  
        for (double u = 0; u <= 1; u += t) {    
            for (int k = 0; k < pontos.size(); k++) {  
                double b = deCasteljau(u, pontos.size() - 1, k);  
                x += pontos.get(k).getX() * b;  
                y += pontos.get(k).getY() * b;  
            }  
            
            pontos2.add(new int[]{(int) x, (int) y});  
            graphics.drawLine((int) x, (int) y, (int) x, (int) y);  
  
            if (pontos2.size() > 1) {  
                graphics.drawLine(pontos2.get(pontos2.size() - 2)[0], pontos2.get(pontos2.size() - 2)[1], (int) x, (int) y); 
                pontos2.remove(0);  
            }  
  
            x = y = 0;  
        }  
   
        graphics.drawLine(pontos2.get(pontos2.size() - 1)[0],pontos2.get(pontos2.size() - 1)[1], (int) pontos.get(pontos.size() - 1).getX(), 
        		(int) pontos.get(pontos.size() - 1).getY());
		
	}
	
	public void limpaPontos() {
		this.pontos = new ArrayList<Point>();
	}
	public void derivadaDeBezier() {
		
	}
	
	private double deCasteljau(double s, int n, int k) {
		return ((fatorial(n)/(fatorial(k)*fatorial(n-k)))*Math.pow(s, k)* Math.pow(1-s, n-k));
	}
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
	
}
