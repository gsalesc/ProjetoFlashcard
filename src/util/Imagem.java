package util;

import java.awt.Image;

public class Imagem {
	public Image redimensionarIcon(Image img) {
		Image nova = img.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
		return nova;
	}
}
