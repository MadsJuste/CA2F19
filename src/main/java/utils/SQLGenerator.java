/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Random;

/**
 *
 * @author mwn
 */
public class SQLGenerator {

    private static String[] addresses = {"Enghaven", "Mølleager", "Gartnerhaven", "Birkevej", "Hesselhaven", "Solbakken", "Sorøvej", "Ved Fjorden", "Grendalsvej", "Næstvedvej", "Enghaven", "Solbakken", "Grendalsvej", "Gl. Strand", "Frederikskaj", "Overgade", "Vermlandsgade", "Nyropsgade", "Blegdamsvej", "Smakkedalen", "John Tranums Vej", "Kampmannsgade", "Snorresgade", "Molestien", "Weidekampsgade", "Klokhøjen", "Ramsingsvej", "Brolæggerstræde"};
    private static String[] firstNames = {"Ida", "Ella", "Emma", "Alma", "Sofia", "Keld", "Kell", "Kelvin", "Kenn", "Kenneth", "Kenni", "Nicklas", "Nicky", "Nicolas", "Niels", "Tobias", "Tristan", "Camilla", "Carin", "Carina", "Carla", "Carmen", "Carola", "Carolina", "Caroline", "Jane", "Jani", "Janice", "Janni", "Jannie", "Jasmin", "Jeane", "Jeanet", "Oda", "Odine", "Ofelia", "Silje", "Silke", "Simone", "Sissel", "Siv", "Smilla", "Soffie", "Sofia", "Sofie"};
    private static String[] lastNames = {"Andreassen", "Bisgård", "Bisgaard", "Bjerre", "Bjerregård", "Bjerregaard", "Bonde", "Brandt", "Brodersen", "Bruun", "Buch", "Bundgård", "Bundgaard", "Carlsen", "Carstensen", "Friis", "Frost", "Gade", "Gregersen", "Hald", "Hansen", "Hassan", "Hedegård", "Hedegaard", "Hemmingsen", "Jeppesen", "Jepsen", "Jespersen", "Jessen", "Johannesen", "Johannsen", "Johansen", "Johansson", "Johnsen", "Schou", "Schrøder", "Schultz", "Simonsen", "Skov"};
    private static String[] mails = {"gmail.com", "hotmail.com", "yahoo.dk", "live.dk", "outlook.dk"};
    private static String[] hobbies = {"Fodbold", "Gymnastik", "Fitness", "Svømning", "Golf", "Håndbold", "Badminton", "Ridning"};
    private static String[] phoneDesc = {"Hjem", "Arbejde", "Mobil"};
    private static Random random = new Random();

    private static int getNumber(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static String generateAddresses(int amount) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < amount; i++) {
            sb.append("INSERT INTO `ca2`.`ADDRESS` (`Street`, `additionalinfo`, `Cityinfo_id`) VALUES ('")
                    .append(addresses[getNumber(0, addresses.length - 1)])
                    .append(" ")
                    .append(getNumber(1, 450))
                    .append("', 'Test', '")
                    .append(getNumber(1, 1352))
                    .append("'")
                    .append(");\n");
        }

        return sb.toString();
    }

    public static String generateNames(int amount) {
        StringBuilder sb = new StringBuilder();
        String firstName = null;
        for (int i = 0; i < amount; i++) {
            firstName = firstNames[getNumber(0, firstNames.length - 1)];
            sb.append("INSERT INTO `ca2`.`PERSON` (`email`, `firstname`, `lastname`, `Address_ID`) VALUES ('")
                    .append(firstName.toLowerCase())
                    .append("@")
                    .append(mails[getNumber(0, mails.length - 1)])
                    .append("', '")
                    .append(firstName)
                    .append("', '")
                    .append(lastNames[getNumber(0, lastNames.length - 1)])
                    .append("', '")
                    .append(getNumber(1, amount))
                    .append("'")
                    .append(");\n");
        }

        return sb.toString();
    }

    public static String generatePhones(int amount) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < amount; i++) {
            sb.append("INSERT INTO `ca2`.`PHONE` (`number`, `description`, `Person_ID`) VALUES ('")
                    .append(getNumber(12345678, 99999999))
                    .append("', '")
                    .append(phoneDesc[getNumber(0, phoneDesc.length - 1)])
                    .append("', '")
                    .append(getNumber(1, amount))
                    .append("'")
                    .append(");\n");
        }

        return sb.toString();
    }

    public static String generateHobbies() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hobbies.length; i++) {
            sb.append("INSERT INTO `ca2`.`HOBBY` (`name`, `description`) VALUES ('")
                    .append(hobbies[i])
                    .append("', '")
                    .append("Test")
                    .append("');\n");
        }

        return sb.toString();
    }

    public static String personHobbies(int amount) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < amount; i++) {
            sb.append("INSERT INTO `ca2`.`PERSON_HOBBY` (`hobbies_id`, `Persons_ID`) VALUES ('")
                    .append(getNumber(1, hobbies.length))
                    .append("', '")
                    .append(getNumber(1, amount))
                    .append("'")
                    .append(");\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println(generateAddresses(500));
////        System.out.println("");
//        System.out.println(generateNames(1000));
//        System.out.println("");
//        System.out.println(generatePhones(500));
//        System.out.println("");
        System.out.println(generateHobbies());
//        System.out.println("");
//        System.out.println(personHobbies(100));
    }

}
