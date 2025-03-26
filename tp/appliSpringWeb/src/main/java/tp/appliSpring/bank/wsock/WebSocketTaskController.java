package tp.appliSpring.bank.wsock;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketTaskController {

	@MessageMapping("/task") //input message received from /chat
	@SendTo("/topic/tasks") //output message pubish to /topic/messages
	public TaskListMessage send(TaskMessage taskMessage) throws Exception {
	    TaskListMessage taskListMessage= new TaskListMessage();
		/*temp*/
		switch(taskMessage.getType()) {
		case "NEW_TASK":
			taskListMessage.setMessage("New Task");
			break;
		case "DONE_TASK":
			taskListMessage.setMessage("Task Done");
			break;
		default:
			taskListMessage.setMessage(taskListMessage.getMessage());
		}
		return taskListMessage;
	}

}