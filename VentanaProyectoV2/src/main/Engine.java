package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Engine extends JFrame implements ActionListener {
	private JPanel contentPanel, panelSur, panelNorte, PanelCasio, PanelBase, displayPanel, buttonPanel;
	private JTextField display;
	private JButton n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, dividir, multiplicar, restar, suma, igual, borrarnumero,
			reset, info, owner, casio;
	private JButton B2, B8, B10, B16;
	private JButton A, B, C, D, E, F;
	private JLabel baseLabel;
	private BaseActual baseActual = BaseActual.B10;

	private enum ButtonType {
		REGULAR, OPERATOR, BASE, MARCA
	}

	private enum BaseActual {
		B2, B8, B10, B16

	}

	private double num1, num2, result;
	private char operation;

	public Engine() {
		this.setSettings();
		this.addActionEvent();
	}

	/**
	 * Metodo setSettings
	 */
	public void setSettings() {
		this.setSize(400, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.contentPanel = new JPanel(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		this.panelNorte = new JPanel(new BorderLayout());
		this.panelSur = new JPanel(new BorderLayout());
		this.displayPanel = new JPanel(new FlowLayout());
		this.PanelCasio = new JPanel(new FlowLayout());
		this.PanelBase = new JPanel(new FlowLayout());
		this.buttonPanel = new JPanel(new GridLayout(8, 4, 5, 5));
		this.display = new JTextField(20);
		this.display.setEditable(false);
		this.display.setFont(new Font("Arial", Font.PLAIN, 18));
		this.display.setHorizontalAlignment(JTextField.RIGHT);
		this.displayPanel.add(this.display);

		JLabel baseLabel = new JLabel("Base : decimal");
		baseLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		this.PanelBase.add(baseLabel);

		this.n0 = new JButton("0");
		this.n1 = new JButton("1");
		this.n2 = new JButton("2");
		this.n3 = new JButton("3");
		this.n4 = new JButton("4");
		this.n5 = new JButton("5");
		this.n6 = new JButton("6");
		this.n7 = new JButton("7");
		this.n8 = new JButton("8");
		this.n9 = new JButton("9");
		this.dividir = new JButton("/");
		this.multiplicar = new JButton("x");
		this.restar = new JButton("-");
		this.suma = new JButton("+");
		this.igual = new JButton("=");
		this.reset = new JButton("C");
		this.borrarnumero = new JButton("←");
		this.B2 = new JButton("B2");
		this.B8 = new JButton("B8");
		this.B10 = new JButton("B10");
		this.B16 = new JButton("B16");
		this.A = new JButton("A");
		this.B = new JButton("B");
		this.C = new JButton("C");
		this.D = new JButton("D");
		this.E = new JButton("E");
		this.F = new JButton("F");
		this.info = new JButton("Info");
		this.owner = new JButton("Owner");
		this.casio = new JButton("CASIO");

		JButton[] buttons = { this.B2, this.B8, this.B10, this.B16, this.D, this.E, this.F, this.info, this.A, this.B,
				this.C, this.owner, this.n7, this.n8, this.n9, this.dividir, this.n4, this.n5, this.n6,
				this.multiplicar, this.n1, this.n2, this.n3, this.suma, this.reset, this.n0, this.borrarnumero,
				this.restar, this.igual, this.casio };

		for (JButton button : buttons) {
			if (button == this.dividir || button == this.multiplicar || button == this.restar || button == this.suma
					|| button == this.igual || button == this.borrarnumero || button == this.reset) {
				this.setFeaturesButton(button, ButtonType.OPERATOR);

			} else if (button == this.B2 || button == this.B8 || button == this.B10 || button == this.B16) {
				this.setFeaturesButton(button, ButtonType.BASE);

			} else {
				this.setFeaturesButton(button, ButtonType.REGULAR);
			}
			if (button != this.casio)
				this.buttonPanel.add(button);
			else {
				this.setFeaturesButton(button, ButtonType.MARCA);
				this.PanelCasio.add(button);
			}
		}
		this.panelNorte.add(this.PanelBase, BorderLayout.WEST);
		this.panelNorte.add(this.PanelCasio, BorderLayout.EAST);
		this.panelSur.add(this.displayPanel, BorderLayout.NORTH);
		this.panelSur.add(this.buttonPanel, BorderLayout.CENTER);
		this.contentPanel.add(this.panelNorte, BorderLayout.NORTH);
		this.contentPanel.add(this.panelSur, BorderLayout.SOUTH);
		this.add(this.contentPanel);
		this.setVisible(true);
	}

	/**
	 * Metodo operation
	 */
	public void operation() {
		switch (this.operation) {
		case '+':
			this.result = this.num1 + this.num2;
			break;
		case '-':
			this.result = this.num1 - this.num2;
			break;
		case 'x':
			this.result = this.num1 * this.num2;
			break;
		case '/':
			this.result = this.num1 / this.num2;
			break;

		}
	}

	/**
	 * Metodo addActionEvent
	 */
	public void addActionEvent() {
		JButton[] buttons = { this.n7, this.n8, this.n9, this.dividir, this.n4, this.n5, this.n6, this.multiplicar,
				this.n1, this.n2, this.n3, this.restar, this.reset, this.n0, this.igual, this.suma, this.borrarnumero,
				this.B2, this.B8, this.B10, this.B16, this.D, this.E, this.F, this.info, this.A, this.B, this.C,
				this.owner };
		for (JButton button : buttons) {
			button.addActionListener(this);
		}
	}

	/**
	 * Metodo setFeaturesButton
	 * 
	 * @param _button
	 * @param _type
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
		switch (_type) {
		case REGULAR:
			_button.setBackground(Color.LIGHT_GRAY);
			_button.setForeground(Color.BLACK);
			break;
		case OPERATOR:
			_button.setBackground(Color.GRAY);
			_button.setForeground(Color.WHITE);
			break;
		case BASE:
			_button.setBackground(Color.PINK);
			_button.setForeground(Color.WHITE);
			break;
		}
		_button.setFont(new Font("Arial", Font.BOLD, 16));
		_button.setOpaque(true);
		_button.setBorderPainted(false);
	}

	/*
	 * Metodo que reseta el display
	 */
	private void resetDisplay() {

		display.setText("");

	}

	/**
	 * Metodo que muestra los numeros y las operaciones en el display
	 */
	public void actionPerformed(ActionEvent e) {
		Object operador = e.getSource();
		String inputText = e.getActionCommand();

		if (operador == reset) {
			resetDisplay();
		} else if (operador == igual) {
			try {
				String[] partes = display.getText().split(" ");
				num1 = Integer.parseInt(partes[0]);
				operation = partes[1].charAt(0);
				num2 = Integer.parseInt(partes[2]);

				if (operation == '÷' && num2 == 0) {
					display.setText("No se puede dividir entre 0");
				} else {
					operation();
					display.setText(String.valueOf(result));
				}
			} catch (Exception ex) {
				display.setText("Error");
			}
		} else if (operador == suma || operador == dividir || operador == multiplicar) {
			display.setText(display.getText() + " " + inputText + " ");
		} else if (operador == restar) {
			String currentText = display.getText();
			if (currentText.isEmpty() || currentText.endsWith(" ")) {
				display.setText(currentText + "-");
			} else {
				display.setText(currentText + " " + inputText + " ");
			}
		} else if (operador == borrarnumero) {
			String text = display.getText();
			if (text.length() > 0) {
				display.setText(text.substring(0, text.length() - 1));// Si hay un caracter en el display lo borra
			}
		} else {
			display.setText(display.getText() + inputText); // Agrega el numero presionado al display
		}
	}

}