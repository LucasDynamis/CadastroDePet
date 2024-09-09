package com.example.cadastrodepets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AnimalAdapter extends BaseAdapter {
    private Context context;
    private List<Animal> animais;
    private LayoutInflater inflater;
    private AnimalDAO animalDAO;

    public AnimalAdapter(Context context, List<Animal> animais) {
        this.context = context;
        this.animais = animais;
        this.inflater = LayoutInflater.from(context);
        this.animalDAO = new AnimalDAO(context);
        this.animalDAO.abrir();
    }

    @Override
    public int getCount() {
        return animais.size();
    }

    @Override
    public Object getItem(int position) {
        return animais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return animais.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_animal, parent, false);
        }

        TextView textViewNome = convertView.findViewById(R.id.textViewNome);
        TextView textViewDetalhes = convertView.findViewById(R.id.textViewDetalhes);
        Button buttonExcluir = convertView.findViewById(R.id.buttonExcluir);

        final Animal animal = animais.get(position);

        textViewNome.setText(animal.getNome());
        String detalhes = "Idade: " + animal.getIdade() + " anos, Peso: " + animal.getPeso() + " kg, Raça: " + animal.getRaca() + ", Cor: " + animal.getCor();
        textViewDetalhes.setText(detalhes);

        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animalDAO.deletarAnimal(animal.getId());
                animais.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Animal excluído", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
