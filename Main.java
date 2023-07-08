import java.text.ParseException;

import Unicorns.Unicorn;

public class Main{
    public static void main(String[] args) throws ParseException {
        Unicorn truth = new Unicorn("blue", "Truth", false, "Emotional Damage", 100, 10, "03.14.0001", 100, 30);
        System.out.println(truth.toString());

        Unicorn lies = new Unicorn("Lies");
            lies.setColor("neon yellow");
            lies.setBirthDate("04.05.0000");
            lies.setMagic("Flattery");
            lies.setHealth(60);
            lies.setPower(200);
            lies.setDefense(15);
            lies.setRarity(3);

        System.out.println(lies.toString());

        // Unicorn.attack(truth, lies);
        // System.out.println(lies.toString());
        // Unicorn.attack(lies, truth);
        // System.out.println(truth.toString());

        System.out.println(truth.hashCode());
        System.out.println(lies.hashCode());

        System.out.println("Truth is Truth? " + truth.equals(truth) + "\n");
        System.out.println("Truth is Lies? " + truth.equals(lies) + "\n");

        Unicorn kindness = new Unicorn("peach", "Kindness", false, "Healing", 80, 8, "10.12.1023", 40, 90);
        Unicorn liberty = new Unicorn("white", "Liberty", true, "Inspiring", 50, 5, "29.02.1861", 70, 15);
        Unicorn oblivion = new Unicorn("black", "Oblivion", true, "Mind Influence", 150, 9, "31.01.1941", 130, 0);
        System.out.println(Unicorn.uniSet);
        System.out.println();
        System.out.println("Is there a unicorn named Oblivion? " + Unicorn.uniSet.contains(oblivion));
        System.out.println();

        // HashSet<Unicorn> searched = Unicorn.searcher(8, "rarity");
        // System.out.println(searched);
        System.out.println("Unicorn Kindness is " + kindness.ageCounter() + " years old. \n");
        System.out.println("Unicorn Liberty is " + liberty.ageCounter() + " years old. \n");

        Unicorn.filter();

    }
}