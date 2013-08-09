package green;

import structures.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Component;
import org.eclipse.wb.swing.FocusTraversalOnArray;


public class Display {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private AdjListGraph<Integer, Integer> graph;
	private Vertex<Integer>	selected = null;
	JPanel drawpad;

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
		graph = new AdjListGraph<Integer, Integer>();
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
		
		drawpad = new canvas();
		drawpad.setDoubleBuffered(true);
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
			@Override
			public void actionPerformed(ActionEvent e) {
				Functions func = new Functions();
				graph = func.colorGraph(graph);
				drawpad.repaint();
				
			}
		});
		GridBagConstraints gbc_btnColorGraph = new GridBagConstraints();
		gbc_btnColorGraph.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnColorGraph.gridx = 22;
		gbc_btnColorGraph.gridy = 0;
		gbc_btnColorGraph.insets = new Insets(5,0,0,5);
		mainPanel.add(btnColorGraph, gbc_btnColorGraph);
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{drawpad}));
	}
	
	class canvas extends JPanel implements MouseListener{
		public canvas(){
			this.addMouseListener(this);
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			
			for(Object e:graph.edges()){
				Edge ee = (Edge) e;
				ee.draw(g2d);
			}
			
			for(Object v:graph.vertices()){
				Vertex<String> vv = (Vertex<String>)v;
				vv.draw(g2d);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int X = e.getX()-13;
			int Y = e.getY()-13;
			
			Vertex<Integer> vv = conflict(e);
			if(vv==null){
				if(e.getButton()==MouseEvent.BUTTON1)
					graph.insertVertex(-1, X, Y);
			}else if(vv.contains(e.getPoint()))
				if(e.getButton()==MouseEvent.BUTTON3){
					if(vv == selected)
						selected = null;
					graph.removeVertex(vv);
					this.getGraphics().clearRect((int)vv.getCoords().getX(), (int)vv.getCoords().getY(), 25,25);
				}else
					vertexClicked(vv, e.isControlDown());
			
			this.repaint();
		}
		
		public Vertex<Integer> conflict(MouseEvent e){
			int X = e.getX()-13;
			int Y = e.getY()-13;
			
			for(Object v: graph.vertices()){
				Vertex<Integer> vv = (Vertex<Integer>) v;
				int vvY = vv.getCoords().y;
				int vvX = vv.getCoords().x;
				int[] collisions = {0,0,0,0};
				
				if(vv.contains(e.getPoint())){
					return vv;
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
					return vv;
				}
			
			}
			
			return null;
		}
		
		public void vertexClicked(Vertex<Integer> vv, boolean controlDown){
			if(selected == null){
				vv.setColor(Color.RED);
				selected = vv;
			}else{
				if(vv == selected){
					selected.setColor(Color.BLACK);
					selected = null;
				}else if(controlDown){
					graph.insertEdge(selected, vv, 0);
					
				}else{
					selected.setColor(Color.BLACK);
					vv.setColor(Color.RED);
					selected = vv;
				}
			}
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
