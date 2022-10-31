package DesignPatterns.Exercise.builderExercise;

public class Contact {

    private String name;
    private String number;
    private String company;
    private String title;
    private String email;
    private String website;
    private String birthday;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Contact contact;

        public Builder() {
            this.contact = new Contact();
        }

        public Builder withName(String name) {
            contact.setName(name);
            return this;
        }

        public Builder withNumber(String number) {
            contact.setNumber(number);
            return this;
        }

        public Builder withCompany(String company) {
            contact.company = company;
            return this;
        }

        public Builder withTitle(String title) {
            contact.title = title;
            return this;
        }

        public Builder withEmail(String email) {
            contact.email = email;
            return this;
        }

        public Builder withWebsite(String website) {
            contact.website = website;
            return this;
        }

        public Builder withBirthday(String birthday) {
            contact.birthday = birthday;
            return this;
        }

        public Contact build() {
            return contact;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().equals("") || name.length() < 2) {
            throw new IllegalStateException("The name must contain at least 2 characters!");
        }

        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number == null || number.trim().equals("") || number.length() < 5) {
            throw new IllegalStateException("The number must contain at least 5 characters!");
        }

        this.number = number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Contact{" +
               "name='" + name + '\'' +
               ", number='" + number + '\'' +
               ", company='" + company + '\'' +
               ", title='" + title + '\'' +
               ", email='" + email + '\'' +
               ", website='" + website + '\'' +
               ", birthday=" + birthday +
               '}';
    }
}
