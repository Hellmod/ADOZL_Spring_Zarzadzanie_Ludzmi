package pl.rafalmiskiewicz.ADOZL.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rafalmiskiewicz.ADOZL.user.User;


@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	@Qualifier("adminRepository")
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public List<User> findAll() {
		List<User> userList = adminRepository.findAll();
		return userList;
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		Page<User> userList = adminRepository.findAll(pageable);
		return userList;
	}
}
