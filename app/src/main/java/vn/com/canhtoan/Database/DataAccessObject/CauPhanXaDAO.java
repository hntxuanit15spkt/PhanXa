package vn.com.canhtoan.Database.DataAccessObject;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import vn.com.canhtoan.Database.Entity.CauPhanXaEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CauPhanXaDAO {

    @Insert(onConflict = REPLACE)
    void insert(CauPhanXaEntity cauPhanXaEntity);

    @Query("SELECT * FROM CauPhanXaEntity")
    LiveData<List<CauPhanXaEntity>> getAllCauPhanXa();
}
