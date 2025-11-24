package codemusic;

public enum Note {
    A("C4", 60),
    B("C#4", 61),
    C("D4", 62),
    D("D#4", 63),
    E("E4", 64),
    F("F4", 65),
    G("F#4", 66),
    H("G4", 67),
    I("G#4", 68),
    J("A4", 69),
    K("A#4", 70),
    L("B4", 71),
    M("C5", 72),
    N("C#5", 73),
    O("D5", 74),
    P("D#5", 75),
    Q("E5", 76),
    R("F5", 77),
    S("F#5", 78),
    T("G5", 79),
    U("G#5", 80),
    V("A5", 81),
    W("A#5", 82),
    X("B5", 83),
    Y("C6", 84, true),
    Z("REST", -1, true);

    private static final char REST_CHAR = 'Z';
    private static final String PRINT_REST = "REST";
    private static final int REST_MIDI_VALUE = -1;
    private static final int LEFT_HAND_OCTAVE_SHIFT = 24; // 2옥타브 (12 * 2)
    private static final int SEMITONES_PER_OCTAVE = 12;
    private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private final String rightHandNote;
    private final int midiNumber;
    private final boolean isLeftHandRest;

    Note(String rightHandNote, int midiNumber) {
        this(rightHandNote, midiNumber, false);
    }

    Note(String rightHandNote, int midiNumber, boolean isLeftHandRest) {
        this.rightHandNote = rightHandNote;
        this.midiNumber = midiNumber;
        this.isLeftHandRest = isLeftHandRest;
    }

    public String getRightHandNote() {
        return this.rightHandNote;
    }

    public int getMidiNumber() {
        return this.midiNumber;
    }

    public String getLeftHandNote() {
        if (this.isLeftHandRest || this.midiNumber == REST_MIDI_VALUE) {
            return PRINT_REST;
        }
        int leftMidi = this.midiNumber - LEFT_HAND_OCTAVE_SHIFT;
        return midiToNoteString(leftMidi);
    }

    public int getLeftHandMidiNumber() {
        if (this.isLeftHandRest || this.midiNumber == REST_MIDI_VALUE) {
            return REST_MIDI_VALUE;
        }
        return this.midiNumber - LEFT_HAND_OCTAVE_SHIFT;
    }

    private String midiToNoteString(int midiNumber) {
        int octave = (midiNumber / SEMITONES_PER_OCTAVE) - 1;
        String note = NOTE_NAMES[midiNumber % SEMITONES_PER_OCTAVE];
        return note + octave;
    }

    public static Note findByChar(char letter) {
        char upperCaseLetter = Character.toUpperCase(letter);

        if (upperCaseLetter == REST_CHAR) {
            return Z;
        }

        for (Note note : values()) {
            if (note.name().charAt(0) == upperCaseLetter) {
                return note;
            }
        }
        return Z;
    }
}