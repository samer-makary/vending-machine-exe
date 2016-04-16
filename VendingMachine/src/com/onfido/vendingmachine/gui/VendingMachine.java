package com.onfido.vendingmachine.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.onfido.vendingmachine.Machine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;

public class VendingMachine {

	private final Machine controller;
	
	private JFrame frmVendingMachine;
	private JTextField textFieldInPound2;
	private JTextField textFieldInPound1;
	private JTextField textFieldInPenny50;
	private JTextField textFieldInPenny20;
	private JTextField textFieldInPenny10;
	private JTextField textFieldInPenny5;
	private JTextField textFieldInPenny2;
	private JTextField textFieldInPenny1;
	private JTextField textFieldOutPound2;
	private JTextField textFieldOutPound1;
	private JTextField textFieldOutPenny50;
	private JTextField textFieldOutPenny20;
	private JTextField textFieldOutPenny10;
	private JTextField textFieldOutPenny5;
	private JTextField textFieldOutPenny2;
	private JTextField textFieldOutPenny1;

	public int selectedProduct = -1;
	private JTextField textFieldSlotProductName;
	private JTextField textFieldSlotProductPounds;
	private JTextField textFieldSlotProductPennies;
	private JTextField textFieldReloadPound2;
	private JTextField textFieldReloadPound1;
	private JTextField textFieldReloadPenny50;
	private JTextField textFieldReloadPenny20;
	private JTextField textFieldReloadPenny10;
	private JTextField textFieldReloadPenny5;
	private JTextField textFieldReloadPenny2;
	private JTextField textFieldReloadPenny1;
	private JButton btnProduct4;
	private JButton btnProduct9;
	private JButton btnProduct7;
	private JButton btnProduct8;
	private JButton btnProduct1;
	private JButton btnProduct2;
	private JButton btnProduct6;
	private JButton btnProduct3;
	private JButton btnProduct10;
	private JButton btnProduct5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VendingMachine window = new VendingMachine();
					window.frmVendingMachine.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VendingMachine() {
		controller = Machine.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVendingMachine = new JFrame();
		frmVendingMachine.setResizable(false);
		frmVendingMachine.setTitle("Vending Machine");
		frmVendingMachine.setBounds(100, 100, 550, 800);
		frmVendingMachine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmVendingMachine.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel consumerPanel = new JPanel();
		tabbedPane.addTab("Consumption", null, consumerPanel, null);
		consumerPanel.setLayout(null);

		JPanel panelDisplay = new JPanel();
		panelDisplay
				.setBorder(new TitledBorder(null, "Machine Display", TitledBorder.LEADING,
						TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20),
						UIManager.getColor("TitledBorder.titleColor")));
		panelDisplay.setBounds(15, 16, 509, 104);
		consumerPanel.add(panelDisplay);
		panelDisplay.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(15, 27, 479, 61);
		panelDisplay.add(textArea);

