package pl.rafalmiskiewicz.ADOZL.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Column(name = "email")
	@NotNull
	private String email;

	@Column(name = "password")
	@NotNull
	private String password;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "last_name")
	@NotNull
	private String lastName;

	@Column(name = "active")
	@NotNull
	private int active;

	@Column(name = "telephone")
	@NotNull
	private int telephone;

	@Column(name = "is_fired")
	@NotNull
	private Boolean is_fired;

	@Column(name = "is_new")
	@NotNull
	private Boolean is_new;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Transient
	private String operacja;

	@Transient
	private int nrRoli;

	@Transient
	private String newPassword;


	//gettery i settery
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {	this.roles = roles;}
	public String getOperacja() {return operacja;}
	public void setOperacja(String operacja) {this.operacja = operacja;	}
	public int getNrRoli() {return nrRoli;}
	public void setNrRoli(int nrRoli) {	this.nrRoli = nrRoli;}
	public String getNewPassword() { return newPassword;}
	public void setNewPassword(String newPassword) { this.newPassword = newPassword;}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public Boolean getIs_fired() {
		return is_fired;
	}

	public void setIs_fired(Boolean is_fired) {
		this.is_fired = is_fired;
	}

	public Boolean getIs_new() {
		return is_new;
	}

	public void setIs_new(Boolean is_new) {
		this.is_new = is_new;
	}
}
