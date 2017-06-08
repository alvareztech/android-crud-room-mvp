package tech.alvarez.contacts.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import tech.alvarez.contacts.data.db.entity.Person;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface PersonDao {


    @Query("SELECT * FROM people ORDER BY name ASC")
    LiveData<List<Person>> findAllPersons();

    @Query("SELECT * FROM people")
    List<Person> getAllChannels();

    @Query("SELECT * FROM people WHERE id=:id")
    Person findPersonById(String id);

    @Query("SELECT * FROM people WHERE id=:id")
    Person findPerson(long id);

    @Insert(onConflict = IGNORE)
    long insertPerson(Person person);

    @Update
    int updatePerson(Person person);

    @Update
    void updatePerson(List<Person> people);

    @Delete
    void deletePerson(Person person);

    @Query("DELETE FROM people")
    void deleteAll();
}
