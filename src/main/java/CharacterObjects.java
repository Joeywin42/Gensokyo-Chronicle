public class CharacterObjects {
    private String name;
    private String image;
    private String title;
    private String species;
    private String abilities;
    private String age;
    private String occupation;
    private String location;
    private String musicThemes;
    private String officialGames;
    private String link;



    public CharacterObjects(){};
    public CharacterObjects(String name){
        this.name = name;

    }

    public CharacterObjects(String name, String image, String title, String species, String abilities, String age,
                            String occupation, String location, String musicThemes, String officialGames, String link) {
        this.name = name;
        this.image = image;
        this.title = title;
        this.species = species;
        this.abilities = abilities;
        this.age = age;
        this.occupation = occupation;
        this.location = location;
        this.musicThemes = musicThemes;
        this.officialGames = officialGames;
        this.link = link;

        System.out.println("created ---------------");
        //System.out.println(this);
    }

    @Override
    public String toString(){
        return "name: " + getName() + ",\n" +
                "image: " + getImage() +",\n" +
                "title: " + getTitle() + ",\n" +
                "species: " + getSpecies() + ",\n" +
                "abilities: " + getAbilities() + ",\n" +
                "age: " + getAge() + ",\n" +
                "occupation: " + getOccupation() + ",\n" +
                "location: " + getLocation() + ",\n" +
                "musicThemes: " + getMusicThemes() + ",\n" +
                "officialGames: " + getOfficialGames() + ",\n" +
                "link: " + getLink() + "\n";
    }

    //region Getters and Setters for all String Values

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
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

    public String getAge() {
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
        System.out.println("SetterName= " + name );
        return this;
    }

    public CharacterObjects setImage(String image) {
        this.image = image;
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

    public CharacterObjects setAge(String age) {
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
    public String getLink() {
        return link;
    }

    public CharacterObjects setLink(String link) {
        this.link = link;
        return this;
    }

    //endregion

    public static void main(String[] args) {
        //TESTER

        CharacterObjects test = new CharacterObjects();


        test.setName("")
                .setImage("")
                .setTitle("")
                .setSpecies("")
                .setAbilities("")
                .setAge("")
                .setOccupation("")
                .setLocation("")
                .setMusicThemes("")
                .setOfficialGames("");

        System.out.println(test);

    }


}
