package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.example.qualityfurnishings.R;
import com.example.qualityfurnishings.model.ProductModal;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EditProducts extends AppCompatActivity {
    Spinner ProductCategory,SubCategory,SpProductQuality;
    ArrayList list = new ArrayList();
    EditText ProductName,ProductPrice,ProductDescription,ProductQuantity,Discount;
    public String imageValue;
    String   CategoryId,  SubCategoryId , QualityId;
    Button updateProduct;
    ImageView imageView;
    TextView selectImg;
    CheckBox sale;
    int discount;
    boolean Liquidation;
    private int Rqststorage=100;
    private int Rqstfile=2;
    private Uri uri;
    String stQuality;
    String Category[] ={"BedRoom","Kitchen","LivingRoom","Bathroom","Office"};
    String Quality[] ={"Superior","Well Founded","Cheap","UnWarranted"};
    //private String uriPath;
//    private Intent intentData;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_products);
        imageView = (ImageView) findViewById(R.id.imgview);
        selectImg = (TextView) findViewById(R.id.picUpload);
        ProductName = (EditText) findViewById(R.id.ProductName);
        ProductPrice = (EditText) findViewById(R.id.ProductPrice);
        ProductDescription = (EditText) findViewById(R.id.ProductDescription);
        ProductQuantity = (EditText) findViewById(R.id.ProducQuantity);
        updateProduct = (Button) findViewById(R.id.btAddProduct);
        updateProduct.setBackgroundColor(Color.parseColor("#1F2633"));
        ProductCategory = (Spinner) findViewById(R.id.ProductCategory);
        SubCategory = (Spinner) findViewById(R.id.ProductsubCategory);
        SpProductQuality = (Spinner) findViewById(R.id.ProductQuality);
        sale = (CheckBox) findViewById(R.id.sale);
        Discount = (EditText) findViewById(R.id.etDiscount);

        //get parcable value from adapter
        Intent intent = getIntent();
        ProductModal productModal = intent.getParcelableExtra("productdata");
        ProductName.setText(productModal.getName());
        int price = productModal.getPrice();
        String stPrice = Integer.toString(price);
        ProductPrice.setText(stPrice);
        ProductDescription.setText(productModal.getDescription());
        int quantity = productModal.getQuantity();
        String stQuantity = Integer.toString(quantity);
        ProductQuantity.setText(stQuantity);
        stQuality= productModal.getQuality();
        imageValue = productModal.getImage();
//        SpProductQuality.setSelection(((ArrayAdapter<String>)SpProductQuality.getAdapter()).getPosition(productModal.getQuality()));
//        DrawableCrossFadeFactory factory =
//                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();


      //  SpProductQuality.setSelection(getIndex(Quality, stQuality));
//        SpProductQuality.setSelection(2);

//        ArrayAdapter pq = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Quality);
//        pq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        SpProductQuality.setAdapter(SpProductQuality.getAdapter());
//        SpProductQuality.setSelection(2,false);
        Glide.with(this)
                .load(productModal.getImage())
                .into(imageView);
        boolean ss = productModal.isSale();
        if (ss == true){
            sale.setChecked(true);
            discount = productModal.getDiscount();
            String stDiscount = Integer.toString(discount);
            Discount.setText(stDiscount);
        }
        else{
            sale.setChecked(false);
            Discount.setVisibility(View.GONE);
        }
        sale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sale.isChecked()){
                    Discount.setVisibility(View.VISIBLE);
                    Discount.setHint("Discount");

                }
                else{
                    Discount.setVisibility(View.GONE);
                }
            }
        });



        ProductCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {


                CategoryId = Category[i].toString();
                if (CategoryId.equals("BedRoom")) {

                    list.clear();
                    list.add("Bed");
                    list.add("Makeup Vanities");
                    list.add("Vanity Stool");
                    list.add("Dressers");


                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SubCategory.setAdapter(aa);
                } else if (CategoryId.equals("Kitchen")) {

                    list.clear();
                    list.add("Kitchen Islands");
                    list.add("Bakers Racks");
                    list.add("Food Pantries");
                    list.add("Wine Racks");



                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SubCategory.setAdapter(aa);

                } else if (CategoryId.equals("LivingRoom")) {

                    list.clear();
                    list.add("Sofas");
                    list.add("Coffee Tables");
                    list.add("Book Cases");
                    list.add("Cabinets & Chests");
                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SubCategory.setAdapter(aa);
                }

                else if (CategoryId.equals("Bathroom")) {

                    list.clear();
                    list.add("Bathroom Vanities");
                    list.add("Bathroom Cabinets");
                    list.add("Medicine Cabinets");
                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SubCategory.setAdapter(aa);
                }
                else if (CategoryId.equals("Office")) {

                    list.clear();
                    list.add("Office Chairs");
                    list.add("Printer Stands");
                    list.add("Office Stools");
                    list.add("Laptop Carts & Stands");

                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SubCategory.setAdapter(aa);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Creating the ArrayAdapter instance having the category list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Category);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        ProductCategory.setAdapter(aa);


        SubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                SubCategoryId=list.get(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        SpProductQuality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                QualityId = Quality[position].toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter productQuality = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Quality);
        productQuality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        SpProductQuality.setAdapter(productQuality);

        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(EditProducts.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},Rqststorage);
                }
                else {
                    selectImage();
                }

            }
        });


        updateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (TextUtils.isEmpty(imageValue)) {
                    Toast.makeText(getApplicationContext(), "Please Upload the image of the product", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(ProductName.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Enter Product Name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(ProductPrice.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Enter Product Price", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(ProductDescription.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Enter Product Description", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(ProductQuantity.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Enter Product Quantity", Toast.LENGTH_SHORT).show();
                }

                else {
                    if  (sale.isChecked()){
                        Liquidation = true;
                        if(TextUtils.isEmpty(Discount.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Please Enter Discount", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            String stDiscount = Discount.getText().toString();
                            discount = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(stDiscount))));
                        }
                    }
                    else{
                        Liquidation = false;
                        discount=0;
                    }
                    String stPrice = ProductPrice.getText().toString();
                    final int price = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(stPrice))));
                    String stQuantity = ProductQuantity.getText().toString();
                    final int quantity = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(stQuantity))));


                    Map<String, Object> updateProduct = new HashMap<>();
                    updateProduct.put("name", ProductName.getText().toString());
                    updateProduct.put("image", imageValue);
                    updateProduct.put("price", price);
                    updateProduct.put("quality", QualityId);
                    updateProduct.put("quantity",quantity );
                    updateProduct.put("description",ProductDescription.getText().toString());
                    updateProduct.put("sale",Liquidation);
                    updateProduct.put("discount",discount);


                    // CREATE Product
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("FurnitureCategory").child(CategoryId).child(SubCategoryId ).child(productModal.getId()).setValue(updateProduct);
                    cleartext();
                    Intent intent = new Intent(getApplicationContext(),UpdateProductCat.class);
                    startActivity(intent);
                }
            }
        });
    }

//    private int getIndex(String[] spProductQuality, String das) {
//        for (int i = 0; i < spProductQuality.length; i++)
//            if(spProductQuality[i].equals(das)) return i;
//        return -1;
//    }


    private void cleartext() {
        if (sale.isChecked()){
            sale.toggle();
        }

        ProductName.setText(null);
        ProductPrice.setText(null);
        ProductDescription.setText(null);
        ProductQuantity.setText(null);
        Discount.setText(null);
        Discount.setHint("Discount");
        imageView.setImageResource(R.drawable.image);
        imageValue = "";
        Toast.makeText(getApplicationContext(),"Product Updated",Toast.LENGTH_LONG).show();


    }

    private void selectImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,Rqstfile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Rqstfile && resultCode == RESULT_OK){
            if(data != null ){
                uri = data.getData();


                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                // SEND IMAGE TO FIREBASE STORAGE

                final StorageReference riversRef = storageRef.child("images/"+ UUID.randomUUID().toString());

                riversRef.putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        imageValue=uri.toString();



                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {

                            }
                        });




            }

        }






    }
}