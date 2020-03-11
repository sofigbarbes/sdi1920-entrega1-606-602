package socialNetwork.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialNetwork.repositories.PostRepository;

@Service
public class PostService {
	@Autowired
	public PostRepository postRepository;

	@PostConstruct
	public void init() {
	}
}
