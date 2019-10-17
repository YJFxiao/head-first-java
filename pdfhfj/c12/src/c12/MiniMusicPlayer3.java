package c12;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JFrame;

public class MiniMusicPlayer3 {
	static JFrame f=new JFrame("My First Music Video");
	static MyDrawPanel ml;
	
	public static void main(String args[]) {
		MiniMusicPlayer3 mini=new MiniMusicPlayer3();
		mini.go();
	}
	
	public void setUpGui() {
		ml=new MyDrawPanel();
		f.setContentPane(ml);
		f.setBounds(30, 30, 300, 300);
		f.setVisible(true);
		
	}

	private void go() {
		// TODO Auto-generated method stub
		setUpGui();
		
		Sequencer sequencer;
		try {
			sequencer = MidiSystem.getSequencer(); //Sequencer发声装置，sequence乐曲，Track,乐曲的信息记录
			sequencer.open();
			sequencer.addControllerEventListener(ml, new int[] {127});
			Sequence seq=new Sequence(Sequence.PPQ,4);
			Track track=seq.createTrack();
			
			int r=0;
			for(int i=0;i<60;i+=4) {
				r=(int)((Math.random()*50)+1);
				track.add(makeEvent(144,1,r,100,i));
				track.add(makeEvent(176,1,127,0,i));
				track.add(makeEvent(128,1,r,100,i+2));
				
				sequencer.setSequence(seq);
				sequencer.setTempoInBPM(120);
				sequencer.start();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	private MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		// TODO Auto-generated method stub
		MidiEvent event=null;
		
		ShortMessage a=new ShortMessage();
		try {
			a.setMessage(comd, chan, one, two);  //类型，频道，音符，音道
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event=new MidiEvent(a,tick);
		
		return event;
	}

}
