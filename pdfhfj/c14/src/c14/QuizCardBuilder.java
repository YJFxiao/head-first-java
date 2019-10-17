package c14;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class QuizCardBuilder {
	private JTextArea question;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private JFrame frame;
	
	public static void main(String[] args) {
		QuizCardBuilder builder=new QuizCardBuilder();
		builder.go();
	}

	private void go() {
		// TODO Auto-generated method stub
		frame=new JFrame("Quiz Card Builder");
		JPanel mainPanel=new JPanel();
		Font bigFont=new Font("sanserif",Font.BOLD,24);
		
		question=new JTextArea(6,20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(bigFont);
		
		JScrollPane qScroller=new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		answer=new JTextArea(6,20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(bigFont);
		
		JScrollPane aScroller=new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton nextButton=new JButton("Next Card");
		
		cardList=new ArrayList<QuizCard>();
		
		JLabel qlabel=new JLabel("Question:");
		JLabel aLabel=new JLabel("Answwer:");
		
		mainPanel.add(qlabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		JMenuItem newMenuItem=new JMenuItem("New");
		JMenuItem saveMenuItem=new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500,600);
		frame.setVisible(true);
		
	}
	public class NextCardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QuizCard card=new QuizCard(question.getText(),answer.getText());
			cardList.add(card);
			clearCard();

		}

	}
	public class SaveMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QuizCard card=new QuizCard(question.getText(),answer.getText());
			cardList.add(card);
			
			JFileChooser fileSave=new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());

		}
	}

		private void saveFile(File file) {
			// TODO Auto-generated method stub
			try {
				BufferedWriter writer=new BufferedWriter(new FileWriter(file));
				
				for(QuizCard card:cardList) {
					writer.write(card.getQuestion()+"/n");
					writer.write(card.getAnswer()+"/n");
					writer.close();
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	public class NewMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			cardList.clear();
			clearCard();

		}
	}

		private void clearCard() {
			// TODO Auto-generated method stub
			question.setText("");
			answer.setText("");
			question.requestFocus();
			
		}

	}


