package socialNetwork.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import socialNetwork.entities.Post;
import socialNetwork.services.FriendRequestService;
import socialNetwork.services.PostService;

@Controller
public class PostController {
	@Autowired
	public PostService postService;

	@Autowired
	public FriendRequestService friendReqServ;

	@RequestMapping("/post/list")
	public String getListRequests(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		Page<Post> posts = new PageImpl<Post>(new LinkedList<Post>());

		posts = postService.getPostsByUser(pageable, email);
		model.addAttribute("postList", posts.getContent());
		model.addAttribute("page", posts);

		return "post/list";
	}

	@RequestMapping(value = "/post/add", method = RequestMethod.GET)
	public String addPost() {
		return "post/add";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}

	@RequestMapping(value = "/post/add", method = RequestMethod.POST)
	public String addPost(@ModelAttribute("post") Post post, Model model,
			@RequestParam("yourImage") MultipartFile yourImage) {
		String fileName = null;
		if (!yourImage.isEmpty()) {
			try {
				fileName = yourImage.getOriginalFilename();
				InputStream is = yourImage.getInputStream();
				String path = "src/main/resources/static/photos/" + fileName;
				Files.copy(is, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
				post.setFileName("/photos/" + fileName);
				
			} catch (IOException e) {
				return "redirect:/error";
			}
		} else {
			post.setFileName("");

		}

		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		post.setDate(strDate);
		post.setEmail(email);
		postService.addPost(post);

		return "redirect:/post/list";
	}

	@RequestMapping("friendRequest/{email}/posts")
	public String getFriendsPost(Model model, Pageable pageable, @PathVariable String email) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String me = auth.getName();

		Page<Post> posts = new PageImpl<Post>(new LinkedList<Post>());
		if (friendReqServ.areFriends(me, email)) {
			posts = postService.getPostsByUser(pageable, email);
			model.addAttribute("postList", posts.getContent());
			model.addAttribute("page", posts);

			return "post/friendsList";
		} else {
			return "error";
		}

	}

}
