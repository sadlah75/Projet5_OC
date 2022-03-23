package com.cleanup.todoc.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.ProjectDAO;
import com.cleanup.todoc.database.dao.TaskDAO;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.concurrent.Executors;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class ToDocDatabase extends RoomDatabase {

    //--- DAO ---
    public abstract ProjectDAO projectDao();
    public abstract TaskDAO taskDao();

    // --- SINGLETON ---
    private static volatile ToDocDatabase INSTANCE;

    //--- Instance
    public static ToDocDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (ToDocDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ToDocDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }

            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        Project[] projects = Project.getAllProjects();
                        for (Project project : projects) {
                            INSTANCE.projectDao().createProject(project);
                        }
                    }
                });
            }
        };
    }

}
