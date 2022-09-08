package tech.alvarez.contacts.data.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import tech.alvarez.contacts.data.db.converter.DateConverter;

@Entity(tableName = "people")
@TypeConverters(DateConverter.class)
public class Person {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String address;
    public Date birthday;
    public String phone;
    public String email;

    @Ignore
    public Person() {
        this.name = "";
        this.address = "";
        this.birthday = null;
        this.phone = "";
        this.email = "";
    }

    public Person(String name, String address, Date birthday, String phone, String email) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }
}
