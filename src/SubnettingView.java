import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class SubnettingView extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JFormattedTextField formattedTextField;
	private JFormattedTextField formattedTextField_1;
	private JFormattedTextField formattedTextField_2;
	private JButton btnCalculate;
	private JTextArea subnetArea;
	private JScrollPane scroll;
	private ArrayList<String> subnets= new ArrayList<String>();
	private JLabel lblNetmaskHere;
	private JLabel lblHostsnetHere;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubnettingView frame = new SubnettingView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public SubnettingView() throws ParseException {
		setTitle("Subnetting Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 294);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNetworkAddress = new JLabel("Network Address:");
		lblNetworkAddress.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		lblNetworkAddress.setBounds(12, 13, 177, 40);
		contentPane.add(lblNetworkAddress);
		
		JLabel lblSubnetMask = new JLabel("Subnet Mask:");
		lblSubnetMask.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		lblSubnetMask.setBounds(12, 49, 139, 40);
		contentPane.add(lblSubnetMask);
		
		JLabel lblNumberOfSubnets = new JLabel("Number of Subnets:");
		lblNumberOfSubnets.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		lblNumberOfSubnets.setBounds(12, 86, 198, 40);
		contentPane.add(lblNumberOfSubnets);
		
		JLabel lblNetmask = new JLabel("Netmask:");
		lblNetmask.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		lblNetmask.setBounds(12, 174, 98, 33);
		contentPane.add(lblNetmask);
		
		JLabel lblHostsnet = new JLabel("Hosts/Net: ");
		lblHostsnet.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		lblHostsnet.setBounds(12, 206, 122, 33);
		contentPane.add(lblHostsnet);
		
		lblNetmaskHere = new JLabel("netmask here");
		lblNetmaskHere.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblNetmaskHere.setBounds(221, 174, 159, 33);
		contentPane.add(lblNetmaskHere);
		
		lblHostsnetHere = new JLabel("hosts/net here");
		lblHostsnetHere.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblHostsnetHere.setBounds(221, 206, 159, 33);
		contentPane.add(lblHostsnetHere);

		JLabel lblSubnets = new JLabel("Subnets");
		lblSubnets.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		lblSubnets.setBounds(425, 13, 98, 33);
		contentPane.add(lblSubnets);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		formattedTextField.setBounds(221, 23, 159, 25);
		contentPane.add(formattedTextField);
		
		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		formattedTextField_1.setBounds(221, 59, 159, 25);
		contentPane.add(formattedTextField_1);
		
		formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		formattedTextField_2.setBounds(221, 96, 159, 25);
		contentPane.add(formattedTextField_2);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btnCalculate.setBounds(10, 139, 370, 33);
		contentPane.add(btnCalculate);
		btnCalculate.addActionListener(this);
		
		subnetArea = new JTextArea();
		subnetArea.setBounds(425, 49, 479, 185);  
		subnetArea.setLineWrap(true);
		subnetArea.setEditable(false);
		subnetArea.setVisible(true);
	    
		scroll = new JScrollPane (subnetArea);
		scroll.setBounds(425, 49, 479, 185);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCalculate){
			if(!formattedTextField.getText().equals("")&&!formattedTextField_1.getText().equals("")&&!formattedTextField_2.getText().equals("")){
				Subnetting s=new Subnetting(formattedTextField.getText(), formattedTextField_1.getText(), Integer.parseInt(formattedTextField_2.getText()));
				subnets=s.getSubnets();
				
				lblNetmaskHere.setText(s.getNewNetMask());
				lblHostsnetHere.setText(s.getNewHostsPerNet());
				
				subnetArea.removeAll();
				
				for(int i=0; i<subnets.size(); i++){
					subnetArea.append(subnets.get(i));
					subnetArea.append("\n");
				}
		}
		}
		
	}
}
