package net.diegoqueres.backendqualification.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Entidade que representa um país.
 * 
 * @author diego
 *
 */
@Entity
@Table(name = "countries")
public class Country implements Serializable {
	private static final long serialVersionUID = 899324165291800664L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String name_pt;

	@JsonProperty("country_abbreviation")
	@Column(name = "country_abbreviation", nullable = false)
	private String countryAbbreviation;

	@Column(nullable = false)
	private Integer bacen;

	@JsonIgnore
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<State> states;

	/**
	 * Construtor da classe.
	 */
	public Country() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_pt() {
		return name_pt;
	}

	public void setName_pt(String name_pt) {
		this.name_pt = name_pt;
	}

	public String getCountryAbbreviation() {
		return countryAbbreviation;
	}

	public void setCountryAbbreviation(String countryAbbreviation) {
		this.countryAbbreviation = countryAbbreviation;
	}

	public Integer getBacen() {
		return bacen;
	}

	public void setBacen(Integer bacen) {
		this.bacen = bacen;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", name_pt=" + name_pt + ", countryAbbreviation="
				+ countryAbbreviation + ", bacen=" + bacen + "]";
	}

}
