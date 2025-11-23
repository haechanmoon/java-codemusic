package codemusic;

public enum Chord {
    PUBLIC("public"),
    INTERFACE("interface"),
    INT("int"),
    CHAR("char"),
    BOOLEAN("boolean"),
    IF("if"),
    FOR("for"),
    WHILE("while"),
    RETURN("return"),
    CLASS("class"),
    ABSTRACT("abstract"),
    IMPLEMENTS("implements"),
    NEW("new"),
    THIS("this"),
    SUPER("super"),
    PRIVATE("private"),
    PROTECTED("protected"),
    STATIC("static"),
    FINAL("final"),
    VOID("void"),
    TRY("try"),
    CATCH("catch"),
    THROWS("throws"),
    THROW("throw"),
    IMPORT("import"),
    PACKAGE("package"),
    TRUE("true"),
    FALSE("false");

    private final String keyword;
    public static final int MIN_ROOT_NOTE = 60;
    public static final int MAX_ROOT_NOTE = 77;
    public static final int FIRST_INTERVAL = 4;
    public static final int SECOND_INTERVAL = 3;
    private static final int SEMITONES_PER_OCTAVE = 12;
    private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    Chord(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public int[] generateMidiNotes(int rootNote, boolean isMajor) {
        if (isMajor) {
            return generateMajorChord(rootNote);
        }
        return generateMinorChord(rootNote);
    }

    private int[] generateMajorChord(int rootNote) {
        return new int[]{rootNote, rootNote + FIRST_INTERVAL, rootNote + FIRST_INTERVAL + SECOND_INTERVAL};
    }

    private int[] generateMinorChord(int rootNote) {
        return new int[]{rootNote, rootNote + SECOND_INTERVAL, rootNote + SECOND_INTERVAL + FIRST_INTERVAL};
    }

    public static String midiArrayToNoteString(int[] midiNumbers) {
        return String.format("%s-%s-%s",
                midiToNoteString(midiNumbers[0]),
                midiToNoteString(midiNumbers[1]),
                midiToNoteString(midiNumbers[2])
        );
    }

    public static String midiToNoteString(int midiNumber) {
        int octave = (midiNumber / SEMITONES_PER_OCTAVE) - 1;
        String note = NOTE_NAMES[midiNumber % SEMITONES_PER_OCTAVE];
        return note + octave;
    }

    public static Chord findByKeyword(String code) {
        for (Chord chord : values()) {
            if (code.startsWith(chord.getKeyword())) {
                return chord;
            }
        }
        return null;
    }
}
