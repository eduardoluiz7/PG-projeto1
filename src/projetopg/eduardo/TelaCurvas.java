package projetopg.eduardo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class TelaCurvas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	double t=0.001;
	private int numPontos=0;
	private JTextField valorDoT;
	public ArrayList<Point> pontos = new ArrayList<Point>();
	public static CurvasDeBezier curva;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCurvas frame = new TelaCurvas();
					frame.setVisible(true);
					frame.setTitle("Desenhando Curvas de Bézier");
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
		setBounds(100, 100, 977, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 20, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(0, 0, 201, 635);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnDesenharCurva = new JButton("Desenhar Curva");
		btnDesenharCurva.setBounds(32, 56, 138, 34);
		panel.add(btnDesenharCurva);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(32, 123, 138, 34);
		panel.add(btnApagar);
		
		valorDoT = new JTextField();
		valorDoT.setBounds(32, 209, 138, 22);
		panel.add(valorDoT);
		valorDoT.setColumns(10);
		valorDoT.setText("0.001");
		valorDoT.setToolTipText("Insira o valor de t e tecle enter");
		
		JLabel lblValorDeT = new JLabel("Valor de t:");
		lblValorDeT.setBounds(34, 188, 75, 16);
		panel.add(lblValorDeT);
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.BOLD, 20));
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setBounds(12, 244, 177, 378);
		panel.add(textPane);
		
		
		//painel de desenho e suas acoes
		JPanel areaG = new JPanel();
		areaG.setLayout(null);
		areaG.setBorder(new EmptyBorder(5, 20, 5, 5));
		areaG.setBounds(202, 0, 757, 635);
		contentPane.add(areaG);
		areaG.setBackground(Color.WHITE);
		areaG.addMouseListener(new MouseAdapter() {//acoes ao clicar com o mouse
            @Override
            public void mousePressed(MouseEvent e) {
            	areaG.getGraphics().fillOval(e.getX(), e.getY(), 10, 10);
            	textPane.setText(textPane.getText() + "\n P"+numPontos+": ("+ e.getX() + ", "+ e.getY()+")");
            	if(numPontos!=0 && numPontos>0) {
            		areaG.getGraphics().drawLine((int)pontos.get(numPontos-1).getX(),(int)pontos.get(numPontos-1).getY(), e.getX(), e.getY());
            	}
            	pontos.add(new Point(e.getX(),e.getY()));
            	System.out.println("" + pontos);
            	numPontos+=1;
            	
            }
		});
		
		//acoes da caixa de texto que recebe o valor de t
		valorDoT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){  
					try {
						if(Double.parseDouble(valorDoT.getText())< 0 ||Double.parseDouble(valorDoT.getText()) >=1 ) {
							JOptionPane.showMessageDialog(null, "Insira valor entre 0 e 1");
					}else {
						t = Double.parseDouble(valorDoT.getText());
						//fazer nova construção da curva com novo valor do acrescimo para t
					}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Insira um valor válido");
					}
				}
			}
		});
		
		//acoes para o botao apagar
		btnApagar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        areaG.repaint(); textPane.setText("");
                        pontos = new ArrayList<Point>();
                        numPontos = 0;
                        
                    }
                });
		//acoes para o botao desenhar curva
		btnDesenharCurva.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	if(numPontos==0) {
                    		JOptionPane.showMessageDialog(null, "Insira os pontos de controle!");
                    	}else {
                    		curva = new CurvasDeBezier(areaG.getGraphics(), pontos, t);
                    		curva.desenhaCurva();
                    		curva.derivadaDeBezier(t);
                    		Grafico gc = new Grafico();
                    		gc.setVisible(true);
                    	}
                    }        
                }
        );

	}
	public static CurvasDeBezier getCurva() {
		return curva;
	}
}
