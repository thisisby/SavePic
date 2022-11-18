package bai.io.savepic.service;

import bai.io.savepic.model.entity.UserRole;
import bai.io.savepic.model.entity.enums.UserRoleEnum;

public interface UserRoleService {

	UserRole getUserRoleByEnumName(UserRoleEnum userRoleEnum);

	UserRole saveRole(UserRole userRoleEntity);
}
