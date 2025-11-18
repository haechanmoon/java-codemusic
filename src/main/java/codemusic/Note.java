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
    Y("C6", 84),
    Z("REST", -1);

    private final String rightHandNote;
    private final int midiNumber;

    Note(String rightHandNote, int midiNumber) {
        this.rightHandNote = rightHandNote;
        this.midiNumber = midiNumber;
    }

    public String getRightHandNote() {
        return this.rightHandNote;
    }

    public int getMidiNumber() { // ⬅️ 'MIDI 숫자 창구' '추가'!
        return this.midiNumber;
    }

    public String getLeftHandNote() {
        if (this == Z || this == Y) {
            return "REST";
        }

        if (this.midiNumber == -1) {
            return "Rest";
        }
        int leftMidi = this.midiNumber - 24;
        return midiToNoteString(leftMidi);
    }

    private String midiToNoteString(int midiNumber) {
        String[] noteNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        int octave = (midiNumber / 12) - 1;
        String note = noteNames[midiNumber % 12];
        return note + octave;
    }

    public static Note findByChar(char letter) {
        char upperCaseLetter = Character.toUpperCase(letter);

        if (upperCaseLetter == 'Z') {
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