package tests;

import static org.junit.Assert.*;

import main.Rental;

import org.junit.Test;

import films.Film;
import films.Type;

public class Main {

	@Test
	public void testFilmMethods() {
		Film f = new Film("Avatar", new Type(1));
		String filmName = f.getName();
		assertEquals("Avatar", filmName);
		String filmType = f.getType();
		assertEquals("New release", filmType);
		filmName = f.setName("The Hunger Games");
		assertEquals("The Hunger Games", filmName);
		filmType = f.setType(2);
		assertEquals("Regular film", filmType);
	}

	@Test
	public void testTypeMethods() {
		Type t = new Type(3);
		String filmType = t.getType();
		assertEquals("Old film", filmType);
	}

	@Test
	public void testRentalMethods() {
		Film f = new Film("The Sound of Music", new Type(3));
		Rental r = new Rental(f, 2);
		String rentalFilm = r.getFilm().getName();
		assertEquals("The Sound of Music", rentalFilm);
		String rentalFilmType = r.getFilm().getType();
		assertEquals("Old film", rentalFilmType);
		int rentalDays = r.getDays();
		assertEquals(2, rentalDays);
		int rentalPrice = r.getPrice();
		assertEquals(3, rentalPrice);
		rentalDays = r.setDays(7);
		assertEquals(7, rentalDays);
		rentalPrice = r.getPrice();
		assertEquals(9, rentalPrice);
	}
}