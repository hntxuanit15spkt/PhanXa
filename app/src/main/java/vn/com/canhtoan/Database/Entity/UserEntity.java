package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
public class UserEntity {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer id;

    public Date dateUsed;

    public Date start_time;

    public Date end_time;
}
