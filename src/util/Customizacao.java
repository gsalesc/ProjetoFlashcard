package util;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Customizacao {
	public static void escreverNumeroCarta(JPanel carta, int i) {
		JLabel label = new JLabel();
		label.setForeground(Color.BLACK);
		label.setText(String.valueOf(i));
		carta.add(label);
	}
	
	public static void mudarHandCursorBotao(JButton btn) {
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public static void ajustarIcon(JLabel label) {
		label.setSize(200, 200);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.NORTH);		
	}
}
