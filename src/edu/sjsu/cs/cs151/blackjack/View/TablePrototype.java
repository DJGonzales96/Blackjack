package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.*;

import edu.sjsu.cs.cs151.blackjack.Model.*;
import edu.sjsu.cs.cs151.blackjack.Model.Card.Rank;
import edu.sjsu.cs.cs151.blackjack.Model.Card.Suit;

public class TablePrototype{
	private Map<String,ImageIcon> cardMap;
	private final int CARD_WIDTH = 150;
	private final int CARD_HEIGHT = 200;
	
	private JFrame frmBlackjack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablePrototype window = new TablePrototype();
					window.frmBlackjack.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
	}

	/**
	 * Create the application.
	 */
	public TablePrototype() {
		initializeCardIcons();
		initialize();
		
		
		
			
	}
	
	
	public JFrame getFrame() {
		return this.frmBlackjack;
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBlackjack = new JFrame();
		frmBlackjack.setPreferredSize(new Dimension(1500, 700));
		frmBlackjack.setSize(new Dimension(1200, 700));
		frmBlackjack.getContentPane().setMinimumSize(new Dimension(600, 400));
		frmBlackjack.setTitle("Blackjack");
		frmBlackjack.getContentPane().setBackground(new Color(0, 100, 0));
		Image frameIcon = new ImageIcon(this.getClass().getResource("/cards_Icon.png")).getImage();
		frmBlackjack.setIconImage(frameIcon);
		frmBlackjack.setBounds(100, 100, 1000, 601);
		frmBlackjack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBlackjack.pack();
		
		
		
		
		/*
		 * Initialize button panel and add buttons.
		 */
		frmBlackjack.getContentPane().setLayout(new CardLayout(0, 0));
		//TODO: Add all the updated table stuff into VIEW.java
		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePanel.setBackground(new Color(0, 128, 0));
		frmBlackjack.getContentPane().add(tablePanel, "name_731835258992155");
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(10, 60));
		btnPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tablePanel.add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnStand = new JButton("Stand");
		btnPanel.add(btnStand);
		
		JButton btnHit = new JButton("Hit");
		btnPanel.add(btnHit);
		
		JButton btnDouble = new JButton("Double Down");
		btnPanel.add(btnDouble);
		
		JMenuBar menuBar = new JMenuBar();
		tablePanel.add(menuBar, BorderLayout.NORTH);
		menuBar.setMaximumSize(new Dimension(0, 10));
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenuItem mntmRestart = new JMenuItem("Restart");
		mnGame.add(mntmRestart);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mnHelp.add(mntmInstructions);
		
		JMenuItem mntmTipsTricks = new JMenuItem("Tips & Tricks");
		mnHelp.add(mntmTipsTricks);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setOpaque(false);
		tablePanel.add(cardPanel, BorderLayout.CENTER);
		cardPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(cardMap.get("ACE of SPADES"));
		label.setBounds(293, 316, 150, 200);
		cardPanel.add(label);
		
		JLabel lblPlayer = new JLabel("PLAYER");
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		lblPlayer.setBounds(159, 401, 125, 26);
		cardPanel.add(lblPlayer);
		
		JLabel lblDealer = new JLabel("DEALER");
		lblDealer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		lblDealer.setBounds(159, 103, 125, 26);
		cardPanel.add(lblDealer);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(cardMap.get("red_back"));
		label_1.setBounds(293, 21, 150, 200);
		cardPanel.add(label_1);
		btnDouble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("IT's A Hit Boiss");
			}
		});
		
		
		/*
		 * Initialize JMenu and add menu items.
		 */
	}
	
	private void initializeCardIcons() {
		cardMap = new HashMap<String, ImageIcon>();
		
		// Build the filepath strings for each .png file
		char[] suit = {'S', 'H', 'D', 'C'};
		String[] rank = {"A","2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		ArrayList<ImageIcon> cardImages = new ArrayList<>();
		
		String filename;
		for(int i = 0; i<suit.length;i++)
			for(int j = 0; j<rank.length; j++) {
				 filename = rank[j] + suit[i] + ".png";
				 cardImages.add(resizeCard(new ImageIcon(this.getClass().getResource("/" + filename))));
			}
		
		// Assign each .png file to an appropriate name
		// Key = "FIVE of HEARTS" -> Value = "/5H.png" image
		// Key = "ACE_LOW of DIAMONDS" -> Value = "/AD.png" image
		// etc.
		String card;
		int index = 0;
		for(Suit s: Card.Suit.values())
			for(Rank r: Card.Rank.values()) {
				card = r.toString() + " of " + s.toString();
				cardMap.put(card, cardImages.get(index++));
			}
		
		// Assign back of card it's image
		cardMap.put("red_back", resizeCard(new ImageIcon(this.getClass().getResource("/" + "red_back.png"))));
	}
	
	// Resizes a card to properly display it
	private ImageIcon resizeCard(ImageIcon card) {
		ImageIcon imageIcon = new ImageIcon(card.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_DEFAULT));
		return imageIcon;
	}
}
