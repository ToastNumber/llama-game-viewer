package llama;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class StateView extends JPanel implements Observer {
	private StateModel model;

	public StateView(StateModel model) {
		super();
		this.model = model;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.clearRect(0, 0, getWidth(), getHeight());

		g2.setFont(new Font("consolas", 0, 10));
		g2.setFont(getLargestFont(g2, model.toString()));

		int stringHeight = g2.getFontMetrics().getAscent();
		int y = getHeight() / 2 + (8 * stringHeight) / 21;

		g2.drawString(model.toString(), 0, y);
	}

	public Font getLargestFont(Graphics2D g2, String s) {
		Font prev = null;
		Font current = g2.getFont();
		String fontFamily = current.getFamily();

		int width = getWidth();
		int height = getHeight();

		int prevFontSize = g2.getFont().getSize();

		do {
			prev = copyFont(current);

			current = new Font(fontFamily, 0, prevFontSize + 1);
			++prevFontSize;

			g2.setFont(current);
		} while ((g2.getFontMetrics()).stringWidth(s) < width && g2.getFontMetrics().getAscent() < height);

		return prev;
	}

	public static Font copyFont(Font font) {
		return new Font(font.getFamily(), font.getStyle(), font.getSize());
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
