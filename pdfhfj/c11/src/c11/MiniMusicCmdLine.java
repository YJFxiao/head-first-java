package c11;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMusicCmdLine {
	public static void main(String[] args) {
		MiniMusicCmdLine mini=new MiniMusicCmdLine();
		if(args.length<2) {
			System.out.println("Don't forget the instrument and note args");
		}else {
			int instrument=Integer.parseInt(args[0]);
			int note=Integer.parseInt(args[1]);
			mini.play(instrument,note);
		}
	}

	private void play(int instrument, int note) {
		// TODO Auto-generated method stub
		
		Sequencer player;
		try {
			player = MidiSystem.getSequencer();
			player.open();
			Sequence seq=new Sequence(Sequence.PPQ,4);
			Track track=seq.createTrack();
			
			MidiEvent event=null;
			
			ShortMessage first=new ShortMessage();   //ÓÃmessage´´½¨Event
			first.setMessage(192, 1, instrument, 0);
			MidiEvent changeInsutument=new MidiEvent(first,1);
			track.add(changeInsutument);
			
			ShortMessage a=new ShortMessage();
			a.setMessage(144, 1, note, 100);
			MidiEvent noteOn=new MidiEvent(a,1);
			track.add(noteOn);
			
			ShortMessage b=new ShortMessage();
			b.setMessage(128, 1, note, 100);
			MidiEvent noteOff=new MidiEvent(b,1);
			track.add(noteOff);
			
			player.setSequence(seq);
			player.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
