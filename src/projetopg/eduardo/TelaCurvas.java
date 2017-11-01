package projetopg.eduardo;
/**
 * imports
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Panel;
import javax.swing.JToolBar;
import javax.swing.JSlider;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaCurvas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Point> pontos = new ArrayList<Point>();
	private JButton btnApagar;
	private double t;
	private int numPontos = 0;
	private JTextField textField;
	JButton btnDesenharCurva = new JButton("Desenhar Curva");
	Point2D p1;
	DecimalFormat df = new DecimalFormat("0.00");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCurvas frame = new TelaCurvas();
					frame.setVisible(true);
					frame.setTitle("Desenhando curvas de Bézier");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public TelaCurvas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 730);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                contentPane.getGraphics().fillOval(e.getX() - 5, e.getY() - 5, 10, 10);
                System.out.println((double)e.getX() + "," + (double)e.getY());
                pontos.add(e.getPoint());
                numPontos +=1;
                System.out.println("" + pontos);
            }
});
		btnDesenharCurva.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDesenharCurva.setBounds(702, 13, 143, 25);
		this.btnDesenharCurva.addActionListener(
	                new ActionListener() {

	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        curvadeBezier(contentPane.getGraphics(), pontos, numPontos, t);
	                        pontos.clear();
	                    }        
	                }
	        );
		
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setBounds(0, 0, 870, 29);
		contentPane.add(toolBar);
		toolBar.add(btnDesenharCurva);
		
		this.btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnApagar.setBounds(748, 86, 97, 25);
		toolBar.add(btnApagar);
		
		JSlider slider = new JSlider();
		slider.setValue(1000);
		slider.setMinimum(0);
		slider.setToolTipText("Valor de t");
		toolBar.add(slider);
		//textField que recebe o valor de t
		textField = new JTextField();
		//acao ao teclar enter
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){  
					try {
						if(Double.parseDouble(textField.getText())< 0 ||Double.parseDouble(textField.getText()) >=1 ) {
							JOptionPane.showMessageDialog(null, "Insira valor entre 0 e 1");
					}else {
						t = slider.getValue()/100.0;
						//fazer nova construção da curva com novo valor de t
					}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Insira um valor válido");
					}
				}
			}
		});
		textField.setText(df.format(slider.getValue()/100.0) + "");
		textField.setBackground(Color.GRAY);
		textField.setBounds(742, 30, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//configura acao do botao apagar
		btnApagar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        contentPane.repaint();
                        pontos = new ArrayList<Point>();
                    }
                }
        );
	}
	//metodo para desenhar a curva de bezier
	private void curvadeBezier(Graphics graphics, ArrayList<Point> pontos, int numeroDePontos, double t) {
		double px = 0; double py = 0;      
        //Nesta lista guardamos os pontos anteriores para  
        //desenhar as linhas ponto a ponto
        ArrayList<int[]> anteriores = new ArrayList<>();
        double precisao = 0.001;
        int tamLista = pontos.size();
        
        //Este ciclo realiza el número de iteraciones que el usuario desee en base 
        //al número de puntos que se desean
        for (double u = 0; u <= 1; u += precisao) {   
            //realiza o calculo dos próximos pontos da curva iterando sob os demais
            for (int k = 0; k < tamLista; k++) {
                double b = deCasteljau(u, tamLista - 1, k);
                px += pontos.get(k).getX() * b;
                py += pontos.get(k).getY() * b;
            }

            //Se almacenan y se dibuja el punto calculado anteriormente
            anteriores.add(new int[] {(int)px, (int) py});           
            graphics.drawLine((int) px, (int) py, (int) px, (int) py);
            
            //Se dibuja la línea que va del punto anterior al recien calculado
            //de esa manera no tenemos que calcular punto por punto de toda la curva
            if (anteriores.size() > 1) {
                graphics.drawLine(anteriores.get(anteriores.size() - 2)[0], anteriores.get(anteriores.size() - 2)[1], (int) px, (int) py);
                //Removemos el primer elemento que ya no se utiliza
                anteriores.remove(0);
            }
            
            //Borramos los valores anteriores para la siguiente iteración
            px = py = 0;
        }
        
        //Se dibuja el último trayecto de la curva
        graphics.drawLine((int) anteriores.get(anteriores.size() - 1)[0], (int) anteriores.get(anteriores.size() - 1)[1], 
                (int) pontos.get(tamLista - 1).getX(), (int) pontos.get(tamLista - 1).getY());
	}
	
	private double deCasteljau(double s, int n, int k) {
		return (double) ((fatorial(n/fatorial(k)*fatorial(n-k)))* Math.pow(s, k) * Math.pow(1-s, n-k));
	}
	private double fatorial(double d) {
		int fatorial = 1;
        if (d == 0 || d == 1) 
            return fatorial;
        else {
            for (int i = 2; i <= d; i++) {
                fatorial *= i; 
            }
            return fatorial;
        }
	}
}