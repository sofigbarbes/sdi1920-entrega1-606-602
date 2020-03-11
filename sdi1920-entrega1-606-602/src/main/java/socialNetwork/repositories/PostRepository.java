package socialNetwork.repositories;

import org.springframework.data.repository.CrudRepository;

import socialNetwork.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

}
