package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import java.sql.Time;

@Entity
public class User_CauPhanXaEntity {

    @NonNull
    public int id_user;

    @NonNull
    public int id_cauphanxa;

    public String reply_content_user;

    public Time reply_time_user;

    public int sound_user;
}
