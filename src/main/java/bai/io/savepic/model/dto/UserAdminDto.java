package bai.io.savepic.model.dto;

import lombok.Data;

@Data
public class UserAdminDto {
	private String username;
	private String email;
	private String password;
	private String fullName;
}
