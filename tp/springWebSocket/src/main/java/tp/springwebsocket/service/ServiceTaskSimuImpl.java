package tp.springwebsocket.service;

import org.springframework.stereotype.Service;
import tp.springwebsocket.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceTaskSimuImpl implements ServiceTask{

    private long compteur=0;
    private Map<Long,Task> mapTasks = new HashMap<>();

    @Override
    public Task searchById(Long id) {
        Task task = mapTasks.get(id);
        if(task==null) throw new RuntimeException("task not found");
        return task;
    }

    @Override
    public List<Task> searchAll() {
        return new ArrayList<Task>(mapTasks.values());
    }

    @Override
    public Task create(Task obj) {
        obj.setNumero(++compteur);  //simule auto_incrémentation
        mapTasks.put(obj.getNumero(),obj); //ajoute dans la table d'association
        return obj;
    }

    @Override
    public Task update(Task obj) {
        mapTasks.put(obj.getNumero(),obj); //
        return obj;
    }

    @Override
    public void removeById(Long id) {
        mapTasks.remove(id);
    }
}
