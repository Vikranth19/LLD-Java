package com.selfLearning.LLD.LLDBookMyShow;

import com.selfLearning.LLD.LLDBookMyShow.Enum.City;
import com.selfLearning.LLD.LLDBookMyShow.Enum.SeatCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BookMyShow {

    MovieController movieController;
    TheatreController theatreController;
    int bookingIdCount = 0;

    BookMyShow() {
        movieController = new MovieController();
        theatreController = new TheatreController();
    }

    public static void main(String[] args) {
        BookMyShow bookMyShow = new BookMyShow();

        bookMyShow.initialize();
        bookMyShow.createBooking("Pravinkoodu shappu", City.HYDERABAD);
        bookMyShow.createBooking("Rekha Chitram", City.BANGALORE);
        bookMyShow.createBooking("Rekha Chitram", City.BANGALORE);
        bookMyShow.createBooking("Pravinkoodu shappu", City.BANGALORE);
    }

    private void initialize() {
        //create movies
        createMovies();

        //create theater with screens, seats and shows
        createTheatre();
    }

    private void createMovies() {

        //create Movies1
        Movie rc = new Movie();
        rc.setId(1);
        rc.setName("Rekha Chitram");
        rc.setDuration(128);

        //create Movies1
        Movie gc = new Movie();
        gc.setId(2);
        gc.setName("Game Changer");
        gc.setDuration(148);

        //create Movies2
        Movie pk = new Movie();
        pk.setId(3);
        pk.setName("Pravinkoodu shappu");
        pk.setDuration(180);


        //add movies against the cities
        movieController.addMovie(rc, City.BANGALORE);
        movieController.addMovie(rc, City.PUNE);
        movieController.addMovie(gc, City.BANGALORE);
        movieController.addMovie(pk, City.HYDERABAD);
        movieController.addMovie(pk, City.KOCHI);
    }

    private void createTheatre() {

        Movie rekhaChitram = movieController.getMovieByName("Rekha Chitram");
        Movie pravinKoodu = movieController.getMovieByName("Pravinkoodu shappu");
        Movie gc = movieController.getMovieByName("Game Changer");

        Theatre inoxTheatre = new Theatre();
        inoxTheatre.setId(1);
        inoxTheatre.setScreenList(createScreen());
        inoxTheatre.setCity(City.BANGALORE);
        List<Show> inoxShows = new ArrayList<>();
        Show inoxMorningShow = createShows(1, inoxTheatre.getScreenList().get(1), rekhaChitram, 8);
        Show inoxEveningShow = createShows(2, inoxTheatre.getScreenList().get(0), gc, 16);
        inoxShows.add(inoxMorningShow);
        inoxShows.add(inoxEveningShow);
        inoxTheatre.setShowList(inoxShows);


        Theatre pvrTheatre = new Theatre();
        pvrTheatre.setId(2);
        pvrTheatre.setScreenList(createScreen());
        pvrTheatre.setCity(City.HYDERABAD);
        List<Show> pvrShows = new ArrayList<>();
        Show pvrMorningShow = createShows(3, pvrTheatre.getScreenList().get(0), pravinKoodu, 13);
        Show pvrEveningShow = createShows(4, pvrTheatre.getScreenList().get(1), pravinKoodu, 20);
        pvrShows.add(pvrMorningShow);
        pvrShows.add(pvrEveningShow);
        pvrTheatre.setShowList(pvrShows);

        theatreController.addTheatre(City.BANGALORE, inoxTheatre);
        theatreController.addTheatre(City.HYDERABAD, pvrTheatre);

    }

    private List<Screen> createScreen() {

        List<Screen> screens = new ArrayList<>();
        Screen screen1 = new Screen();
        screen1.setId(1);
        screen1.setSeats(createSeats());

        Screen screen2 = new Screen();
        screen2.setId(2);
        screen2.setSeats(createSeats());

        screens.add(screen1);
        screens.add(screen2);

        return screens;
    }

    private Show createShows(int showId, Screen screen, Movie movie, int showStartTime) {

        Show show = new Show();
        show.setId(showId);
        show.setScreen(screen);
        show.setMovie(movie);
        show.setStartTime(showStartTime);
        return show;
    }

    //creating 100 seats
    private List<Seat> createSeats() {

        //creating 100 seats for testing purpose, this can be generalised
        List<Seat> seats = new ArrayList<>();

        //1 to 40 : SILVER
        for (int i = 0; i < 40; i++) {
            Seat seat = new Seat();
            seat.setId(i);
            seat.setSeatCategory(SeatCategory.SILVER);
            seats.add(seat);
        }

        //41 to 70 : SILVER
        for (int i = 40; i < 70; i++) {
            Seat seat = new Seat();
            seat.setId(i);
            seat.setSeatCategory(SeatCategory.GOLD);
            seats.add(seat);
        }

        //1 to 40 : SILVER
        for (int i = 70; i < 100; i++) {
            Seat seat = new Seat();
            seat.setId(i);
            seat.setSeatCategory(SeatCategory.PLATINUM);
            seats.add(seat);
        }

        return seats;
    }

    private void createBooking(String movieName, City city) {

        //1 search by location
        List<Movie> movies = movieController.getMoviesByCity(city);

        Movie interestedMovie = null;

        //2 select movie from this
        for (Movie movie1 : movies) {
            if (Objects.equals(movie1.getName(), movieName)) {
                interestedMovie = movie1;
            }
        }

        if (interestedMovie == null) {
            System.out.println("No shows of the desired movie in the selected city");
        } else {
            //3 get all shows
            Map<Theatre, List<Show>> theatreVsShows = theatreController.getAllShows(interestedMovie, city);

            //4 select a theatre and a show
            Map.Entry<Theatre, List<Show>> interestedTheatreVsShows = theatreVsShows.entrySet().iterator().next();
            List<Show> runningShows = interestedTheatreVsShows.getValue();
            Show interestedShow = runningShows.getFirst();

            //5 select the seat
            int interestedSeatNumber = 30;
            List<Integer> bookedSeats = interestedShow.getBookedSeatIds();

            if (!bookedSeats.contains(interestedSeatNumber)) {
                bookedSeats.add(interestedSeatNumber);
                //Available for booking
                Booking booking = new Booking();
                List<Seat> seatsBookedByUser = new ArrayList<>();
                for (Seat seat : interestedShow.getScreen().getSeats()) {
                    if (seat.getId() == interestedSeatNumber) {
                        seatsBookedByUser.add(seat);
                    }
                }
                Payment payment = new Payment();
                payment.setId(1);
                payment.setAmount(200);

                booking.setBookedSeats(seatsBookedByUser);
                booking.setId(bookingIdCount++);
                booking.setShow(interestedShow);
                booking.setPayment(payment);

                System.out.println("BOOKING SUCCESSFUL. Here are the details:");
                System.out.println("Movie name " + booking.getShow().getMovie().getName());
                System.out.println("screen number " + booking.getShow().getScreen().getId());
                System.out.println("seat numbers: " + booking.getBookedSeats().getFirst());
                System.out.println("Show time: " + booking.getShow().getStartTime());
                System.out.println("-----------------------------");
            } else {
                //throw exception
                System.out.println("seat already booked, try again");
            }
        }
    }
}
