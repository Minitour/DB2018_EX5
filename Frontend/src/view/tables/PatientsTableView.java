package view.tables;

import model.Person;
import network.api.PatientsAPI;
import view.forms.PersonForm;
import view.generic.GenericTableView;
import view.generic.UIFormView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By Tony on 26/07/2018
 */
public class PatientsTableView extends GenericTableView<Person> {

    private PatientsAPI api;

    public PatientsTableView(boolean delete, boolean update, boolean insert) {
        super(delete, update, insert);
    }

    private List<Person> personList = new ArrayList<>();

    @Override
    protected UIFormView<Person> onInsert() {
        return new PersonForm(null,this);
    }

    @Override
    protected Class<Person> classType() {
        return Person.class;
    }

    @Override
    protected UIFormView<Person> onView(int index) {
        Person p = personList.get(index);
        return new PersonForm(p,this);
    }

    @Override
    protected void onDelete(int index) {
        Person p = personList.remove(index);
        api.delete(p,System.out::println);
        reloadData();
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        api = new PatientsAPI();
        api.setRunOnUi(true);
        reloadDataFromServer();
    }

    private void reloadDataFromServer(){
        api.readAll((response, items) -> {
            personList.clear();
            personList.addAll(items);
            reloadData();
        });
    }

    @Override
    public Collection<? extends Person> dataSource() {
        return personList;
    }



    @Override
    public void callback(Person value) {
        super.callback(value);
        //on update or insert
        api.upsert(value, System.out::println);
        reloadDataFromServer();
    }
}
