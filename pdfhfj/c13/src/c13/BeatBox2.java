package c13;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BeatBox2 {
	JPanel mainPanel;
	ArrayList<JCheckBox> checkboxList;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;
	
	String[] instrumentNames= {"Bass Drum","Closed Hi-Hat","Open Hi-Hat","Acoustic Snare",
			"Crash Cymbal","Hand Clap","High Tom","Hi Bongo","Maracas","Whistle","Low Conga",
			"Cowbell","Vibraslap","Low-mid Tom","High Agogo","Open Hi Conga"};
	int[] instruments= {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	
	public static void main(String[] args) {
		new BeatBox2().buildGUI();
	}

	public void buildGUI() {
		// TODO Auto-generated method stub
		theFrame=new JFrame("Cyber BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout=new BorderLayout();
		JPanel background=new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		checkboxList=new ArrayList<JCheckBox>();
		Box buttonBox=new Box(BoxLayout.Y_AXIS);
		
		JButton start=new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);
		
		JButton stop=new JButton("stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);
		
		JButton upTempo=new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo=new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		Box nameBox=new Box(BoxLayout.Y_AXIS);
		for(int i=0;i<16;i++) {
			nameBox.add(new Label(instrumentNames[i]));
		}
		
		background.add(BorderLayout.EAST,buttonBox);
		background.add(BorderLayout.WEST,nameBox);
		
		theFrame.getContentPane().add(background);
		
		GridLayout grid=new GridLayout(16,16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel=new JPanel(grid);
		background.add(BorderLayout.CENTER,mainPanel);
		
		for(int i=0;i<256;i++) {
			JCheckBox c=new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}
		
		setUpMidi();
		
		theFrame.setBounds(50, 50, 300, 300);
		theFrame.pack();
		theFrame.setVisible(true);
	}
	
	private void setUpMidi() {
		// TODO Auto-generated method stub
		try {
			sequencer=MidiSystem.getSequencer();
			sequencer.open();
			sequence=new Sequence(Sequence.PPQ,4);
			track=sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public class MyStartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			buildTrackAndStart();

		}

		private void buildTrackAndStart() {      //创建出16个元素的数组来存储一项乐器的值。如果用于表演，其值会是关键字值，否则值为0
			// TODO Auto-generated method stub
			int[] trackList=null;
			
			sequence.deleteTrack(track);
			track=sequence.createTrack();
			
			for(int i=0;i<16;i++) {           //对每个乐器执行一次
				trackList=new int[16];
				
				int key=instruments[i];
				
				for(int j=0;j<16;j++) {
					JCheckBox jc=(JCheckBox) checkboxList.get(j+(16*i));
					if(jc.isSelected()) {        //如果有勾选，将关键字值放到数组的该位置上，不然的话就补0
						trackList[j]=key;
					}else {
						trackList[j]=0;
					}
				}
				makeTracks(trackList);      //创建此乐器的事件并加到track上
				track.add(makeEvent(176,1,127,0,16));
			}
			track.add(makeEvent(192,9,1,0,15));  //确保第16拍有事情，否则beatbox不会重复播放
			
			try {
				sequencer.setSequence(sequence);
				sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY); //指定无穷的重复次数
				sequencer.start();
				sequencer.setTempoInBPM(120);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public class MyStopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			sequencer.stop();

		}

	}
	public class MyUpTempoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			float tempoFactor=sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*1.03));  //节奏因子，预设为1.0，每次调整3%

		}

	}
	public class MyDownTempoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			float tempoFactor=sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*.97));

		}

	}

	private MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		// TODO Auto-generated method stub
		MidiEvent event=null;
		ShortMessage a=new ShortMessage();
		try {
			a.setMessage(comd, chan, one, two);
			event=new MidiEvent(a,tick);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
	}

	private void makeTracks(int[] list) {
		// TODO Auto-generated method stub
		for(int i=0;i<16;i++) {
			int key=list[i];
			
			if(key!=0) {
				track.add(makeEvent(144,9,key,100,i));   //创建note on和note off事件并加入到track上
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
		
	}

}
