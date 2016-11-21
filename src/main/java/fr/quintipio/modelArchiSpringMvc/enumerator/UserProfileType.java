package fr.quintipio.modelArchiSpringMvc.enumerator;

import lombok.Getter;

import java.io.Serializable;

/**
 * Les roles de l'appli
 */
public enum UserProfileType implements Serializable{
	ROLE_USER("ROLE_USER"),
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_DBA("ROLE_DBA");

    @Getter
	private String libelle;


	private UserProfileType(String libelle) {
		this.libelle = libelle;
	}

	
}
