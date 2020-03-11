package socialNetwork.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import socialNetwork.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

	@Query("SELECT r from Post r where (r.email=?1)")
	Page<Post> findPostsByUser(Pageable pageable, String email);
	
	Page<Post> findAll(Pageable pageable);

}
