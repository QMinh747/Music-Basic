package com.example.music;

import android.content.Context;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.music.models.DataModel;


public class MyExoplayer {
    private static ExoPlayer exoPlayer = null;
    private static DataModel currentSong = null;

    public static DataModel getCurrentSong() {
        return currentSong;
    }

    public static ExoPlayer getInstance() {
        return exoPlayer;
    }

    public static void startPlaying(Context context, DataModel song) {
        if (exoPlayer == null) {
            exoPlayer = new ExoPlayer.Builder(context).build();
        }

        if (currentSong != song) {
            currentSong = song;
            updateCount();
            String songUrl = currentSong.getUrl();
            if (songUrl != null) {
                MediaItem mediaItem = MediaItem.fromUri(songUrl);
                exoPlayer.setMediaItem(mediaItem);
                exoPlayer.prepare();
                exoPlayer.play();
            }
        }
    }

    public static void updateCount() {
        DataModel currentSong = MyExoplayer.getCurrentSong();
        if (currentSong != null && currentSong.getId() != null) {
            String id = currentSong.getId();
            FirebaseFirestore.getInstance().collection("songs")
                    .document(id)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        Long latestCount = documentSnapshot.getLong("count");
                        if (latestCount == null) {
                            latestCount = 1L;
                        } else {
                            latestCount += 1;
                        }

                        FirebaseFirestore.getInstance().collection("songs")
                                .document(id)
                                .update("count", latestCount);
                    });
        }
    }

}
