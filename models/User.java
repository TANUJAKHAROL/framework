package models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private Boolean activated;
    private String langKey;
    private String createdBy;
    private String createdDate;
    private String lastModifiedBy;
    private String lastModifiedDate;

    // Private constructor to enforce the use of the builder
    public User(UserBuilder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.imageUrl = builder.imageUrl;
        this.activated = builder.activated;
        this.langKey = builder.langKey;
        this.createdBy = builder.createdBy;
        this.createdDate = builder.createdDate;
        this.lastModifiedBy = builder.lastModifiedBy;
        this.lastModifiedDate = builder.lastModifiedDate;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    // Builder class
    public static class UserBuilder {
        private Integer id;
        private String login;
        private String firstName;
        private String lastName;
        private String email;
        private String imageUrl;
        private Boolean activated;
        private String langKey;
        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;

        public UserBuilder() {}

        public UserBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public UserBuilder activated(Boolean activated) {
            this.activated = activated;
            return this;
        }

        public UserBuilder langKey(String langKey) {
            this.langKey = langKey;
            return this;
        }

        public UserBuilder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public UserBuilder createdDate(String createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public UserBuilder lastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
            return this;
        }

        public UserBuilder lastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}