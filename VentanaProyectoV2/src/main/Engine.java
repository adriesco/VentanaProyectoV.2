package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Engine extends JFrame implements ActionListener {
	private JPanel contentPanel, panelSur, panelNorte, PanelCasio, PanelBase, displayPanel, buttonPanel;
	private JTextField display;
	private JButton n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, dividir, multiplicar, restar, suma, igual, borrarnumero,reset, info, owner, casio;
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
				this.owner };
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
						display.setText(String.valueOf((int) result));
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

			// Cambio de base
		} else if (operador == B2 || operador == B8 || operador == B10 || operador == B16) {
			display.setText(cambioBase(operador));
			
		} else {
			display.setText(display.getText() + inputText); // Agrega el número presionado al display
		}
	}
	
	
	/**
	 * Metodo que cambia de base
	 * @param base
	 */
	public void actualizarBase(BaseActual base) {
		this.baseActual = base;
		this.PanelBase.removeAll();

		switch (base) {
		case B2:
			infobase = new JLabel("Estas en base binaria");
			break;
		case B8:
			infobase = new JLabel("Estas en base octal");
			break;
		case B10:
			infobase = new JLabel("Estas en base decimal");
			break;
		case B16:
			infobase = new JLabel("Estas en base hexadecimal");
			break;
		default:
			System.out.println("Error en la base");
		}

		infobase.setFont(new Font("Arial", Font.BOLD, 14));
		this.PanelBase.add(infobase);
		this.PanelBase.revalidate();//Para que cambie de base
		this.PanelBase.repaint();
	}
	
	
	/**
	 * Metodo que dependiendo de la base , pasa el numero a su correspondiente base
	 * @param operador
	 * @return
	 */
	public String cambioBase(Object operador) {
		int number = pasarDecimal();
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
			this.display.setText("Base no encotrada");
		}
		return "";
	}

	public void operationBase(BaseActual base) {
		switch (base) {
		case B2:
			this.operationBinario();
			break;
		case B8:
			this.operationOctal();
			break;
		case B10:
			this.operationDecimal();
			break;
		case B16:
			this.operationHexadecimal();
			break;
		}
	}

	// Convierte el número del display al formato hexadecimal
	private void operationHexadecimal() {
	    try {
	        int number = Integer.parseInt(display.getText());
	        display.setText(Integer.toHexString(number).toUpperCase());
	    } catch (NumberFormatException e) {
	        display.setText("Error en hexadecimal");
	    }
	}

	// Convierte el número del display al formato decimal
	private void operationDecimal() {
	    try {
	        int number = pasarDecimal();
	        display.setText(Integer.toString(number));
	    } catch (NumberFormatException e) {
	        display.setText("Error en decimal");
	    }
	}

	// Convierte el número del display al formato octal
	private void operationOctal() {
	    try {
	        int number = Integer.parseInt(display.getText());
	        display.setText(Integer.toOctalString(number));
	    } catch (NumberFormatException e) {
	        display.setText("Error en octal");
	    }
	}

	// Convierte el número del display al formato binario
	private void operationBinario() {
	    try {
	        int number = Integer.parseInt(display.getText());
	        display.setText(Integer.toBinaryString(number));
	    } catch (NumberFormatException e) {
	        display.setText("Error en binario");
	    }
	}

	// Convierte el número actual del display a decimal según la base seleccionada
	private int pasarDecimal() {
	    try {
	        String text = display.getText();
	        switch (baseActual) {
	            case B2:
	                return Integer.parseInt(text, 2); 
	            case B8:
	                return Integer.parseInt(text, 8); 
	            case B16:
	                return Integer.parseInt(text, 16); 
	            default:
	                return Integer.parseInt(text); 
	        }
	    } catch (NumberFormatException e) {
	        display.setText("Error al convertir");
	        return 0;
	    }
	}


}