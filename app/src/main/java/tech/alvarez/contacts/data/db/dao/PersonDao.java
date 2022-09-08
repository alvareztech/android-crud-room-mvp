package tech.alvarez.contacts.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tech.alvarez.contacts.data.db.entity.Person;

import static androidx.room.OnConflictStrategy.IGNORE;

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
