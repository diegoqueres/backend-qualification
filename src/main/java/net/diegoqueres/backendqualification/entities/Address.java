package net.diegoqueres.backendqualification.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
	private String number;

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

	@Column(nullable = false, columnDefinition = "char(8)")
	private String zipcode;

	@Column(nullable = true)
	private Double latitude;

	@Column(nullable = true)
	private Double longitude;

	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	/**
	 * Construtor da classe.
	 */
	public Address() {
	}

	/**
	 * Construtor da classe com argumentos.
	 * 
	 * @param streetName
	 * @param number
	 * @param complement
	 * @param neighbourhood
	 * @param city
	 * @param state
	 * @param country
	 * @param zipcode
	 * @param latitude
	 * @param longitude
	 */
	public Address(String streetName, String number, String complement, String neighbourhood, City city, State state,
			Country country, String zipcode, Double latitude, Double longitude) {
		this.streetName = streetName;
		this.number = number;
		this.complement = complement;
		this.neighbourhood = neighbourhood;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}

	@PrePersist
	public void prePersist() {
		final Instant atual = Instant.now();
		createdAt = atual;
		updatedAt = atual;
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
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", streetName=" + streetName + ", number=" + number + ", complement=" + complement
				+ ", neighbourhood=" + neighbourhood + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", zipcode=" + zipcode + ", latitude=" + latitude + ", longitude=" + longitude + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
