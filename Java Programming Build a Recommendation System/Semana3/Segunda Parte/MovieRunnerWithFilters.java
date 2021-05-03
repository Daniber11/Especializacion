import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + thirdRatings.getRaterSize() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");        
        int minNumOfRatings = 35; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatings(minNumOfRatings);
        System.out.println("Hay " + averageRatings.size() + " peliculas con " +minNumOfRatings + " o más calificaciones:");        
        Collections.sort(averageRatings);
        
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfter () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + thirdRatings.getRaterSize() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");        
        int year = 2000; // variable
        YearAfterFilter yaf = new YearAfterFilter (year);       
        int minNumOfRatings = 20; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, yaf);
        System.out.println("Hay " + averageRatings.size() + " películas lanzadas después "+ year + " con " + minNumOfRatings + " o más calificaciones: ");        
        Collections.sort(averageRatings);
        
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + thirdRatings.getRaterSize() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");        
        String genre   = "Comedy"; // variable
        GenreFilter gf = new GenreFilter (genre);        
        int minNumOfRatings = 20; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, gf);
        System.out.println("Hay " + averageRatings.size() + " películas en el género de \"" + genre + "\" con " + minNumOfRatings + " o más calificaciones: ");        
        Collections.sort(averageRatings);
        
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Género (s) : " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + thirdRatings.getRaterSize() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " pelicualas");        
        int minMinutes = 105; // variable
        int maxMinutes = 135; // variable
        MinutesFilter mf = new MinutesFilter (minMinutes, maxMinutes);        
        int minNumOfRatings = 5; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, mf);
        System.out.println("Hay " + averageRatings.size() + " películas que están entre " + minMinutes + " y " + maxMinutes + " longitud con " + minNumOfRatings + " o más calificaciones: ");
        Collections.sort(averageRatings);
        
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " Tiempo: " + MovieDatabase.getMinutes(rating.getItem())+ " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + thirdRatings.getRaterSize() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");        
        String directorsList = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"; // variable
        DirectorsFilter df = new DirectorsFilter (directorsList);        
        int minNumOfRatings = 4; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, df);
        System.out.println("Hay " + averageRatings.size() + " películas que fueron dirigidas " + " por cualquiera de esos directores: " +  directorsList + ", con " + minNumOfRatings + " o más calificaciones: ");        
        Collections.sort(averageRatings);
        
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Dirigido por : " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + thirdRatings.getRaterSize() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " peliculas");        
        int year = 1990; // variable
        YearAfterFilter yaf = new YearAfterFilter (year);        
        String genre   = "Drama"; // variable
        GenreFilter gf = new GenreFilter (genre);        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);        
        int minNumOfRatings = 8; // variable
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, af);
        System.out.println("Hay " + avgRatings.size() + " película (s) en el género de  \""+ genre + "\" que fue (fueron) dirigidos después de " + year + " con " + minNumOfRatings + " o más calificaciones: ");        
        Collections.sort(avgRatings);
        
        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genero : " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");        
        System.out.println("Leer datos para " + thirdRatings.getRaterSize() + " evaluadores");
        System.out.println("Leer datos para " + MovieDatabase.size() + " pelicula");        
        String directorsList = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"; // variable
        DirectorsFilter df = new DirectorsFilter (directorsList);        
        int minMinutes = 90; // variable
        int maxMinutes = 180; // variable
        MinutesFilter mf = new MinutesFilter (minMinutes, maxMinutes);        
        AllFilters af = new AllFilters();
        af.addFilter(df);
        af.addFilter(mf);        
        int minNumOfRatings = 3; // variable
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, af);
        System.out.println("Hay " + avgRatings.size() + " película (s) que fueron filmadas por " + " Cualquiera de estos directores: " + directorsList + "; and between "  + minMinutes + " y " + maxMinutes + " de longitud, con " + minNumOfRatings + " o más calificaciones: ");         
        Collections.sort(avgRatings);
        
        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " Tiempo: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Dirigida por : " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
}
