package socialNetwork.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import socialNetwork.entities.Post;
import socialNetwork.repositories.PostRepository;

@Service
public class PostService {
	@Autowired
	public PostRepository postRepository;

	@PostConstruct
	public void init() {
	}

	public Page<Post> getPostsByUser(Pageable pageable, String email) {
		Page<Post> result = postRepository.findPostsByUser(pageable, email);
		return result;
	}

	public void addService(Post post) {
		postRepository.save(post);
	}
}
