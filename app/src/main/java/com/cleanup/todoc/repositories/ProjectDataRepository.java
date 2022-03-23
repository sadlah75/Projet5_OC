package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDAO;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDAO projectDAO;


    public ProjectDataRepository(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }


    // --- GET ---
    public LiveData<List<Project>> getAllProjects() {
       return projectDAO.getAllProjects();
    }

    public LiveData<Project> getProject(long projectId) {
        return projectDAO.getProject(projectId);
    }
}
