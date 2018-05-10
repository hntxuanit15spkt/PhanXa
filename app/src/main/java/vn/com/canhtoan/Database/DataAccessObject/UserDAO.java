package vn.com.canhtoan.Database.DataAccessObject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import vn.com.canhtoan.Database.Entity.UserEntity;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface UserDAO {

    @Query("select * from UserEntity where id = :id")
    UserEntity loadUserEntityById(int id);

    @Insert(onConflict = IGNORE)
    void insertUser(UserEntity user);
}
