package net.slipp.user;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/login/form")
	public String loginForm() {
		return "user/login";
	}
	
	@RequestMapping("/login")
	public String login(String userId, String password, Model model) {
		logger.debug("userId : {}, password : {}", userId, password);
		
		try {
			userService.login(userId, password);
		} catch (PasswordMismatchException e) {
			model.addAttribute("error", "비밀번호가 틀립니다.");
			return "user/login";
		}
		return "redirect:/user";
	}
	
	@RequestMapping("")
	public String list(Model model) {
		List<User> users = userService.findUserList();
		model.addAttribute("users", users);
		return "user/list";
	}
	
	@RequestMapping("/form")
	public String createForm(Model model) {
		model.addAttribute("user", new User());
		return "user/form";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(User user) {
		try {
			userService.create(user);
		} catch (ExistedUserException e) {
		} catch (ExceedAdminUserException e) {
		}
		return "redirect:/user";
	}
	
	@RequestMapping("/{userId}/form")
	public String updateForm(@PathVariable String userId, Model model) {
		User user = userService.findUser(userId);
		model.addAttribute("user", user);
		return "user/form";
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public String update(User user) {
		userService.update(user);
		return "redirect:/user";
	}
	
	@RequestMapping("/{userId}/delete")
	public String delete(@PathVariable String userId) {
		userService.remove(userId);
		return "redirect:/user";
	}
}