		JPanel panelProducts = new JPanel();
		panelProducts.setBounds(15, 135, 510, 220);
		panelProducts.setBorder(new TitledBorder(null, "Products", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20),
				UIManager.getColor("TitledBorder.titleColor")));
		consumerPanel.add(panelProducts);
		panelProducts.setLayout(new GridLayout(2, 5, 1, 1));

		btnProduct1 = new JButton("X");
		btnProduct1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProduct1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedProduct = 0;
			}
		});
		panelProducts.add(btnProduct1);

		btnProduct2 = new JButton("X");
		btnProduct2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProduct2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedProduct = 1;
			}
		});
		panelProducts.add(btnProduct2);

		btnProduct3 = new JButton("X");
		btnProduct3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProduct3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedProduct = 2;
			}
		});
		panelProducts.add(btnProduct3);

		btnProduct4 = new JButton("X");
		btnProduct4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelProducts.add(btnProduct4);

		btnProduct5 = new JButton("X");
		btnProduct5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelProducts.add(btnProduct5);

		btnProduct6 = new JButton("X");
		btnProduct6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelProducts.add(btnProduct6);

		btnProduct7 = new JButton("X");
		btnProduct7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelProducts.add(btnProduct7);

		btnProduct8 = new JButton("X");
		btnProduct8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelProducts.add(btnProduct8);

		btnProduct9 = new JButton("X");
		btnProduct9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelProducts.add(btnProduct9);

		btnProduct10 = new JButton("X");
		btnProduct10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelProducts.add(btnProduct10);

		JPanel panelCashIn = new JPanel();
		panelCashIn.setBorder(new TitledBorder(null, "Cash In", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20), null));
		panelCashIn.setBounds(15, 355, 509, 118);
		consumerPanel.add(panelCashIn);
		panelCashIn.setLayout(new GridLayout(2, 8, 2, 2));

		JLabel lblInPound2 = new JLabel("\u00A32");
		lblInPound2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInPound2.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(lblInPound2);

		JLabel lblInPound1 = new JLabel("\u00A31");
		lblInPound1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInPound1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(lblInPound1);

		JLabel lblInPenny50 = new JLabel("50p");
		lblInPenny50.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInPenny50.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(lblInPenny50);

		JLabel lblInPenny20 = new JLabel("20p");
		lblInPenny20.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInPenny20.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(lblInPenny20);

		JLabel lblInPenny10 = new JLabel("10p");
		lblInPenny10.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInPenny10.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(lblInPenny10);

		JLabel lblInPenny5 = new JLabel("5p");
		lblInPenny5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInPenny5.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(lblInPenny5);

		JLabel lblInPenny2 = new JLabel("2p");
		lblInPenny2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInPenny2.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(lblInPenny2);

		JLabel lblInPenny1 = new JLabel("1p");
		lblInPenny1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInPenny1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(lblInPenny1);

		textFieldInPound2 = new JTextField();
		textFieldInPound2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldInPound2.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(textFieldInPound2);
		textFieldInPound2.setColumns(10);

		textFieldInPound1 = new JTextField();
		textFieldInPound1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldInPound1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(textFieldInPound1);
		textFieldInPound1.setColumns(10);

		textFieldInPenny50 = new JTextField();
		textFieldInPenny50.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldInPenny50.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(textFieldInPenny50);
		textFieldInPenny50.setColumns(10);

		textFieldInPenny20 = new JTextField();
		textFieldInPenny20.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldInPenny20.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(textFieldInPenny20);
		textFieldInPenny20.setColumns(10);

		textFieldInPenny10 = new JTextField();
		textFieldInPenny10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldInPenny10.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(textFieldInPenny10);
		textFieldInPenny10.setColumns(10);

		textFieldInPenny5 = new JTextField();
		textFieldInPenny5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldInPenny5.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(textFieldInPenny5);
		textFieldInPenny5.setColumns(10);

		textFieldInPenny2 = new JTextField();
		textFieldInPenny2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldInPenny2.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(textFieldInPenny2);
		textFieldInPenny2.setColumns(10);

		textFieldInPenny1 = new JTextField();
		textFieldInPenny1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldInPenny1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashIn.add(textFieldInPenny1);
		textFieldInPenny1.setColumns(10);

		JPanel panelCashOut = new JPanel();
		panelCashOut
				.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
						"Cash Out", TitledBorder.LEADING, TitledBorder.TOP,
						new Font("Tahoma", Font.PLAIN, 20), new Color(0, 0, 0)));
		panelCashOut.setBounds(15, 566, 509, 118);
		consumerPanel.add(panelCashOut);
		panelCashOut.setLayout(new GridLayout(2, 8, 2, 2));

		JLabel lblOutPound2 = new JLabel("\u00A32");
		lblOutPound2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOutPound2.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashOut.add(lblOutPound2);

		JLabel lblOutPound1 = new JLabel("\u00A31");
		lblOutPound1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOutPound1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashOut.add(lblOutPound1);

		JLabel lblOutPenny50 = new JLabel("50p");
		lblOutPenny50.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOutPenny50.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashOut.add(lblOutPenny50);

		JLabel lblOutPenny20 = new JLabel("20p");
		lblOutPenny20.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOutPenny20.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashOut.add(lblOutPenny20);

		JLabel lblOutPenny10 = new JLabel("10p");
		lblOutPenny10.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOutPenny10.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashOut.add(lblOutPenny10);

		JLabel lblOutPenny5 = new JLabel("5p");
		lblOutPenny5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOutPenny5.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashOut.add(lblOutPenny5);

		JLabel lblOutPenny2 = new JLabel("2p");
		lblOutPenny2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOutPenny2.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashOut.add(lblOutPenny2);

		JLabel lblOutPenny1 = new JLabel("1p");
		lblOutPenny1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOutPenny1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCashOut.add(lblOutPenny1);

		textFieldOutPound2 = new JTextField();
		textFieldOutPound2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldOutPound2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOutPound2.setEditable(false);
		textFieldOutPound2.setColumns(10);
		panelCashOut.add(textFieldOutPound2);

		textFieldOutPound1 = new JTextField();
		textFieldOutPound1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldOutPound1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOutPound1.setEditable(false);
		textFieldOutPound1.setColumns(10);
		panelCashOut.add(textFieldOutPound1);

		textFieldOutPenny50 = new JTextField();
		textFieldOutPenny50.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldOutPenny50.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOutPenny50.setEditable(false);
		textFieldOutPenny50.setColumns(10);
		panelCashOut.add(textFieldOutPenny50);

		textFieldOutPenny20 = new JTextField();
		textFieldOutPenny20.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldOutPenny20.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOutPenny20.setEditable(false);
		textFieldOutPenny20.setColumns(10);
		panelCashOut.add(textFieldOutPenny20);

		textFieldOutPenny10 = new JTextField();
		textFieldOutPenny10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldOutPenny10.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOutPenny10.setEditable(false);
		textFieldOutPenny10.setColumns(10);
		panelCashOut.add(textFieldOutPenny10);

		textFieldOutPenny5 = new JTextField();
		textFieldOutPenny5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldOutPenny5.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOutPenny5.setEditable(false);
		textFieldOutPenny5.setColumns(10);
		panelCashOut.add(textFieldOutPenny5);

		textFieldOutPenny2 = new JTextField();
		textFieldOutPenny2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldOutPenny2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOutPenny2.setEditable(false);
		textFieldOutPenny2.setColumns(10);
		panelCashOut.add(textFieldOutPenny2);

		textFieldOutPenny1 = new JTextField();
		textFieldOutPenny1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldOutPenny1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOutPenny1.setEditable(false);
		textFieldOutPenny1.setColumns(10);
		panelCashOut.add(textFieldOutPenny1);

		JPanel panelActions = new JPanel();
		panelActions.setBounds(15, 476, 509, 55);
		consumerPanel.add(panelActions);
		panelActions.setLayout(new GridLayout(1, 3, 5, 5));

		JButton btnBuy = new JButton("Buy :D");
		btnBuy.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("The selected product is " + selectedProduct);
			}
		});
		panelActions.add(btnBuy);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelActions.add(btnCancel);

		JButton btnThanks = new JButton("Thanks!");
		btnThanks.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelActions.add(btnThanks);

		JPanel maintenancePanel = new JPanel();
		tabbedPane.addTab("Maintenance", null, maintenancePanel, null);
		maintenancePanel.setLayout(null);

		JPanel panelSlots = new JPanel();
		panelSlots.setBounds(15, 15, 510, 220);
		panelSlots.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Slots", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20), new Color(0, 0, 0)));
		maintenancePanel.add(panelSlots);
		panelSlots.setLayout(new GridLayout(2, 5, 1, 1));

		JButton btnSlot1 = new JButton("Slot 1");
		btnSlot1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleRestockSlot(VendingMachine.this, 0);
			}
		});
		btnSlot1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelSlots.add(btnSlot1);

		JButton btnSlot2 = new JButton("Slot 2");
		btnSlot2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleRestockSlot(VendingMachine.this, 1);
			}
		});
		btnSlot2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelSlots.add(btnSlot2);

		JButton btnSlot3 = new JButton("Slot 3");
		btnSlot3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleRestockSlot(VendingMachine.this, 2);
			}
		});
		btnSlot3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelSlots.add(btnSlot3);

		JButton btnSlot4 = new JButton("Slot 4");
		btnSlot4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleRestockSlot(VendingMachine.this, 3);
			}
		});
		btnSlot4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelSlots.add(btnSlot4);

		JButton btnSlot5 = new JButton("Slot 5");
		btnSlot5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleRestockSlot(VendingMachine.this, 4);
			}
		});
		btnSlot5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelSlots.add(btnSlot5);

		JButton btnSlot6 = new JButton("Slot 6");
		btnSlot6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleRestockSlot(VendingMachine.this, 5);
			}
		});
		btnSlot6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelSlots.add(btnSlot6);

		JButton btnSlot7 = new JButton("Slot 7");
		btnSlot7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleRestockSlot(VendingMachine.this, 6);
			}
		});
		btnSlot7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelSlots.add(btnSlot7);

		JButton btnSlot8 = new JButton("Slot 8");
		btnSlot8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleRestockSlot(VendingMachine.this, 7);
			}
		});
		btnSlot8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelSlots.add(btnSlot8);

		JButton btnSlot9 = new JButton("Slot 9");
		btnSlot9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleRestockSlot(VendingMachine.this, 8);
			}
		});
		btnSlot9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelSlots.add(btnSlot9);

		JButton btnSlot10 = new JButton("Slot 10");
		btnSlot10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleRestockSlot(VendingMachine.this, 9);
			}
		});
		btnSlot10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelSlots.add(btnSlot10);

		JPanel panelProductInfo = new JPanel();
		panelProductInfo.setBorder(
				new TitledBorder(null, "Slot Product Info", TitledBorder.LEADING,
						TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20), null));
		panelProductInfo.setBounds(15, 239, 509, 122);
		maintenancePanel.add(panelProductInfo);
		panelProductInfo.setLayout(new GridLayout(2, 3, 5, 5));

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		panelProductInfo.add(lblName);

		JLabel lblPounds = new JLabel("Pounds");
		lblPounds.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPounds.setHorizontalAlignment(SwingConstants.CENTER);
		panelProductInfo.add(lblPounds);

		JLabel lblPennies = new JLabel("Pennies");
		lblPennies.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPennies.setHorizontalAlignment(SwingConstants.CENTER);
		panelProductInfo.add(lblPennies);

		textFieldSlotProductName = new JTextField();
		textFieldSlotProductName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldSlotProductName.setHorizontalAlignment(SwingConstants.CENTER);
		panelProductInfo.add(textFieldSlotProductName);
		textFieldSlotProductName.setColumns(10);

		textFieldSlotProductPounds = new JTextField();
		textFieldSlotProductPounds.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldSlotProductPounds.setHorizontalAlignment(SwingConstants.CENTER);
		panelProductInfo.add(textFieldSlotProductPounds);
		textFieldSlotProductPounds.setColumns(10);

		textFieldSlotProductPennies = new JTextField();
		textFieldSlotProductPennies.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldSlotProductPennies.setHorizontalAlignment(SwingConstants.CENTER);
		panelProductInfo.add(textFieldSlotProductPennies);
		textFieldSlotProductPennies.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("TitledBorder.titleColor"));
		separator.setBounds(0, 370, 540, 2);
		maintenancePanel.add(separator);

		JPanel panelCashReload = new JPanel();
		panelCashReload
				.setBorder(new TitledBorder(null, "Cash Reload", TitledBorder.LEADING,
						TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20), null));
		panelCashReload.setBounds(15, 380, 510, 120);
		maintenancePanel.add(panelCashReload);
		panelCashReload.setLayout(new GridLayout(2, 8, 2, 2));

		JLabel lblReloadPound2 = new JLabel("\u00A32");
		lblReloadPound2.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloadPound2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelCashReload.add(lblReloadPound2);

		JLabel lblReloadPound1 = new JLabel("\u00A31");
		lblReloadPound1.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloadPound1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelCashReload.add(lblReloadPound1);

		JLabel lblReloadPenny50 = new JLabel("50p");
		lblReloadPenny50.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloadPenny50.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelCashReload.add(lblReloadPenny50);

		JLabel lblReloadPenny20 = new JLabel("20p");
		lblReloadPenny20.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloadPenny20.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelCashReload.add(lblReloadPenny20);

		JLabel lblReloadPenny10 = new JLabel("10p");
		lblReloadPenny10.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloadPenny10.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelCashReload.add(lblReloadPenny10);

		JLabel lblReloadPenny5 = new JLabel("5p");
		lblReloadPenny5.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloadPenny5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelCashReload.add(lblReloadPenny5);

		JLabel lblReloadPenny2 = new JLabel("2p");
		lblReloadPenny2.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloadPenny2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelCashReload.add(lblReloadPenny2);

		JLabel lblReloadPenny1 = new JLabel("1p");
		lblReloadPenny1.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloadPenny1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelCashReload.add(lblReloadPenny1);

		textFieldReloadPound2 = new JTextField();
		textFieldReloadPound2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReloadPound2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldReloadPound2.setColumns(10);
		panelCashReload.add(textFieldReloadPound2);

		textFieldReloadPound1 = new JTextField();
		textFieldReloadPound1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReloadPound1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldReloadPound1.setColumns(10);
		panelCashReload.add(textFieldReloadPound1);

		textFieldReloadPenny50 = new JTextField();
		textFieldReloadPenny50.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReloadPenny50.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldReloadPenny50.setColumns(10);
		panelCashReload.add(textFieldReloadPenny50);

		textFieldReloadPenny20 = new JTextField();
		textFieldReloadPenny20.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReloadPenny20.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldReloadPenny20.setColumns(10);
		panelCashReload.add(textFieldReloadPenny20);

		textFieldReloadPenny10 = new JTextField();
		textFieldReloadPenny10.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReloadPenny10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldReloadPenny10.setColumns(10);
		panelCashReload.add(textFieldReloadPenny10);

		textFieldReloadPenny5 = new JTextField();
		textFieldReloadPenny5.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReloadPenny5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldReloadPenny5.setColumns(10);
		panelCashReload.add(textFieldReloadPenny5);

		textFieldReloadPenny2 = new JTextField();
		textFieldReloadPenny2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReloadPenny2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldReloadPenny2.setColumns(10);
		panelCashReload.add(textFieldReloadPenny2);

		textFieldReloadPenny1 = new JTextField();
		textFieldReloadPenny1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReloadPenny1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldReloadPenny1.setColumns(10);
		panelCashReload.add(textFieldReloadPenny1);

		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleCashReload(VendingMachine.this);
			}
		});
		btnReload.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReload.setBounds(210, 515, 115, 30);
		maintenancePanel.add(btnReload);
	}
	public JTextField getTextFieldInPound2() {
		return textFieldInPound2;
	}
	public JTextField getTextFieldInPenny1() {
		return textFieldInPenny1;
	}
	public JTextField getTextFieldInPenny10() {
		return textFieldInPenny10;
	}
	public JTextField getTextFieldInPenny2() {
		return textFieldInPenny2;
	}
	public JTextField getTextFieldInPound1() {
		return textFieldInPound1;
	}
	public JTextField getTextFieldInPenny20() {
		return textFieldInPenny20;
	}
	public JTextField getTextFieldInPenny50() {
		return textFieldInPenny50;
	}
	public JTextField getTextFieldInPenny5() {
		return textFieldInPenny5;
	}
	public JTextField getTextFieldReloadPound2() {
		return textFieldReloadPound2;
	}
	public JTextField getTextFieldReloadPenny10() {
		return textFieldReloadPenny10;
	}
	public JTextField getTextFieldReloadPenny20() {
		return textFieldReloadPenny20;
	}
	public JTextField getTextFieldReloadPenny5() {
		return textFieldReloadPenny5;
	}
	public JTextField getTextFieldReloadPound1() {
		return textFieldReloadPound1;
	}
	public JTextField getTextFieldReloadPenny50() {
		return textFieldReloadPenny50;
	}
	public JTextField getTextFieldReloadPenny1() {
		return textFieldReloadPenny1;
	}
	public JTextField getTextFieldReloadPenny2() {
		return textFieldReloadPenny2;
	}
	public JTextField getTextFieldSlotProductName() {
		return textFieldSlotProductName;
	}
	public JTextField getTextFieldSlotProductPounds() {
		return textFieldSlotProductPounds;
	}
	public JTextField getTextFieldSlotProductPennies() {
		return textFieldSlotProductPennies;
	}	
	public JButton getBtnProductAt(int index) {
		switch (index) {
		case 0:
			return btnProduct1;
		case 1:
			return btnProduct2;
		case 2:
			return btnProduct3;
		case 3:
			return btnProduct4;
		case 4:
			return btnProduct5;
		case 5:
			return btnProduct6;
		case 6:
			return btnProduct7;
		case 7:
			return btnProduct8;
		case 8:
			return btnProduct9;
		case 9:
			return btnProduct10;
		default:
			return null;
		}
	}
}
