package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Time;

@Entity
public class CauDocCuaNguoiHocEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public String content;

    public int sound;

    public Time time_user;

    public Time time_recommended;

    @NonNull
    public int id_user;
}
