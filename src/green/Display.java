package green;

import structures.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.TextField;

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
import java.util.ArrayList;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.JList;
import javax.swing.JTextArea;


public class Display {

	private JFrame frame;
	private final ButtonGroup bgpFunctions = new ButtonGroup();
	private AdjListGraph<Integer, Integer> graph;
	private Vertex<Integer>	selected = null;
	private Vertex<Integer> altSelected = null;
	private JPanel drawpad;
	private JTextArea consoleOut;

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
		frame.setTitle("Graphite");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0,0};
		gbl_mainPanel.rowHeights = new int[]{0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		drawpad = new canvas();
		drawpad.setDoubleBuffered(true);
		drawpad.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_drawpad = new GridBagConstraints();
		gbc_drawpad.gridwidth = 22;
		gbc_drawpad.gridheight = 2;
		gbc_drawpad.insets = new Insets(5, 5, 5, 5);
		gbc_drawpad.fill = GridBagConstraints.BOTH;
		gbc_drawpad.gridx = 0;
		gbc_drawpad.gridy = 0;
		mainPanel.add(drawpad, gbc_drawpad);
		
		JPanel sidePanel = new JPanel();
		GridBagConstraints gbc_sidePanel = new GridBagConstraints();
		gbc_sidePanel.gridheight = 2;
		gbc_sidePanel.insets = new Insets(5, 5, 5, 5);
		gbc_sidePanel.fill = GridBagConstraints.BOTH;
		gbc_sidePanel.gridx = 22;
		gbc_sidePanel.gridy = 0;
		mainPanel.add(sidePanel, gbc_sidePanel);
		GridBagLayout gbl_sidePanel = new GridBagLayout();
		gbl_sidePanel.columnWidths = new int[]{118};
		gbl_sidePanel.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_sidePanel.columnWeights = new double[]{1.0};
		gbl_sidePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		sidePanel.setLayout(gbl_sidePanel);
		
		JButton btnColorGraph = new JButton("Color Graph");
		btnColorGraph.setPreferredSize(new Dimension(120,25));
		GridBagConstraints gbc_btnColorGraph = new GridBagConstraints();
		gbc_btnColorGraph.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnColorGraph.insets = new Insets(0, 0, 5, 0);
		gbc_btnColorGraph.gridx = 0;
		gbc_btnColorGraph.gridy = 0;
		sidePanel.add(btnColorGraph, gbc_btnColorGraph);
		bgpFunctions.add(btnColorGraph);
		btnColorGraph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Functions func = new Functions();
				graph = func.colorGraph(graph);
				consoleOut.setText("Colors: "+graph.attributes.get("colors")+"\nBipartite: " + graph.attributes.get("bipartite"));
				drawpad.repaint();
				
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.setPreferredSize(new Dimension(120,25));
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(5, 0, 5, 0);
		gbc_btnClear.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnClear.gridx = 0;
		gbc_btnClear.gridy = 2;
		sidePanel.add(btnClear, gbc_btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				graph = new AdjListGraph<Integer, Integer>();
				consoleOut.setText("");
				drawpad.repaint();
			}
		});
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setPreferredSize(new Dimension(120,25));
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.insets = new Insets(0, 0, 5, 0);
		gbc_btnHelp.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnHelp.gridx = 0;
		gbc_btnHelp.gridy = 3;
		sidePanel.add(btnHelp, gbc_btnHelp);
		btnHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Help help = new Help();
				
			}
		});
		
		JButton btnFindPath = new JButton("Find Path");
		btnFindPath.setPreferredSize(new Dimension(120,25));
		GridBagConstraints gbc_btnFindPath = new GridBagConstraints();
		gbc_btnFindPath.insets = new Insets(0, 0, 5, 0);
		gbc_btnFindPath.gridx = 0;
		gbc_btnFindPath.gridy = 1;
		sidePanel.add(btnFindPath, gbc_btnFindPath);
		btnFindPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Functions func = new Functions();
				graph = func.findPath(graph, selected, altSelected);
				drawpad.repaint();
			}
		});
		
		consoleOut = new JTextArea();
		consoleOut.setEditable(false);
		GridBagConstraints gbc_consoleOut = new GridBagConstraints();
		gbc_consoleOut.fill = GridBagConstraints.BOTH;
		gbc_consoleOut.gridx = 0;
		gbc_consoleOut.gridy = 8;
		sidePanel.add(consoleOut, gbc_consoleOut);
		consoleOut.setPreferredSize(new Dimension(10,10));
		
		
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
			if(e.getButton() == MouseEvent.BUTTON1){
				if(vv == null){
					graph.insertVertex(-1, X, Y);
					consoleOut.setText("");
				}else{
					vertexClicked(vv, e);
				}
			}else if(e.getButton() == MouseEvent.BUTTON3){
				if(vv!=null){
					if(vv == selected)
						selected = null;
					graph.removeVertex(vv);
					consoleOut.setText("");
					this.getGraphics().clearRect((int)vv.getCoords().getX(), (int)vv.getCoords().getY(), 25,25);
				}
			}
		
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
		
		public void vertexClicked(Vertex<Integer> vv, MouseEvent e){
			if(e.isShiftDown()){
				if(altSelected != null) altSelected.put("color", Color.BLACK);
				vv.put("color", Color.RED);
				altSelected = vv;
				if(vv == selected){
					selected = null;
				}
			}else if(vv == selected){
				selected.put("color", Color.BLACK);
				selected = null;
			}else if(e.isControlDown()){
				if(selected != null) {
					graph.insertEdge(selected, vv, 0);
					consoleOut.setText("");
				}
			}else{
				if(selected != null) selected.put("color",Color.BLACK);
				vv.put("color", Color.GREEN);
				selected = vv;
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
