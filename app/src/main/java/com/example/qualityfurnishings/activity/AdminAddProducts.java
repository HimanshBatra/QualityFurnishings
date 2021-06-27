package com.example.qualityfurnishings.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualityfurnishings.R;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdminAddProducts extends AppCompatActivity {

    Spinner ProductCategory,SubCategory,SpProductQuality;
    ArrayList list = new ArrayList();
    EditText ProductName,ProductPrice,ProductDescription,ProductQuantity;
     public String imageValue;
    String   CategoryId,  SubCategoryId , QualityId;
    Button addProduct;
    ImageView imageView;
    TextView selectImg;
    CheckBox sale;
    boolean Liquidation;
    private int Rqststorage=1;
    private int Rqstfile=2;
    private Uri uri;
    String Category[] ={"BedRoom","Kitchen","LivingRoom","Bathroom","Office"};
    String Quality[] ={"Superior","Well Founded","Cheap","UnWarranted"};
    //private String uriPath;
    private Intent intentData;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_products);

        imageView = (ImageView)findViewById(R.id.imgview);
        selectImg = (TextView) findViewById(R.id.picUpload);
        ProductName = (EditText) findViewById(R.id.ProductName);
        ProductPrice = (EditText) findViewById(R.id.ProductPrice);
        ProductDescription = (EditText) findViewById(R.id.ProductDescription);
        ProductQuantity = (EditText) findViewById(R.id.ProducQuantity);
        addProduct = (Button) findViewById(R.id.btAddProduct);
        addProduct.setBackgroundColor(Color.parseColor("#1F2633"));
        ProductCategory = (Spinner) findViewById(R.id.ProductCategory);
        SubCategory = (Spinner) findViewById(R.id.ProductsubCategory);
        SpProductQuality = (Spinner) findViewById(R.id.ProductQuality);
        sale = (CheckBox) findViewById(R.id.sale);



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

                    ActivityCompat.requestPermissions(AdminAddProducts.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},Rqststorage);
                }
                else {
                    selectImage();
                }

            }
        });


        addProduct.setOnClickListener(new View.OnClickListener() {
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
                    if (sale.isChecked()){
                         Liquidation = true;
                    }
                    else{
                         Liquidation = false;
                    }
                    String stPrice = ProductPrice.getText().toString();
                    final int price = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(stPrice))));
                    String stQuantity = ProductQuantity.getText().toString();
                    final int quantity = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(stQuantity))));
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    Map<String, Object> furnitureProduct = new HashMap<>();
                    furnitureProduct.put("name", ProductName.getText().toString());
                    furnitureProduct.put("image", imageValue);
                    furnitureProduct.put("price", price);
                    furnitureProduct.put("quality", QualityId);
                    furnitureProduct.put("quantity",quantity );
                    furnitureProduct.put("description",ProductDescription.getText().toString());
                    furnitureProduct.put("sale",Liquidation);


                    // CREATE Product
                    DatabaseReference db_ref = database.child("FurnitureCategory").child(CategoryId).child(SubCategoryId ).push(); // new key is created
                    db_ref.setValue(furnitureProduct);
                    
                    cleartext();
                }
            }
        });
    }

    private void cleartext() {
        if (sale.isChecked()){
            sale.toggle();
        }

        ProductName.setText(null);
        ProductPrice.setText(null);
        ProductDescription.setText(null);
        ProductQuantity.setText(null);
        imageView.setImageResource(R.drawable.image);
        imageValue = "";



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
                intentData = data;

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
                                //Get a URL to the uploaded content
                                //Uri downloadUrl = taskSnapshot.getDownloadURL();
                                riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        final Uri downloadUrl = uri;
                                        imageValue=uri.toString();



                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                // ...
                            }
                        });




            }

        }
    }
}