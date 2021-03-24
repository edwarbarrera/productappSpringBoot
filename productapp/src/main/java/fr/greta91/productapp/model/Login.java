package fr.greta91.productapp.model;

public class Login {

	private String login="james";
		private String password="007";
		
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String returnAction() {
			System.out.println("dans return action");
			return password.equals("007") ? "produit" : "login";
		}
		
		
		

	}

