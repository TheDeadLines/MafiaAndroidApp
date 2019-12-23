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
import com.thedeadlines.mafiap2p.data.db.role.RoleDao;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;
import com.thedeadlines.mafiap2p.data.db.role.RoleGenerator;
import com.thedeadlines.mafiap2p.game.GameConstants;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;

@Database(entities = {GameEntity.class, PlayerEntity.class, GamePlayerJoinEntity.class,
        RoleEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;

    public abstract GameDao gameDao();

    public abstract PlayerDao playerDao();

    public abstract GamePlayerJoinDao gamePlayerJoinDao();

    public abstract RoleDao roleDao();

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
                        Executors.newSingleThreadScheduledExecutor().execute(() -> {
                            List<RoleEntity> roleEntityList = RoleGenerator.generateRoles(appContext);
                            UUID deviceUUID = UUID.randomUUID();
                            appContext
                                    .getSharedPreferences(AppConstants.APP_PREFERENCES, Context.MODE_PRIVATE)
                                    .edit()
                                    .putString(AppConstants.DEVICE_ID_PREFERENCE_KEY, deviceUUID.toString());
                            PlayerEntity devicePlayer = new PlayerEntity(AppConstants.MY_PLAYER_DATABASE_ID, deviceUUID.toString());
                            GameEntity defaultGame = new GameEntity(AppConstants.DEFAULT_GAME_ID,
                                    AppConstants.MY_PLAYER_DATABASE_ID,
                                    new Date(),
                                    GameConstants.DEFAULT_MAFIA_COUNT,
                                    GameConstants.DEFAULT_PLAYERS_COUNT);
                            getInstance(appContext).putDefaultGame(defaultGame);
                            getInstance(appContext).putSelfPlayer(devicePlayer);
                            getInstance(appContext).populateRoles(roleEntityList);

                        });
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

    private void populateRoles(final List<RoleEntity> roles) {
        sInstance.runInTransaction(() -> sInstance.roleDao().insert(roles));
    }

    private void putSelfPlayer(final PlayerEntity entity) {
        sInstance.runInTransaction(() -> sInstance.playerDao().insert(entity));
    }

    private void putDefaultGame(final GameEntity entity) {
        sInstance.runInTransaction(() -> sInstance.gameDao().insert(entity));
    }
}
