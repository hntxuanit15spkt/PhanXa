package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = {@ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "id_user"),
        @ForeignKey(entity = CauDocEntity.class, parentColumns = "id", childColumns = "id_caudoc")})
public class User_CauDocEntity {

    @PrimaryKey
    @NonNull
    public int id;

    @NonNull
    public int id_user;

    @NonNull
    public int id_caudoc;

    public double read_time_user;

    public int sound_user;

    public Boolean is_restudy;
}
