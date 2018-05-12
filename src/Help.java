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
import javax.swing.JTextArea;

public class Help extends JDialog {

	/**Creates a new Panel*/
	private final JPanel contentPanel = new JPanel();
	/**Creates a new Scroll Panel*/
	private final JScrollPane scrollPane = new JScrollPane();

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
		setBounds(0, 0, 500, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			try{
				Scanner read = new Scanner(new File("Rules.txt")); //Copied for testing, can change
				String text="";
				int bound=0;
				int counter = 0;
				while(read.hasNext()){
					text+=read.nextLine()+"\n";
				}
				JTextArea textArea=new JTextArea(10,20);
				JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				contentPanel.add(scrollPane);
				
				JTextPane txtpnTest = new JTextPane();
				txtpnTest.setContentType("HTML/plain");
				txtpnTest.setEditable(false);
				scrollPane.setViewportView(txtpnTest);
				txtpnTest.setText(text);
				getContentPane().setLayout(new BorderLayout(0, 0));

		        getContentPane().add(scrollPane, BorderLayout.CENTER);

		        scrollPane.setViewportView(txtpnTest);

			}catch(IOException e){
				JTextPane txtpnTest = new JTextPane();
				txtpnTest.setText("Error! File not found. :(");
				

			}
		}
	}

}
