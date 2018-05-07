package vn.com.canhtoan.models;

public class CauDoc {
    private String sentence;
    private int sound;
    private int time;

    public CauDoc(String sentence, int sound, int time) {
        this.setSentence(sentence);
        this.setSound(sound);
        this.setTime(time);
    }


    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
