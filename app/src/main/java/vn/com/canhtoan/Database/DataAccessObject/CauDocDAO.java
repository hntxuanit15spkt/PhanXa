package vn.com.canhtoan.Database.DataAccessObject;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import vn.com.canhtoan.Database.Entity.CauDocEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CauDocDAO {
    @Query("select * from CauDocEntity where id = :id")
        //public UserEntity getUserEntityById(int id);
    LiveData<CauDocEntity> getCauDocById(int id);


    @Query("SELECT * FROM CauDocEntity")
    LiveData<List<CauDocEntity>> getAllCauDocs();

    @Insert(onConflict = REPLACE)
    public void insertCauDoc(CauDocEntity caudoc);

    @Insert(onConflict = REPLACE)
    public void insertCauDocs(CauDocEntity... caudocs);

    @Update
    public int updateCauDoc(CauDocEntity caudoc);

    @Delete
    public int deleteCauDoc(CauDocEntity caudoc);
}
