package com.svalero.hureanensamble.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.contract.AddEventContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.presenter.AddEventPresenter;

import java.util.Calendar;
import java.util.List;

public class AddEventView extends AppCompatActivity implements AddEventContract.View {

    // Referencias a las vistas
    private EditText eventDateEditText;
    private EditText eventPlaceEditText;
    private Spinner userSpinner;
    private Spinner playlistSpinner;
    private Button addButton;
    private AddEventPresenter presenter;
    private List<User> userList;
    private List<Playlist> playlistsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_view);

        // Vinculación de vistas
        eventDateEditText = findViewById(R.id.edit_event_date);
        eventPlaceEditText = findViewById(R.id.edit_event_place);
       //paidCheckbox = findViewById(R.id.checkbox_paid);
        userSpinner = findViewById(R.id.spinner_users);
        playlistSpinner = findViewById(R.id.spinner_playlists);
        addButton = findViewById(R.id.btnModifyEvent);

        // Inicializar presenter y cargar usuarios
        presenter = new AddEventPresenter(this);
        presenter.loadUsers(); //carga lista de usuarios desde el modelo

        // Selector de fecha con DatePicker
        eventDateEditText.setOnClickListener(v -> showDatePicker());

        // Al seleccionar usuario, cargar playlists relacionadas
        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                User selectedUser = userList.get(position);
                presenter.loadPlaylistsByUser(selectedUser.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // No hacer nada
            }
        });
    }

    public void addButton(View view) {
        // Validación de campos obligatorios
        String place = eventPlaceEditText.getText().toString().trim();
        String date = eventDateEditText.getText().toString().trim();
//        boolean paid = paidCheckbox.isChecked();
        User selectedUser = (User) userSpinner.getSelectedItem();

//        if (place.isEmpty() || date.isEmpty()) {
//            showError("Todos los campos son obligatorios.");
//            return;
//        }
//
//        if (userSpinner.getSelectedItem() == null || playlistSpinner.getSelectedItem() == null) {
//            showError("Selecciona usuario y playlist.");
//            return;
//        }

        // Crear evento y enviarlo al presenter
        Event event = new Event();
        event.setPlace(place);
        event.setEventDate(date);
        event.setEventUser((User) userSpinner.getSelectedItem());
        event.setEventPlaylist((Playlist) playlistSpinner.getSelectedItem());

        presenter.addEvent(event);
    }

    public void cancelButton(View view) {
        getOnBackPressedDispatcher().onBackPressed(); // Cierra la vista
    }


    // Muestra el selector de fecha
    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this,
                (view, y, m, d) -> eventDateEditText.setText(String.format("%04d-%02d-%02d", y, m + 1, d)),
                year, month, day);
        dialog.show();
    }

    // Mostrar usuarios en el spinner
    @Override
    public void showUsers(List<User> users) {
        this.userList = users;

        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
                android.R.layout.simple_spinner_item,
                users) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView label = (TextView) super.getView(position, convertView, parent);
                label.setText(users.get(position).getName());
                return label;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView label = (TextView) super.getDropDownView(position, convertView, parent);
                label.setText(users.get(position).getName());
                return label;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(adapter);
    }

    // Mostrar playlists del usuario seleccionado
    @Override
    public void showPlaylists(List<Playlist> playlists) {
        this.playlistsList = playlists;

        ArrayAdapter<Playlist> adapter = new ArrayAdapter<Playlist>(this,
                android.R.layout.simple_spinner_item,
                playlists) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView label = (TextView) super.getView(position, convertView, parent);
                label.setText(playlists.get(position).getName());
                return label;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView label = (TextView) super.getDropDownView(position, convertView, parent);
                label.setText(playlists.get(position).getName());
                return label;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playlistSpinner.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void showError(String error) {


    }

    //crear el menu actionbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_comun, menu);
        return true;
    }

    //logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            //cierro session
            UserSession session = new UserSession(this);
            session.clear();

            //Regirigo a la pantalla de Login
            Intent intent = new Intent(this, LoginView.class);
            // Establece flags para limpiar el historial de actividades y empezar una nueva tarea
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.home){
            Intent intent = new Intent(this, HomepageView.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.userProfile) {
            Intent intent = new Intent(this, UserProfileView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

//    // Mostrar mensaje de éxito
//    @Override
//    public void showMessage(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//        finish(); // cerrar la vista tras éxito
//    }
//
//    // Mostrar errores
//    @Override
//    public void showError(String error) {
//        Toast.makeText(this, "Error: " + error, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void showUsers(List<User> users) {
//
//    }
//
//    @Override
//    public void showPlaylists(List<Playlist> playlists) {
//
//    }
//
//    @Override
//    public void showMessage(String message) {
//
//    }
//
//    @Override
//    public void showError(String error) {
//
//    }
}
