package com.example.demo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatroomController {
	
	@MessageMapping("/messageControl")
	@SendTo("/topic/Chatroom")
		public ChatServer messageControl(ChatClient responseMessage) throws InterruptedException{
			Thread.sleep(10);
			return new ChatServer(responseMessage.getname());
	}
}
