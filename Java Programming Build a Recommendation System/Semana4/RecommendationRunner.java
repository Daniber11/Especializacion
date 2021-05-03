import java.util.*;

public class RecommendationRunner implements Recommender {
    
    public ArrayList<String> getItemsToRate () {
        ArrayList<String> itemsToRate = new ArrayList<String> ();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
       
        for (int i=0; i < 20; i++) {
            Random rand = new Random();
            int random = rand.nextInt(movies.size());
            if (! itemsToRate.contains(movies.get(random))) {
                itemsToRate.add(movies.get(random));
            }
        }
        
        return itemsToRate;
    }
    
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings ();
        MovieDatabase.initialize("ratedmoviesfull");
        RaterDatabase.initialize("ratings");        
        System.out.println("<p>Leer datos para " + Integer.toString(RaterDatabase.size()) + " evaluadores</p>");
        System.out.println("<p>Leer datos para " + Integer.toString(MovieDatabase.size()) + "peliculas</p>");        
        int numSimilarRaters = 50; // variable
        int minNumOfRatings = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minNumOfRatings);
        
        if (similarRatings.size() == 0) {
            System.out.println("No se encontraron pel�culas que coincidan");
        } else {
            String header = ("<table> <tr> <th>T�tulo de la pel�cula</th> <th>Valor de calificaci�n</th>  <th>Generos</th> </tr>");
            String body = "";
            for (Rating rating : similarRatings) {
                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) + "</td> <td>" + Double.toString(rating.getValue()) + "</td> <td>" + MovieDatabase.getGenres(rating.getItem()) + "</td> </tr> ";
            }
            System.out.println(header  + body + "</table>");
        }
    }
}
