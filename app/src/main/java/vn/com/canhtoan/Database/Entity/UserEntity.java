package vn.com.canhtoan.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

import vn.com.canhtoan.Database.Converter.DateConverter;

@Entity
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer id;

    @TypeConverters(DateConverter.class)
    public Date dateUsed;

    @TypeConverters(DateConverter.class)
    public Date start_time;

    @TypeConverters(DateConverter.class)
    public Date end_time;
}
