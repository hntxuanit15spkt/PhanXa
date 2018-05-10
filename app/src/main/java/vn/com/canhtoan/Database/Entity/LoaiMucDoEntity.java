package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class LoaiMucDoEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public String name;
}
