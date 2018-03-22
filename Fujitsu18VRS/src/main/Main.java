package main;

import java.util.ArrayList;
import java.util.List;

import films.Film;
import films.Type;

public class Main {
	private static List<Film> allFilms = new ArrayList<>();
	private static List<Film> availableFilms = new ArrayList<>();
	private static List<Rental> rentals = new ArrayList<>();
	protected static int price = 0;
	protected static int income = 0;
	protected static int bonus = 0;
	protected static boolean bonusUsed = false;
	protected static Film bonusFilm = new Film("", new Type(1));

	public static void printFilmInfo(Film f) {
		System.out.println("Film information:");
		System.out.println(f.getName() + " (" + f.getType() + ")\n\n");
	}

	public static void printAllFilms() {
		System.out.println("List of all films:");
		for (int i = 0; i < allFilms.size(); i++) {
			System.out.println(allFilms.get(i).getName() +
					" (" + allFilms.get(i).getType() + ")");
		}
		System.out.println("----------x----------\n\n");
	}

	public static void printAvailableFilms() {
		System.out.println("Available films in store:");
		for (int i = 0; i < availableFilms.size(); i++) {
			System.out.println(availableFilms.get(i).getName() +
					" (" + availableFilms.get(i).getType() + ")");
		}
		System.out.println("----------x----------\n\n");
	}

	public static void printRentedFilms() {
		price = 0;
		System.out.println("List of rentals:");
		for (int i = 0; i < rentals.size(); i++) {
			int newPrice = rentals.get(i).getPrice();
			if (bonusUsed && (rentals.get(i).getFilm().getName() == bonusFilm.getName())) {
				newPrice -= 4;
				bonusUsed = false;
				bonusFilm.setName("");
			}
			price += newPrice;
			System.out.println(rentals.get(i).getFilm().getName() +
					" (" + rentals.get(i).getFilm().getType() +
					") " + rentals.get(i).getDays() + " days " +
					newPrice + " EUR");
		}
		System.out.println("Total price : " + price + " EUR");
		System.out.println("----------x----------\n\n");
	}

	public static void addFilm(Film f) {
		if (!allFilms.contains(f)) {
			allFilms.add(f);
		}
		availableFilms.add(f);
	}

	public static void removeFilm(Film f) {
		if (availableFilms.contains(f)) {
			availableFilms.remove(f);
		} else {
			throw new NullPointerException("This film is not available.");
		}
	}

	public static void rentFilm(Film f, int days) {
		Rental r = new Rental(f, days);
		removeFilm(r.getFilm());
		rentals.add(r);
		if (r.getFilm().getType() == "New release") {
			bonus += 2;
			// System.out.println("CONGRATULATIONS! YOU EARNED 2 BONUS POINTS!");
			// printFilmInfo(r.getFilm());
		} else {
			bonus += 1;
			// System.out.println("CONGRATULATIONS! YOU EARNED 1 BONUS POINT!");
			// printFilmInfo(r.getFilm());
		}
		income += r.getPrice();
	}

	public static void returnLate(Film f, int days) {
		for (int i = 0; i < rentals.size(); i++) {
			if (rentals.get(i).getFilm() == f) {
				rentals.get(i).setDays(days);
				income += rentals.get(i).getPrice();
				System.out.println("Extended rent for \"" +
						rentals.get(i).getFilm().getName() + "\" for " + days + " days.\n");
				printRentedFilms();
				rentals.remove(i);
				addFilm(f);
				break;
			}
		}
	}

	public static void returnFilm(Film f) {
		for (int i = 0; i < rentals.size(); i++) {
			if (rentals.get(i).getFilm() == f) {
				rentals.remove(i);
				addFilm(f);
				break;
			}
		}
	}

	public static void showIncome() {
		System.out.println("Income : " + income + " EUR\n\n");
	}

	public static void showBonusPoints() {
		System.out.println("Remaining bonus points : " + bonus + "\n\n");
	}

	public static void main(String[] args) {
		Film f1 = new Film("Matrix 11", new Type(1));
		Film f2 = new Film("Spider man", new Type(2));
		Film f3 = new Film("Spider man 2", new Type(2));
		Film f4 = new Film("Out of africa", new Type(3));
		printFilmInfo(f1);
		f1.setType(2);				// Changing the type of a film
		printFilmInfo(f1);
		f1.setType(1);				// Resetting the type of the film
		printFilmInfo(f1);
		addFilm(f1);				// Adding a film
		addFilm(f2);
		addFilm(f3);
		addFilm(f4);
		printAllFilms();			// Listing all films
		printAvailableFilms();		// Listing all films in store
		removeFilm(f1);				// Removing a film
		printAvailableFilms();
		addFilm(f1);				// Adding a film back
		printAvailableFilms();
		rentFilm(f1, 1);			// Renting a film for X days
		rentFilm(f2, 5);
		rentFilm(f3, 2);
		rentFilm(f4, 7);
		printAvailableFilms();
		printRentedFilms();			// Listing current rentals
		returnFilm(f3);				// Returning a film
		returnFilm(f4);
		printRentedFilms();
		printAvailableFilms();
		returnLate(f1, 2);			// Returning a film X days late
		returnLate(f2, 1);
		showIncome();				// Showing income
		showBonusPoints();			// Showing bonus points
		Film f5 = new Film("Shall we dance?", new Type(3));
		addFilm(f5);
		Film f6 = new Film("Collateral beauty", new Type(1));
		addFilm(f6);
		Film f7 = new Film("Ice Age 5", new Type(1));
		addFilm(f7);
		Film f8 = new Film("La La Land", new Type(1));
		addFilm(f8);
		Film f9 = new Film("Cars 3", new Type(1));
		addFilm(f9);
		printAllFilms();			// Extended inventory of films
		printAvailableFilms();
		rentFilm(f1, 2);			// Renting all films to gain bonus points
		rentFilm(f2, 2);
		rentFilm(f3, 2);
		rentFilm(f4, 4);
		rentFilm(f5, 2);
		rentFilm(f6, 3);
		rentFilm(f7, 5);
		rentFilm(f8, 7);
		rentFilm(f9, 3);
		printRentedFilms();
		printAvailableFilms();
		returnFilm(f1);				// Returning all films
		returnFilm(f2);
		returnFilm(f3);
		returnFilm(f4);
		returnFilm(f5);
		returnFilm(f6);
		returnFilm(f7);
		returnFilm(f8);
		returnFilm(f9);
		printAvailableFilms();
		showIncome();
		rentFilm(f8, 2);
		rentFilm(f6, 3);
		rentFilm(f5, 2);
		showBonusPoints();
		rentFilm(f1, 2);			// Finally 25+ bonus points!
		showBonusPoints();
		printRentedFilms();
		showBonusPoints();
		showIncome();
	}
}