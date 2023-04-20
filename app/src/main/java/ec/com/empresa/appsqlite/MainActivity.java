package ec.com.empresa.appsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Productos productos;
    EditText txtId, txtNombre, txtClasificacion, txtPrecio, txtCantidad, txtIVA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new Productos(this, "empresa.db", 1);
        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        txtClasificacion = findViewById(R.id.txtClasificacion);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtIVA = findViewById(R.id.txtIVA);
    }

    public void cmdCreate_onClick(View v)
    {
        Producto p = productos.Create(
                txtId.getText().toString(),
                txtNombre.getText().toString(),
                txtClasificacion.getText().toString(),
                Integer.parseInt( txtCantidad.getText().toString()),
                Double.parseDouble( txtPrecio.getText().toString()),
                Double.parseDouble( txtIVA.getText().toString())
        );
        if (p != null)
            Toast.makeText(this, "PRODUCTO INSERTADO OK", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "ERROR !! PRODUCTO NO INSERTADO", Toast.LENGTH_SHORT).show();
    }

    public void cmdReadOne_onClick(View v)
    {
        Producto p = productos.Read_One( txtId.getText().toString());
        if (p != null)
        {
            txtId.setText( p.Id);
            txtNombre.setText( p.Nombre);
            txtClasificacion.setText( p.Clasificacion);
            txtPrecio.setText( "" + p.Precio);
            txtCantidad.setText( "" + p.Cantidad);
            txtIVA.setText( "" + p.IVA);
        }
        else
        {
            Toast.makeText(this, "PRODUCTO NO ENCONTRADO !!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cmdUpdate_onClick(View v)
    {
        boolean resultado = productos.Update(
                txtId.getText().toString(),
                txtNombre.getText().toString(),
                txtClasificacion.getText().toString(),
                Integer.parseInt( txtCantidad.getText().toString()),
                Double.parseDouble( txtPrecio.getText().toString()),
                Double.parseDouble( txtIVA.getText().toString())
        );
        if (resultado == true)
            Toast.makeText(this, "PRODUCTO ACTUALZIADO OK", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "ERROR!! PRODUCTO NO ACTUALIZADO", Toast.LENGTH_SHORT).show();
    }

    public void cmdDelete_onClick(View v)
    {
        boolean resultado = productos.Delete( txtId.getText().toString());
        if (resultado == true)
        {
            Toast.makeText(this, "REGISTRO BORRADO OK ", Toast.LENGTH_SHORT).show();
            txtId.setText( "");
            txtNombre.setText( "");
            txtClasificacion.setText( "");
            txtPrecio.setText( "" );
            txtCantidad.setText( "" );
            txtIVA.setText( "" );
        }
        else
            Toast.makeText(this, "ERROR: PRODUCTO NO ENCONTRADO !!!", Toast.LENGTH_SHORT).show();
    }

}