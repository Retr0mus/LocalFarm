    package com.github.countrybros.model.product;

    import jakarta.persistence.*;

    /**
     * Certification details.
     */
    @Entity
    public class Certification {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String name;
        private String description;

        public Certification() {}

        public Certification(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getId() {
            return id;
        }
    }
