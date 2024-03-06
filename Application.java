import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MovieTicketBookingApp extends Application {

    private MovieTicketBookingSystem bookingSystem;
    private Movie selectedMovie;
    private List<String> selectedSeats;

    @Override
    public void start(Stage primaryStage) {
        bookingSystem = new MovieTicketBookingSystem();
        initializeMovies();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label movieLabel = new Label("Select Movie:");
        GridPane.setConstraints(movieLabel, 0, 0);

        ComboBox<String> movieComboBox = new ComboBox<>();
        for (Movie movie : bookingSystem.getAvailableMovies()) {
            movieComboBox.getItems().add(movie.getTitle());
        }
        movieComboBox.setOnAction(e -> {
            String selectedMovieTitle = movieComboBox.getSelectionModel().getSelectedItem();
            selectedMovie = bookingSystem.getMovieByTitle(selectedMovieTitle);
        });
        GridPane.setConstraints(movieComboBox, 1, 0);

        Label seatsLabel = new Label("Select Seats:");
        GridPane.setConstraints(seatsLabel, 0, 1);

        CheckBox[] seatCheckBoxes = new CheckBox[selectedMovie.getAvailableSeats().size()];
        for (int i = 0; i < selectedMovie.getAvailableSeats().size(); i++) {
            String seat = selectedMovie.getAvailableSeats().get(i);
            seatCheckBoxes[i] = new CheckBox(seat);
            GridPane.setConstraints(seatCheckBoxes[i], 1, i + 1);
        }

        Button bookButton = new Button("Book Tickets");
        bookButton.setOnAction(e -> {
            selectedSeats = new ArrayList<>();
            for (CheckBox checkBox : seatCheckBoxes) {
                if (checkBox.isSelected()) {
                    selectedSeats.add(checkBox.getText());
                }
            }
            if (selectedSeats.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select at least one seat.");
            } else {
                boolean booked = bookingSystem.bookTickets(selectedMovie, selectedSeats);
                if (booked) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Tickets booked successfully!");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Selected seats are not available.");
                }
            }
        });
        GridPane.setConstraints(bookButton, 1, selectedMovie.getAvailableSeats().size() + 1);

        grid.getChildren().addAll(movieLabel, movieComboBox, seatsLabel, bookButton);
        for (CheckBox checkBox : seatCheckBoxes) {
            grid.getChildren().add(checkBox);
        }

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setTitle("Movie Ticket Booking System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeMovies() {
        Movie movie1 = new Movie("Avengers: Endgame", "Joe Russo, Anthony Russo", "Action", 180, 10.0);
        Movie movie2 = new Movie("Inception", "Christopher Nolan", "Sci-Fi", 148, 8.0);
        bookingSystem.addMovie(movie1);
        bookingSystem.addMovie(movie2);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
