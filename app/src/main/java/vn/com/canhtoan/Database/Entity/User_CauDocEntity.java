package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import java.sql.Time;

@Entity
public class User_CauDocEntity {
    @NonNull
    public int id_user;

    @NonNull
    public int id_caudoc;

    public Time read_time_user;

    public int sound_user;

    public Boolean is_restudy;
}
