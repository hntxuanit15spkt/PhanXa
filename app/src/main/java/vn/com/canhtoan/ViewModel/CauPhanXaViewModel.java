package vn.com.canhtoan.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import vn.com.canhtoan.Database.AppDatabase;
import vn.com.canhtoan.Database.DataAccessObject.CauPhanXaDAO;
import vn.com.canhtoan.Database.Entity.CauPhanXaEntity;

public class CauPhanXaViewModel extends AndroidViewModel{

    private CauPhanXaDAO cauPhanXaDAO;

    private LiveData<List<CauPhanXaEntity>> mAllCauPhanXa;

    private AppDatabase mDb;

    public CauPhanXaViewModel(Application application)
    {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        cauPhanXaDAO = db.cauphanxaDao();
        mAllCauPhanXa = cauPhanXaDAO.getAllCauPhanXa();
        createDb();
    }

    public LiveData<List<CauPhanXaEntity>> getmAllCauPhanXa() {
        return mAllCauPhanXa;
    }

    public void insert(CauPhanXaEntity cauPhanXaEntity){
        new insertAsyncTask(cauPhanXaDAO).execute(cauPhanXaEntity);
    }

    public void createDb() {
        mDb = AppDatabase.getDatabase(this.getApplication());

        // Populate it with initial data
        vn.com.canhtoan.Database.utils.DatabaseInitializer.populateAsync(mDb);
    }

    private static class insertAsyncTask extends AsyncTask<CauPhanXaEntity, Void, Void> {
        private CauPhanXaDAO mAsyncTaskDao;

        insertAsyncTask(CauPhanXaDAO cauPhanXaDAO){
            mAsyncTaskDao = cauPhanXaDAO;
        }

        @Override
        protected Void doInBackground(CauPhanXaEntity... cauPhanXaEntities) {
            mAsyncTaskDao.insert(cauPhanXaEntities[0]);
            return null;
        }
    }
}
