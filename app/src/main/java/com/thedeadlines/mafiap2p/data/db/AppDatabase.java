package com.thedeadlines.mafiap2p.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.thedeadlines.mafiap2p.AppConstants;
import com.thedeadlines.mafiap2p.data.db.converters.DateConverter;
import com.thedeadlines.mafiap2p.data.db.game.GameDao;
import com.thedeadlines.mafiap2p.data.db.game.GameEntity;
import com.thedeadlines.mafiap2p.data.db.gameplayerjoin.GamePlayerJoinDao;
import com.thedeadlines.mafiap2p.data.db.gameplayerjoin.GamePlayerJoinEntity;
import com.thedeadlines.mafiap2p.data.db.player.PlayerDao;
import com.thedeadlines.mafiap2p.data.db.player.PlayerEntity;

@Database(entities = {GameEntity.class, PlayerEntity.class, GamePlayerJoinEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;

    public abstract GameDao gameDao();

    public abstract PlayerDao playerDao();

    public abstract GamePlayerJoinDao gamePlayerJoinDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, AppConstants.DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppDatabase database = getInstance(appContext);
//                        Executors.newSingleThreadScheduledExecutor().execute(() -> {

//                        });
                        database.setDatabaseCreated();
                    }
                })
                .build();
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(AppConstants.DB_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}
