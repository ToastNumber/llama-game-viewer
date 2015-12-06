package llama;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class StatePanel extends JPanel {
	
	public StatePanel(State state, MoveSequence moveSequence) {
		super();
		
		StateModel model = new StateModel(state);
		StateView view = new StateView(model);
		model.addObserver(view);
		ControlPanel pnlControl = new ControlPanel(model, moveSequence);
		
		setLayout(new BorderLayout());
		add(view, BorderLayout.CENTER);
		add(pnlControl, BorderLayout.SOUTH);
	}
}
