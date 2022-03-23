package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createTask(Task task);

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllTasks();

    @Query("DELETE FROM Task WHERE id = :taskId")
    int deleteTask(long taskId);

    @Query("SELECT * FROM Task WHERE projectId = :projectId")
    LiveData<List<Task>> getTasksByProject(long projectId);
}
