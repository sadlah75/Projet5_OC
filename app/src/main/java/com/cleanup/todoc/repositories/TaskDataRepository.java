package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDAO;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDAO taskDAO;

    public TaskDataRepository(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public void createTask(Task task) {
        this.taskDAO.createTask(task);
    }

    public LiveData<List<Task>> getTasks(long projectId) {
        return this.taskDAO.getTasksByProject(projectId);
    }

    public LiveData<List<Task>> getAllTasks() {
        return this.taskDAO.getAllTasks();
    }

    public void deleteTask(long taskId) {
        this.taskDAO.deleteTask(taskId);
    }
}
