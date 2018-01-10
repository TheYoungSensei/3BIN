public class Album {
    private final String titre;
    private final String artiste;
    private final String label;
    private final String producteur;
    private final String pays;
    private final String version;
    private final String genre;
    private final int anneeParution;
    private final boolean remasterise;
    private final int anneeParutionOriginale;
    private final int debitStandard;
    private final int debitAbonne;

    private Album(Builder builder) {
        this.titre = builder.titre;
        this.artiste = builder.artiste;
        this.label = builder.label;
        this.producteur = builder.producteur;
        this.pays = builder.pays;
        this.version = builder.version;
        this.genre = builder.genre;
        this.anneeParution = builder.anneeParution;
        this.remasterise = builder.remasterise;
        this.anneeParutionOriginale = builder.anneeParutionOriginale;
        this.debitStandard = builder.debitStandard;
        this.debitAbonne = builder.debitAbonne;
    }

    public static class Builder {
        private String titre;
        private String artiste;
        private String label;
        private String producteur;
        private String pays;
        private String version;
        private String genre;
        private int anneeParution;
        private boolean remasterise;
        private int anneeParutionOriginale;
        private int debitStandard;
        private int debitAbonne;

        public Builder(String titre, String artiste) {
            this.titre = titre;
            this.artiste = artiste;
        }

        public Builder setLabel(String label) {
            this.label = label;
            return this;
        }

        public Builder setProducteur(String producteur) {
            this.producteur = producteur;
            return this;
        }

        public Builder setPays(String pays) {
            this.pays = pays;
            return this;
        }

        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder setAnneeParution(int anneeParution) {
            this.anneeParution = anneeParution;
            return this;
        }

        public Builder setRemasterise(boolean remasterise) {
            this.remasterise = remasterise;
            return this;
        }

        public Builder setAnneeParutionOriginale(int anneeParutionOriginale) {
            this.anneeParutionOriginale = anneeParutionOriginale;
            return this;
        }

        public Builder setDebitStandard(int debitStandard) {
            this.debitStandard = debitStandard;
            return this;
        }

        public Builder setDebitAbonne(int debitAbonne) {
            this.debitAbonne = debitAbonne;
            return this;
        }

        public Album build() {
            return new Album(this);
        }
    }



}
