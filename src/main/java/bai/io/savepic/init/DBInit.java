package bai.io.savepic.init;

import bai.io.savepic.model.entity.UserAdmin;
import bai.io.savepic.model.entity.UserClient;
import bai.io.savepic.model.entity.UserRole;
import bai.io.savepic.model.entity.enums.UserRoleEnum;
import bai.io.savepic.repository.UserAdminRepository;
import bai.io.savepic.repository.UserClientRepository;
import bai.io.savepic.repository.UserRepository;
import bai.io.savepic.repository.UserRoleRepository;
import bai.io.savepic.service.LocationService;
import bai.io.savepic.service.UserRoleService;
import bai.io.savepic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

	private final UserService userService;
	private final LocationService locationService;
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final UserRoleService userRoleService;
	private final UserClientRepository userClientRepository;
	private final UserAdminRepository userAdminRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public DBInit(UserService userService, LocationService locationService, UserRepository userRepository, UserRoleRepository userRoleRepository, UserRoleService userRoleService, UserClientRepository userClientRepository, UserAdminRepository userAdminRepository, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.locationService = locationService;
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.userRoleService = userRoleService;
		this.userClientRepository = userClientRepository;
		this.userAdminRepository = userAdminRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public void run(String... args) throws Exception {
		userService.initRoles();
		locationService.initLocations();

		UserAdmin bai = new UserAdmin("Bai", "bi@fa.co", passwordEncoder.encode("123"), "Baistan Tashkulov");
		UserRole userRole = userRoleService.getUserRoleByEnumName(UserRoleEnum.ADMIN);
		bai.setRoles(List.of(userRole));

		UserClient isko = new UserClient("Isko", "Isk@fa.co", passwordEncoder.encode("123"));
		UserRole userRole2 = userRoleService.getUserRoleByEnumName(UserRoleEnum.CLIENT);
		isko.setRoles(List.of(userRole2));

		userAdminRepository.save(bai);
		userClientRepository.save(isko);

	}
}
