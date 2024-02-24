package com.app.edu.utils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
    private static SoundManager instance;
    private Clip clip;
    private final Queue<String> soundQueue;

    public SoundManager() {
        soundQueue = new LinkedList<>();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            if (!soundFile.exists()) {
                System.err.println("Sound file not found: " + soundFilePath);
                return;
            }

            if (clip != null && clip.isActive()) {
                soundQueue.offer(soundFilePath);
                return;
            }

            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();

            clip.addLineListener(event -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close();
                    playNextSound();
                }
            });

        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    private void playNextSound() {
        if (!soundQueue.isEmpty()) {
            String nextSound = soundQueue.poll();
            playSound(nextSound);
        }
    }

//    public boolean isPlaying() {
//        return clip != null && clip.getStatus() == MediaPlayer.Status.PLAYING;
//    }
//
//    public void playSound(String filePath) {
//        if (isPlaying()) {
//            soundQueue.offer(filePath);
//            System.out.println("A sound is already playing. Waiting for it to finish...");
//        } else {
//            play(filePath);
//        }
//    }
//
//    private void play(String filePath) {
//        Media media = new Media(new File(filePath).toURI().toString());
//        clip = new MediaPlayer(media);
//        clip.setOnEndOfMedia(() -> {
//            clip.dispose();
//            clip = null;
//            playNextSound();
//        });
//        clip.play();
//    }
//
//    private void playNextSound() {
//        if (!soundQueue.isEmpty()) {
//            String nextSound = soundQueue.poll();
//            play(nextSound);
//        }
//    }
}
