package green;

import structures.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MenuListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.MouseAdapter;


public class Display {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private AdjListGraph graph;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display window = new Display();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Display() {
		graph = new AdjListGraph();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new canvas();
		frame.getContentPane().add(panel);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JPanel drawpad = new canvas();
		drawpad.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_drawpad = new GridBagConstraints();
		gbc_drawpad.gridwidth = 22;
		gbc_drawpad.insets = new Insets(5, 0, 5, 5);
		gbc_drawpad.fill = GridBagConstraints.BOTH;
		gbc_drawpad.gridx = 0;
		gbc_drawpad.gridy = 0;
		mainPanel.add(drawpad, gbc_drawpad);
		
		JButton btnColorGraph = new JButton("Color Graph");
		btnColorGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnColorGraph = new GridBagConstraints();
		gbc_btnColorGraph.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnColorGraph.gridx = 22;
		gbc_btnColorGraph.gridy = 0;
		gbc_btnColorGraph.insets = new Insets(5,0,0,5);
		mainPanel.add(btnColorGraph, gbc_btnColorGraph);
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{drawpad, btnColorGraph, frame.getContentPane(), mainPanel}));
	}
	
	class canvas extends JPanel implements MouseListener{
		public canvas(){
			this.addMouseListener(this);
		}
		public void paint(Graphics g){
			super.paint(g);
			for(Object v:graph.vertices()){
				Vertex vv = (Vertex)v;
				vv.draw(g);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			graph.insertVertex("", e.getX()-13, e.getY()-13);
			this.repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

}
