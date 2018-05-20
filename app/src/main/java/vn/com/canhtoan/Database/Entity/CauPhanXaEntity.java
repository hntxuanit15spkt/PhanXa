package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = MucDoPhanXaEntity.class, parentColumns = "id", childColumns = "id_mucdo"))
public class CauPhanXaEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public String question;

    public String answer;

    public double time;

    @NonNull
    public int id_mucdo;
}
