package codemusic;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        NoteConverter noteConverter = new NoteConverter();

        CodeMusicGame game = new CodeMusicGame(inputView, outputView, noteConverter);

        game.start();
    }
}