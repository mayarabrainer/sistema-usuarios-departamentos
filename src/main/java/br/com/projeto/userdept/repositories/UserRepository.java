package br.com.projeto.userdept.repositories;

import br.com.projeto.userdept.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{


}
