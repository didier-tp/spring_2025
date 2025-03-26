package tp.appliSpring.bank.core.service.direct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.bank.core.model.Task;
import tp.appliSpring.bank.core.service.ServiceTask;
import tp.appliSpring.bank.persistence.entity.TaskEntity;
import tp.appliSpring.bank.persistence.repository.TaskRepository;
import tp.appliSpring.generic.service.direct.GenericServiceDirectImpl;

@Service
@Transactional
public class ServiceTaskDirectImpl  extends GenericServiceDirectImpl<Task, TaskEntity,Long> implements ServiceTask {

    @Autowired
    private TaskRepository daoTask;

    @Autowired
    public ServiceTaskDirectImpl(TaskRepository daoTask){
        super(Task.class,TaskEntity.class,daoTask);
        this.daoTask=daoTask;
    }
}
