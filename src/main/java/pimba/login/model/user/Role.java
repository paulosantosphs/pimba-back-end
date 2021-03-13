package pimba.login.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "roles")
	@JsonBackReference
	private List<User> users;

	protected Role() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUser(List<User> users) {
		this.users = users;
	}

}
