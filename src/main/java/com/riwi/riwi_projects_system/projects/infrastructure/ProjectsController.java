// package com.riwi.riwi_projects_system.projects.infrastructure;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.riwi.riwi_projects_system.projects.aplication.ProjectsCreator;
// import com.riwi.riwi_projects_system.projects.domain.ProjectsEntity;
// import
// com.riwi.riwi_projects_system.projects.infrastructure.dtos.request.CreateProjectsDto;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;

// @RestController
// @RequestMapping("/projects")
// public class ProjectsController {

// @Autowired
// private ProjectsCreator projectsCreator;

// public ProjectsController(ProjectsCreator projectsCreator) {
// this.projectsCreator = projectsCreator;
// }

// @PostMapping("/create")
// public CreateProjectsDto createProject(@RequestBody CreateProjectsDto
// createProjectsDto) {
// return projectsCreator.createProject(createProjectsDto);
// }

// @GetMapping("/readById{id}")
// public ResponseEntity<ProjectsEntity> getProjectById(@PathVariable Long id) {
// return
// projectsCreator.getProjectById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
// }

// @GetMapping("/readAll")
// public List<ProjectsEntity> getAllProjects() {
// return projectsCreator.getAllProjects();
// }

// @PutMapping("/update{id}")
// public ResponseEntity<ProjectsEntity> updateProjects(@PathVariable Long id,
// @RequestBody ProjectsEntity projectsEntityDetails) {
// return ResponseEntity.ok(projectsCreator.updateProjectsEntity(id,
// projectsEntityDetails));
// }

// @DeleteMapping("/delete{id}")
// public ResponseEntity<ProjectsEntity> deleteProject(@PathVariable Long id) {
// projectsCreator.deleteProject(projectsCreator.getProjectById(id).get());
// return ResponseEntity.noContent().build();
// }

// }