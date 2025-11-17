package codemusic;

public enum Chord {
    PUBLIC("public", "C4-E4-G4"),
    INT("int", "Cffff"),
    CHAR("char", ""),
    BOOLEAN("boolean", ""),
    IF("if", ""),
    FOR("for", ""),
    WHILE("while", ""),
    RETURN("return", ""),
    CLASS("class", ""),
    INTERFACE("interface", ""),
    ABSTRACT("abstract", ""),
    IMPLEMENTS("implements", ""),
    NEW("new", ""),
    THIS("this", ""),
    SUPER("super", ""),
    PUBLIC("public", ""),
    PRIVATE("private", ""),
    PROTECTED("protected", ""),
    STATIC("static", ""),
    FINAL("final", ""),
    VOID("void", ""),
    TRY("try", ""),
    CATCH("catch", ""),
    THROW("throw", ""),
    THROWS("throws", ""),
    IMPORT("import", ""),
    PACKAGE("package", ""),
    TRUE("true", ""),
    FALSE("false", "");

    private final String keyword;
    private final String chordNotes;

    Chord(String keyword, String chordNotes) {
        this.keyword = keyword;
        this.chordNotes = chordNotes;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getChordNotes() {
        return this.chordNotes;
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
