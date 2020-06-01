package net.diegoqueres.backendqualification.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import net.diegoqueres.backendqualification.entities.Address;
import net.diegoqueres.backendqualification.entities.City;
import net.diegoqueres.backendqualification.entities.Country;
import net.diegoqueres.backendqualification.entities.State;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AddressRepositoryTest {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CountryRepository countryRepo;

	private Integer addressId;

	private Integer countryId;

	@Before
	public void setUp() throws Exception {
		Address obj = obterDadosDaEntidade();
		obj = addressRepository.save(obj);
		addressId = obj.getId();
		countryId = obj.getCountry().getId();
	}

	@After
	public void tearDown() {
		this.addressRepository.deleteAll();
	}

	@Test
	public void testFindAllByCountryId() {
		List<Address> list = addressRepository.findAllByCountryId(countryId);
		assertEquals(1, list.size());
	}

	@Test
	public void testFindByExistentAddress() {
		Optional<Address> obj = addressRepository.findById(addressId);
		assertTrue(obj.isPresent());
	}

	@Test
	public void testFindByInexistentAddress() {
		Optional<Address> obj = addressRepository.findById(343434);
		assertTrue(obj.isEmpty());
	}

	private Address obterDadosDaEntidade() {
		Country country = new Country();
		country.setId(1);
		country.setName("Brazil");
		country.setName_pt("Brasil");
		country.setCountryAbbreviation("BR");
		country.setBacen(1);
		country = countryRepo.save(country);

		State state = new State();
		state.setId(1);
		state.setName("Distrito Federal");
		state.setStateAbbreviation("DF");
		state.setIbge(1);
		state.setCountry(country);
		state = stateRepo.save(state);

		City city = new City();
		city.setId(1);
		city.setName("Brasília");
		city.setIbge(1);
		city.setState(state);
		city = cityRepo.save(city);

		country.getStates().add(state);
		state.getCities().add(city);
		countryRepo.save(country);
		stateRepo.save(state);

		Address obj = new Address();
		obj.setStreetName("Rua Ficticia, 23");
		obj.setNumber("234");
		obj.setNeighbourhood("Jardim das Ilusões");
		obj.setCity(city);
		obj.setState(state);
		obj.setCountry(country);
		obj.setZipcode("12345000");
		return obj;

	}

}
