package fr.quintipio.modelArchiSpringMvc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="USER")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	Integer id;

	@Getter
	Long version;

	@NotNull
	@Column(name="SSO_ID", unique=true, nullable=false)
    String ssoId;

	@Column(name="PASSWORD", nullable=false)
    @NotNull(message = "{NonVide}")
    @Size(min= 8 ,max = 255, message = "{PassMinMax}")
    String password;

	@Column(name="FIRST_NAME", nullable=false)
    @NotNull(message = "{NonVide}")
    @Size(max = 255, message = "{Max255}")
	private String firstName;

	@Column(name="LAST_NAME", nullable=false)
    @NotNull(message = "{NonVide}")
    @Size(max = 255, message = "{Max255}")
	private String lastName;

    @Past(message = "{DateNaissanceInf}")
    @Column(name="BIRTH_DATE", nullable=false)
    Date birthDate;

	@Column(name="EMAIL", nullable=false)
    @NotNull(message = "{NonVide}")
    @Size(max = 255, message = "{Max255}")
    @Email(message = "{MailInvalide}")
	private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idcommune")
    Commune ville;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "APP_USER_USER_PROFILE", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	Set<UserProfile> userProfiles = new HashSet<UserProfile>();


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	/*
	 * DO-NOT-INCLUDE passwords in toString function.
	 * It is done here just for convenience purpose.
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}


	
}
