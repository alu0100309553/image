package Imagen;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class InfoPanel extends JPanel {
	private Imagen data;
	private ImagePanel image;
	private JLabel r = new JLabel ("R");
	private JLabel g = new JLabel ("G");
	private JLabel b = new JLabel ("B");
	private JLabel y = new JLabel ("Y");
	private JLabel dim = new JLabel ();
	private JLabel brillo = new JLabel();
	private JLabel contraste = new JLabel();
	private JLabel entropia = new JLabel();
	private JLabel rango = new JLabel();
	private JTextField tr = new JTextField(3);
	private JTextField tg = new JTextField(3);
	private JTextField tb = new JTextField(3);
	private JTextField ty = new JTextField(3);
	private JTextField pos = new JTextField(6); 
	private ButtonGroup mode = new ButtonGroup();
	private JRadioButton rgb = new JRadioButton("rgb");
	private JRadioButton gris = new JRadioButton("y");

	InfoPanel (Imagen data){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		tr.setBorder(null);
		tr.setBackground(this.getBackground());
		tg.setBorder(null);
		tg.setBackground(this.getBackground());
		tb.setBorder(null);
		tb.setBackground(this.getBackground());
		ty.setBorder(null);
		ty.setBackground(this.getBackground());
		pos.setBorder(null);
		pos.setBackground(this.getBackground());
		tr.setBorder(null);
		tr.setBackground(this.getBackground());
		dim.setText("Dimensiones: (" + data.sizeX() +", "+data.sizeY() + ")");
		this.add(pos);
		this.add(r);
		this.add(tr);
		this.add(g);
		this.add(tg);
		this.add(b);
		this.add(tb);
		this.add(y);
		this.add(ty);
		rgb.addActionListener(new MyRadioListener());
		gris.addActionListener(new MyRadioListener());
		mode.add(rgb);
		mode.add(gris);
		this.add(rgb);
		this.add(gris);
		this.add(dim);
		DecimalFormat formatter = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance( Locale.FRANCE ));
		formatter.setRoundingMode( RoundingMode.DOWN );
		brillo.setText("Brillo: " + data.getBrillo());
		contraste.setText("Contraste: " + data.getContraste());
		this.add(brillo);
		this.add(contraste);
		rango.setText("Rango: [" + data.getMin() + "," + data.getMax() +"]");
		this.add(rango);
		this.data = data;
		entropia.setText("Entropia: " + formatter.format(data.getEntropia()));
		this.add(entropia);
	}

	public void setData(int x, int y){
		pos.setText("("+x+","+y +")");
		tr.setText("" + data.getData(x, y)[0]);
		tg.setText("" + data.getData(x, y)[1]);
		tb.setText("" + data.getData(x, y)[2]);
		ty.setText("" + data.getData(x, y)[3]);
	}
	public void setImage(ImagePanel image){
		this.image = image;
	}

	protected class MyRadioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==rgb){
				image.setMode(true);
			}
			else if (e.getSource()==gris){
				image.setMode(false);
			}
			image.repaint();

		}
	}
}
