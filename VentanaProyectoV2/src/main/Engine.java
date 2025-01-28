package main;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * La clase Engine es una calculadora gráfica que soporta operaciones
 * aritméticas básicas en diferentes bases numéricas (binaria, octal, decimal,
 * hexadecimal). La interfaz permite realizar cálculos y convertir entre
 * diferentes bases.
 */
public class Engine extends JFrame implements ActionListener {
	// Paneles de la interfaz de usuario
	private JPanel contentPanel, panelSur, panelNorte, PanelCasio, PanelBase, displayPanel, buttonPanel;
	private JTextField display;

	// Botones para la calculadora
	private JButton n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, dividir, multiplicar, restar, suma, igual, borrarnumero,
			reset, info, owner, casio;
	private JButton B2, B8, B10, B16;
	private JButton A, B, C, D, E, F;

	// Enumeraciones para el tipo de botones y la base actual
	private enum ButtonType {
		REGULAR, OPERATOR, BASE, MARCA, HEXADECIMAL, INFO
	}

	private enum BaseActual {
		B2, B8, B10, B16
	}

	private BaseActual baseActual = BaseActual.B10;
	private JLabel infobase;

	private int num1, num2, result;
	private char operation;
	private String numero16, numero17;

	/**
	 * Constructor de la clase Engine. Inicializa la interfaz gráfica de la
	 * calculadora y configura los botones y sus eventos.
	 */
	public Engine() {
		this.setSettings();
		this.addActionEvent();
	}

	/**
	 * Agrega los ActionListener a todos los botones de la interfaz de usuario.
	 */
	public void addActionEvent() {
		JButton[] buttons = { this.n7, this.n8, this.n9, this.dividir, this.n4, this.n5, this.n6, this.multiplicar,
				this.n1, this.n2, this.n3, this.restar, this.reset, this.n0, this.igual, this.suma, this.borrarnumero,
				this.B2, this.B8, this.B10, this.B16, this.D, this.E, this.F, this.info, this.A, this.B, this.C,
				this.owner, this.casio };
		for (JButton button : buttons) {
			button.addActionListener(this);
		}
	}

	/**
	 * Configura los parámetros principales de la calculadora, como el tamaño, la
	 * disposición de los paneles y la configuración inicial de la interfaz.
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

	/**
	 * Realiza la operación aritmética básica basada en el operador definido.
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
	 * Establece las características visuales de los botones (colores, fuente, etc.)
	 * según el tipo de botón.
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
		default:
			break;
		}
		_button.setFont(new Font("Arial", Font.BOLD, 18));
		_button.setOpaque(true);
		_button.setBorderPainted(false);
	}

	/**
	 * Resetea el texto del display de la calculadora.
	 */
	public void resetDisplay() {
		display.setText("");
	}

