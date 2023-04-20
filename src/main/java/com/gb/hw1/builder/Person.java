package com.gb.hw1.builder;

public class Person {
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String country;
    private final String address;
    private final String phone;
    private final int age;
    private final String gender;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Person(PersonBuilder personBuilder) {
        this.firstName = personBuilder.firstName;
        this.lastName = personBuilder.lastName;
        this.middleName = personBuilder.middleName;
        this.country = personBuilder.country;
        this.address = personBuilder.address;
        this.phone = personBuilder.phone;
        this.age = personBuilder.age;
        this.gender = personBuilder.gender;
    }


    public static class PersonBuilder {
        private final String firstName;
        private final String lastName;
        private final String country;
        private final int age;
        private final String gender;
        private String middleName;
        private String address;
        private String phone;

        public PersonBuilder(
                String firstName,
                String lastName,
                String country,
                int age,
                String gender
        ) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.country = country;
            this.age = age;
            this.gender = gender;
        }

        public PersonBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public PersonBuilder address(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    public static void main(String[] args) {
        Person person = new Person.PersonBuilder(
            "Bob",
            "Smith",
            "Canada",
            32,
            "male"
        )
            .middleName("Jr.")
            .address("unspecified")
            .build();
        System.out.println(person.getFirstName());
        System.out.println(person.getAddress());
    }
}


