package ru.home.beatBox.test;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class MusicTest1 {

    public static void main(String[] args) {
        MusicTest1 mt = new MusicTest1();
        mt.play();
    }

    public void play() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            System.out.println("Мы полчили синтезатор");
        } catch (MidiUnavailableException e) {
            System.out.println("Неудача");
        }
    }
}
