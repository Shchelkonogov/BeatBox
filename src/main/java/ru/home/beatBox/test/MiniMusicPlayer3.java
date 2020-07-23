package ru.home.beatBox.test;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayer3 {

    static JFrame frame = new JFrame("Мой первый музыкальный клип");
    static MyDrawPanel m1;

    public static void main(String[] args) {
        MiniMusicPlayer3 mini = new MiniMusicPlayer3();
        mini.go();
    }

    public void setUpGui() {
        m1 = new MyDrawPanel();
        frame.setContentPane(m1);
        frame.setBounds(30, 30, 300, 300);
        frame.setVisible(true);
    }

    public void go() {
        setUpGui();

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            sequencer.addControllerEventListener(m1, new int[] {127});

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int r = 0;
            for (int i = 5; i < 61; i+= 4) {
                r = (int) ((Math.random() * 50) + 1);
                track.add(makeEvent(144, 1, r, 100, i));
                track.add(makeEvent(176, 1, 127, 0, i));
                track.add(makeEvent(128, 1, r, 100, i + 2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MidiEvent makeEvent(int command, int chan, int one, int two, int tick) {
        MidiEvent event = null;

        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(command, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e) {

        }

        return event;
    }

    class MyDrawPanel extends JPanel implements ControllerEventListener {

        boolean msg = false;

        @Override
        protected void paintComponent(Graphics g) {
            if (msg) {
                Graphics2D g2 = (Graphics2D) g;

                int r = (int) (Math.random() * 250);
                int gr = (int) (Math.random() * 250);
                int b = (int) (Math.random() * 250);

                g2.setColor(new Color(r, gr, b));

                int height = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);
                int x = (int) ((Math.random() * 40) + 10);
                int y = (int) ((Math.random() * 40) + 10);

                g2.fillRect(x, y, width, height);

                msg = false;
            }
        }

        public void controlChange(ShortMessage event) {
            msg = true;
            repaint();
        }
    }
}
