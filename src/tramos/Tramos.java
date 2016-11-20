package tramos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

import Imagen.Imagen;

public class Tramos extends JFrame {
	private int tramos;
	private JTextField jttramos;
	private JLabel jltramos;
	private TramosPanel panel;
	private JPanel jftramos;
	private JPanel tramosdefpanel = new JPanel();
	private JScrollPane scrollArea = new JScrollPane(tramosdefpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton aplicar;
	private ArrayList <Puntos> tramosdef = new ArrayList <Puntos>();
	private Imagen original;

	public class Puntos {
		public int x;
		public int y;
		private JTextField xtf;
		private JTextField ytf;
		Puntos () {
			xtf = new JTextField("0");
			ytf = new JTextField("0");
			xtf.addActionListener(new Listener());
			ytf.addActionListener(new Listener());
			x = 0;
			y = 0;
		}
		private class Listener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == xtf){
					x = Integer.parseInt(xtf.getText());
					panel.modificar(tramosdef);
				} else if (e.getSource() == ytf){
					y = Integer.parseInt(ytf.getText());
					panel.modificar(tramosdef);
				}
			}
		}
	}
	public Tramos (Imagen origen) {
		original = origen;
		setLayout(new BorderLayout());
		setTitle("Ajuste por tramos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setVisible(true);
		panel = new TramosPanel();
		add(panel, BorderLayout.CENTER);
		jftramos= new JPanel();
		jftramos.setLayout(new GridLayout(1,3));
		jltramos = new JLabel("Número de tramos:");
		jftramos.add(jltramos);
		jttramos = new JTextField();
		jttramos.addActionListener(new JTChangeListener());
		jttramos.setText("0");
		jftramos.add(jttramos);
		aplicar = new JButton ("Aplicar");
		aplicar.addActionListener(new JTChangeListener());
		jftramos.add(aplicar);
		Dimension dim = new Dimension (300, 300);
		scrollArea.setMinimumSize(dim);
		scrollArea.setPreferredSize(dim);
		scrollArea.setSize(dim);
		add(scrollArea,BorderLayout.SOUTH);
		add(jftramos,BorderLayout.NORTH);

	}
	private void tablaConversion(){
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
		panel.repaint();
		tramosdefpanel.revalidate();
		tramosdefpanel.repaint();
	}
	private class JTChangeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jttramos){
				tramos = Integer.parseInt(jttramos.getText());
				tramosdef.clear();
				for (int i = 0; i <= tramos; i++){
					tramosdef.add(new Puntos());
					setTramosDef();
				}
			} else if (e.getSource()== aplicar){
				tablaConversion();
			}
		}
	}
}
