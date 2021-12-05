import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TilinPaasivu extends JFrame {

	private JPanel contentPane;
	private JLabel label_tilinomistaja_noedit;
	private JLabel label_tilinsaldo_noedit;
	public static JLabel label_tilinsaldo_p;
	private JLabel label_tilinomistaja_p;
	private JButton nappi_talletus;
	private JButton nappi_nosto;
	
	private TalletusIkkuna talletusIkkuna = new TalletusIkkuna();
	private NostoIkkuna nostoIkkuna = new NostoIkkuna();
	
	String saldo;
	String etunimi;
	String sukunimi;
	
	String filename = "src/Resources/saldo.txt";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TilinPaasivu frame = new TilinPaasivu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TilinPaasivu() {
		setTitle("Tilin P\u00E4\u00E4sivu");
		
		File f = new File(filename);
		if(!f.exists()) {
			alustaTiedosto();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_tilinomistaja_noedit = new JLabel("Tilin Omistaja:");
		label_tilinomistaja_noedit.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_tilinomistaja_noedit.setBounds(34, 58, 139, 33);
		contentPane.add(label_tilinomistaja_noedit);
		
		label_tilinsaldo_noedit = new JLabel("Tilin Saldo:");
		label_tilinsaldo_noedit.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_tilinsaldo_noedit.setBounds(38, 175, 115, 33);
		contentPane.add(label_tilinsaldo_noedit);
		
		nappi_talletus = new JButton("Tee talletus");
		nappi_talletus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				talletusIkkuna.dispose();
				talletusIkkuna = new TalletusIkkuna();
				
				talletusIkkuna.label_tilinomistaja_t.setText(label_tilinomistaja_p.getText());
				talletusIkkuna.label_tilinsaldo_t.setText(label_tilinsaldo_p.getText());
				
				talletusIkkuna.setVisible(true);			
				}
		});
		nappi_talletus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nappi_talletus.setBounds(343, 126, 162, 33);
		contentPane.add(nappi_talletus);
		
		nappi_nosto = new JButton("Tee nosto");
		nappi_nosto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nappi_nosto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nostoIkkuna.dispose();
				nostoIkkuna = new NostoIkkuna();
				
				nostoIkkuna.saldo = saldo;
				
				nostoIkkuna.label_TilinSaldo_n.setText(label_tilinsaldo_p.getText());
				
				
				nostoIkkuna.setVisible(true);
			}
		});
		nappi_nosto.setBounds(343, 177, 162, 33);
		contentPane.add(nappi_nosto);
		
		label_tilinsaldo_p = new JLabel("");
		label_tilinsaldo_p.setHorizontalAlignment(SwingConstants.CENTER);
		label_tilinsaldo_p.setBounds(36, 207, 105, 27);
		contentPane.add(label_tilinsaldo_p);
		
		label_tilinomistaja_p = new JLabel("");
		label_tilinomistaja_p.setHorizontalAlignment(SwingConstants.CENTER);
		label_tilinomistaja_p.setBounds(34, 89, 177, 27);
		contentPane.add(label_tilinomistaja_p);
		
		lueSaldo(filename);
	}
	
	public void lueSaldo(String filename) {
		
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);
			String line;
			saldo = br.readLine();	//Saldo = 1000
			saldo = kasitteleRivi(saldo);
			
			etunimi = kasitteleRivi(br.readLine());
			sukunimi = kasitteleRivi(br.readLine());
			
			label_tilinomistaja_p.setText(etunimi + " " + sukunimi);
			label_tilinsaldo_p.setText(saldo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void kirjoitaTiedostoon(String txt, String Filename) {
		try { 
			// Valinta true lopussa aiheuttaa sen, että ei ylikirjoiteta vaan jatketaan olemassa olevan perään
			FileWriter fwriter = new FileWriter(filename, true);
			fwriter.write(txt + "\n");
			fwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alustaTiedosto() {
		String kirjoitettava = "Saldo = 1000\n"
				+ "Etunimi = Matti\n"
				+ "Sukunimi = Meikäläinen";
		kirjoitaTiedostoon(kirjoitettava, filename);
	}
	
	public String kasitteleRivi(String txt) {	//saldo = 1000
		txt = txt.replace(" ", "");				//saldo=1000
		String[] temp = txt.split("=");			//temp[0] => Saldo, temp[1] => 1000
		
		return temp[1];
	}

}
