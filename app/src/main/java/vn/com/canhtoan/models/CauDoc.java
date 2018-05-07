package vn.com.canhtoan.models;

public class CauDoc {
    private String sentence;
    private int sound;
    private int thoiGianDoc;

    public CauDoc(String sentence, int sound, int thoiGianDoc) {
        this.setSentence(sentence);
        this.setSound(sound);
        this.setThoiGianDoc(thoiGianDoc);
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

    public int getThoiGianDoc() {
        return thoiGianDoc;
    }

    public void setThoiGianDoc(int thoiGianDoc) {
        this.thoiGianDoc = thoiGianDoc;
    }
}
