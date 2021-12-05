import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TalletusIkkuna extends JDialog {
	
	private JLabel label_tilinomistaja_noedit;
	private JLabel label_saldo_t;
	private JButton nappi_teetalletus;
	public JLabel label_tilinomistaja_t;
	public JLabel label_tilinsaldo_t;
	private JLabel label_talletuksenm‰‰r‰;
	private JTextField talletus;


	/**
	 * Create the dialog.
	 */
	public TalletusIkkuna() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				TilinPaasivu.label_tilinsaldo_p.setText(label_tilinsaldo_t.getText());
			}
		});
		setTitle("Talletus Ikkuna");
		setBounds(100, 100, 597, 388);
		getContentPane().setLayout(null);
		
		label_tilinomistaja_noedit = new JLabel("Tilin Omistaja:");
		label_tilinomistaja_noedit.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_tilinomistaja_noedit.setBounds(42, 52, 136, 30);
		getContentPane().add(label_tilinomistaja_noedit);
		
		label_saldo_t = new JLabel("Tilin saldo:");
		label_saldo_t.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_saldo_t.setBounds(42, 180, 136, 30);
		getContentPane().add(label_saldo_t);
		
		nappi_teetalletus = new JButton("Tee talletus");
		nappi_teetalletus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				String tmp_saldo;
				tmp_saldo = "" + (Integer.parseInt(label_tilinsaldo_t.getText()) + Integer.parseInt(talletus.getText()));
				
				int result = JOptionPane.showConfirmDialog(null, "Talletetaanko summa?", "Talletus", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.OK_OPTION) {
					label_tilinsaldo_t.setText(tmp_saldo);
					talletus.setText("");
				}
				if (result == JOptionPane.NO_OPTION) {
					talletus.setText("");
				}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "V‰‰r‰ syˆte");
					talletus.setText("");
				}
				
			}
		});
		nappi_teetalletus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nappi_teetalletus.setBounds(340, 181, 125, 30);
		getContentPane().add(nappi_teetalletus);
		
		label_tilinomistaja_t = new JLabel("");
		label_tilinomistaja_t.setBounds(42, 85, 167, 24);
		getContentPane().add(label_tilinomistaja_t);
		
		label_tilinsaldo_t = new JLabel("");
		label_tilinsaldo_t.setBounds(42, 218, 167, 24);
		getContentPane().add(label_tilinsaldo_t);
		
		label_talletuksenm‰‰r‰ = new JLabel("Talletuksen m\u00E4\u00E4r\u00E4:");
		label_talletuksenm‰‰r‰.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_talletuksenm‰‰r‰.setBounds(330, 85, 167, 30);
		getContentPane().add(label_talletuksenm‰‰r‰);
		
		talletus = new JTextField();
		talletus.setBounds(340, 131, 125, 28);
		getContentPane().add(talletus);
		talletus.setColumns(10);
	}
}
