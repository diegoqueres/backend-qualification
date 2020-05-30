package net.diegoqueres.backendqualification.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class State implements Serializable {
	private static final long serialVersionUID = 7950907231181720754L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Column(name = "state_abbreviation", nullable = false)
	private String stateAbbreviation;
	
	@Column(nullable = false)
	private Integer ibge;

	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private List<City> cities;
	

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;
	
	
	/** 
	* Construtor da classe.
	*/
	public State() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStateAbbreviation() {
		return stateAbbreviation;
	}


	public void setStateAbbreviation(String stateAbbreviation) {
		this.stateAbbreviation = stateAbbreviation;
	}
	
	
	public Integer getIbge() {
		return ibge;
	}


	public void setIbge(Integer ibge) {
		this.ibge = ibge;
	}


	public List<City> getCities() {
		return cities;
	}


	public void setCities(List<City> cities) {
		this.cities = cities;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
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
		State other = (State) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + ", stateAbbreviation=" + stateAbbreviation + ", ibge=" + ibge
				+ ", cities=" + cities + ", country=" + country + "]";
	}


	

}
