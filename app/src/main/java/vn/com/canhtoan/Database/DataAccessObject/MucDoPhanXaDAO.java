package vn.com.canhtoan.Database.DataAccessObject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import vn.com.canhtoan.Database.Entity.MucDoPhanXaEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MucDoPhanXaDAO {
    @Insert(onConflict = REPLACE)
    void insert(MucDoPhanXaEntity mucDoEntity);
}
