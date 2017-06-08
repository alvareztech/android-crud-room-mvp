package tech.alvarez.contacts.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tech.alvarez.contacts.data.db.AppDatabase;
import tech.alvarez.contacts.data.db.dao.PersonDao;
import tech.alvarez.contacts.data.db.entity.Person;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private PersonDao mPersonDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mPersonDao = mDb.personModel();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {

        mDb.personModel().deleteAll();

        List<Person> persons = mPersonDao.getAllChannels();
        assertThat(persons.size(), equalTo(0));

        Person person = addPerson(mDb, 1, "Name 1", "Address 1", "111111", Calendar.getInstance().getTime());
        addPerson(mDb, 2, "Name 2", "Address 2", "22222", Calendar.getInstance().getTime());
        addPerson(mDb, 3, "Name 3", "Address 3", "33333", Calendar.getInstance().getTime());

        persons = mPersonDao.getAllChannels();
        assertThat(persons.size(), equalTo(3));
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    private static Person addPerson(final AppDatabase db, final long id, final String name,
                                    final String address, final String phone, final Date birthday) {
        Person person = new Person();
        person.id = id;
        person.name = name;
        person.address = address;
        person.phone = phone;
        person.birthday = birthday;
        db.personModel().insertPerson(person);
        return person;
    }

}
