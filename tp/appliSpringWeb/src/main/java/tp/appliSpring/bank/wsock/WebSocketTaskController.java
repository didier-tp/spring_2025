package tp.appliSpring.bank.wsock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import tp.appliSpring.bank.core.model.Task;
import tp.appliSpring.bank.core.service.ServiceTask;

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
		Task task = serviceTask.searchById(numero);
		task.setResponse(doneTaskResponse);
		task.setAuthor(author);
		task.setTimestamp(new Date());
		serviceTask.update(task);
		this.lastTasksList=serviceTask.searchAll();
		this.lastMessage="Task Done by "+author + " with numero=" + numero;
	}

	public void newTask(TaskMessage newTaskMessage) {
		Task partialNewTask = newTaskMessage.getTask();
		long numero = 0; //partialDoneTask.getNumero();
	    String author =""; //...
		this.lastTasksList=serviceTask.searchAll();
		this.lastMessage="New Task created by "+author + " with numero=" + numero;
	}

	@MessageMapping("/task") //input message received from /chat
	@SendTo("/topic/tasks") //output message pubish to /topic/messages
	public TaskListMessage send(TaskMessage taskMessage) throws Exception {
	    TaskListMessage taskListMessage= new TaskListMessage();
		switch(taskMessage.getType()) {
			case "NEW_TASK":
				newTask(taskMessage);
				taskListMessage.setTasks(this.lastTasksList);
				taskListMessage.setMessage(lastMessage);
				break;
			case "DONE_TASK":
				updateDoneTask(taskMessage);
				taskListMessage.setTasks(this.lastTasksList);
				taskListMessage.setMessage(lastMessage);
				break;
			default:
				//JOIN or LEAVE:
				this.lastTasksList=serviceTask.searchAll();
				taskListMessage.setTasks(this.lastTasksList);
				taskListMessage.setMessage(taskMessage.getSender() + " : " + taskMessage.getType());
		}
		return taskListMessage;
	}

}