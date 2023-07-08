package Unicorns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Unicorn {
    private String color;
    private Date birthday;
    private boolean sex;
    private String magic;
    private String name;
    private int power;
    private int rarity;
    private long id;
    private int health;
    private int defense;

    private static Stack<Long> idStack = new Stack<>(); // Стэк единорожьих айдишников, чтобы автоматически генерировать новые из последнего
    public static HashSet<Unicorn> uniSet = new HashSet<>(); // Множество единорогов.

    public Unicorn(String color, String name, boolean sex, String magic, int power, int rarity, String birthday, int health, int defense) throws ParseException {
        this.color = color;
        this.sex = sex;
        this.magic = magic;
        this.name = name;
        this.power = power;
        this.rarity = rarity;
        this.birthday = parseDate(birthday);
        this.health = health;
        this.defense = defense;
        this.id = identifier(); // Генерируем уникальный айди
        idStack.add(id);
        uniSet.add(this); // Сразу при создании добавляем единорога в множество
    }

    // Ленивый конструктор
    public Unicorn(String name) throws ParseException {
        this("color unknown", name, false, "magic unknown", 0, 0, "birthday unknown", 0, 0);
    }

    // Много сеттеров и геттеров
    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean getSex() {
        return sex;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public String getMagic() {
        return magic;
    }

    public void setPower(int power) {
        if (power >= 0 && power <= 200) {
            this.power = power;
        } else {
            System.out.println("Sorry, couldn't set power. It shoulnd be from 0 to 200.");
        }
    }

    public int getPower() {
        return power;
    }

    public void setRarity(int rarity) {
        if (rarity >= 0 && rarity <= 10) {
            this.rarity = rarity;
        } else {
            System.out.println("Sorry, couldn't set rarity. It should be from 0 to 10.");
        }
    }

    public int getRarity() {
        return rarity;
    }

    public void setBirthDate(String birthday) throws ParseException {
        this.birthday = parseDate(birthday);
    }

    public Date getBirthDate() {
        return birthday;
    }

    public void setHealth(int health) {
        if (health > 0 && health <= 300) {
            this.health = health;
        } else {
            System.out.println("Sorry, couldn't set health. It should be from 1 to 300");
        }
    }

    public int geHealth() {
        return health;
    }

    public void setDefense(int defense) {
        if (defense >= 0 && defense <= 100) {
            this.defense = defense;
        } else {
            System.out.println("Sorry, couldn't set defence. It should be from 0 to 100");
        }
    }

    public int getDefense() {
        return defense;
    }

    
    /**
     * Переводит строку в дату. Если не парсится — берется дата создания экземпляра.
     * @param d строка с датой
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String d) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("dd.MM.yyyy");
            Date birthday = sdf.parse(d);
            return birthday;
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * Считает возраст единорожки.
     * @return
     */
    public long ageCounter() {
        Date today = new Date();
        long millisecs = today.getTime() - birthday.getTime();
        long inYear = 365 * 24 * 60 * 60 * 1000L;
        long age = millisecs / inYear;
        return age;
    }

    /**
     * Считает урон, который наносит единорожка, исходя из его мощи
     * @return
     */
    public int damager() {
        float coefficient = (float) power / (float) 100;
        Random rand = new Random();
        float damage = rand.nextInt(5, power);
        damage = damage * coefficient;
        return (int) damage;
    }

    /**
     * Считает урон, который получает другой единорожка, исходя из защиты
     * @param damage
     */
    public void damageGetter(int damage) {
        float coefficient = (float) defense / (float) 100;
        float harm = (float) damage * coefficient;
        health = health - (int) harm;
    }

    /**
     * Атака
     * @param one Атакующий
     * @param two Огребающий
     */
    public static void attack(Unicorn one, Unicorn two) {
        int damage = one.damager();
        two.damageGetter(damage);
    }

    // Некоторые единорожки могут лечить и усиливать друг друга

    /**
     * Считает очки здоровья, которые восстанавливает лекарь
     * @return
     */
    public int healer() {
        float coefficient = (float) power / (float) 100;
        Random rand = new Random();
        float healpoints = rand.nextInt(5, power);
        healpoints = healpoints * coefficient;
        return (int) healpoints;
    }

    /**
     * Прибавляет к здоровью единорожки очки лечения
     * @param healpoints
     */
    public void healGetter(int healpoints) {
        health = health + healpoints;
    }

    /**
     * Лечение
     * @param one Лекарь
     * @param two Пациент
     */
    public void healing(Unicorn one, Unicorn two) {
        int healpoints = one.healer();
        two.healGetter(healpoints);
    }


    /**
     * Считает очки вдохновения, которые дает поддержка единорожки
     * @return
     */
    public int inspirator() {
        float coefficient = (float) power / (float) 100;
        Random rand = new Random();
        float inspirepoints = rand.nextInt(5, power);
        inspirepoints = inspirepoints * coefficient;
        return (int) inspirepoints;
    }

    /**
     * Прибавляет очки вдохновения к мощи другого единорожки
     * @param inspirepoints
     */
    public void inspirationGetter(int inspirepoints) {
        power = power + inspirepoints;
    }

    /**
     * Усиление
     * @param one Поддержка
     * @param two Получатель
     */
    public void inspiring(Unicorn one, Unicorn two) {
        int inspirepoints = one.inspirator();
        two.inspirationGetter(inspirepoints);
    }

    /**
     * Генерирует айди из айди последнего единорожки в стэке
     * @return
     */
    public long identifier() {
        if (idStack.isEmpty()) {
            return 0;
        } else {
            return idStack.peek() + 1; 
        }
    }
    
    @Override
    public String toString() {
        String description = "\nUnicorn " + name + ", " + color + ". Master of " + magic + ".\n" +
                                "Health: " + health + ", power: " + power + ", defense: " + defense +
                                ".\nID: " + id + "\nRarity: " + rarity + "\n"; 
        return description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Unicorn toCompare = (Unicorn) obj;

        return this.id == toCompare.id;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Ищет единорогов, соответствующих заданному минимальному значению по заданному критерию
     * @param minValue минимальное значение
     * @param criteria характеристика
     * @return
     */
    public static HashSet<Unicorn> searcher(int minValue, String criteria) {
        HashSet<Unicorn> filtered  = new HashSet<>();
        switch (criteria) {
            case "power":
                for (Unicorn uni : uniSet) {
                    if (uni.power >= minValue) {
                        filtered.add(uni);
                    }
                }
                break;
            case "health":
                for (Unicorn uni : uniSet) {
                    if (uni.health >= minValue) {
                        filtered.add(uni);
                    }
                }
                break;
            case "defense":
                for (Unicorn uni : uniSet) {
                    if (uni.defense >= minValue) {
                        filtered.add(uni);
                    }
                }
                break;
            case "rarity":
                for (Unicorn uni : uniSet) {
                    if (uni.rarity >= minValue) {
                        filtered.add(uni);
                    }
                }
                break;
        }
        return filtered;
    }

    /**
     * Запрашивает критерий фильтрации и выдает результат.
     */
    public static void filter() {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose number of filter and enter:\n1 — power\n2 — health\n3 — defense\n4 — rarity");
        String filter = in.nextLine();
        HashSet<Unicorn> filtered = new HashSet<>();
        switch (filter) {
            default:
                System.out.println("There is no such option.");
                break;
            case "1":
                System.out.println("Enter the minimal power value: ");
                String minPower = in.nextLine();
                if (isInteger(minPower)) {
                    filtered = searcher(Integer.parseInt(minPower), "power");
                }
                break;
            case "2":
                System.out.println("Enter the minimal health value: ");
                String minHealth = in.nextLine();
                if (isInteger(minHealth)) {
                    filtered = searcher(Integer.parseInt(minHealth), "health");
                }
                break;
            case "3":
                System.out.println("Enter the minimal defense value: ");
                String minDefense = in.nextLine();
                if (isInteger(minDefense)) {
                    filtered = searcher(Integer.parseInt(minDefense), "defense");
                }
                break;
            case "4":
                System.out.println("Enter the minimal rarity value: ");
                    String minRarity = in.nextLine();
                    if (isInteger(minRarity)) {
                        filtered = searcher(Integer.parseInt(minRarity), "rarity");
                    }
                    break;
            }
        if (filtered.isEmpty()) {
            System.out.println("There are no such unicorns");
        } else {
            System.out.println(filtered);
        }
        in.close();
    }
}
