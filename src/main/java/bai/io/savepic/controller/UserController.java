package bai.io.savepic.controller;

import bai.io.savepic.model.dto.UserAdminDto;
import bai.io.savepic.model.dto.UserClientDto;
import bai.io.savepic.model.entity.UserAdmin;
import bai.io.savepic.model.entity.UserClient;
import bai.io.savepic.model.entity.baseEntity.UserEntity;
import bai.io.savepic.model.entity.enums.UserRoleEnum;
import bai.io.savepic.model.jwt.JwtRequest;
import bai.io.savepic.model.jwt.JwtResponse;
import bai.io.savepic.security.JWTUtility;
import bai.io.savepic.security.UserEntityDetailService;
import bai.io.savepic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	private final UserService userService;
	private final UserEntityDetailService userEntityDetailService;
	private final JWTUtility jwtUtility;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public UserController(UserService userService, UserEntityDetailService userEntityDetailService, JWTUtility jwtUtility, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.userEntityDetailService = userEntityDetailService;
		this.jwtUtility = jwtUtility;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping
	public ResponseEntity<List<UserEntity>> getUsers() {
		List<UserEntity> userEntityList = userService.findAllUsersEntity();
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(userEntityList);
	}

	@GetMapping("/{id}")
	public UserEntity getUserById(@PathVariable Long id) {
		return userService.findUserEntityById(id);
	}

	@GetMapping("/username/{name}")
	public UserEntity getUserById(@PathVariable String name) {
		return userService.findUserEntityByUsername(name);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteUserById(@PathVariable Long id) {
		boolean isRemoved = userService.deleteUserById(id);

		if(!isRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(id);
	}


	@PostMapping("/registerClient")
	public ResponseEntity<UserClient> registerClient(@RequestBody UserClientDto user) {

		if (user.getUsername() == null || user.getEmail() == null || user.getPassword() == null) {
			throw new RuntimeException("Invalid data");
		}
		if (userService.userExists(user.getUsername(), user.getEmail())) {
			throw new RuntimeException("Username or email address already in use.");
		}
		UserClient userClient = userService.registerClient(user);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userClient);
	}

	@PostMapping("/registerAdmin")
	public ResponseEntity<UserAdmin> registerAdmin(@RequestBody UserAdminDto user) {
		if (userService.userExists(user.getUsername(), user.getEmail())) {
			throw new RuntimeException("Username or email address already in use.");
		}
		UserAdmin userAdmin = userService.registerAdmin(user);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userAdmin);
	}

	@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							jwtRequest.getUsername(),
							jwtRequest.getPassword()
					)
			);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userDetails
				= userEntityDetailService.loadUserByUsername(jwtRequest.getUsername());

		final String token =
				jwtUtility.generateToken(userDetails);
		return new JwtResponse(token);
	}
	@PostMapping("/login")
	public String logInUser(@RequestParam String username) {
		System.out.println("asd");
		UserEntity userByUsername = this.userService.findUserEntityByUsername(username);
		if (userByUsername.getRoles().stream()
				.anyMatch(u -> u.getRole().equals(UserRoleEnum.CLIENT))) {
			return "CLIENT";
		} else if (userByUsername.getRoles().stream()
				.anyMatch(u -> u.getRole().equals(UserRoleEnum.ADMIN))) {
			return "ADMIN";
		}
		return null;
	}
}
