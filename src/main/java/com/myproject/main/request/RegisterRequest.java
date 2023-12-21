package com.myproject.main.request;

import java.sql.Timestamp;

public class RegisterRequest {
	    private String username;
	    private String password;
	    private String email;
	    private String fullname;
	    private String description;
	    private String role;
	    private Timestamp createAt;
		
	    public Timestamp getCreateAt() {
			return createAt;
		}
		public void setCreateAt(Timestamp createAt) {
			this.createAt = createAt;
		}
		public RegisterRequest(String username, String password, String email, String fullname, String description,
				String role, Timestamp createAt) {
			super();
			this.username = username;
			this.password = password;
			this.email = email;
			this.fullname = fullname;
			this.description = description;
			this.role = role;
			this.createAt = createAt;
		}
		public RegisterRequest() {
			super();
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
}
