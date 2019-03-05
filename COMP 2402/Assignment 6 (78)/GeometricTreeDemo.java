package comp2402a6;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

/* MenuDemo.java requires images/middle.gif. */

/*
 * This class is just like MenuLookDemo, except the menu items
 * actually do something, thanks to event listeners.
 */
public class GeometricTreeDemo implements ActionListener, ItemListener {
	GeometricTree t;
	JPanel output;
	JScrollPane scrollPane;
	String newline = "\n";

	public GeometricTreeDemo() {
		t = new GeometricTree();
		GeometricTree.completeBinaryTree(t, 50);
		t.inorderDraw();
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Actions");
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItem("new galton-watson tree");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("new complete binary tree");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("in-order layout");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("leftist layout");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("balanced layout");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		return menuBar;
	}

	public Container createContentPane() {
		// Create the content-pane-to-be.
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);

		// Create a scrolled text area.
		output = new JPanel() {
			private static final long serialVersionUID = 2196729896823372494L;
			public void paint(Graphics g) {
				if (t != null) recursivePaint(g, t.r);
			}
			protected void recursivePaint(Graphics g, GeometricTreeNode u) {
				final int r = 10,  m = 20;
				if (u == null) return;
				g.fillOval(u.position.x * m, u.position.y * m, r, r);
				if (u.left != null) {
					g.drawLine(u.position.x * m + r/2, u.position.y * m  + r/2, 
							u.left.position.x * m + r/2, u.left.position.y *m + r/2);
					recursivePaint(g, u.left);
				}
				if (u.right != null) {
					g.drawLine(u.position.x * m + r/2, u.position.y * m + r/2, 
							u.right.position.x * m + r/2, u.right.position.y *m + r/2);
					recursivePaint(g, u.right);
				}
			}
		};

		// Add the text area to the content pane.
		contentPane.add(output, BorderLayout.CENTER);

		return contentPane;
	}

	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem) (e.getSource());
		String s = "Action event detected." + newline + "    Event source: "
				+ source.getText();
		System.out.println(s);
		if (source.getText().equals("new galton-watson tree")) {
			t.clear();
			GeometricTree.galtonWatsonTree(t, 100);
			t.inorderDraw();
			output.repaint();
		} else 	if (source.getText().equals("new complete binary tree")) {
			t.clear();
			GeometricTree.completeBinaryTree(t, 50);
			t.inorderDraw();			
			output.repaint();
		} else if (source.getText().equals("in-order layout")) {
			t.inorderDraw();
			output.repaint();
		} else if (source.getText().equals("leftist layout")) {
			t.leftistDraw();
			output.repaint();
		} else if (source.getText().equals("balanced layout")) {
			t.balancedDraw();
			output.repaint();
		}

	}

	public void itemStateChanged(ItemEvent e) {
//		JMenuItem source = (JMenuItem) (e.getSource());
//		String s = "Item event detected."
//				+ newline
//				+ "    Event source: "
//				+ source.getText()
//				+ newline
//				+ "    New state: "
//				+ ((e.getStateChange() == ItemEvent.SELECTED) ? "selected"
//						: "unselected");
//		System.out.println(s);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("MenuDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		GeometricTreeDemo demo = new GeometricTreeDemo();
		frame.setJMenuBar(demo.createMenuBar());
		frame.setContentPane(demo.createContentPane());

		// Display the window.
		frame.setSize(450, 260);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}