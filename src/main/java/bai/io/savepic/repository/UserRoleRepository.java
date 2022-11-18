package bai.io.savepic.repository;

import bai.io.savepic.model.entity.UserRole;
import bai.io.savepic.model.entity.enums.UserRoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	Optional<UserRole> findByRole(UserRoleEnum roleEnum);
}
