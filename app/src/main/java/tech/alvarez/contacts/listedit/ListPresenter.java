package tech.alvarez.contacts.listedit;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import tech.alvarez.contacts.data.db.dao.PersonDao;
import tech.alvarez.contacts.data.db.entity.Person;

public class ListPresenter implements ListContract.Presenter {

    private final ListContract.View mView;
    private final PersonDao personDao;

    public ListPresenter(ListContract.View view, PersonDao personDao) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.personDao = personDao;
    }

    @Override
    public void start() {

    }

    @Override
    public void addNewPerson() {
        mView.showAddPerson();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void populatePeople() {
        personDao.findAllPersons().observeForever(new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> persons) {
                mView.setPersons(persons);
                if (persons == null || persons.size() < 1) {
                    mView.showEmptyMessage();
                }
            }
        });
    }

    @Override
    public void openEditScreen(Person person) {
        mView.showEditScreen(person.id);
    }

    @Override
    public void openConfirmDeleteDialog(Person person) {
        mView.showDeleteConfirmDialog(person);
    }

    @Override
    public void delete(long personId) {
        Person person = personDao.findPerson(personId);
        personDao.deletePerson(person);
    }
}
