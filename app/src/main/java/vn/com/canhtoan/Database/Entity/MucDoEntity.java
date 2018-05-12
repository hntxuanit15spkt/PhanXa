package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Time;

@Entity(foreignKeys=@ForeignKey(entity=LoaiMucDoEntity.class, parentColumns = "id", childColumns = "id_leveltype"))
public class MucDoEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public int level_number;

    @NonNull
    public int id_leveltype;

}
