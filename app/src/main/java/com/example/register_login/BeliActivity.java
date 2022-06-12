package com.example.register_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.register_login.databinding.ActivityAnimBinding;
import com.example.register_login.databinding.ActivityBeliBinding;

import java.util.ArrayList;

public class BeliActivity extends AppCompatActivity {

    ActivityBeliBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBeliBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.demacolin,R.drawable.anadex,R.drawable.dextamine,R.drawable.fixiphar,R.drawable.laprosin,R.drawable.depakote};
        String[] item = {"Demacolin Sirup 60 ml","Anadex Sirup 60 ml","Dextamine Sirup 60 ml","Fixiphar Dry Sirup 60 ml","Laprosin Sirup 60 ml","Depakote ER 500 mg"};
        String[] harga = {"Rp19.200 - Rp26.300 Per Botol","Rp22.800 - Rp56.000 Per Botol","Rp35.000 - Rp39.100 Per Botol","Rp138.300 - Rp181.200 Per Botol","Rp91.400 - Rp117.300 Per Botol","Rp450.000 - Rp480.000 Per Botol"};
        String[] tipe = {"Batuk","Batuk","Alergi","Antibiotik","Antivirus","Mental"};
        String[] deskripsi = {"Demacolin Sirup 60 ml merupakan obat flu yang mengandung Paracetamol, Pseudoephedrine HCl, dan Chlorpheniramine maleat. Paracetamol digunakan sebagai pereda demam dan nyeri. Chlorpheniramine maleate bekerja sebagai antihistamin/anti alergi untuk meredakan gejala alergi seperti bersin-bersin.",
                "Anadex Sirup 60 ml mengandung zat aktif Paracetamol, Phenylpropanolamine HCl, Chlorpheniramine Maleat, dan Dextromethorphan HBr. Obat ini digunakan untuk mengatasi gejala flu seperti demam, sakit kepala, bersin-bersin dan hidung tersumbat yang disertai batuk.",
                "Dextamine Sirup 60 ml merupakan obat dengan kandungan Dexamethasone (glukokortikoid) dan Dexchlorpheniramine Maleate. Obat ini bekerja sebagai antiinflamasi, antirematik, serta antialergi/antihistamin. ",
                "Fixiphar Dry Sirup 60 ml digunakan untuk mengobati infeksi saluran kemih tanpa komplikasi, otitis media, faringiris dan tonsilitis, serta bronkitis akut dan kronis dengan eksaserbasi akut.",
                "Laprosin Sirup 60 ml merupakan obat yang digunakan untuk mengobati beberapa infeksi yang disebabkan oleh virus. Laprosin mengandung methisoprinol, Cara kerja obat ini dengan cara meningkatkan respons kekebalan alami limfosit dan dapat menghambat perkembangan virus dengan meningkatkan ribosom dan polyribosomes.",
                "Terapi episode manik akut atau campuran yang berhubungan dengan gangguan bipolar dengan atau tanpa disertai psikosis. Terapi tunggal dan tambahan untuk kejang parsial kompleks yang terjadi pada kasus khusus atau yang berhubungan dgengan kejang tipe lain dan kejang tipe multipel. Profilaksis migren pada orang dewasa"};

        ArrayList<Item> itemArrayList = new ArrayList<>();

        for (int i = 0;i< imageId.length;i++){

            Item itemm = new Item(item[i],harga[i],tipe[i],deskripsi[i],imageId[i]);
            itemArrayList.add(itemm);

        }

        ListAdapter listAdapter = new ListAdapter(BeliActivity.this, itemArrayList);

        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(BeliActivity.this,ItemActivity.class);
                i.putExtra("item",item[position]);
                i.putExtra("harga",harga[position]);
                i.putExtra("deskripsi",deskripsi[position]);
                i.putExtra("imageid",imageId[position]);
                startActivity(i);

            }
        });

    }
}