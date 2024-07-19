package org.api_FullStack.service.implementation;

import org.api_FullStack.model.Project;
import org.api_FullStack.repository.ProjectRepository;
import org.api_FullStack.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProjectServiceImpl implements ProjectService {

    //Carrega a Interface do Repository para sobreescrever os metodos.
    private final ProjectRepository projectRepository;

    //Construtor do projeto para saber qual projeto esta sendo passado.
    public ProjectServiceImpl(ProjectRepository repository) {
        this.projectRepository = repository;
    }

    //Consultar se existe o projeto, senão retorna erro
    @Override
    public Project findById(Long id) {
        return projectRepository

                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
    //Consultar se existe o projeto, caso não tenha ele irá criar.
    @Override
    public Project create(Project projectToCreate) {
        //verifica se ja tem um projeto criado com esse codigo
        if(projectRepository.existsById(projectToCreate.getId())){
            throw new NoSuchElementException("Já existe projeto com esse código.");
        }
        return projectRepository.save(projectToCreate);
    }
}
