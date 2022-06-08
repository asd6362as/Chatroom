package com.example.demo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.ChatClient;
import com.example.demo.ChatServer;
@Controller
public class ChatroomController {
	@MessageMapping("/messageControl")
	@SendTo("topic/getResponse")
		public ChatServer said(ChatClient responseMessage) throws InterruptedException{
			Thread.sleep(3000);
			return new ChatServer("歡迎來到," + responseMessage.getClientname());
	}
}
