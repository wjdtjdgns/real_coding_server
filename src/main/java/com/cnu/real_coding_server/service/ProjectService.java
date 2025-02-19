package com.cnu.real_coding_server.service;

import com.cnu.real_coding_server.entity.Project;
import com.cnu.real_coding_server.model.request.ProjectRequest;
import com.cnu.real_coding_server.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project createProject(ProjectRequest projectRequest) {
        return projectRepository.save(projectRequest.toEntity());
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProject(Integer projectId) {
        return projectRepository.findById(projectId);
    }

    public Optional<Project> updateProject(Integer postId, ProjectRequest projectRequest) {
        return projectRepository.findById(postId)
                .map(project -> {
                    project.setTitle(projectRequest.getTitle());
                    project.setDescription(projectRequest.getDescription());
                    project.setSummary(projectRequest.getSummary());
                    project.setEndDate(projectRequest.getEndDate());
                    project.setStartDate(projectRequest.getStartDate());
                    project.setIsInProgress(projectRequest.getIsInProgress());
                    return projectRepository.save(project);
                });
    }

    public void deleteProject(Integer projectId) {
        projectRepository.findById(projectId)
                .ifPresent(projectRepository::delete);
    }
}
