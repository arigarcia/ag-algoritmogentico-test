package ag.test.geneticsalgoritms.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private JPanel content;
	private JTextField objectiveText;
	private JTextField charactersText;
	private JTextArea resultText;
	private JButton btnExit;
	private JButton btnRunn;
	private MainWindowListener listener;

	private void createLogPanel(){
		//
		JPanel panel = new JPanel();
		//
		panel.add(new JLabel("Resultados:"));
		//
		resultText = new JTextArea(20, 58);
		JScrollPane resultScroll = new JScrollPane(resultText);
		panel.add(resultScroll);
		//
		content.add(panel);
	}
	
	private void createObjectivePanel(){
		//
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//
		panel.add(new JLabel("Texto Objetivo:"));
		//
		objectiveText = new JTextField(10);
		objectiveText.setText("Aló Mundo");
		panel.add(objectiveText);
		//
		content.add(panel);
	}
	
	private void createCharactersPanel(){
		//
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//
		panel.add(new JLabel("Caracteres possíveis:"));
		//
		charactersText = new JTextField(30);
		charactersText.setText("!,.:;?áÁãÃâÂõÕôÔóÓéêÉÊíQWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuiopasdfghjklçzxcvbnm1234567890 ");
		panel.add(charactersText);
		//
		content.add(panel);
	}
	
	private void createButtonsPanel(){
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//
		btnRunn = new JButton(new AbstractAction("Executar") {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.listener.run(MainWindow.this.resultText);
			}
		});
		//
		btnExit = new JButton(new AbstractAction("Sair do Programa") {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//
		panel.add(btnRunn);
		panel.add(btnExit);
		//
		content.add(panel);
	}
	

	public MainWindow(MainWindowListener l) {
		content = new JPanel();
		add(content);
		//
		listener = l;
		//objective text
		createObjectivePanel();
		//caracteres
		createCharactersPanel();
		//logs
		createLogPanel();
		//buttons
		createButtonsPanel();
    }
	
	public String getObjective(){
		return objectiveText.getText();
	}
	
	public String getCharacters(){
		return charactersText.getText();
	}

}
