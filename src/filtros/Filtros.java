package filtros;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Imagen.Imagen;

public class Filtros extends JFrame {
	private int tamV;
	private int tamH;
	private JTextField celdasVJT = new JTextField ();
	private JTextField celdasHJT = new JTextField ();
	private JLabel celdasV = new JLabel ("Tamaño del filtro en vertical");
	private JLabel celdasH = new JLabel ("Tamaño del filtro en horizontal");
	private JPanel tamKernelPanel = new JPanel(new GridLayout (3,2));
	private JPanel defkernelPanel = new JPanel();
	private ArrayList<ArrayList <Valor>> kernel;
	private Imagen original;
	private JButton aplicar = new JButton ("Aplicar");

	public class Valor {
		public double valor;
		private JTextField valorJT;
		Valor () {
			valorJT = new JTextField("0");
			valorJT.addActionListener(new Listener());
			valorJT.addFocusListener(new FocusList());
			valor = 0;
		}
		private class Listener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				valor = Double.parseDouble(valorJT.getText());

			}
		}

		private class FocusList implements FocusListener{

			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				valor = Double.parseDouble(valorJT.getText());
			}
		}

	}
	public Filtros (Imagen origen) {

		original = origen;
		setLayout(new BorderLayout());
		setTitle("Filtros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setVisible(true);
		celdasVJT.addActionListener(new JTChangeListener());
		celdasHJT.addActionListener(new JTChangeListener());
		tamKernelPanel.add(celdasV);
		tamKernelPanel.add(celdasVJT);
		tamKernelPanel.add(celdasH);
		tamKernelPanel.add(celdasHJT);
		add(tamKernelPanel, BorderLayout.NORTH);
		aplicar.addActionListener(new JTChangeListener());
		add(aplicar, BorderLayout.SOUTH);
		add(defkernelPanel, BorderLayout.CENTER);
	}
	
	private void rejilla () {
		kernel = new ArrayList<ArrayList <Valor>>();
		defkernelPanel.removeAll();
		defkernelPanel.setLayout(new GridLayout(tamV, tamH));
		for (int i = 0; i < tamV; i++){
			ArrayList <Valor> aux = new ArrayList <Valor>();
			for (int j = 0; j < tamH; j++){
				Valor celda = new Valor();
				aux.add(celda);
				defkernelPanel.add(celda.valorJT);
				
			}
			kernel.add(aux);
		}
		defkernelPanel.revalidate();
		defkernelPanel.repaint();

		
	}
	/**
	private void aplicar(){
		int [] aux = new int [256];
		for (int i = 1; i < tramosdef.size(); i ++){
			int x1 = tramosdef.get(i-1).x;
			int x2 = tramosdef.get(i).x;
			int y1 = tramosdef.get(i-1).y;
			int y2 = tramosdef.get(i).y;
			double a = (double)(y2-y1)/(double)(x2-x1);
			double b = (double)y1-(a * (double)x1);
			for (int j = x1;j < x2; j++ ){
				aux [j] = (int)(a*(double)j+b);
			}
		}
		new Imagen (original, aux);
	}
	private void setTramosDef(){
		tramosdefpanel.removeAll();
		tramosdefpanel.setLayout(new GridLayout(tramosdef.size(), 4));
		int i = 0;
		for (Puntos tram : tramosdef){
			tramosdefpanel.add(new JLabel("Punto " + i + ": x"));
			tramosdefpanel.add(tram.xtf);
			tramosdefpanel.add(new JLabel(" y"));
			tramosdefpanel.add(tram.ytf);
			i++;
		}
		tramosdefpanel.revalidate();
		tramosdefpanel.repaint();
	}
	 **/
	private class JTChangeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == celdasVJT){
				if (Integer.parseInt(celdasVJT.getText()) % 2 == 1 && Integer.parseInt(celdasHJT.getText()) % 2  == 1 ){
					tamV = Integer.parseInt(celdasVJT.getText());
					rejilla();
				} else {
					System.out.println("Tamaño Incorrecto");
				}
			} else if (e.getSource() == celdasHJT){
				if (Integer.parseInt(celdasVJT.getText()) % 2 == 1 && Integer.parseInt(celdasHJT.getText()) % 2  == 1 ){
					tamH = Integer.parseInt(celdasHJT.getText());
					rejilla();
				} else {
					System.out.println("Tamaño Incorrecto");
				}
			} else if (e.getSource()== aplicar){
				//aplicar();
			}
		}
	}
}
