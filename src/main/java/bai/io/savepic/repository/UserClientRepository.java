package bai.io.savepic.repository;

import bai.io.savepic.model.entity.UserClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClientRepository extends CrudRepository<UserClient, Long> {


}
