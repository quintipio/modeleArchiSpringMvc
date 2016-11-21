package fr.quintipio.modelArchiSpringMvc.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PERSISTENT_LOGINS")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersistentLogin implements Serializable{

	@Id
	String series;

	@Column(name="USERNAME", unique=true, nullable=false)
	String username;
	
	@Column(name="TOKEN", unique=true, nullable=false)
	String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_used;

	
	
}
