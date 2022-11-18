package bai.io.savepic.service;

import bai.io.savepic.model.dto.UserAdminDto;
import bai.io.savepic.model.dto.UserClientDto;
import bai.io.savepic.model.entity.UserAdmin;
import bai.io.savepic.model.entity.UserClient;
import bai.io.savepic.model.entity.baseEntity.UserEntity;

import java.util.List;

public interface UserService {

	// init Roles
	void initRoles();

	// get List of Users
	List<UserEntity> findAllUsersEntity();
	List<UserClient> findAllUsersClient();
	List<UserAdmin> findAllUserAdmin();

	// get User by id
	UserEntity findUserEntityById(Long id);
	UserClient findUserClientById(Long id);
	UserAdmin findUserAdminById(Long id);

	// get User by username
	UserEntity findUserEntityByUsername(String username);

	// get User by email
	UserEntity findUserEntityByEmail(String email);

	// delete by id
	boolean deleteUserById(Long id);

	// register User by role
	UserClient registerClient(UserClientDto userClientDto);
	UserAdmin registerAdmin(UserAdminDto userAdminDto);

	// check if user exists
	boolean userExists(String username, String email);


}
