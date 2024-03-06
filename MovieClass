import java.util.*;

// Movie class to represent a movie
class Movie {
    private String title;
    private String director;
    private String genre;
    private int duration;
    private double price;
    private List<String> availableSeats;

    public Movie(String title, String director, String genre, int duration, double price) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
        this.price = price;
        this.availableSeats = generateSeats();
    }

    // Generate available seats for the movie
    private List<String> generateSeats() {
        List<String> seats = new ArrayList<>();
        for (char row = 'A'; row <= 'E'; row++) {
            for (int seatNum = 1; seatNum <= 10; seatNum++) {
                seats.add(row + String.format("%02d", seatNum));
            }
        }
        return seats;
    }

    // Getters and setters
    // Implement as needed
}

// User class for user authentication
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    // Implement as needed
}

// Booking class to represent a booking
class Booking {
    private Movie movie;
    private User user;
    private List<String> seats;
    private double totalPrice;

    public Booking(Movie movie, User user, List<String> seats, double totalPrice) {
        this.movie = movie;
        this.user = user;
        this.seats = seats;
        this.totalPrice = totalPrice;
    }

    // Getters and setters
    // Implement as needed
}

// MovieTicketBookingSystem class to manage movie ticket booking system
public class MovieTicketBookingSystem {
    private List<Movie> movies;
    private List<User> users;
    private List<Booking> bookings;

    public MovieTicketBookingSystem() {
        movies = new ArrayList<>();
        users = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    // Add movie to the system
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    // Register new user
    public void registerUser(User user) {
        users.add(user);
    }

    // Authenticate user
    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Book tickets for a movie
    public boolean bookTickets(Movie movie, User user, List<String> seats) {
        // Check seat availability
        List<String> availableSeats = movie.getAvailableSeats();
        for (String seat : seats) {
            if (!availableSeats.contains(seat)) {
                return false; // Seat not available
            }
        }

        // Calculate total price based on number of seats and movie price
        double totalPrice = seats.size() * movie.getPrice();
        Booking booking = new Booking(movie, user, seats, totalPrice);
        bookings.add(booking);

        // Update available seats
        availableSeats.removeAll(seats);
        movie.setAvailableSeats(availableSeats);

        return true; // Booking successful
    }

    // Display list of available movies
    public List<Movie> getAvailableMovies() {
        return movies;
    }

    // Main method to test the system
    public static void main(String[] args) {
        // Code for JavaFX user interface can be implemented here
    }
}
