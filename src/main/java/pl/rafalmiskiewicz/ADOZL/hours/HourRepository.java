package pl.rafalmiskiewicz.ADOZL.hours;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.rafalmiskiewicz.ADOZL.user.User;

import java.util.List;


@Repository("hourRepository")
public interface HourRepository extends JpaRepository<Hour, Integer> {
    public List<Hour> findById(int id);

    @Query(value = "SELECT * FROM hours WHERE hours.id_user=:idUser ", nativeQuery = true)
    List<Hour> findAllByUserId(@Param("idUser") int idUser);

}
