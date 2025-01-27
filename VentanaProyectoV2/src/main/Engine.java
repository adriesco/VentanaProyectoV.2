package main;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Engine extends JFrame implements ActionListener {
	private JPanel contentPanel, panelSur, panelNorte, PanelCasio, PanelBase, displayPanel, buttonPanel;
	private JTextField display;
	private JButton n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, dividir, multiplicar, restar, suma, igual, borrarnumero,
			reset, info, owner, casio;
	private JButton B2, B8, B10, B16;
	private JButton A, B, C, D, E, F;

	private enum ButtonType {
		REGULAR, OPERATOR, BASE, MARCA, HEXADECIMAL, INFO
	}

	private enum BaseActual {
		B2, B8, B10, B16

	}

	private BaseActual baseActual = BaseActual.B10;
	private JLabel infobase;

	private double num1, num2, result;
	private char operation;

	/*
	 * Constructora de la clase
	 */
	public Engine() {
		this.setSettings();
		this.addActionEvent();
	}

	/*
	 * Metodo que agrega un AcctionListener a los botones
	 */
	public void addActionEvent() {
		JButton[] buttons = { this.n7, this.n8, this.n9, this.dividir, this.n4, this.n5, this.n6, this.multiplicar,
				this.n1, this.n2, this.n3, this.restar, this.reset, this.n0, this.igual, this.suma, this.borrarnumero,
				this.B2, this.B8, this.B10, this.B16, this.D, this.E, this.F, this.info, this.A, this.B, this.C,
				this.owner , this.casio };
		for (JButton button : buttons) {
			button.addActionListener(this);
		}
	}

	/*
	 * Metodo para configurar los parametros principales de la calculadora
	 */
	public void setSettings() {
		this.setSize(400, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.contentPanel = new JPanel(new BorderLayout());
		this.contentPanel.setBackground(new Color(173, 216, 230));
		this.contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		this.panelNorte = new JPanel(new BorderLayout());
		this.panelNorte.setBackground(new Color(173, 216, 230));

		this.panelSur = new JPanel(new BorderLayout());

		this.displayPanel = new JPanel(new FlowLayout());

		this.PanelCasio = new JPanel(new FlowLayout());
		this.PanelCasio.setBackground(new Color(173, 216, 230));

		this.PanelBase = new JPanel(new FlowLayout());
		this.PanelBase.setBackground(new Color(173, 216, 230));

		this.buttonPanel = new JPanel(new GridLayout(8, 4, 5, 5));
		this.buttonPanel.setBackground(new Color(173, 216, 230));
		this.display = new JTextField(20);
		this.display.setEditable(false);
		this.display.setFont(new Font("Arial", Font.BOLD, 18));
		this.display.setHorizontalAlignment(JTextField.LEFT);
		this.displayPanel.setBackground(new Color(173, 216, 230));
		this.display.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 235), 1));
		this.displayPanel.add(this.display);

		JLabel baseLabel = new JLabel("Estas en Base:Decimal");
		baseLabel.setFont(new Font("Arial", Font.BOLD, 14));
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
		this.owner = new JButton("Own");
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

			} else if (button == this.A || button == this.B || button == this.C || button == this.D || button == this.E
					|| button == this.F) {
				this.setFeaturesButton(button, ButtonType.HEXADECIMAL);
			} else if (button == this.owner || button == this.info) {
				this.setFeaturesButton(button, ButtonType.INFO);
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

	/*
	 * Metodo que independientemente del operador te realiza una operacion u otra
	 */
	public void operation() {
		switch (this.operation) {
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num1 - num2;
			break;
		case '*':
			result = num1 * num2;
			break;
		case '/':
			result = num1 / num2;
			break;
		default:
			throw new UnsupportedOperationException("Operación no soportada");
		}
	}

	/**
	 * Método para establecer las características visuales de los botones (colores,
	 * fuente, etc.) según el tipo de botón (Regular, Operador, Base, Hexadecimal,
	 * Info).
	 * 
	 * @param _button El botón al que se le configurarán las características.
	 * @param _type   El tipo de botón, utilizado para definir el estilo visual.
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
		switch (_type) {
		case REGULAR:
			_button.setBackground(new Color(173, 216, 230));
			_button.setForeground(Color.WHITE);
			break;
		case OPERATOR:
			_button.setBackground(new Color(70, 130, 180));
			_button.setForeground(Color.WHITE);
			break;
		case BASE:
			_button.setBackground(new Color(144, 238, 144));
			_button.setForeground(Color.BLACK);
			break;
		case HEXADECIMAL:
			_button.setBackground(new Color(30, 144, 255));
			_button.setForeground(Color.WHITE);
			break;
		case INFO:
			_button.setBackground(new Color(138, 43, 226));
			_button.setForeground(Color.WHITE);
			break;
		}
		_button.setFont(new Font("Arial", Font.BOLD, 18));
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
	 * Método que maneja las acciones cuando un botón es presionado, incluyendo la
	 * entrada de números, la ejecución de operaciones y la apertura de ventanas de
	 * información.
	 * 
	 * @param e El evento de acción asociado al botón presionado.
	 */
	public void actionPerformed(ActionEvent e) {
		Object operador = e.getSource();
		String inputText = e.getActionCommand();
		String currentText = display.getText();

		if (operador == reset) {
			resetDisplay();
			num1 = 0;
			num2 = 0;
			operation = '\0';

		} else if (operador == igual) {
			try {
				String[] partes = currentText.split(" ");
				if (partes.length == 3) {
					num1 = Integer.parseInt(partes[0]);
					operation = partes[1].charAt(0);
					num2 = Integer.parseInt(partes[2]);

					if (operation == '/' && num2 == 0) {
						display.setText("No se puede dividir entre 0");
					} else {
						operation();
						num1 = result;
						num2 = 0;
						operation = '\0';
					}

				} else {
					display.setText("Error");
				}
			} catch (Exception ex) {
				display.setText("Error");
			}
		} else if (operador == suma || operador == dividir || operador == multiplicar || operador == restar) {
			if (!currentText.isEmpty() && !currentText.endsWith(" ")) {
				if (operador == multiplicar) {
					display.setText(currentText + " * ");
				} else if (operador == dividir) {
					display.setText(currentText + " / ");
				} else {
					display.setText(currentText + " " + inputText + " ");
				}
			} else if (operador == restar && (currentText.isEmpty() || currentText.endsWith(" "))) {
				display.setText(currentText + "-"); // Permite usar un número negativo
			}
		} else if (operador == owner) {
			JFrame ownerWindow = new JFrame("Información del Creador");
			ownerWindow.setSize(400, 200);
			ownerWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			ownerWindow.setLocationRelativeTo(this);

			JPanel panel = new JPanel(new FlowLayout());
			JLabel label = new JLabel("<html>Desarrollado por Adrián Escolar<br>Estudiante de 2 DAM</html>");
			label.setFont(new Font("Arial", Font.BOLD, 16));
			panel.add(label);

			ownerWindow.add(panel);
			ownerWindow.setVisible(true);
		} else if (operador == info) {
			JFrame infoWindow = new JFrame("Información de la Calculadora");
			infoWindow.setSize(500, 300);
			infoWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			infoWindow.setLocationRelativeTo(this);

			JPanel panel = new JPanel(new BorderLayout());
			JLabel label = new JLabel("<html>Esta calculadora permite operaciones con números:<br>" + "- Positivos<br>"
					+ "- Negativos<br>" + "- Diferentes bases (B2, B8, B10, B16)<br>"
					+ "Además, soporta números hexadecimales.<br></html>");
			label.setFont(new Font("Arial", Font.BOLD, 16));
			panel.add(label, BorderLayout.CENTER);

			infoWindow.add(panel);
			infoWindow.setVisible(true);

		} else if (operador == B2 || operador == B8 || operador == B10 || operador == B16) {
			// Cambio de base
			display.setText(cambioBase(operador));

		}else if (operador == casio) {
		    try {
		        Desktop.getDesktop().browse(new URI("https://www.casio.com"));
		    } catch (Exception ex) {
		        ex.printStackTrace(); 
		    }
		} else {
			display.setText(display.getText() + inputText); // Agrega el número presionado al display
		}
	}

	/**
	 * Método que cambia de base.
	 * 
	 * @param base Nueva base a establecer.
	 */
	public void actualizarBase(BaseActual base) {
		this.baseActual = base;
		this.PanelBase.removeAll(); // Limpia el panel base

		String mensajeBase;
		switch (base) {
		case B2:
			mensajeBase = "Estás en base binaria";
			break;
		case B8:
			mensajeBase = "Estás en base octal";
			break;
		case B10:
			mensajeBase = "Estás en base decimal";
			break;
		case B16:
			mensajeBase = "Estás en base hexadecimal";
			break;
		default:
			mensajeBase = "Error en la base";
		}

		infobase = new JLabel(mensajeBase);
		infobase.setFont(new Font("Arial", Font.BOLD, 14));
		this.PanelBase.add(infobase);

		this.PanelBase.revalidate(); // Actualiza el panel
		this.PanelBase.repaint();
	}

	/**
	 * Método que convierte el número actual del display a la base seleccionada.
	 * 
	 * @param operador Base a la que se desea convertir.
	 * @return Número convertido como cadena.
	 */
	public String cambioBase(Object operador) {
		int number = pasarDecimal(); // Convierte el valor actual a decimal
		try {
			if (operador == B2) {
				actualizarBase(BaseActual.B2);
				return Integer.toBinaryString(number);
			} else if (operador == B8) {
				actualizarBase(BaseActual.B8);
				return Integer.toOctalString(number);
			} else if (operador == B10) {
				actualizarBase(BaseActual.B10);
				return Integer.toString(number);
			} else if (operador == B16) {
				actualizarBase(BaseActual.B16);
				return Integer.toHexString(number).toUpperCase();
			}
		} catch (NumberFormatException e) {
			this.display.setText("Base no encontrada");
		}
		return "";
	}

	/**
	 * Método que realiza operaciones según la base actual.
	 * 
	 * @param base Base en la que se realizará la operación.
	 */
	public void operationBase(BaseActual base) {
		switch (base) {
		case B2:
			operationBinario();
			break;
		case B8:
			operationOctal();
			break;
		case B10:
			operationDecimal();
			break;
		case B16:
			operationHexadecimal();
			break;
		}
	}

	/**
	 * Realiza la operación en formato hexadecimal.
	 */
	public void operationHexadecimal() {
		try {
			String num16 = null;
			// Convertir los números binarios a enteros decimales
			int num1 = Integer.parseInt(num16,8);
			int num2 = Integer.parseInt(num16,8);
			int resultado = 0;

			// Realizar la operación según el operador
			switch (this.operation) {
			case '+':
				resultado = num1 + num2;
				break;
			case '-':
				resultado = num1 - num2;
				break;
			case 'x':
				resultado = num1 * num2;
				break;
			case '/':
				if (num2 != 0) {
					resultado = num1 / num2;
				} else {
					this.display.setText("Error");
					return;
				}
				break;
			default:
				this.display.setText("Operación inválida");
				return;
			}

			// Mostrar el resultado en binario
			this.result = Integer.parseInt(Integer.toHexString(resultado), 2);
			this.display.setText(Integer.toHexString(resultado));

		} catch (NumberFormatException e) {
			this.display.setText("Error binario");
		}
	}

	/**
	 * Realiza la operación en formato decimal.
	 */
	public void operationDecimal() {
		try {
			operation();
		} catch (NumberFormatException e) {
			this.display.setText("Error decimal");
		}
	}

	/**
	 * Realiza la operación en formato octal.
	 */
	public void operationOctal() {
		try {
			// Convertir los números binarios a enteros decimales
			int num1 = Integer.parseInt(Integer.toString((int) this.num1), 8);
			int num2 = Integer.parseInt(Integer.toString((int) this.num2), 8);
			int resultado = 0;

			// Realizar la operación según el operador
			switch (this.operation) {
			case '+':
				resultado = num1 + num2;
				break;
			case '-':
				resultado = num1 - num2;
				break;
			case 'x':
				resultado = num1 * num2;
				break;
			case '/':
				if (num2 != 0) {
					resultado = num1 / num2;
				} else {
					this.display.setText("Error");
					return;
				}
				break;
			default:
				this.display.setText("Operación inválida");
				return;
			}

			// Mostrar el resultado en binario
			this.result = Integer.parseInt(Integer.toOctalString(resultado), 2);
			this.display.setText(Integer.toOctalString(resultado));

		} catch (NumberFormatException e) {
			this.display.setText("Error binario");
		}
	}

	/**
	 * Realiza la operación en formato binario.
	 */
	public void operationBinario() {
		try {
			// Convertir los números binarios a enteros decimales
			int num1 = Integer.parseInt(Integer.toString((int) this.num1), 2);
			int num2 = Integer.parseInt(Integer.toString((int) this.num2), 2);
			int resultado = 0;

			// Realizar la operación según el operador
			switch (this.operation) {
			case '+':
				resultado = num1 + num2;
				break;
			case '-':
				resultado = num1 - num2;
				break;
			case 'x':
				resultado = num1 * num2;
				break;
			case '/':
				if (num2 != 0) {
					resultado = num1 / num2;
				} else {
					this.display.setText("Error");
					return;
				}
				break;
			default:
				this.display.setText("Operación inválida");
				return;
			}

			// Mostrar el resultado en binario
			this.result = Integer.parseInt(Integer.toBinaryString(resultado), 2);
			this.display.setText(Integer.toBinaryString(resultado));

		} catch (NumberFormatException e) {
			this.display.setText("Error binario");
		}
	}

	/**
	 * Convierte el número actual del display a decimal según la base seleccionada.
	 * 
	 * @return Valor convertido a decimal.
	 */
	public int pasarDecimal() {
		String text = display.getText();
		try {
			switch (baseActual) {
			case B2:
				return Integer.parseInt(text, 2); // Convierte desde binario
			case B8:
				return Integer.parseInt(text, 8); // Convierte desde octal
			case B16:
				return Integer.parseInt(text, 16); // Convierte desde hexadecimal
			default:
				return Integer.parseInt(text); // Base decimal
			}
		} catch (NumberFormatException e) {
			display.setText("Error al convertir");

		}
		return 0;
	}

}