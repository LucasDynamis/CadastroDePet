package com.example.cadastrodepets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AnimalFormActivity extends AppCompatActivity {

    private EditText editTextNome, editTextIdade, editTextPeso, editTextRaca, editTextCor, editTextNomeDono, editTextTelefoneDono;
    private Button buttonSalvarAnimal;
    private AnimalDAO animalDAO;
    private Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_form);

        setTitle("Cadastre Seu Pet");

        editTextNome = findViewById(R.id.editTextNome);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextPeso = findViewById(R.id.editTextPeso);
        editTextRaca = findViewById(R.id.editTextRaca);
        editTextCor = findViewById(R.id.editTextCor);
        editTextNomeDono = findViewById(R.id.editTextNomeDono);
        editTextTelefoneDono = findViewById(R.id.editTextTelefoneDono);
        buttonSalvarAnimal = findViewById(R.id.buttonSalvarAnimal);

        animalDAO = new AnimalDAO(this);
        animalDAO.abrir();

        Intent intent = getIntent();
        int animalId = intent.getIntExtra("animalId", -1);

        if (animalId != -1) {
            animal = animalDAO.obterAnimalPorId(animalId);
            if (animal != null) {
                editTextNome.setText(animal.getNome());
                editTextIdade.setText(String.valueOf(animal.getIdade()));
                editTextPeso.setText(String.valueOf(animal.getPeso()));
                editTextRaca.setText(animal.getRaca());
                editTextCor.setText(animal.getCor());
                editTextNomeDono.setText(animal.getNomeDono());
                editTextTelefoneDono.setText(animal.getTelefoneDono());
            }
        }

        buttonSalvarAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarAnimal();
            }
        });
    }

    private void salvarAnimal() {
        String nome = editTextNome.getText().toString();
        int idade = Integer.parseInt(editTextIdade.getText().toString());
        double peso = Double.parseDouble(editTextPeso.getText().toString());
        String raca = editTextRaca.getText().toString();
        String cor = editTextCor.getText().toString();
        String nomeDono = editTextNomeDono.getText().toString();
        String telefoneDono = editTextTelefoneDono.getText().toString();

        if (animal == null) {
            animal = new Animal(0, nome, idade, peso, raca, cor, nomeDono, telefoneDono);
            animalDAO.adicionarAnimal(animal);
        } else {
            animal.setNome(nome);
            animal.setIdade(idade);
            animal.setPeso(peso);
            animal.setRaca(raca);
            animal.setCor(cor);
            animal.setNomeDono(nomeDono);
            animal.setTelefoneDono(telefoneDono);
            animalDAO.atualizarAnimal(animal);
        }

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animalDAO.fechar();
    }
}
