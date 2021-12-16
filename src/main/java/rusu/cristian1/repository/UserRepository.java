package rusu.cristian1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import rusu.cristian1.domain.Users.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
