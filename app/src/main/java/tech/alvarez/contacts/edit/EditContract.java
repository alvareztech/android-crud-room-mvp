package tech.alvarez.contacts.edit;

import java.util.Date;

import tech.alvarez.contacts.BasePresenter;
import tech.alvarez.contacts.BaseView;
import tech.alvarez.contacts.data.db.entity.Person;

public interface EditContract {

    interface Presenter extends BasePresenter {
        void save(Person person);

        boolean validate(Person person);

        void showDateDialog();

        void getPersonAndPopulate(long id);

        void update(Person person);
    }

    interface View extends BaseView<EditContract.Presenter> {

        void showErrorMessage(int field);

        void clearPreErrors();

        void openDateDialog();

        void close();

        void populate(Person person);
    }

    interface DateListener {

        void setSelectedDate(Date date);

    }
}
