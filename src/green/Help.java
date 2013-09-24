package green;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Help extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTextArea textHelp = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Help dialog = new Help();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Help() {
		setResizable(false);
		setBounds(100, 100, 450, 238);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		textHelp.setColumns(35);
		contentPanel.add(textHelp);
		textHelp.setText("Left Click:  Create a new node or select a node.\n\nRight Click: Destroy the node.\n\nCtrl-Click: Connect selected node to clicked node.\n\nShift-Click: Select destination node.\n\nFind Path: Find shortest path between the selected node and the destination node.");
		textHelp.setLineWrap(true);
		textHelp.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());
		textHelp.setEditable(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent evt) {
						dispose();
						
					}
					
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

}
