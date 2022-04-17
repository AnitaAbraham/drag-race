package menu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class WonSound {
    private Clip clip;
    private AudioInputStream ais;

    public WonSound() {
        try {
            String filePath = "Winner.wav";
            ais = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-30.0f);
    }

    public void play() {
        clip.setMicrosecondPosition(0);
        clip.start();
    }
}
