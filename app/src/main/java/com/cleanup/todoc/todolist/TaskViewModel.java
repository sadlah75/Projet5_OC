package com.cleanup.todoc.todolist;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    private final ProjectDataRepository projectDataSource;
    private final TaskDataRepository taskDataSource;
    private final Executor executor;

    private LiveData<Project> currentProject;

    public TaskViewModel(ProjectDataRepository projectDataSource, TaskDataRepository taskDataSource, Executor executor) {
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }

    public void init(long projectId) {
        if(this.currentProject != null) {
            return;
        }
        currentProject = this.projectDataSource.getProject(projectId);
    }

    // For Project

    public LiveData<Project> getProject() {
        return this.currentProject;
    }

    // For Task

    //public void createTask(long projectId, @NonNull String name, long creationTimestamp) {
    public void createTask(Task task) {
       executor.execute(new Runnable() {
           @Override
           public void run() {
               taskDataSource.createTask(task);
               //createTask(new Task(projectId,name,creationTimestamp));
           }
       });
    }

    public LiveData<List<Task>> getTasks(long projectId) {
        return this.taskDataSource.getTasks(projectId);
    }

    public LiveData<List<Task>> getAllTasks() {
        return this.taskDataSource.getAllTasks();
    }

    public void deleteTask(long taskId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                taskDataSource.deleteTask(taskId);
            }
        });
    }
}
