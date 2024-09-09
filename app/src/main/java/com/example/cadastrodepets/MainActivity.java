package com.example.cadastrodepets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewAnimais;
    private Button buttonAdicionarAnimal;
    private AnimalDAO animalDAO;
    private AnimalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Pets Cadastrados");

        listViewAnimais = findViewById(R.id.listViewAnimais);
        buttonAdicionarAnimal = findViewById(R.id.buttonAdicionarAnimal);

        animalDAO = new AnimalDAO(this);
        animalDAO.abrir();

        buttonAdicionarAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnimalFormActivity.class);
                startActivity(intent);
            }
        });

        listViewAnimais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Animal animal = (Animal) adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, AnimalFormActivity.class);
                intent.putExtra("animalId", animal.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Animal> animais = animalDAO.obterTodosAnimais();
        adapter = new AnimalAdapter(this, animais);
        listViewAnimais.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animalDAO.fechar();
    }
}
