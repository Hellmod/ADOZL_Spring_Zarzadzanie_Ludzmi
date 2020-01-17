package pl.rafalmiskiewicz.ADOZL.hours;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository("hourRepository")
public interface HourRepository extends JpaRepository<Hour, Integer> {
    public Hour findById(int id);

    @Query(value = "SELECT * FROM hours WHERE hours.id_user=:idUser ", nativeQuery = true)
    List<Hour> findAllByUserId(@Param("idUser") int idUser);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `hours` (`id_hours`, `id_user`, `hour_from`, `hour_to`) VALUES (NULL, :id_user, :hour_from, :hour_to);", nativeQuery = true)
    public void insertHour(@Param("id_user") int id_user, @Param("hour_from") Date hour_from, @Param("hour_to") Date hour_to);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `hours` (`id_hours`, `id_user`, `hour_from`, `hour_to`) VALUES (NULL, :id_user, :hour_from, :hour_to);", nativeQuery = true)
    public void insertHourString(@Param("id_user") int id_user, @Param("hour_from") String hour_from, @Param("hour_to") String hour_to);

}
