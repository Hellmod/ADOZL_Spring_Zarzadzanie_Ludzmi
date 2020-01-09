package pl.rafalmiskiewicz.ADOZL.hours;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rafalmiskiewicz.ADOZL.user.User;


@Repository("hourRepository")
public interface HourRepository extends JpaRepository<Hour, Integer> {
}
