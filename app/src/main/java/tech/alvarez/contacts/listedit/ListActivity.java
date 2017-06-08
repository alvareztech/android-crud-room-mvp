package tech.alvarez.contacts.listedit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import tech.alvarez.contacts.R;
import tech.alvarez.contacts.data.db.AppDatabase;
import tech.alvarez.contacts.data.db.entity.Person;
import tech.alvarez.contacts.edit.EditActivity;
import tech.alvarez.contacts.utils.Constants;

public class ListActivity extends AppCompatActivity implements ListContract.View, ListContract.OnItemClickListener, ListContract.DeleteListener {

    private ListContract.Presenter mPresenter;
    private PeopleAdapter mPeopleAdapter;

    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewPerson();
            }
        });

        mEmptyTextView = (TextView) findViewById(R.id.emptyTextView);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPeopleAdapter = new PeopleAdapter(this);
        recyclerView.setAdapter(mPeopleAdapter);

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        mPresenter = new ListPresenter(this, db.personModel());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.populatePeople();
    }

    @Override
    public void showAddPerson() {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPersons(List<Person> persons) {
        mEmptyTextView.setVisibility(View.GONE);
        mPeopleAdapter.setValues(persons);
    }

    @Override
    public void showEditScreen(long id) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Constants.PERSON_ID, id);
        startActivity(intent);
    }

    @Override
    public void showDeleteConfirmDialog(Person person) {
        DeleteConfirmFragment fragment = new DeleteConfirmFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.PERSON_ID, person.id);
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "confirmDialog");
    }

    @Override
    public void showEmptyMessage() {
        mEmptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void clickItem(Person person) {
        mPresenter.openEditScreen(person);
    }

    @Override
    public void clickLongItem(Person person) {
        mPresenter.openConfirmDeleteDialog(person);
    }

    @Override
    public void setConfirm(boolean confirm, long personId) {
        if (confirm) {
            mPresenter.delete(personId);
        }
    }
}
