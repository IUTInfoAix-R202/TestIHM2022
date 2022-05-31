package fr.univ_amu.iut;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WordTest {

    @Test
    void testThatValidWordLettersAreValid(){
        Word wordleWord = new Word("valid");
        assertThat(wordleWord.letters()).isEqualTo(new char[]{'v', 'a', 'l', 'i', 'd'});
    }

    @Test
    void testThatFewWordLettersShouldRaiseException() {
        assertThatThrownBy(() -> new Word("vali")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Too few letters. Should be 5");
    }

    @Test
    void testThatTooManyWordLettersShouldRaiseException() {
        assertThatThrownBy(() -> new Word("toolong")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Too many letters. Should be 5");
    }

    @Test
    void testThatInvalidLettersShouldRaiseException(){
        assertThatThrownBy(() -> new Word("vali*")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("word contain invalid letters");
    }

    @Test
    void testThatPointShouldRaiseException(){
        assertThatThrownBy(() -> new Word("v.lid")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("word contain invalid letters");
    }

    @Test
    public void testThatTwoWordsAreNotTheSame() {
        Word firstWord = new Word("valid");
        Word secondWord = new Word("happy");
        assertThat(firstWord).isNotEqualTo(secondWord);
    }

    @Test
    public void testThatTwoWordsAreTheSame() {
        Word firstWord = new Word("valid");
        Word secondWord = new Word("valid");

        assertThat(firstWord).isEqualTo(secondWord);
    }

    @Test
    public void testThatLettersForGrassWord() {
        Word grassWord = new Word("grass");

        assertThat(grassWord.letters()).isEqualTo(new char[]{'g', 'r', 'a', 's', 's'});
    }

    @Test
    public void testThatTwoWordHasNoMatch() {
        Word firstWord = new Word("trees");
        Word secondWord = new Word("valid");

        assertThat(firstWord.matchesPositionWith(secondWord)).isEqualTo(List.of());
    }

    @Test
    public void testThatMatchesFirstLetter() {
        Word firstWord = new Word("trees");
        Word secondWord = new Word("table");

        assertThat(firstWord.matchesPositionWith(secondWord)).isEqualTo(List.of(1));
    }

    @Test
    public void testThatMatchesAllLetters() {
        Word firstWord = new Word("trees");
        Word secondWord = new Word("trees");

        assertThat(firstWord.matchesPositionWith(secondWord)).isEqualTo(List.of(1,2,3,4,5));
    }

    @Test
    public void testThatMatchesIncorrectPositions() {
        Word firstWord = new Word("trees");
        Word secondWord = new Word("drama");

        assertThat(firstWord.matchesPositionWith(secondWord)).isEqualTo(List.of(2));
        assertThat(firstWord.matchesIncorrectPositionWith(secondWord)).isEqualTo(List.of());
    }

    @Test
    public void testThatMatchesIncorrectPositionsWithMatch() {
        Word firstWord = new Word("alarm");
        Word secondWord = new Word("drama");

        assertThat(firstWord.matchesPositionWith(secondWord)).isEqualTo(List.of(3));
        assertThat(firstWord.matchesIncorrectPositionWith(secondWord)).isEqualTo(List.of(1,4,5));

        assertThat(secondWord.matchesPositionWith(firstWord)).isEqualTo(List.of(3));
        assertThat(secondWord.matchesIncorrectPositionWith(firstWord)).isEqualTo(List.of(2,4,5));
    }

}