	/**
	 * Maneja los eventos de acción cuando se presionan los botones de la
	 * calculadora. Realiza las operaciones aritméticas, cambios de base y muestra
	 * información relevante.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object operador = e.getSource();
		String inputText = e.getActionCommand();

		if (operador == reset) {
			resetDisplay();
			num1 = 0;
			num2 = 0;
			operation = '\0';
		} else if (operador == igual) {
			try {
				String[] partes = display.getText().split(" ");
				if (partes.length == 3) {
					// Convertir números según la base actual
					switch (baseActual) {
					case B2:
						num1 = Integer.parseInt(partes[0], 2);
						num2 = Integer.parseInt(partes[2], 2);
						break;
					case B8:
						num1 = Integer.parseInt(partes[0], 8);
						num2 = Integer.parseInt(partes[2], 8);
						break;
					case B16:
						num1 = Integer.parseInt(partes[0], 16);
						num2 = Integer.parseInt(partes[2], 16);
						break;
					case B10:
					default:
						num1 = Integer.parseInt(partes[0]);
						num2 = Integer.parseInt(partes[2]);
						break;
					}

					operation = partes[1].charAt(0);

					if (operation == '/' && num2 == 0) {
						display.setText("No se puede dividir entre 0");
					} else {
						operationBase(baseActual);
					}
				} else {
					display.setText("Error");
				}
			} catch (Exception ex) {
				display.setText("Error");
			}
		} else if (operador == B2 || operador == B8 || operador == B10 || operador == B16) {
			// Cambiar la base y convertir el número actual
			String resultado = cambioBase(operador);
			if (!resultado.isEmpty()) {
				display.setText(resultado);
			}
		} else if (operador == suma || operador == dividir || operador == multiplicar || operador == restar) {
			String currentText = display.getText();
			if (!currentText.isEmpty() && !currentText.endsWith(" ")) {
				if (operador == multiplicar) {
					display.setText(currentText + " * ");
				} else if (operador == dividir) {
					display.setText(currentText + " / ");
				} else {
					display.setText(currentText + " " + inputText + " ");
				}
			} else if (operador == restar && (currentText.isEmpty() || currentText.endsWith(" "))) {
				display.setText(currentText + "-");
			}
		} else if (operador == this.owner) {
			JFrame ownerWindow = new JFrame("Información del Creador");
			ownerWindow.setSize(400, 200);
			ownerWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			ownerWindow.setLocationRelativeTo(this);

			JPanel panel = new JPanel(new FlowLayout());
			JLabel label = new JLabel("Desarrollado por Adrián Escolar");

			label.setFont(new Font("Arial", Font.BOLD, 16));
			panel.add(label);

			ownerWindow.add(panel);
			ownerWindow.setVisible(true);
		} else if (operador == this.info) {
			JFrame infoWindow = new JFrame("Información del Programa");
			infoWindow.setSize(500, 300);
			infoWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			infoWindow.setLocationRelativeTo(this);

			JPanel panel = new JPanel(new BorderLayout());
			JLabel label = new JLabel("<html>Esta calculadora admite operaciones con números:<br>" + "- Positivos<br>"
					+ "- Negativos<br>" + "- Diferentes bases (B2, B8, B10, B16)<br>"
					+ "Además, soporta números hexadecimales.<br></html>");
			label.setFont(new Font("Arial", Font.BOLD, 16));
			panel.add(label, BorderLayout.CENTER);

			infoWindow.add(panel);
			infoWindow.setVisible(true);
		} else if (operador == this.casio) {
			try {
				Desktop.getDesktop().browse(new URI("https://www.casio.com"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			// Para números y otros caracteres
			String currentText = display.getText();
			boolean isValidInput = true;

			// Validar entrada según la base actual
			switch (baseActual) {
			case B2:
				isValidInput = inputText.matches("[0-1]");
				break;
			case B8:
				isValidInput = inputText.matches("[0-7]");
				break;
			case B16:
				isValidInput = inputText.matches("[0-9A-Fa-f]");
				break;
			case B10:
				isValidInput = inputText.matches("[0-9]");
				break;
			}

			if (isValidInput) {
				display.setText(currentText + inputText);
			}
		}
	}

	/**
	 * Actualiza la base de la calculadora y ajusta la interfaz para mostrar la base
	 * seleccionada.
	 * 
	 * @param base La base seleccionada (binaria, octal, decimal o hexadecimal).
	 */
	public void actualizarBase(BaseActual base) {
		this.display.setText("");

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

		// Resetear los valores de operación
		num1 = 0;
		num2 = 0;
		operation = '\0';
	}

	/**
	 * Metodo que cambia de base
	 * 
	 * @param operador
	 * @return
	 */
	public String cambioBase(Object operador) {
		if (display.getText().trim().isEmpty()) {
			actualizarBase(getBase(operador));
			return "";
		}

		int number = pasarDecimal(); // Convierte el valor actual a decimal
		String resultado = "";
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
		return resultado;
	}

