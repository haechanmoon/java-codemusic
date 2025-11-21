package codemusic;

public class Messages {
    public static final String ASK_RIGHT_HAND = "\n오른손 코드를 입력하세요 ('done'을 입력하면 종료): ";
    public static final String ASK_LEFT_HAND = "\n왼손 코드를 입력하세요 ('done'을 입력하면 종료): ";
    public static final String ASK_TEMPO = "\n연주 속도를 입력하세요 (1:느림 ~ 9:빠름): ";

    public static final String END_WORD = "done";

    public static final String START_MSG = "--- 코드를 음악으로 변환합니다. ---";
    public static final String PLAY_START = "연주를 시작합니다...";
    public static final String PLAY_END = "연주 끝!";

    public static final String OUTPUT_RIGHT = "오른손:\t";
    public static final String OUTPUT_LEFT = "왼손:\t";
    public static final String OUTPUT_DYNAMIC = "셈여림:\t";

    public static final String ERROR_PREFIX = "[ERROR] ";
    public static final String ERROR_EMPTY_CODE = ERROR_PREFIX + "코드는 1글자 이상 입력해야 합니다.";
    public static final String ERROR_TEMPO_RANGE = ERROR_PREFIX + "속도는 1에서 9 사이의 숫자여야 합니다.";
    public static final String ERROR_NOT_NUMBER = ERROR_PREFIX + "숫자만 입력 가능합니다.";
}
