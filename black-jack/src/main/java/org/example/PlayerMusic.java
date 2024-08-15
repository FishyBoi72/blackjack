package org.example;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

// Define the PlayerMusic class, which handles the playback of audio files.
public class PlayerMusic {

    // Declare a private Clip object to handle the audio clip that will be played.
    private Clip clip;

    // Define the playMusic method, which takes a file path (musicLocation) as a parameter.
    public void playMusic(String musicLocation) {
        try {
            // Load the audio file as an InputStream from the classpath resources.
            InputStream audioSrc = getClass().getResourceAsStream(musicLocation);
            if (audioSrc == null) {
                // If the file cannot be found, print an error message and exit the method.
                System.out.println("Can't find file: " + musicLocation);
                return;
            }
            // Convert the InputStream into an AudioInputStream for use with the audio system.
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioSrc);
            // Obtain a Clip object from the audio system to handle the playback.
            clip = AudioSystem.getClip();
            // Open the audio clip with the provided AudioInputStream.
            clip.open(audioInput);
            // Obtain the gain control for the clip to adjust the volume.
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            // Set the volume by reducing it by 10 decibels.
            gainControl.setValue(-10.0f); 
            // Start playing the audio clip.
            clip.start();
            // Loop the audio clip continuously.
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            // If an exception occurs during playback, print the stack trace for debugging.
            e.printStackTrace();
        }
    }

    // Define the stopMusic method to stop the playback of the audio clip.
    public void stopMusic() {
        // Check if the clip is not null and is currently running.
        if (clip != null && clip.isRunning()) {
            // Stop the audio clip.
            clip.stop();
        }
    }
}
