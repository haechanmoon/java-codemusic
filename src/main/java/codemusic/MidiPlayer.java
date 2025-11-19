package codemusic;

import java.util.List;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MidiPlayer {
    private Synthesizer synthesizer;
    private MidiChannel channel;

    public MidiPlayer() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channel = synthesizer.getChannels()[0];
        } catch (MidiUnavailableException e) {
            System.out.println("MIDI 시스템을 사용할 수 없습니다.");
        }
    }

    public void play(List<List<Integer>> rightHandScores, List<Integer> leftHandScores, List<String> dynamics) {
        if (synthesizer == null || channel == null) {
            return;
        }

        System.out.println("연주를 시작합니다...");

        try {
            for (int i = 0; i < rightHandScores.size(); i++) {
                List<Integer> rightNotes = rightHandScores.get(i);
                Integer leftNote = leftHandScores.get(i);
                String dynamic = dynamics.get(i);

                int velocity = dynamic.equals("f") ? 110 : 60;

                for (int note : rightNotes) {
                    if (note != -1) {
                        channel.noteOn(note, velocity);
                    }
                }
                if (leftNote != -1) {
                    channel.noteOn(leftNote, velocity);
                }
                Thread.sleep(70);

                for (int note : rightNotes) {
                    if (note != -1) {
                        channel.noteOff(note);
                    }
                }
                if (leftNote != -1) {
                    channel.noteOff(leftNote);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            close();
        }
        System.out.println("연주 끝!");
    }

    public void close() {
        if (synthesizer != null && synthesizer.isOpen()) {
            synthesizer.close();
        }
    }
}