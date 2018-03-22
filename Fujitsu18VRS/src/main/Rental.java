package main;

import films.Film;

public class Rental {
	private Film film;
	private int days, price;
	protected static int PREMIUM_PRICE = 4;
	protected static int BASIC_PRICE = 3;

	public Rental(Film film, int days) {
		this.film = film;
		this.days = days;
		this.price = getPrice();
	}

	public Film getFilm() {
		return film;
	}

	public int getDays() {
		return days;
	}

	public int setDays(int days) {
		this.days = days;
		return days;
	}

	public int getPrice() {
		if (film.getType() == "New release") {
			price = PREMIUM_PRICE * days;
			if (main.Main.bonus >= 25) {
				price = PREMIUM_PRICE * (days - 1);
				System.out.println(film.getName() + " (" +
						film.getType() + ") " +
						days + " days (Paid with 25 bonus points)");
				main.Main.bonus -= 25;
				main.Main.bonusUsed = true;
				main.Main.bonusFilm.setName(film.getName());
			}
		} else if (film.getType() == "Regular film") {
			if (days <= 3) {
				price = BASIC_PRICE;
			} else {
				price = BASIC_PRICE + (BASIC_PRICE * (days - 3));
			}
		} else if (film.getType() == "Old film") {
			if (days <= 5) {
				price = BASIC_PRICE;
			} else {
				price = BASIC_PRICE + (BASIC_PRICE * (days - 5));
			}
		}
		return price;
	}
}