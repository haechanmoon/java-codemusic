package codemusic;

public class Validator {
    public static void validateCodeNotEmpty(String code) {
        if (code.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 코드는 1글자 이상 입력해야 합니다.");
        }
    }

    public static int validateTempo(String input) {
        try {
            int tempo = Integer.parseInt(input);
            if (tempo < 1 || tempo > 9) {
                throw new IllegalArgumentException(Messages.ERROR_TEMPO_RANGE);
            }
            return tempo;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Messages.ERROR_NOT_NUMBER);
        }
    }
}
