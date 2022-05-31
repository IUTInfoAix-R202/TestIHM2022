package fr.univ_amu.iut;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Word {
    String letters;
    public Word(String word) {
        isValidWord(word);
        this.letters = word;
    }

    private void isValidWord(String word) {
        hasACorrectLength(word);
        hasOnlyValidLetters(word);
    }

    private void hasOnlyValidLetters(String word) {
        if(! word.matches("[a-z]*"))
            throw new IllegalArgumentException("word contain invalid letters");
    }

    private void hasACorrectLength(String word) {
        if(word.length() > 5)
            throw new IllegalArgumentException("Too many letters. Should be 5");

        if(word.length() < 5)
            throw new IllegalArgumentException("Too few letters. Should be 5");
    }

    public char[] letters() {
        return letters.toCharArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return Objects.equals(letters, word.letters);
    }

    @Override
    public int hashCode() {
        return letters != null ? letters.hashCode() : 0;
    }

    @Override
    public String toString() {
        return letters;
    }

    public List<Integer> matchesPositionWith(Word word) {
        return IntStream.range(1, Math.min(this.letters.length()+1, word.letters.length()+1))
                .filter(i -> this.letters.charAt(i-1) == word.letters.charAt(i-1)).boxed().collect(Collectors.toList());
    }

    public List<Integer> matchesIncorrectPositionWith(Word word) {
        List<Integer> matchesIncorrectPosition = IntStream.range(1, Math.min(this.letters.length()+1, word.letters.length()+1))
                .filter(i -> word.letters.indexOf(this.letters.charAt(i-1))!= -1 ).boxed().collect(Collectors.toList());
        matchesIncorrectPosition.removeAll(matchesPositionWith(word));
        return matchesIncorrectPosition;
    }
}
