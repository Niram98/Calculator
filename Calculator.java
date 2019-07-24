import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator extends JFrame implements ActionListener {
	
	JLabel rezultat;
	JButton dugmici[][] = new JButton[4][3];
	JButton operacije[][] = new JButton[4][1];
	
	int leviOperand = 0;
	String izabraniOperator = "+";
	
	
	public Calculator(String name) 
	{
		super(name);
		setBounds(300, 300, 400, 400);
		setMinimumSize(new Dimension(400, 400));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		setRezultat();
		setDugmici();
		setOperacije();
	}
	
	void setRezultat()
	{
		JPanel p = new JPanel();
		p.setBackground(Color.ORANGE);
		rezultat = new JLabel("0");
		rezultat.setFont(new Font("Arial", 50, 40));
		rezultat.setForeground(Color.WHITE);
		p.add(rezultat);
		
		getContentPane().add(p, BorderLayout.NORTH);
		pack();
	}
	

	void setDugmici()
	{
		JPanel panelCentar = new JPanel();
		panelCentar.setBackground(Color.ORANGE);
		panelCentar.setLayout(new GridLayout(4, 3, 5, 5));
		
		int k = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				dugmici[i][j] = new JButton(k + "");
				dugmici[i][j].setSize(new Dimension(40, 40));
				dugmici[i][j].setBackground(Color.LIGHT_GRAY);
				dugmici[i][j].setFont(new Font("Arial",40, 40));
				dugmici[i][j].addActionListener(this);
				panelCentar.add(dugmici[i][j]);
				k++;
			}
		}
		
		dugmici[3][0] = new JButton("CE");
		dugmici[3][0].setSize(new Dimension(40, 40));
		dugmici[3][0].setBackground(Color.LIGHT_GRAY);
		dugmici[3][0].setFont(new Font("Arial",40, 40));
		dugmici[3][0].addActionListener(this);
		panelCentar.add(dugmici[3][0]);
		
		dugmici[3][1] = new JButton("0");
		dugmici[3][1].setSize(new Dimension(40, 40));
		dugmici[3][1].setBackground(Color.LIGHT_GRAY);
		dugmici[3][1].setFont(new Font("Arial",40, 40));
		dugmici[3][1].addActionListener(this);
		panelCentar.add(dugmici[3][1]);
		
		
		dugmici[3][2] = new JButton("=");
		dugmici[3][2].setSize(new Dimension(40, 40));
		dugmici[3][2].setBackground(Color.LIGHT_GRAY);
		dugmici[3][2].setFont(new Font("Arial",40, 40));
		dugmici[3][2].addActionListener(this);
		panelCentar.add(dugmici[3][2]);
		
		getContentPane().add(panelCentar, BorderLayout.CENTER);
		pack();
		
	}
	
	void setOperacije()
	{
		JPanel panelIstok = new JPanel();
		panelIstok.setBackground(Color.ORANGE);
		panelIstok.setLayout(new GridLayout(4, 1, 5, 5));
		
		String ops[] = new String[] {"+", "-", "*", "/"};
		for (int i = 0; i < ops.length; i++) {
			operacije[i][0] = new JButton(ops[i]);
			operacije[i][0].setSize(new Dimension(100, 40));
			operacije[i][0].setMaximumSize(new Dimension(100, 40));
			operacije[i][0].setBackground(Color.LIGHT_GRAY);
			operacije[i][0].setFont(new Font("Arial",40, 40));
			operacije[i][0].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton button = (JButton)e.getSource();
					leviOperand = Integer.parseInt(rezultat.getText());
					izabraniOperator = button.getText();
					System.out.println("Levi operand je : " + leviOperand 
							+ " operacija je " + izabraniOperator);
					rezultat.setText("0");
					pack();
					
				}
			});
			panelIstok.add(operacije[i][0]);
		}
		getContentPane().add(panelIstok, BorderLayout.EAST);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() instanceof JButton)
		{
			JButton button = (JButton)e.getSource();
			System.out.println("Pritisnuto dugme " + button.getText());
			
			if(button.getText() == "CE")
			{
				rezultat.setText("0");
			}
			else if(button.getText() == "=")
			{
				int desniOperand = Integer.parseInt(rezultat.getText());
				double rez;
				if(izabraniOperator == "+")
					rez = leviOperand + desniOperand;
				else if (izabraniOperator == "-")
					rez = leviOperand - desniOperand;
				else if (izabraniOperator == "*")
					rez = leviOperand * desniOperand;
				else
					rez = leviOperand * 1.0 / desniOperand;
				rezultat.setText(rez + "");
				pack();
			}else
			{
				if(rezultat.getText() == "0")
					rezultat.setText(button.getText());
				else
					rezultat.setText(rezultat.getText() + button.getText());
							
			}
			pack();	
		}
		
	}

}
