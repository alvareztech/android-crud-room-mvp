package tech.alvarez.contacts.edit;

import tech.alvarez.contacts.data.db.dao.PersonDao;
import tech.alvarez.contacts.data.db.entity.Person;
import tech.alvarez.contacts.utils.Constants;
import tech.alvarez.contacts.utils.Util;

public class EditPresenter implements EditContract.Presenter {

    private final EditContract.View mView;
    private final PersonDao personDao;

    public EditPresenter(EditContract.View mMainView, PersonDao personDao) {
        this.mView = mMainView;
        this.mView.setPresenter(this);
        this.personDao = personDao;
    }

    @Override
    public void start() {

    }

    @Override
    public void save(Person person) {
        long ids = this.personDao.insertPerson(person);
        mView.close();
    }

    @Override
    public boolean validate(Person person) {
        mView.clearPreErrors();
        if (person.name.isEmpty() || !Util.isValidName(person.name)) {
            mView.showErrorMessage(Constants.FIELD_NAME);
            return false;
        }
        if (person.address.isEmpty()) {
            mView.showErrorMessage(Constants.FIELD_ADDRESS);
            return false;
        }
        if (person.phone.isEmpty() || !Util.isValidPhone(person.phone)) {
            mView.showErrorMessage(Constants.FIELD_PHONE);
            return false;
        }
        if (person.email.isEmpty() || !Util.isValidEmail(person.email)) {
            mView.showErrorMessage(Constants.FIELD_EMAIL);
            return false;
        }
        if (person.birthday == null) {
            mView.showErrorMessage(Constants.FIELD_BIRTHDAY);
            return false;
        }

        return true;
    }

    @Override
    public void showDateDialog() {
        mView.openDateDialog();
    }

    @Override
    public void getPersonAndPopulate(long id) {
        Person person = personDao.findPerson(id);
        if (person != null) {
            mView.populate(person);
        }
    }

    @Override
    public void update(Person person) {
        int ids = this.personDao.updatePerson(person);
        mView.close();
    }
}
