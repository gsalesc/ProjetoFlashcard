package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Flashcard;

public class FlashcardPanel extends JPanel{
	private static final long serialVersionUID = 7088063219615438130L;
	private Flashcard flashcard;
	
	public FlashcardPanel() {
		
	}
	
	public FlashcardPanel(Flashcard fl) {
		setFlashcard(fl);
	}

	public Flashcard getFlashcard() {
		return flashcard;
	}

	public void setFlashcard(Flashcard flashcard) {
		this.flashcard = flashcard;
	}
}
