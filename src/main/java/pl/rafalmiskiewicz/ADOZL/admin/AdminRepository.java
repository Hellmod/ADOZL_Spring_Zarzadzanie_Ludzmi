package pl.rafalmiskiewicz.ADOZL.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rafalmiskiewicz.ADOZL.user.User;


@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<User, Integer> {

}
