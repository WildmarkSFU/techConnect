package techconnect.techconnectproject.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByUsernameAndPassword(String username, String password);
    List<User> findByUsername(String username);
    List<User> findByVerificationCode(int verificationCode);
}
