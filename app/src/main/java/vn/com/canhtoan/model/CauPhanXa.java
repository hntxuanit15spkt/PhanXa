package vn.com.canhtoan.model;

public class CauPhanXa {
    private String cauHoi;
    private String cauTraLoi1;
    private String cauTraLoi2;

    public CauPhanXa() {
    }

    public CauPhanXa(String cauHoi, String cauTraLoi1, String cauTraLoi2) {
        this.cauHoi = cauHoi;
        this.cauTraLoi1 = cauTraLoi1;
        this.cauTraLoi2 = cauTraLoi2;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getCauTraLoi1() {
        return cauTraLoi1;
    }

    public void setCauTraLoi1(String cauTraLoi1) {
        this.cauTraLoi1 = cauTraLoi1;
    }

    public String getCauTraLoi2() {
        return cauTraLoi2;
    }

    public void setCauTraLoi2(String cauTraLoi2) {
        this.cauTraLoi2 = cauTraLoi2;
    }
    /*public CauPhanXa addCauPhanXa(){
        ArrayList<CauPhanXa> listcau = new ArrayList<CauPhanXa>();

        listcau.add(new CauPhanXa("What is your name?","John", "My name is John"));
        listcau.add(new CauPhanXa("What is your job?","Student", "I am a student"));
        listcau.add(new CauPhanXa("Do you have a girl friend?","No", "No, i do not"));
        listcau.add(new CauPhanXa("How are you?","I am fine", "Fine"));
        listcau.add(new CauPhanXa("How old are you?","eighteen", "I am eighteen years old"));
        listcau.add(new CauPhanXa("Where are you from?","Viet Nam", "I from Viet Nam"));
        listcau.add(new CauPhanXa("What is your hobby?","Reading book", "My hobby is reading book"));
        listcau.add(new CauPhanXa("1 + 1 = ?","2", "two"));
        listcau.add(new CauPhanXa("Are you like American?","Yes", "Yes I am"));
        listcau.add(new CauPhanXa("Are you tired?","Yes", "Yes I am"));

        return this;
    }*/
}
