package tramos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Imagen.Imagen;

public class Tramos extends JFrame {
	private int tramos;
	private JTextField jttramos;
	private JLabel jltramos;
	private TramosPanel panel;
	private JPanel jftramos;
	private JPanel tramosdefpanel = new JPanel();
	private ArrayList <Puntos> tramosdef = new ArrayList <Puntos>();
	private class Puntos {
		private int x;
		private int y;
		private JTextField xtf;
		private JTextField ytf;
		Puntos () {
			xtf = new JTextField("0");
			ytf = new JTextField("0");
			x = 0;
			y = 0;
		}
	}
	public Tramos (Imagen origen) {
		setLayout(new BorderLayout());
		setTitle("Ajuste por tramos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setVisible(true);
		panel = new TramosPanel();
		add(panel, BorderLayout.CENTER);
		jftramos= new JPanel();
		jftramos.setLayout(new GridLayout(1,2));
		jltramos = new JLabel("Número de tramos:");
		jftramos.add(jltramos);
		jttramos = new JTextField();
		jttramos.addActionListener(new JTChangeListener());
		jttramos.setText("0");
		jftramos.add(jttramos);
		add(jftramos,BorderLayout.NORTH);
		
	}
	private void setTramosDef(){
		tramosdefpanel.removeAll();
		tramosdefpanel.revalidate();
		tramosdefpanel.setLayout(new GridLayout(tramosdef.size(), 5));
		int i = 0;
		for (Puntos tram : tramosdef){
			tramosdefpanel.add(new JLabel("Punto " + i + ": x"));
			tramosdefpanel.add(tram.xtf);
			tramosdefpanel.add(new JLabel(" y"));
			tramosdefpanel.add(tram.ytf);
			i++;
		}
		this.add(tramosdefpanel);
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
			}
		}
	}
}
