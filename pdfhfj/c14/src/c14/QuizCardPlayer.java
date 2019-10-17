package c14;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class QuizCardPlayer {
	private JTextArea display;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private QuizCard currentCard;
	private int currentCardIndex;
	private JFrame frame;
	private JButton nextButton;
	private boolean isShowAnswer;
	
	public static void main(String[] args) {
		QuizCardPlayer reader=new QuizCardPlayer();
		reader.go();
	}

	private void go() {
		// TODO Auto-generated method stub
		frame = new JFrame("Quiz Card Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        display = new JTextArea(9,20);
        display.setFont(bigFont);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        display.setEditable(false);
       
        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(
              ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(
              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      
        nextButton = new JButton("Show Question");
        
        mainPanel.add(qScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
            
        loadMenuItem.addActionListener(new OpenMenuListener());
            
        fileMenu.add(loadMenuItem);
        
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
			if(isShowAnswer) {
				//显示答案
				display.setText(currentCard.getAnswer());
				nextButton.setText("Next Card");
				isShowAnswer=false;
			}else {
				//显示问题
				if(currentCardIndex<cardList.size()) {
					showNextCard();
				}else {
					//没有更多的卡片了
					display.setText("That was last card");
					nextButton.setEnabled(false);
				}
			}

		}
	}

		private void showNextCard() {
			// TODO Auto-generated method stub
			currentCard=cardList.get(currentCardIndex);
			currentCardIndex++;
			display.setText(currentCard.getQuestion());
			nextButton.setText("Show Answer");
			isShowAnswer=true;
			
		}

	
	public class OpenMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser fileOpen=new JFileChooser();
			fileOpen.showOpenDialog(frame);
			loadFile(fileOpen.getSelectedFile());

		}
	}

		private void loadFile(File file) {
			// TODO Auto-generated method stub
			cardList=new ArrayList<QuizCard>();
			try {
				BufferedReader reader=new BufferedReader(new FileReader(file));
				String line=null;
				while((line=reader.readLine())!=null) {
					makeCard(line);   //读取一行数据，传给makeCard()来把字符串解析成卡片加到arrayList中
				}
				reader.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("couldn't read the card file");
				e.printStackTrace();
			}
		}

		private void makeCard(String lineToParse) {
			// TODO Auto-generated method stub
			String[] result=lineToParse.split("/");  //每一行都是一张卡片，但需要分解成问题和答案
			QuizCard card=new QuizCard(result[0],result[1]);
			cardList.add(card);
			System.out.println("made a card");
			
		}

}
