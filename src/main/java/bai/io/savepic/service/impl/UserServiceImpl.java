package bai.io.savepic.service.impl;

import bai.io.savepic.exception.NotFoundException;
import bai.io.savepic.model.dto.UserAdminDto;
import bai.io.savepic.model.dto.UserClientDto;
import bai.io.savepic.model.entity.UserAdmin;
import bai.io.savepic.model.entity.UserClient;
import bai.io.savepic.model.entity.UserRole;
import bai.io.savepic.model.entity.baseEntity.UserEntity;
import bai.io.savepic.model.entity.enums.UserRoleEnum;
import bai.io.savepic.repository.UserAdminRepository;
import bai.io.savepic.repository.UserClientRepository;
import bai.io.savepic.repository.UserRepository;
import bai.io.savepic.service.UserRoleService;
import bai.io.savepic.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserAdminRepository userAdminRepository;
	private final UserClientRepository userClientRepository;
	private final UserRoleService userRoleService;
	private final ModelMapper modelMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserAdminRepository userAdminRepository, UserClientRepository userClientRepository, UserRoleService userRoleService, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.userAdminRepository = userAdminRepository;
		this.userClientRepository = userClientRepository;
		this.userRoleService = userRoleService;
		this.modelMapper = modelMapper;
	}


	@Override
	public void initRoles() {
		UserRole userRole = new UserRole();
		userRole.setRole(UserRoleEnum.CLIENT);
		userRoleService.saveRole(userRole);
		UserRole adminRole = new UserRole();
		adminRole.setRole(UserRoleEnum.ADMIN);
		userRoleService.saveRole(adminRole);
	}

	@Override
	public List<UserEntity> findAllUsersEntity() {
		List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
		return userEntityList;
	}

	@Override
	public List<UserClient> findAllUsersClient() {
		List<UserClient> userClientList = (List<UserClient>) userClientRepository.findAll();
		return userClientList;
	}

	@Override
	public List<UserAdmin> findAllUserAdmin() {
		List<UserAdmin> userAdminList = (List<UserAdmin>) userAdminRepository.findAll();
		return userAdminList;
	}

	@Override
	public UserEntity findUserEntityById(Long id) {
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if(userEntity.isPresent()) {
			return userEntity.get();
		}
		throw new NotFoundException("User not found!");
	}

	@Override
	public UserClient findUserClientById(Long id) {
		Optional<UserClient> userClient = userClientRepository.findById(id);
		if(userClient.isPresent()) {
			return userClient.get();
		}
		throw new NotFoundException("User not found!");
	}

	@Override
	public UserAdmin findUserAdminById(Long id) {
		Optional<UserAdmin> userAdmin = userAdminRepository.findById(id);
		if(userAdmin.isPresent()) {
			return userAdmin.get();
		}
		throw new NotFoundException("User not found!");
	}

	@Override
	public UserEntity findUserEntityByUsername(String username) {
		Optional<UserEntity> userEntity = userRepository.findByUsername(username);
		if (userEntity.isPresent()) {
			return userEntity.get();
		}
		throw new NotFoundException("User not Found!");
	}

	@Override
	public UserEntity findUserEntityByEmail(String email) {
		Optional<UserEntity> userEntity = userRepository.findByEmail(email);

		if(userEntity.isPresent()) {
			return userEntity.get();
		}
		throw new NotFoundException("User not Found!");
	}

	@Override
	public boolean deleteUserById(Long id) {
		UserEntity userEntity = findUserEntityById(id);
		if (userEntity == null) {
			return false;
		}
		userRepository.deleteById(id);
		return true;
	}

	@Override
	public UserClient registerClient(UserClientDto userClientDto) {
		UserRole userRole = userRoleService.getUserRoleByEnumName(UserRoleEnum.CLIENT);
		UserClient userClient = this.modelMapper.map(userClientDto, UserClient.class);
		userClient.setRoles(List.of(userRole));
		return userClientRepository.save(userClient);
	}

	@Override
	public UserAdmin registerAdmin(UserAdminDto userAdminDto) {
		UserRole userRole = userRoleService.getUserRoleByEnumName(UserRoleEnum.ADMIN);
		UserAdmin userAdmin = this.modelMapper.map(userAdminDto, UserAdmin.class);
		userAdmin.setRoles(List.of(userRole));
		return userAdminRepository.save(userAdmin);

	}

	@Override
	public boolean userExists(String username, String email) {
		Optional<UserEntity> byUsername = userRepository.findByUsername(username);
		Optional<UserEntity> byEmail = userRepository.findByEmail(email);

		return byEmail.isPresent() || byUsername.isPresent();
	}
}
