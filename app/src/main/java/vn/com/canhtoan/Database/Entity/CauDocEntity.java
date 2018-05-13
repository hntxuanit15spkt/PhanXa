package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class CauDocEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public String sentence;

    public int sound;

    public int time;

    @NonNull
    public int getId() {
        return id;
    }

    public String getSentence() {
        return sentence;
    }

    public int getSound() {
        return sound;
    }

    public int getTime() {
        return time;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
