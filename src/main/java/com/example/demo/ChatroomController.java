package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatroomController {
	
	@Autowired
	MemberRepository memberrepository;
	
	@GetMapping("/")
	public String login(Model model) {
		Chatroommember member = new Chatroommember();
		model.addAttribute("member", member);
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		Chatroommember member = new Chatroommember();
		model.addAttribute("member", member);
		return "register";
	}
	
	@PostMapping("/register")
	public String registed(@ModelAttribute Chatroommember memberregister, Model model) {
		memberrepository.save(memberregister);
		Chatroommember member = new Chatroommember();
		model.addAttribute("member", member);
		return "login";
	}
	
	@PostMapping("/message")
	public String index(@ModelAttribute Chatroommember member, Model model) {
		Chatroommember membertemp = new Chatroommember();
		membertemp = memberrepository.findCheckMemberAccount(member.getUsername(), member.getPassword());
		if (membertemp != null)	{
		model.addAttribute("name", member.getUsername());
		return "index";
		} 
		model.addAttribute("member", member);
		return "login";
	}
	@MessageMapping("/messageControl")
	@SendTo("/topic/Chatroom")
	public ChatServer messageControl(ChatClient responseMessage)
			throws InterruptedException {
		Thread.sleep(10);
		return new ChatServer("<h6><b>" + responseMessage.getname() + "：　</b></h6>"+"<h7>" + responseMessage.getdialogue() + "</h7> <div class = \"showtime\">"
				+ responseMessage.gettime() + "</div>");
	}
}
