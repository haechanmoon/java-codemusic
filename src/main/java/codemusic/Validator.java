package codemusic;

public class Validator {
    private static final int MAX_TEMPO = 9;
    private static final int MIN_TEMPO = 1;

    public static void validateCodeNotEmpty(String code) {
        if (code.isEmpty()) {
            throw new IllegalArgumentException(Messages.ERROR_EMPTY_CODE);
        }
    }

    public static int validateTempo(String input) {
        try {
            int tempo = Integer.parseInt(input);
            if (tempo < MIN_TEMPO || tempo > MAX_TEMPO) {
                throw new IllegalArgumentException(Messages.ERROR_TEMPO_RANGE);
            }
            return tempo;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Messages.ERROR_NOT_NUMBER);
        }
    }
}
