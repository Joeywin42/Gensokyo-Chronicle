public class CharacterObjects {
    String name = "";
    String title = "";
    String species = "";
    String abilities = "";
    int age = 0;
    String occupation = "";
    String location = "";
    String musicThemes = "";
    String officialGames = "";


    public CharacterObjects(){};
    public CharacterObjects(String name){
        this.name = name;

    }
    @Override
    public String toString(){
        return "name: " + getName() + ",\n" +
                "title: " + getTitle() + ",\n" +
                "species: " + getSpecies() + ",\n" +
                "abilities: " + getAbilities() + ",\n" +
                "age: " + getAge() + ",\n" +
                "occupation: " + getOccupation() + ",\n" +
                "location: " + getLocation() + ",\n" +
                "musicThemes: " + getMusicThemes() + ",\n" +
                "officialGames: " + getOfficialGames() + "\n";
    }

    //region Getters and Setters for all String Values

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getSpecies() {
        return species;
    }

    public String getAbilities() {
        return abilities;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getLocation() {
        return location;
    }

    public String getMusicThemes() {
        return musicThemes;
    }

    public String getOfficialGames() {
        return officialGames;
    }

    public CharacterObjects setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterObjects setTitle(String title) {
        this.title = title;
        return this;
    }

    public CharacterObjects setSpecies(String species) {
        this.species = species;
        return this;
    }

    public CharacterObjects setAbilities(String abilities) {
        this.abilities = abilities;
        return this;
    }

    public CharacterObjects setAge(int age) {
        this.age = age;
        return this;
    }

    public CharacterObjects setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public CharacterObjects setLocation(String location) {
        this.location = location;
        return this;
    }

    public CharacterObjects setMusicThemes(String musicThemes) {
        this.musicThemes = musicThemes;
        return this;
    }

    public CharacterObjects setOfficialGames(String officialGames) {
        this.officialGames = officialGames;
        return this;
    }

    //endregion

    public static void main(String[] args) {
        //TESTER

        CharacterObjects test = new CharacterObjects();


        test.setName("")
                .setTitle("")
                .setSpecies("")
                .setAbilities("")
                .setAge(500)
                .setOccupation("")
                .setLocation("")
                .setMusicThemes("")
                .setOfficialGames("");

        System.out.println(test);

    }


}
