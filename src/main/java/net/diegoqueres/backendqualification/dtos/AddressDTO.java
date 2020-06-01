package net.diegoqueres.backendqualification.dtos;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.diegoqueres.backendqualification.entities.Address;

/**
 * DTO da entidade Address (Endereço).
 * 
 * @author Diego Queres Queres
 *
 */
public class AddressDTO implements Serializable {
	private static final long serialVersionUID = -4398537102909994482L;

	@JsonInclude(Include.NON_EMPTY)
	private Optional<Integer> id = Optional.empty();
	
	private String streetName;
	private String number;

	@JsonInclude(Include.NON_NULL)
	private String complement;
	private String neighbourhood;
	private Integer city;
	private Integer state;
	private Integer country;
	private String zipcode;

	@JsonInclude(Include.NON_NULL)
	private Double latitude;

	@JsonInclude(Include.NON_NULL)
	private Double longitude;

	/**
	 * Construtor padrão.
	 */
	public AddressDTO() {
	}

	/**
	 * Inicializa um novo objeto DTO a partir de uma entidade Address.
	 * 
	 * @param address
	 */
	public AddressDTO(Address address) {
		id = Optional.of(address.getId());
		streetName = address.getStreetName();
		number = address.getNumber();
		complement = address.getComplement();
		neighbourhood = address.getNeighbourhood();
		city = address.getCity().getId();
		state = address.getState().getId();
		country = address.getCountry().getId();
		zipcode = address.getZipcode();
		latitude = address.getLatitude();
		longitude = address.getLongitude();
	}

	public Optional<Integer> getId() {
		return id;
	}

	public void setId(Optional<Integer> id) {
		this.id = id;
	}

	@NotEmpty(message = "Street name cannot be empty.")
	@Length(min = 3, max = 255, message = "Street name must contain between 3 and 255 characters.")
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@NotEmpty(message = "Number cannot be empty.")
	@Length(min = 1, max = 20, message = "Number must contain between 1 and 20 characters.")
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

	@NotEmpty(message = "Neighbourhood cannot be empty.")
	@Length(min = 3, max = 255, message = "Neighbourhood must contain between 3 and 255 characters.")
	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	@NotNull(message = "City cannot be empty.")
	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@NotNull(message = "State cannot be empty.")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@NotNull(message = "Country cannot be empty.")
	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@NotEmpty(message = "Zipcode cannot be empty.")
	@Length(min = 5, max = 8, message = "Zipcode must contain between 5 and 8 characters.")
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "AddressDTO [id=" + id + ", streetName=" + streetName + ", number=" + number + ", complement="
				+ complement + ", neighbourhood=" + neighbourhood + ", city=" + city + ", state=" + state + ", country="
				+ country + ", zipcode=" + zipcode + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