	/**
	 * Método auxiliar para obtener la base del operador
	 * 
	 * @param operador
	 * @return
	 */
	public BaseActual getBase(Object operador) {
		if (operador == B2)
			return BaseActual.B2;
		if (operador == B8)
			return BaseActual.B8;
		if (operador == B16)
			return BaseActual.B16;
		return BaseActual.B10; // default
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
	 * Metodo que calcula en base Hexadecimal
	 */
	public void operationHexadecimal() {
		try {
			int num1 = (int) this.num1;
			int num2 = (int) this.num2;
			int resultado = 0;
			switch (this.operation) {
			case '+':
				resultado = num1 + num2;
				break;
			case '-':
				resultado = num1 - num2;
				break;
			case '*':
				resultado = num1 * num2;
				break;
			case '/':
				if (num2 != 0) {
					resultado = num1 / num2;
				} else {
					this.display.setText("Error: División por cero");
					return;
				}
				break;
			default:
				this.display.setText("Operación inválida");
				return;
			}

			// Mostrar el resultado en hexadecimal y convertir a mayúsculas
			this.display.setText(Integer.toHexString(resultado).toUpperCase());

		} catch (NumberFormatException e) {
			this.display.setText("Error en la conversión hexadecimal");
		}
	}

	/**
	 * Metodo que calcula en base Hexadecimal
	 */
	public void operationDecimal() {
		try {
			switch (this.operation) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case 'x':
				result = num1 * num2;
				break;
			case '/':
				if (num2 != 0) {
					result = num1 / num2;
				} else {
					this.display.setText("Error");
					return;
				}
				break;
			default:
				this.display.setText("Operación inválida");
				return;
			}

			this.display.setText(String.valueOf(result));
		} catch (NumberFormatException e) {
			this.display.setText("Error decimal");
		}
	}

	/**
	 * Metodo que calcula en base Octal
	 */
	public void operationOctal() {
		try {
			int num1 = (int) this.num1;
			int num2 = (int) this.num2;
			int resultado = 0;

			switch (this.operation) {
			case '+':
				resultado = num1 + num2;
				break;
			case '-':
				resultado = num1 - num2;
				break;
			case '*':
				resultado = num1 * num2;
				break;
			case '/':
				if (num2 != 0) {
					resultado = num1 / num2;
				} else {
					this.display.setText("Error: División por cero");
					return;
				}
				break;
			default:
				this.display.setText("Operación inválida");
				return;
			}

			// Mostrar el resultado en octal
			this.display.setText(Integer.toOctalString(resultado));

		} catch (NumberFormatException e) {
			this.display.setText("Error en la conversión octal");
		}
	}

	/**
	 * Metodo que calcula en base Binaria
	 */
	public void operationBinario() {
		try {
			int num1 = (int) this.num1;
			int num2 = (int) this.num2;
			int resultado = 0;
			switch (this.operation) {
			case '+':
				resultado = num1 + num2;
				break;
			case '-':
				resultado = num1 - num2;
				break;
			case '*':
				resultado = num1 * num2;
				break;
			case '/':
				if (num2 != 0) {
					resultado = num1 / num2;
				} else {
					this.display.setText("Error: División por cero");
					return;
				}
				break;
			default:
				this.display.setText("Operación inválida");
				return;
			}

			// Mostrar el resultado en binario
			this.display.setText(Integer.toBinaryString(resultado));

		} catch (NumberFormatException e) {
			this.display.setText("Error en la conversión binaria");
		}
	}

	/**
	 * Convierte el número actual del display a decimal según la base seleccionada.
	 * 
	 * @return Valor convertido a decimal, o -1 si hubo un error.
	 */
	public int pasarDecimal() {
		String text = display.getText().trim(); // Eliminar posibles espacios en blanco
		try {
			switch (this.baseActual) {
			case B2:
				return Integer.parseInt(text, 2); // Convierte desde binario
			case B8:
				return Integer.parseInt(text, 8); // Convierte desde octal
			case B16:
				return Integer.parseInt(text, 16); // Convierte desde hexadecimal
			case B10:
			default:
				return Integer.parseInt(text); // Convierte desde decimal
			}
		} catch (NumberFormatException e) {
			display.setText("Error al convertir");
		}
		return 0;
	}

}