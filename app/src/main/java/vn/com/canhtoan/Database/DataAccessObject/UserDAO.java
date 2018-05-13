package vn.com.canhtoan.Database.DataAccessObject;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import vn.com.canhtoan.Database.Entity.UserEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDAO {

    @Query("select * from UserEntity where id = :id")
        //public UserEntity getUserEntityById(int id);
    LiveData<UserEntity> getUserById(int id);


    @Query("SELECT * FROM UserEntity")
    LiveData<List<UserEntity>> getAllUsers();

    @Insert(onConflict = REPLACE)
    public void insertUser(UserEntity user);

    @Insert(onConflict = REPLACE)
    public void insertUsers(UserEntity... users);

    @Update
    public int updateUser(UserEntity user);

    @Delete
    public int deleteUser(UserEntity user);
}
