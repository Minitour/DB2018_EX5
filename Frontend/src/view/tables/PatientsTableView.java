package view.tables;

import model.Person;
import network.api.PatientsAPI;
import network.generic.GenericAPI;
import utils.Response;
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
        api.readAll((response, items) -> {
            personList.addAll(items);
            reloadData();
        });
    }

    @Override
    public int numberOfColumns() {
        return 11;
    }

    @Override
    public Collection<? extends Person> dataSource() {
        return personList;
    }

    @Override
    public String bundleIdForIndex(int index) {
        switch (index){
            case 0: return "ID";
            case 1: return "firstName";
            case 2: return "surName";
            case 3: return "dateOfBirth";
            case 4: return "city";
            case 5: return "street";
            case 6: return "gender";
            case 7: return "phone";
            case 8: return "bloodType";
            case 9: return "careFacility";
            case 10: return "contactID";
        }
        return null;
    }

    @Override
    public TableColumnValue<Person> cellValueForColumnAt(int index) {
        switch (index){
            case 0: return Person::getID;
            case 1: return Person::getFirstName;
            case 2: return Person::getSurName;
            case 3: return Person::getDateOfBirth;
            case 4: return Person::getCity;
            case 5: return Person::getStreet;
            case 6: return Person::getGender;
            case 7: return Person::getPhone;
            case 8: return Person::getBloodType;
            case 9: return Person::getCareFacility;
            case 10: return Person::getContactID;
        }
        return null;
    }

    @Override
    public void callback(Person value) {
        //on update or insert
        api.upsert(value, System.out::println);
    }
}
