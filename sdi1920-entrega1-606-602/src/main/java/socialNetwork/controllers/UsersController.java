
package socialNetwork.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import socialNetwork.entities.FriendRequest;
import socialNetwork.entities.User;
import socialNetwork.services.FriendRequestService;
import socialNetwork.services.SecurityService;
import socialNetwork.services.UsersService;
import socialNetwork.validators.SignUpFormValidator;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;

	@Autowired
	private FriendRequestService friendRequestService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}

		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		return "home";

	}

	@RequestMapping("/user/list")
	public String getListado(Model model, Pageable pageable,
			@RequestParam(value = "", required = false) String searchText) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUser(pageable, searchText, email);
		} else {
			users  = usersService.getListUsers(pageable, email);
		}
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		return "user/list";
	}

	@RequestMapping(value = "/user/{email}/sendfriendreq", method = RequestMethod.GET)
	public String setResendTrue(Model model, @PathVariable String email) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String senderEmail = auth.getName();

		FriendRequest fr = new FriendRequest(senderEmail, email);
		friendRequestService.addFriendRequest(fr);

		System.out.println("Envio peticion de " + senderEmail + " a " + email);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "/user/friends", method = RequestMethod.GET)
	public String getFriends() {
		return null;
	}
	
	@RequestMapping(value = "/user/requests", method = RequestMethod.GET)
	public String getRequests() {
		return null;
	}
}
