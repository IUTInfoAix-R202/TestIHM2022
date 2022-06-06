package fr.univ_amu.iut;

import fr.univ_amu.iut.game.Dictionary;
import fr.univ_amu.iut.game.Word;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class WordleData {
    List<String> solutions =
            new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/data/solutions.txt")))).lines().toList();
    Dictionary dictionary =
            new Dictionary(new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/data/dictionary.txt")))).lines().map(Word::new).toArray(Word[]::new));

    public WordleData() {
        System.out.println(getWordOfTheDay());
    }

    public Word getWordOfTheDay() {
        int indexOfTheDay = Long.valueOf(LocalDate.of(2022, 6, 5).until(LocalDate.now(), ChronoUnit.DAYS)).intValue();
        String solutionOfTheDay = solutions.get(indexOfTheDay);
        return new Word(solutionOfTheDay);
    }

    List<Integer> getCorrectPosition(List<Character> characters) {
        Word guess = new Word(characters);
        return guess.matchesPositionWith(getWordOfTheDay());
    }

    List<Integer> getIncorrectPosition(List<Character> characters) {
        Word guess = new Word(characters);
        return guess.matchesIncorrectPositionWith(getWordOfTheDay());
    }

    List<Integer> getWrongLetterPosition(List<Character> characters) {
        return IntStream.range(0, 5).filter(value -> ! getCorrectPosition(characters).contains(value)).filter(value -> ! getIncorrectPosition(characters).contains(value)).boxed().toList();
    }

    public boolean isWordValid(List<Character> characters) {
        return dictionary.includesWord(new Word(characters));
    }
}
