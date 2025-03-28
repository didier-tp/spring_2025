package tp.springwebsocket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import tp.springwebsocket.model.Task;
import tp.springwebsocket.model.TaskListMessage;
import tp.springwebsocket.model.TaskMessage;
import tp.springwebsocket.service.ServiceTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class WebSocketTaskController {

	@Autowired
	private ServiceTask serviceTask;

	private List<Task> lastTasksList = new ArrayList<Task>();
	private String lastMessage = "";

	public void updateDoneTask(TaskMessage doneTaskMessage) {
		Task partialDoneTask = doneTaskMessage.getTask();
		long numero = partialDoneTask.getNumero();
		String author = partialDoneTask.getAuthor();
		String doneTaskResponse = partialDoneTask.getResponse();
        try {
            Task task = serviceTask.searchById(numero);
			//a faire en TP :
			task.setAuthor(author);
			task.setResponse(doneTaskResponse);
			task.setTimestamp(new Date());
			//ajouter dans task les valeurs récupérées de partialDoneTask
            //ajouter la date courante dans partie timestamp de task
            //enregistrer task modifiée dans la base via serviceTask
			serviceTask.update(task);
            this.lastMessage="Task Done by "+author + " with numero=" + numero;
        } catch (Exception e) {
			this.lastMessage="WrongTask Done by "+author + " with non existing numero=" + numero;
        }
    }

	public void newTask(TaskMessage newTaskMessage) {
		Task partialNewTask = newTaskMessage.getTask();
	    String sender = newTaskMessage.getSender();
		//a faire en TP :
		//ajouter la nouvelle tache en base  via serviceTask
		//récupérer le numero de la nouvelle tache (généré automatiquement)
		Task savedTask = serviceTask.create(partialNewTask);
		long numero= savedTask.getNumero();
		this.lastMessage="New Task created by "+sender + " with numero=" + numero;
	}

	@MessageMapping("/task") //input message received from /task
	@SendTo("/topic/tasks") //output message pubish to /topic/tasks
	public TaskListMessage send(TaskMessage taskMessage) throws Exception {
	    TaskListMessage taskListMessage= new TaskListMessage();
		switch(taskMessage.getType()) {
			case "NEW_TASK":
				newTask(taskMessage);
				break;
			case "DONE_TASK":
				updateDoneTask(taskMessage);
				break;
			default:
				//JOIN or LEAVE:
				this.lastMessage=taskMessage.getSender() + " : " + taskMessage.getType();
		}
		this.lastTasksList=serviceTask.searchAll();
		taskListMessage.setTasks(this.lastTasksList);
		taskListMessage.setMessage(lastMessage);
		return taskListMessage;
	}

}