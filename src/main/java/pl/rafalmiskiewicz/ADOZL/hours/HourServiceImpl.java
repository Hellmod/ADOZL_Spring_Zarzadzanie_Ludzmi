package pl.rafalmiskiewicz.ADOZL.hours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rafalmiskiewicz.ADOZL.user.User;

import java.util.List;


@Service("hourService")
@Transactional
public class HourServiceImpl implements HourService {

	@Qualifier("hourRepository")
	@Autowired
	private HourRepository hourRepository;
	
	@Override
	public List<Hour> findAll() {
		List<Hour> userList = hourRepository.findAll();
		return userList;
	}

	@Override
	public Hour findHourById(int id) {
		return (Hour) hourRepository.findById(id);
	}

	@Override
	public List<Hour> findAllByUserId(int id) {
		return hourRepository.findAllByUserId(id);
	}

}
