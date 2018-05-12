package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Time;

@Entity(foreignKeys=@ForeignKey(entity=MucDoEntity.class, parentColumns="id", childColumns="id_mucdo"))
public class CauPhanXaEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public String question;

    public String answer;

    public Time time;

    @NonNull
    public int id_mucdo;
}
