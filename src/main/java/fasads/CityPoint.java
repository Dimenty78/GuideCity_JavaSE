package fasads;

public class CityPoint {

        private String category;
        private String name;
        private int rating;
        private String description;
        private String responses;
        private String worktim;
        private String telefon;
        private String adres;


        public CityPoint(int id, String category, String favorites, String name, int rating, String description, String responses, String worktim, String telefon, String adres){
            this.category = category;
            this.name = name;
            this.rating = rating;
            this.description = description;
            this.responses = responses;
            this.worktim = worktim;
            this.telefon = telefon;
            this.adres = adres;
        }


        public String getCategory() {
            return category;
        }

        public String getName() {
            return name;
        }

        public int getRating() {
            return rating;
        }

        public String getDescription() {
            return description;
        }

        public String getResponses() {
            return responses;
        }

        public String getWorktim() {
            return worktim;
        }

        public String getTelefon() {
            return telefon;
        }

        public String getAdres() {
            return adres;
        }
}
