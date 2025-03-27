package tp.springwebsocket.service;

import tp.springwebsocket.model.Task;

import java.util.List;

public interface ServiceTask {
    public Task searchById(Long id);
    public List<Task> searchAll();
    public Task create(Task obj);
    public Task update(Task obj);
    public void removeById(Long id);
}
