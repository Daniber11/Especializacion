import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + RaterDatabase.size() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");        
        int minNumOfRatings = 35; // variable
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatings(minNumOfRatings);
        System.out.println("Hay " + averageRatings.size() + " peliculas con " + minNumOfRatings + "o m�s calificaciones:");        
        Collections.sort(averageRatings);
        
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + RaterDatabase.size() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");
        int year = 1990; // variable
        YearAfterFilter yaf = new YearAfterFilter (year);        
        String genre   = "Drama"; // variable
        GenreFilter gf = new GenreFilter (genre);        
        AllFilters af  = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);        
        int minNumOfRatings = 8; // variable
        ArrayList<Rating> avgRatings = fourthRatings.getAverageRatingsByFilter(minNumOfRatings, af);
        System.out.println("Hay " + avgRatings.size() + " pel�culas) en el g�nero de \"" + genre + "\" que fue (fueron) dirigidos despu�s de " + year + " con " + minNumOfRatings + " o m�s calificaciones: ");
        
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genero : " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatings () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + RaterDatabase.size() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");  
        String id = "71"; // variable
        int numSimilarRaters = 20; // variable
        int minimalRaters    = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        System.out.println("Hay " + similarRatings.size() + " pel�cula(s) que es (son) " + "recomendado para el evaluador con identificaci�n " + id + " con " + minimalRaters + " o m�s calificaciones. " + numSimilarRaters + " se consideraron los evaluadores m�s cercanos.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + RaterDatabase.size() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");  
        String genre   = "Mystery"; // variable
        GenreFilter gf = new GenreFilter(genre);
        String id      = "964"; // variable
        int numSimilarRaters = 20; // variable
        int minimalRaters    = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, gf);
        System.out.println("Hay " + similarRatings.size() + " pel�cula(s) que es (son) " + "recomendado para el evaluador con identificaci�n " + id + " y con " + minimalRaters + " o m�s calificacion (es), en \"" + genre + "\" genero. " + numSimilarRaters + " se consideraron los evaluadores m�s cercanos.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genero: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + RaterDatabase.size() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"; // variable
        DirectorsFilter df = new DirectorsFilter(directors);         
        String id = "120"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters    = 2; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, df);
        System.out.println("Hay " + similarRatings.size() + " pel�cula(s) que es (son) " + "recomendado para el evaluador con identificaci�n " + id + " y con " + minimalRaters + " o m�s calificaciones, que fueron dirigidas por cualquiera de los siguientes directores: " + directors + ". " + numSimilarRaters + " se consideraron los evaluadores m�s cercanos.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Dirigido por : " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + RaterDatabase.size() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");  
        String genre   = "Drama"; // variable
        GenreFilter gf = new GenreFilter (genre);        
        int minMin     = 80; // variable
        int maxMin     = 160; // variable
        MinutesFilter mf = new MinutesFilter (minMin, maxMin);        
        AllFilters af    = new AllFilters();
        af.addFilter(gf);
        af.addFilter(mf);        
        String id = "168"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters    = 3; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " pel�cula(s) que es (son) " + "recomendado para el evaluador con identificaci�n " + id + " y con " + minimalRaters + " o m�s calificacion (es):, en \"" + genre + "\" g�nero, es decir (est�n) entre " + minMin + " y " + maxMin + " minutos de duraci�n. " + numSimilarRaters + " se consideraron los evaluadores m�s cercanos.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " Tiempo " + MovieDatabase.getMinutes(rating.getItem()));
            System.out.println("Genero: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + RaterDatabase.size() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");   
        int year = 1975; // variable
        YearAfterFilter yaf = new YearAfterFilter (year);        
        int minMin = 70; // variable
        int maxMin = 200; // variable
        MinutesFilter mf = new MinutesFilter (minMin, maxMin);        
        AllFilters af    = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(mf);        
        String id = "314"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters    = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " pel�cula(s) que es (son) " + "recomendado para el evaluador con identificaci�n " + id + " y con " + minimalRaters + " o m�s calificaciones, es decir, entre " + minMin + " y " + maxMin + " minutos de duraci�n y publicado tras a�o " + year + ". " + numSimilarRaters + " se consideraron los evaluadores m�s cercanos.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " A�o: " + MovieDatabase.getYear(rating.getItem()) + " Tiempo: " + MovieDatabase.getMinutes(rating.getItem()));
        }
    }
}
