package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Time;

@Entity(foreignKeys = {@ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "id_user"),
@ForeignKey(entity = CauPhanXaEntity.class, parentColumns = "id", childColumns = "id_cauphanxa")})
public class User_CauPhanXaEntity {

    @PrimaryKey
    @NonNull
    public int id;

    @NonNull
    public int id_user;

    public int id_cauphanxa;

    public String reply_content_user;

    public double reply_time_user;

    public int sound_user;
}
