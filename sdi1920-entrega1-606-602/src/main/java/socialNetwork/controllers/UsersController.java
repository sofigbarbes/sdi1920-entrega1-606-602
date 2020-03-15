
package socialNetwork.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import socialNetwork.entities.FriendRequest;
import socialNetwork.entities.User;
import socialNetwork.services.FriendRequestService;
import socialNetwork.services.LoggerService;
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
	private LoggerService loggerService;
	
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

		user.setRole("ROLE_STANDARD");
		usersService.addUser(user);
		loggerService.signUp(user.getEmail());
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error) {
		if (error != null) {
			model.addAttribute("error", error);
		}
		
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		usersService.getUserByEmail(email);
		return "home";

	}

	@RequestMapping("/user/list")
	public String getListado(Model model, Pageable pageable,
			@RequestParam(value = "", required = false) String searchText) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		loggerService.listUsers(email);

		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUser(pageable, searchText, email);
		} else {
			if (!usersService.getUserByEmail(email).getRole().equals("ROLE_ADMIN")) {
				users = usersService.getListUsers(pageable, email);
			} else {
				users = usersService.getListUsersAdmin(pageable, email);
			}
		}

		Page<User> friends = usersService.getFriends(pageable, email);
		Page<User> reqsTo = usersService.getReqToUser(pageable, email);
		Page<User> reqsBy = usersService.getReqByUser(pageable, email);

		model.addAttribute("usersList", users.getContent());
		model.addAttribute("friendsList", friends.getContent());
		model.addAttribute("reqsToList", reqsTo.getContent());
		model.addAttribute("reqsByList", reqsBy.getContent());
		model.addAttribute("page", users);
		return "user/list";
	}

	@RequestMapping(value = "/user/sendfriendreq/{email}", method = RequestMethod.GET)
	public String setResendTrue(Model model, @PathVariable String email) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String senderEmail = auth.getName();
		
		loggerService.sendReq(senderEmail, email);

		User user = usersService.getUserByEmail(email);

		if (!user.getRole().equals("ROLE_ADMIN")) {
			FriendRequest fr = new FriendRequest(senderEmail, email, false, true);
			friendRequestService.addFriendRequest(fr);

			FriendRequest fr2 = new FriendRequest(email, senderEmail, false, false);
			friendRequestService.addFriendRequest(fr2);
		}

		return "redirect:/user/list";
	}

	@RequestMapping(value = "/user/delete/{email}")
	public String delete(@PathVariable String email) {
		usersService.deleteUserByEmail(email);
		friendRequestService.deleteRequestsUser(email);
		
		loggerService.delete(email);

		return "redirect:/user/list";
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String deleteUsers(Model model, @RequestParam Map<String, Object> paramList) {
		List<String> emails = readEmailsFromRequest(paramList);
		for (String email : emails) {
			usersService.deleteUserByEmail(email);
			friendRequestService.deleteRequestsUser(email);
		}
		return "redirect:/user/list";
	}

	private List<String> readEmailsFromRequest(Map<String, Object> paramList) {
		List<String> keySet = new ArrayList<String>(paramList.keySet());
		List<String> values = new ArrayList<String>();
		String str = "";
		for (String key : keySet) {
			values.add(key);
			str = key;
		}
		String[] v = str.split(":");
		v = v[1].substring(1, v[1].length()).substring(0, v[1].length() - 3).split(",");
		List<String> emails = new ArrayList<String>();
		for (String each : v) {
			emails.add(each.substring(1, each.length() - 1));
			System.out.println(each.substring(1, each.length() - 1));

		}
		return emails;

	}

}
