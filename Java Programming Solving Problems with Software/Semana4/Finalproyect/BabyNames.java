import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class BabyNames {
    public void totalBirths(FileResource fr) {
        int totalBirths  = 0;
        int totalGirls   = 0;
        int totalBoys    = 0;
        CSVParser parser = fr.getCSVParser(false);

        for (CSVRecord record : parser) {
            int numBorn = Integer.parseInt(record.get(2));
            String gender = record.get(1);
            totalBirths += numBorn;
            
            if (gender.equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
            
        }

        System.out.println("Total: " + totalBirths);
        System.out.println("Niños: " + totalBoys);
        System.out.println("Niñas: " + totalGirls);
    }
    
    public long getRank(int year, String name, String gender) {
	long rank = -1;
	FileResource fr  = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv");
	CSVParser parser = fr.getCSVParser(false);

	for (CSVRecord record : parser) {
	    String currName   = record.get(0);
	    String currGender = record.get(1);
			
	    if (currGender.equals(gender) && currName.equals(name)) {
	        rank = record.getRecordNumber();
	    }
	    
	}
	
	return rank;
    }
    
    public String getName(int year, int rank, String gender) {
	String name = "";
	FileResource fr  = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv");
	CSVParser parser = fr.getCSVParser(false);

	for (CSVRecord record : parser) {
	    long currRank = record.getRecordNumber();
	    String currGender = record.get(1);
	    String currName   = record.get(0);

	    if (currRank == rank && currGender.equals(gender)) {
		name = currName;
	    }
	    
	}

	if (name != "") {
	    return name;
	} 
	else {
	    return "NO NAME";
	}
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
	FileResource fr     = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv");
	FileResource newFr  = new FileResource("us_babynames/us_babynames_test/yob" + newYear + "short.csv");
	CSVParser parserOld = fr.getCSVParser(false);
	CSVParser parserNew = newFr.getCSVParser(false);
	String newName  = "";
	long popularity = 0;

	for (CSVRecord record : parserOld) {
	    String currName   = record.get(0);
	    String currGender = record.get(1);

	    if (currName.equals(name) && currGender.equals(gender)) {
	        popularity = record.getRecordNumber();
	    }
	    
	}

	for(CSVRecord record : parserNew) {
	    String currGender = record.get(1);
	    long currPopularity = record.getRecordNumber();

	    if(currGender.equals(gender) && popularity == currPopularity) {
		newName = record.get(0);
	    }
	}

        System.out.println(name + " Nacio en " + year + " Se llamaria " + newName + " Si ella nace en " + newYear);
    }
    
    public int yearOfHighestRank(String name, String gender) {
	long highestRank = 0;
	int yearOfHighestRank = -1;
	DirectoryResource dr  = new DirectoryResource();
	String fileName = "";
			
	// Iterar en todos los archivos
	for( File f : dr.selectedFiles()) {
	    FileResource fr  = new FileResource(f);
	    CSVParser parser = fr.getCSVParser(false);
			
	    // Iterar en todos los records de un archivo
	    for (CSVRecord record : parser) {
	        String currName   = record.get(0);
		String currGender = record.get(1);

		if (currName.equals(name) && currGender.equals(gender)) {
		    long currRank = record.getRecordNumber();
					
		    if (highestRank == 0) {
			highestRank = currRank;
			fileName = f.getName();
					} 
		    else {
		        if(highestRank > currRank) {
		            highestRank = currRank;
		            fileName = f.getName();
			}
			
		    }
		    
		}
		
	    }
	    
	}

	// Quitamos todos los caracteres no numericos del nombre del archivo
	fileName = fileName.replaceAll("[^\\d]", "");
		
	// Convertimos String fileName a Integer
	yearOfHighestRank = Integer.parseInt(fileName);

	return yearOfHighestRank;
    }
    
    public double getAverageRank(String name, String gender) {
	DirectoryResource dr = new DirectoryResource();
	double rankTotal = 0.0;
	int howMany = 0;
		
	for (File f : dr.selectedFiles()) {
	    FileResource fr  = new FileResource(f);
	    CSVParser parser = fr.getCSVParser(false);
	    
	    for (CSVRecord record : parser) {
	        String currName   = record.get(0);
	        String currGender = record.get(1);
	        
	        if (currName.equals(name) && currGender.equals(gender)) {
	            long currRank = record.getRecordNumber();
	            rankTotal += (double)currRank;
	            howMany += 1;
		}
		
	    }
	    
	}
	
	double avgRank = rankTotal / (double)howMany;
	return avgRank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
	int numBorn = 0;
	long rank   = getRank(year, name, gender);
	FileResource fr  = new FileResource();
	CSVParser parser = fr.getCSVParser(false);
	
	for (CSVRecord record : parser) {
	    int currBorn  = Integer.parseInt(record.get(2));
	    String currGender = record.get(1);
	    long currRank = record.getRecordNumber();
	    
	    if (gender.equals(currGender) && rank > currRank) {
	        numBorn += currBorn;
	    }
	    
	}
	
	return numBorn;
    }
    /*
    public void testTotlaBirth() {
	FileResource fr = new FileResource("yob1900.csv");
	totalBirths(fr);

	// long rank = getRank(2012, "Mason", "M");
	// System.out.println("Rank is: " + rank);

	String name = getName(2012, 10, "M");
	System.out.println("Name: " + name);

	// whatIsNameInYear("Isabella", 2012, 2014, "F");

	// System.out.println(yearOfHighestRank("Mason", "M"));
		
	//System.out.println(getAverageRank("Mason", "M"));
		
	// System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
    }

    public static void main() {
        BabyNames names = new BabyNames();
	names.testTotlaBirth();
    }*/
}
    

