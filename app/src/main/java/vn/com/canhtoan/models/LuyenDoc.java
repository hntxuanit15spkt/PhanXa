package vn.com.canhtoan.models;

public class LuyenDoc {
    private String sentence;
    private int sound;

    public LuyenDoc(String sentence, int sound) {
        this.sentence = sentence;
        this.sound = sound;
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
}
