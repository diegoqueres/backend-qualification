package net.diegoqueres.backendqualification.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Entidade que representa um endereço físico.
 * 
 * @author diego
 *
 */
@Entity
@Table(name = "addresses")
public class Address implements Serializable {
	private static final long serialVersionUID = -6132961718782935078L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "street_name", nullable = false)
	private String streetName;
	
	@Column(nullable = false)
	private Integer number;
	
	@Column(nullable = true)
	private String complement;
	
	@Column(nullable = false)
	private String neighbourhood;
	
	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)	
	private State state;
	
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)		
	private Country country;
	
	@Column(nullable = false)
	private String zipcode;
	
	@Column(nullable = true)
	private Double latitude;
	
	@Column(nullable = true)
	private Double longitude;
	
	
	
	/** 
	* Construtor da classe.
	*/
	public Address() {}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getStreetName() {
		return streetName;
	}


	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public String getComplement() {
		return complement;
	}


	public void setComplement(String complement) {
		this.complement = complement;
	}


	public String getNeighbourhood() {
		return neighbourhood;
	}


	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
	
	

}
