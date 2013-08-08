package green;

import structures.*;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
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

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.geom.Ellipse2D;


public class Display {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private AdjListGraph graph;
	private Vertex	selected = null;

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
	}
	
	class canvas extends JPanel implements MouseListener{
		public canvas(){
			this.addMouseListener(this);
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			for(Object v:graph.vertices()){
				Vertex vv = (Vertex)v;
				vv.draw(g2d);
			}
			for(Object e:graph.edges()){
				Edge ee = (Edge) e;
				ee.draw(g2d);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int X = e.getX()-13;
			int Y = e.getY()-13;
			
			if(!conflict(e))
				graph.insertVertex("", X, Y);
			
			this.repaint();
		}
		
		public boolean conflict(MouseEvent e){
			int X = e.getX()-13;
			int Y = e.getY()-13;
			
			for(Object v: graph.vertices()){
				Vertex vv = (Vertex) v;
				int vvY = vv.getCoords().y;
				int vvX = vv.getCoords().x;
				int[] collisions = {0,0,0,0};
				
				if(vv.contains(e.getPoint())){
					if(selected == null){
						vv.setColor(Color.RED);
						selected = vv;
					}else{
						if(vv == selected){
							selected.setColor(Color.BLACK);
							selected = null;
						}else if(e.isControlDown()){
							graph.insertEdge(selected, vv, "");
							
						}else{
							selected.setColor(Color.BLACK);
							vv.setColor(Color.RED);
							selected = vv;
						}
					}
					return true;
				}
				
				if(X+12 > vvX-12)
					collisions[0]=1;
				if(Y+12 > vvY-12)
					collisions[1]=1;
				if(X-12 < vvX+12)
					collisions[2]=1;
				if(Y-12 < vvY+12)
					collisions[3]=1;
				
				for(int i=0;i<3;i++)
					collisions[3]+=collisions[i];
				
				if(collisions[3]>3){
					return true;
				}
			
			}
			
			return false;
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
