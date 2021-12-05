import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;

public class NostoIkkuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel label_TilinSaldo_noedit;
	public JLabel label_TilinSaldo_n;
	private JLabel lblNostettevaMr;
	private JTextField nosto;
	private JButton nappi_teeNosto;
	private JSlider slider;
	
	public String saldo;

	/**
	 * Create the dialog.
	 */
	public NostoIkkuna() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				TilinPaasivu.label_tilinsaldo_p.setText(label_TilinSaldo_n.getText());
			}
			@Override
			public void windowActivated(WindowEvent arg0e) { 
				slider.setMaximum(Integer.parseInt(label_TilinSaldo_n.getText()));
				slider.setValue(Integer.parseInt(label_TilinSaldo_n.getText()));
			}
		});
		setTitle("Nosto Ikkuna");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		label_TilinSaldo_noedit = new JLabel("Tilin Saldo:");
		label_TilinSaldo_noedit.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_TilinSaldo_noedit.setBounds(34, 83, 98, 32);
		contentPanel.add(label_TilinSaldo_noedit);
		
		label_TilinSaldo_n = new JLabel("");
		label_TilinSaldo_n.setBounds(44, 114, 75, 20);
		contentPanel.add(label_TilinSaldo_n);
		
		lblNostettevaMr = new JLabel("Nostettava m\u00E4\u00E4r\u00E4:");
		lblNostettevaMr.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNostettevaMr.setBounds(209, 92, 165, 14);
		contentPanel.add(lblNostettevaMr);
		
		nosto = new JTextField();
		nosto.setBounds(220, 114, 96, 20);
		contentPanel.add(nosto);
		nosto.setColumns(10);
		
		nappi_teeNosto = new JButton("Tee nosto");
		nappi_teeNosto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				String tmp_saldo;
				tmp_saldo = "" + (Integer.parseInt(label_TilinSaldo_n.getText()) - Integer.parseInt(nosto.getText()));
				
				
				int result = JOptionPane.showConfirmDialog(null, "Nostetaanko summa?", "Nosto", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.OK_OPTION) {
					label_TilinSaldo_n.setText(tmp_saldo);
					nosto.setText("");
					slider.setMaximum(Integer.parseInt(tmp_saldo));
					slider.setValue(Integer.parseInt(tmp_saldo));
				}
				if (result == JOptionPane.NO_OPTION) {
					nosto.setText("");
				}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Väärä syöte");
					nosto.setText("");
				}
				
			}
		});
		nappi_teeNosto.setBounds(220, 145, 96, 23);
		contentPanel.add(nappi_teeNosto);
		
		slider = new JSlider();
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				nosto.setText("" + slider.getValue());
			}
		});
		slider.setBounds(370, 29, 56, 223);
		contentPanel.add(slider);
	}
}
