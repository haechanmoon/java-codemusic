package codemusic;

import java.util.List;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MidiPlayer {
    private static final String FORTE = "f";
    private static final String PIANO = "p";
    private static final int FORTE_VELOCITY = 110;
    private static final int PIANO_VELOCITY = 60;
    private static final int TEMPO_ORIGINAL = 1100;
    private static final int TEMPO_ABSTRACT_INTERVAL = 100;

    private Synthesizer synthesizer;
    private MidiChannel channel;

    public MidiPlayer() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channel = synthesizer.getChannels()[0];
        } catch (MidiUnavailableException e) {
            System.out.println(Messages.ERROR_MIDI_UNAVAILABLE);
        }
    }

    public void play(List<List<Integer>> rightScores, List<Integer> leftScores, List<String> dynamics, int tempo) {
        if (synthesizer == null || channel == null) {
            return;
        }
        System.out.println(Messages.PLAY_START);

        int sleepTime = calculateSleepTime(tempo);

        try {
            for (int i = 0; i < rightScores.size(); i++) {
                playOneBeat(rightScores.get(i), leftScores.get(i), dynamics.get(i), sleepTime);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            close();
        }
        System.out.println(Messages.PLAY_END);
    }

    private void playOneBeat(List<Integer> rightNotes, int leftNote, String dynamic, int sleepTime)
            throws InterruptedException {
        int velocity = 0;
        if (dynamic.equals(FORTE)) {
            velocity = FORTE_VELOCITY;
        }
        if (dynamic.equals(PIANO)) {
            velocity = PIANO_VELOCITY;
        }

        noteOn(rightNotes, leftNote, velocity);
        Thread.sleep(sleepTime);
        noteOff(rightNotes, leftNote);
    }

    private void noteOn(List<Integer> rightNotes, int leftNote, int velocity) {
        for (int note : rightNotes) {
            if (note != -1) {
                channel.noteOn(note, velocity);
            }
        }
        if (leftNote != -1) {
            channel.noteOn(leftNote, velocity);
        }
    }

    private void noteOff(List<Integer> rightNotes, int leftNote) {
        for (int note : rightNotes) {
            if (note != -1) {
                channel.noteOff(note);
            }
        }
        if (leftNote != -1) {
            channel.noteOff(leftNote);
        }
    }

    private int calculateSleepTime(int tempo) {
        return TEMPO_ORIGINAL - (tempo * TEMPO_ABSTRACT_INTERVAL);
    }


    public void close() {
        if (synthesizer != null && synthesizer.isOpen()) {
            synthesizer.close();
        }
    }
}