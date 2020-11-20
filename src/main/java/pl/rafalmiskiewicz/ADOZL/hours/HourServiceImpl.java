package pl.rafalmiskiewicz.ADOZL.hours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("hourService")
@Transactional
public class HourServiceImpl implements HourService {
    @Autowired
    EntityManager em;

    @Qualifier("hourRepository")
    @Autowired
    private HourRepository hourRepository;

    @Override
    public List<Hour> findAll() {
        List<Hour> userList = hourRepository.findAll();
        return userList;
    }

    @Override
    public List<Hour> findAllFilter(Hour hour) {
        Integer id = hour.getId_user();
        Date hourTo = hour.getHour_to();


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hour> cq = cb.createQuery(Hour.class);

        Root<Hour> book = cq.from(Hour.class);
        List<Predicate> predicates = new ArrayList<>();

        if (id != null) {
            predicates.add(cb.equal(book.get("id_user"), id));
        }
        if (hourTo != null) {
            predicates.add(cb.like(book.get("hour_to"), ">" + hourTo ));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public Hour findHourById(int id) {
        return (Hour) hourRepository.findById(id);
    }

    @Override
    public List<Hour> findAllByUserId(int id) {
        return hourRepository.findAllByUserId(id);
    }

    @Override
    public void saveHour(Hour hour) {

        hourRepository.save(hour);
    }

    @Override
    public void saveHourNew(Hour hour) {

        hourRepository.insertHour(hour.getId_user(), hour.getHour_from(), hour.getHour_to());
    }

    @Override
    public void insertHourString(Hour hour) {
        hourRepository.insertHourString(hour.getId_user(), hour.getHour_from_string(), hour.getHour_to_string());
    }

    @Override
    public void updateHour(Hour hour) {
        hourRepository.updateHour(hour.getId_hours(), hour.getHour_from(), hour.getHour_to());
    }
}
