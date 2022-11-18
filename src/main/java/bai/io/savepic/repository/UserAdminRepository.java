package bai.io.savepic.repository;

import bai.io.savepic.model.entity.UserAdmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAdminRepository extends CrudRepository<UserAdmin, Long> {

	Optional<UserAdmin> findByFullName(String fullName);
}
