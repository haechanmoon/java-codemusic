package codemusic;

public enum Note {
    A("C4"),
    B("C#4"),
    C("D4"),
    D("D#4"),
    E("E4"),
    F("F4"),
    G("F#4"),
    H("G4"),
    I("G#4"),
    J("A4"),
    K("A#4"),
    L("B4"),
    M("C5"),
    N("C#5"),
    O("D5"),
    P("D#5"),
    Q("E5"),
    R("F5"),
    S("F#5"),
    T("G5"),
    U("G#5"),
    V("A5"),
    W("A#5"),
    X("B5"),
    Y("C6"),
    Z("REST");

    private final String rightHandNote;

    Note(String rightHandNote) {
        this.rightHandNote = rightHandNote;
    }

    public String getRightHandNote() {
        return this.rightHandNote;
    }

    public String getLeftHandNote() {
        if (this == Z || this == Y) {
            return "REST";
        }

        String note = this.rightHandNote;
        if (note.equals("REST")) {
            return "REST";
        }

        char noteName = note.charAt(0);
        char accidental;
        int octave;

        if (note.length() == 3) {
            accidental = note.charAt(1);
            octave = Integer.parseInt(note.substring(2));
            return "" + noteName + accidental + (octave - 2);
        } else {
            octave = Integer.parseInt(note.substring(1));
            return "" + noteName + (octave - 2);
        }
    }

    public static Note findByChar(char letter) {
        for (Note note : values()) {
            char enumChar = note.name().charAt(0);
            if (Character.toLowerCase(enumChar) == letter) {
                return note;
            }
        }
        return Z;
    }
}