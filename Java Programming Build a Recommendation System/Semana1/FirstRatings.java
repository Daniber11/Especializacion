import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies (String filename) {
        ArrayList<Movie> movieData = new ArrayList<Movie> ();        
        FileResource fr  = new FileResource("data/" + filename + ".csv");
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record: parser) {
            String currentID       = record.get(0);
            String currentTitle    = record.get(1);
            String currentYear     = record.get(2);
            String currentCountry  = record.get(3);
            String currentGenre    = record.get(4);
            String currentDirector = record.get(5);
            int currentMinutes     = Integer.parseInt(record.get(6));
            String currentPoster   = record.get(7);            
            Movie currentMovie     = new Movie(currentID, currentTitle, currentYear, currentGenre, currentDirector, 
            currentCountry, currentPoster, currentMinutes);            
            movieData.add(currentMovie);
        }
        
        return movieData;
    }
    
    // casos de prueba para el archivo
    public void testLoadMovies () {
        ArrayList<Movie> movies = loadMovies("ratedmoviesfull");        
        System.out.println("Numero de peliculas: " + movies.size());        
        String countInGenre = "Comedy"; // variable
        int countComedies   = 0;        
        int minutes = 150; // variable
        int countMinutes = 0;
        
        for (Movie movie : movies) {
            if (movie.getGenres().contains(countInGenre)) {
                countComedies +=1;
            }
            
            if (movie.getMinutes() > minutes) {
                countMinutes +=1;
            }
        }
        
        System.out.println("Hay " + countComedies + " comedias en la lista ");
        System.out.println("Hay " + countMinutes + " películas con más de " + minutes + " minutos en la lista ");
        
        // Cree un HashMap con el recuento de cuántas películas filmó cada director en particular
        HashMap<String,Integer> countMoviesByDirector = new HashMap<String,Integer> ();
        for (Movie movie : movies) {
            String[] directors = movie.getDirector().split(",");            
            for (String director : directors ) {
                director = director.trim();
                if (! countMoviesByDirector.containsKey(director)) {
                    countMoviesByDirector.put(director, 1);                    
                } else {
                    countMoviesByDirector.put(director, countMoviesByDirector.get(director) + 1);
                }
            }
        }
        
        // Contar el número máximo de películas dirigidas por un director en particular
        int maxNumOfMovies = 0;
        for (String director : countMoviesByDirector.keySet()) {
            if (countMoviesByDirector.get(director) > maxNumOfMovies) {
                maxNumOfMovies = countMoviesByDirector.get(director);
            }
        }
        
        // Cree una ArrayList con directores de la lista que dirigieron el número máximo de películas
        ArrayList<String> directorsList = new ArrayList<String> ();
        for (String director : countMoviesByDirector.keySet()) {
            if (countMoviesByDirector.get(director) == maxNumOfMovies) {
                directorsList.add(director);
            }
        }
        
        System.out.println("Número máximo de películas dirigidas por un director: " + maxNumOfMovies);
        System.out.println("Los directores que dirigieron mas películas son " + directorsList);
    }
    
    public ArrayList<Rater> loadRaters (String filename) {
        ArrayList<Rater> ratersData = new ArrayList<Rater> ();
        ArrayList<String> listOfIDs = new ArrayList<String> ();        
        FileResource fr = new FileResource("data/" + filename + ".csv");
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            String currentRaterID = record.get(0);
            String currentMovieID = record.get(1);
            double currentMovieRating = Double.parseDouble(record.get(2));
            
            if (! listOfIDs.contains(currentRaterID)) {
                Rater currentRater = new Rater(currentRaterID);
                ratersData.add(currentRater);
                currentRater.addRating(currentMovieID, currentMovieRating);                
            } else {
                for (int k=0; k < ratersData.size(); k++) {
                    if (ratersData.get(k).getID().equals(currentRaterID)) {
                        ratersData.get(k).addRating(currentMovieID, currentMovieRating);
                    }
                }
            }
            
            listOfIDs.add(currentRaterID);
        }
        
        return ratersData;
    }
    
    // casos de prueba para el archivo
    public void testLoadRaters () {
        ArrayList<Rater> raters = loadRaters("ratings");        
        System.out.println("Número total de evaluadores: " + raters.size());        
        HashMap<String, HashMap<String, Double>> hashmap = new HashMap<String, HashMap<String, Double>> ();
        
        for (Rater rater : raters) {
            HashMap<String, Double> ratings = new HashMap<String, Double> ();
            ArrayList<String> itemsRated = rater.getItemsRated();
            
            for (int i=0; i < itemsRated.size(); i++) {
                String movieID = itemsRated.get(i);
                double movieRating = rater.getRating(movieID);
                
                ratings.put(movieID, movieRating);
            }
            
            hashmap.put(rater.getID(), ratings);
        }
        
        String raterID  = "193"; // rater_id
        int ratingsSize = hashmap.get(raterID).size();
        System.out.println("Número de calificaciones para el evaluador " + raterID + " : " + ratingsSize);        
        int maxNumOfRatings = 0;
        
        for (String key : hashmap.keySet()) {
            int currAmountOfRatings = hashmap.get(key).size();
            
            if (currAmountOfRatings > maxNumOfRatings) {
                maxNumOfRatings = currAmountOfRatings;
            }
        }
        
        System.out.println("Número máximo de calificaciones por cualquier evaluador: " + maxNumOfRatings);        
        ArrayList<String> raterWithMaxNumOfRatings = new ArrayList<String> ();
        
        for (String key : hashmap.keySet()) {
            int currAmountOfRatings = hashmap.get(key).size();
            
            if (maxNumOfRatings == currAmountOfRatings) {
                raterWithMaxNumOfRatings.add(key);
            }
        }
        
        System.out.println("Evaluador (es) con la mayor cantidad de películas calificadas: " + raterWithMaxNumOfRatings);        
        String movieID = "1798709";
        int numOfRatings = 0;
        
        for (String key : hashmap.keySet()) {
            if(hashmap.get(key).containsKey(movieID)) {
                numOfRatings +=1;
            }
        }
        
        System.out.println("Número de clasificaciones de la película " + movieID + " tiene : " + numOfRatings);        
        ArrayList<String> uniqueMovies = new ArrayList<String> ();
        
        for (String key : hashmap.keySet()) {
            for (String currMovieID : hashmap.get(key).keySet()) {
                if (! uniqueMovies.contains(currMovieID)) {
                    uniqueMovies.add(currMovieID);
                }
            }
        }
        
        System.out.println("Número total de películas unicas calificadas: " + uniqueMovies.size());
    }
}
