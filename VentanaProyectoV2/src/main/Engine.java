package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Engine extends JFrame implements ActionListener {
	private JPanel contentPanel, displayPanel, buttonPanel;
	private JTextField display;
	private JButton n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, dividir, multiplicar, restar, suma, igual, reset,
			borrarnumero;

	private enum ButtonType {
		REGULAR, OPERATOR
	}

	private int num1, num2, result;
	private char operation;

	/**
	 * Constructora
	 */
	public Engine() {
		setSettings();
		addActionEvent();
	}

	/**
	 * Metodo que añade un actionListener a todos los componentes menos al frame.
	 */
	public void addActionEvent() {
		JButton[] buttons = { n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, dividir, multiplicar, restar, suma, igual, reset,
				borrarnumero };
		for (JButton button : buttons) {
			button.addActionListener(this);
		}
	}

	/**
	 * Metodo donde se crea toda la ventana
	 */
	public void setSettings() {
		this.setTitle("Mi Calculadora");
		this.setSize(350, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		contentPanel = new JPanel(new BorderLayout());
		displayPanel = new JPanel(new BorderLayout());
		displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		// Panel para distribuir los botones en 5 filas y 4 columnas
		buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
		buttonPanel.setBackground(new Color(144, 202, 249));
		displayPanel.setBackground(new Color(144, 202, 249));

		// Creacion del display , un borde de color gris y que el texto salga en a
		// izquierda .
		display = new JTextField(20);
		display.setEditable(false);
		display.setFont(new Font("Verdana", Font.BOLD, 18));
		display.setHorizontalAlignment(JTextField.LEFT);
		display.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

		displayPanel.add(display);
		// poner this en todos
		n0 = new JButton("0");
		n1 = new JButton("1");
		n2 = new JButton("2");
		n3 = new JButton("3");
		n4 = new JButton("4");
		n5 = new JButton("5");
		n6 = new JButton("6");
		n7 = new JButton("7");
		n8 = new JButton("8");
		n9 = new JButton("9");
		dividir = new JButton("÷");
		multiplicar = new JButton("×");
		restar = new JButton("-");
		suma = new JButton("+");
		igual = new JButton("=");
		reset = new JButton("AC");
		borrarnumero = new JButton("←");

		JButton[] buttons = { n7, n8, n9, dividir, n4, n5, n6, multiplicar, n1, n2, n3, restar, reset, n0, igual, suma,
				borrarnumero };

		// Añadir los botones al panel y clasificarlos.
		for (JButton button : buttons) {
			if (button == dividir || button == multiplicar || button == restar || button == suma || button == igual
					|| button == borrarnumero) {
				setFeaturesButton(button, ButtonType.OPERATOR);
			} else {
				setFeaturesButton(button, ButtonType.REGULAR);
			}
			buttonPanel.add(button);
		}

		contentPanel.add(displayPanel, BorderLayout.NORTH);
		contentPanel.add(buttonPanel, BorderLayout.CENTER);
		this.add(contentPanel);
		this.setVisible(true);
	}

	/**
	 * Metodo que dependiendo del operador realiza una operacion o otra
	 */
	public void operation() {
		switch (operation) {
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num1 - num2;
			break;
		case '×':
			result = num1 * num2;
			break;
		case '÷':
			result = num1 / num2;
			break;
		}
	}

	/**
	 * Metodo que aplica colores a los botones
	 * 
	 * @param _button
	 * @param _type
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
		switch (_type) {
		case REGULAR:
			_button.setFont(new Font("Arial", Font.PLAIN, 20));
			_button.setFocusPainted(false);
			_button.setBackground(Color.LIGHT_GRAY);
			break;
		case OPERATOR:
			_button.setFont(new Font("Arial", Font.BOLD, 20));
			_button.setFocusPainted(false);
			_button.setBackground(Color.BLACK);
			_button.setForeground(Color.WHITE);
			break;
		}
	}

	/**
	 * Metodo que limpia el display
	 */
	public void resetDisplay() {
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