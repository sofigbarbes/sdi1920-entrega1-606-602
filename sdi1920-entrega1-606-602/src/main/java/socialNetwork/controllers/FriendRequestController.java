
package socialNetwork.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import socialNetwork.entities.FriendRequest;
import socialNetwork.entities.User;
import socialNetwork.services.FriendRequestService;
import socialNetwork.services.UsersService;

@Controller
public class FriendRequestController {
	@Autowired
	private FriendRequestService friendRequestService;

	@Autowired
	private UsersService usersService;

	@RequestMapping("/friendRequest/listAccepted")
	public String getListFriends(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {

		String email = principal.getName();
		User user = usersService.getUserByEmail(email);

		Page<User> fr2 = new PageImpl<User>(new LinkedList<User>());

		fr2 = usersService.getFriends(pageable, user.getEmail());

		model.addAttribute("reqList", fr2.getContent());
		model.addAttribute("page", fr2);

		return "friend/list";
	}

	@RequestMapping("/request/list")
	public String getListRequests(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		Page<FriendRequest> reqs = new PageImpl<FriendRequest>(new LinkedList<FriendRequest>());

		reqs = friendRequestService.getRequests(pageable, email);
		System.out.println(reqs.getContent().toString());
		model.addAttribute("reqList", reqs.getContent());
		model.addAttribute("page", reqs);

		return "request/list";
	}

	@RequestMapping(value = "/request/{email}/accept", method = RequestMethod.GET)
	public String acceptRequest(Model model, @PathVariable String email) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String myEmail = auth.getName();

		friendRequestService.acceptRequest(email, myEmail);

		System.out.println("Acepto peticion de " + email + " a " + myEmail);
		return "redirect:/friendRequest/listAccepted";
	}

}
