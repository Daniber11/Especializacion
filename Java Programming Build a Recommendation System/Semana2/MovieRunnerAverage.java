import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings () {
        SecondRatings secondRatings = new SecondRatings ("ratedmoviesfull", "ratings");        
        System.out.println("Número total de películas: " + secondRatings.getMovieSize());
        System.out.println("Número total de evaluadores: " + secondRatings.getRaterSize());        
        int MinNumOfRatings = 12; // variable
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(MinNumOfRatings);
        Collections.sort(averageRatings);
        
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
        }
        
        System.out.println("Hay " + averageRatings.size() + " películas con " + MinNumOfRatings + " o más calificaciones");
    }
    
    public void getAverageRatingOneMovie () {
        SecondRatings secondRatings = new SecondRatings ("ratedmoviesfull", "ratings");        
        String title = "No Country for Old Men"; // variable
        int MinNumOfRatings = 1; // variable        
        String movieID = secondRatings.getID(title);
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(MinNumOfRatings);
        
        for (Rating rating : averageRatings) {
            if (rating.getItem().equals(movieID)) {
                System.out.println("Para la pelicula \"" + title + "\" la calificación promedio es " + rating.getValue());
            }
        }
    }
}
