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
import org.springframework.web.bind.annotation.RequestMapping;

import socialNetwork.entities.Post;
import socialNetwork.services.PostService;

@Controller
public class PostController {
	@Autowired
	public PostService postService;
	
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
}
