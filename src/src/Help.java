import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class Help extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Help dialog = new Help();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Help() {
		setBounds(0, 0, 900, 775);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			//Still needs work
			try{Scanner read = new Scanner(new File("Rules.txt"));//Copied for testing, can change
				JTextPane txtpnTest = new JTextPane();
				String text="";
				int bound=0;
				while(read.hasNext()){
					String s=read.next();
					bound+=s.length();
					if(s.indexOf('.')!=-1){s+="\n";bound=0;}
					if(s.indexOf(':')!=-1){s+="\n\n";bound=0;}
					if(bound<125){
						text+=s+" ";
					}else{
						text+="\n"+s+" ";
						bound=0;
					}
				}
				txtpnTest.setText(text);
				contentPanel.add(txtpnTest);
			}catch(IOException e){
				JTextPane txtpnTest = new JTextPane();
				txtpnTest.setText("Error! File not found. :(");
				contentPanel.add(txtpnTest);
			}
		}
	}

}
