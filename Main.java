import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.io.*;

public class Main implements ActionListener {
	private JFrame jFrame;
	private JPanel fromsPanel, submitPanel;
	private JLabel productInfoLabel;
	private JTextField productFeild, priceFeild;
	private JButton checkoutButton, exitButton, addButton;
	private List<String> productList, priceList;
	private FileWriter writer;

	@Override
  public void actionPerformed(ActionEvent e) {
		
		//setting up product add button actions
		if(e.getSource() == addButton) {
			String prod = productFeild.getText();
			productList.add(prod);

			String pric = priceFeild.getText();
			priceList.add(pric);
		}	

		//setting up checkout button actions
		else if(e.getSource() == checkoutButton) {
			try{
				writer = new FileWriter("output.txt");

				/*
				for(String p : productList){
					writer.write(p + System.lineSeparator());
				}
				*/
				
				//using iterator on list to loop thorough both list

				Iterator product = productList.iterator();
				Iterator price = priceList.iterator();

				while(product.hasNext() && price.hasNext()) {
					writer.write( (String)product.next() + " ");
					writer.write( (String)price.next() + System.lineSeparator());
      	}

				writer.flush();
				writer.close();
				
				//reset list
				productList.clear();
				priceList.clear();

			} catch (IOException exception) {
      	exception.printStackTrace();
    	}
		}
			
		//setting up exit button actions
		else if(e.getSource() == exitButton){
			System.exit(0);
		}
	}

	public void initialize() {
		//setting up gui layout
		jFrame = new JFrame("Check Out");
		jFrame.setLayout(new GridLayout(2,1));

		//adding border layout to panel for styling
		fromsPanel = new JPanel(new BorderLayout());
		fromsPanel.setBorder(BorderFactory.createEmptyBorder(30,30,20,20));

		submitPanel = new JPanel();
		submitPanel.setBorder(BorderFactory.createEmptyBorder(30,30,20,20));

		jFrame.setSize(400,250);
    jFrame.add(fromsPanel);
		jFrame.add(submitPanel);

		//setting up form
		productInfoLabel = new JLabel();
		productInfoLabel.setText("Products to be added");
		fromsPanel.add(productInfoLabel, BorderLayout.NORTH);

		productFeild = new JTextField(22);
		fromsPanel.add(productFeild, BorderLayout.WEST);

		priceFeild = new JTextField(8);
		fromsPanel.add(priceFeild, BorderLayout.EAST);

		addButton = new JButton("Add");
		fromsPanel.add(addButton, BorderLayout.SOUTH);

		//setting up submitting panel
		checkoutButton = new JButton("Check Out");
		submitPanel.add(checkoutButton);

		exitButton = new JButton("Exit");
		exitButton.setBackground(Color.RED);
		exitButton.setForeground(Color.WHITE);
		exitButton.setOpaque(true);
		submitPanel.add(exitButton);

		//adding action listeners to addButton
		addButton.addActionListener(this);
		checkoutButton.addActionListener(this);
		exitButton.addActionListener(this);

		//default settings
		jFrame.setVisible(true);
		productList = new ArrayList<>();
		priceList = new ArrayList<>();
	}

	public static void main(String [] args) {
      Main jf = new Main();
      jf.initialize();
  }
}