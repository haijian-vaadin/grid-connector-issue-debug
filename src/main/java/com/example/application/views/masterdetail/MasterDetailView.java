package com.example.application.views.masterdetail;

import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonService;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.example.application.views.dashboard.DashboardView;
import com.example.application.views.main.MainView;


import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;

@Route(value = "master-detail", layout = MainView.class)
@PageTitle("Master-Detail")
@CssImport("styles/views/masterdetail/master-detail-view.css")
public class MasterDetailView extends Div {

    private Grid<Person> grid;

    public MasterDetailView(@Autowired PersonService personService) {
        setId("master-detail-view");
        // Configure Grid
        grid = new Grid<>(Person.class);
        grid.setEnabled(false);
        grid.setColumns("firstName", "lastName", "email");
        grid.setDataProvider(new CrudServiceDataProvider<Person, Void>(personService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        MyCustomField myCustomField = new MyCustomField(grid);
        grid.setWidth("500px");
        grid.setHeight("400px");
        myCustomField.setEnabled(false);
        add(myCustomField);
    }
}

class MyCustomField extends CustomField<Person> {
    private Grid<Person> grid;
    public MyCustomField(Grid<Person> grid) {
        this.grid = grid;
        add(grid);
        
    }

    @Override
    protected Person generateModelValue() {
        // TODO Auto-generated method stub
        if(grid!=null && !grid.getSelectedItems().isEmpty()){
            return grid.getSelectedItems().iterator().next();
        }
        return null;
    }

    @Override
    protected void setPresentationValue(Person arg0) {
        // TODO Auto-generated method stub
        grid.select(arg0);
    }
}
