package vn.com.canhtoan.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import vn.com.canhtoan.Database.Converter.DateConverter;
import vn.com.canhtoan.Database.DataAccessObject.CauDocCuaNguoiHocDAO;
import vn.com.canhtoan.Database.DataAccessObject.CauDocDAO;
import vn.com.canhtoan.Database.DataAccessObject.CauPhanXaDAO;
import vn.com.canhtoan.Database.DataAccessObject.MucDoPhanXaDAO;
import vn.com.canhtoan.Database.DataAccessObject.UserDAO;
import vn.com.canhtoan.Database.DataAccessObject.User_CauDocDAO;
import vn.com.canhtoan.Database.DataAccessObject.User_CauPhanXaDAO;
import vn.com.canhtoan.Database.Entity.CauDocCuaNguoiHocEntity;
import vn.com.canhtoan.Database.Entity.CauDocEntity;
import vn.com.canhtoan.Database.Entity.CauPhanXaEntity;
import vn.com.canhtoan.Database.Entity.MucDoPhanXaEntity;
import vn.com.canhtoan.Database.Entity.UserEntity;
import vn.com.canhtoan.Database.Entity.User_CauDocEntity;
import vn.com.canhtoan.Database.Entity.User_CauPhanXaEntity;

@Database(entities = {CauDocCuaNguoiHocEntity.class, CauDocEntity.class,
        CauPhanXaEntity.class,
        MucDoPhanXaEntity.class, User_CauDocEntity.class,
        User_CauPhanXaEntity.class, UserEntity.class}, version = 3)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    /*Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            .allowMainThreadQueries()
                            .build();*/

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "PhanXa_Database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Tham khảo
     */
    /*public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }*/
    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract CauDocCuaNguoiHocDAO productDao();

    public abstract User_CauPhanXaDAO user_cauPhanXa();

    public abstract UserDAO userDAO();

    public abstract User_CauDocDAO user_cauDocDAO();

    public abstract MucDoPhanXaDAO mucDoPhanXaDAO();

    //private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public abstract CauDocDAO cauDocDAO();

    public abstract CauPhanXaDAO cauphanxaDao();
}
