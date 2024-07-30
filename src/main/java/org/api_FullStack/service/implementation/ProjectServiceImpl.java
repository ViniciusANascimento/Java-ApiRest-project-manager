package org.api_FullStack.service.implementation;

import org.api_FullStack.controller.ProjectController;
import org.api_FullStack.model.Project;
import org.api_FullStack.model.User;
import org.api_FullStack.repository.ProjectRepository;
import org.api_FullStack.repository.UserRepository;
import org.api_FullStack.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    //Carrega a Interface do Repository para sobreescrever os metodos.
    private final ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

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

    @Override
    public  List<Project> findByUserId(Long idUser){
        return projectRepository.findProjectsByUserId(idUser);
    }
    //Consultar se existe o projeto, caso não tenha ele irá criar.
    @Override
    public Project create(Project projectToCreate) {
        //verifica se ja tem um projeto criado com esse codigo
        if(projectRepository.existsById(projectToCreate.getId())){
            throw new NoSuchElementException("Já existe projeto com esse código.");
        }
        //Verifico se o codigo de usuario que esta sendo passado para salvar no projeto, existe.
        Optional<User> userOptional = userRepository.findById(projectToCreate.getUserID());
        if(!userOptional.isPresent()){
            throw new NoSuchElementException("Usuário não cadastrado no sistema");
        }

        return projectRepository.save(projectToCreate);
    }

    @Override
    public Project update(Project projectUpdate) {
        if(!projectRepository.existsById(projectUpdate.getId())) {
            throw new NoSuchElementException("Projeto não existe no sistema");
        }
        //salvar o projeto.
        return projectRepository.save(projectUpdate);
    }

    @Override
    public void delete(Long id, Project projectToDelete) {
        //Garanto que o projeto que esta sendo passado é o que vai ser apagado.
        projectToDelete.setId(id);
        //Verifico novamente se o projeto existe.
        if(!projectRepository.existsById(projectToDelete.getId())){
            throw new NoSuchElementException("Projeto não existe no sistema");
        }
        projectRepository.delete(projectToDelete);
    }
}
