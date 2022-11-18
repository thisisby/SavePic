package bai.io.savepic.service.impl;

import bai.io.savepic.model.entity.UserRole;
import bai.io.savepic.model.entity.enums.UserRoleEnum;
import bai.io.savepic.repository.UserRoleRepository;
import bai.io.savepic.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	private final UserRoleRepository userRoleRepository;

	@Autowired
	public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public UserRole getUserRoleByEnumName(UserRoleEnum userRoleEnum) {
		Optional<UserRole> byRole = userRoleRepository.findByRole(userRoleEnum);
		return byRole.get();
	}

	@Override
	public UserRole saveRole(UserRole userRoleEntity) {
		return userRoleRepository.save(userRoleEntity);
	}
}
