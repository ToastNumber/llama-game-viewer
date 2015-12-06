package llama;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	private JPanel pnlButtons = new JPanel();
	private JButton btnReset = new JButton("Reset");
	private JButton btnForwards = new JButton("Forwards");
	private MoveLabel lblMoves;
	private JLabel lblError;

	public ControlPanel(StateModel model, MoveSequence moveSequence) {
		super();

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.reset();
				moveSequence.reset();
				lblError.setForeground(Color.BLACK);
				lblError.setText("---");

				btnForwards.setEnabled(!moveSequence.atEnd());
			}
		});

		btnReset.setFocusable(false);

		btnForwards.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Move move = moveSequence.get();
				boolean valid = model.performMove(move);

				if (valid) {
					lblError.setForeground(Color.BLACK);
					lblError.setText("---");

					moveSequence.forwards();
					// If this puts the sequence at the end, then disable this
					// button
					if (moveSequence.atEnd()) btnForwards.setEnabled(false);
				} else {
					lblError.setForeground(Color.BLACK);
					lblError.setText(String.format("Could not perform the move %s", move.toString()));
				}
			}
		});
		
		btnForwards.setEnabled(!moveSequence.atEnd());
		btnForwards.setFocusable(false);

		lblMoves = new MoveLabel(moveSequence.toString(), moveSequence);
		lblMoves.setHorizontalAlignment(JLabel.CENTER);
		moveSequence.addObserver(lblMoves);

		lblError = new JLabel("---");
		lblError.setForeground(Color.BLACK);
		lblError.setHorizontalAlignment(JLabel.CENTER);

		pnlButtons.setLayout(new FlowLayout());
		pnlButtons.add(btnReset);
		pnlButtons.add(btnForwards);

		setLayout(new BorderLayout());
		add(lblMoves, BorderLayout.NORTH);
		add(pnlButtons, BorderLayout.CENTER);
		add(lblError, BorderLayout.SOUTH);
	}

	class MoveLabel extends JLabel implements Observer {
		private MoveSequence moveSequence;

		MoveLabel(String text, MoveSequence moveSequence) {
			super(text);
			this.moveSequence = moveSequence;
		}

		@Override
		public void update(Observable o, Object arg) {
			setText(moveSequence.toString());
		}
	}
}
