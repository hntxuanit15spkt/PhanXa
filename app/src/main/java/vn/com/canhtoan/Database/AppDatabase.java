package vn.com.canhtoan.Database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import vn.com.canhtoan.Database.Converter.DateConverter;
import vn.com.canhtoan.Database.DataAccessObject.CauDocCuaNguoiHocDAO;
import vn.com.canhtoan.Database.DataAccessObject.CauDocDAO;
import vn.com.canhtoan.Database.DataAccessObject.CauPhanXaDAO;
import vn.com.canhtoan.Database.DataAccessObject.LoaiMucDoDAO;
import vn.com.canhtoan.Database.DataAccessObject.MucDoDAO;
import vn.com.canhtoan.Database.DataAccessObject.UserDAO;
import vn.com.canhtoan.Database.DataAccessObject.User_CauDocDAO;
import vn.com.canhtoan.Database.DataAccessObject.User_CauPhanXaDAO;
import vn.com.canhtoan.Database.Entity.CauDocCuaNguoiHocEntity;

@Database(entities = {CauDocCuaNguoiHocEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "English_Reflex.db";

    public abstract CauDocCuaNguoiHocDAO productDao();

    public abstract User_CauPhanXaDAO user_cauPhanXa();

    public abstract UserDAO userDAO();

    public abstract User_CauDocDAO user_cauDocDAO();

    public abstract MucDoDAO mucDoDAO();

    public abstract LoaiMucDoDAO loaiMucDoDAO();

    public abstract CauDocDAO cauDocDAO();

    public abstract CauPhanXaDAO cauphanxaDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    /*public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }*/

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    /*private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        //executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            //List<ProductEntity> products = DataGenerator.generateProducts();
                           // List<CommentEntity> comments = DataGenerator.generateCommentsForProducts(products);

                            //insertData(database, products, comments);
                            // notify that the database was created and it's ready to be used
                            //database.setDatabaseCreated();
                        //});
                    }
                }).build();
    }*/

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    /*private static void insertData(final AppDatabase database, final List<ProductEntity> products,
                                   final List<CommentEntity> comments) {
        database.runInTransaction(() -> {
            database.productDao().insertAll(products);
            database.commentDao().insertAll(comments);
        });
    }*/

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}
