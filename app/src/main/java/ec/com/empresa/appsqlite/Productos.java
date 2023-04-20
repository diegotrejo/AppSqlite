package ec.com.empresa.appsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Productos {

    private DBHellper dbHelper;
    private SQLiteDatabase db;

    public Productos(Context contexto, String dbName, int version){
        dbHelper = new DBHellper(contexto, dbName, null, version);
    }

    public Producto Create( String id, String nombre, String clasificacion, Integer cantidad, Double precio, Double iva)
    {
        db = dbHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("id", id);
        row.put("nombre", nombre);
        row.put("clasificacion", clasificacion);
        row.put("cantidad", cantidad);
        row.put("precio", precio);
        row.put("iva", iva);

        long qty = db.insert("productos", null, row);
        if (qty > 0)
        {
            Producto data = new Producto();
            data.Id = id;
            data.Nombre = nombre;
            data.Clasificacion = clasificacion;
            data.Precio = precio;
            data.Cantidad = cantidad;
            data.IVA = iva;
            return  data;
        }
        else
        {
            return  null;
        }
    }

    public  Producto Read_One( String id)
    {
        db = dbHelper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT id, nombre, clasificacion, cantidad, precio, iva FROM productos WHERE id = '" + id + "' ", null);
        if (cr.getCount()>0)
        {
            Producto prod = new Producto();
            cr.moveToNext();
            prod.Id = cr.getString(0);
            prod.Nombre = cr.getString(1);
            prod.Clasificacion = cr.getString(2);
            prod.Cantidad = cr.getInt(3);
            prod.Precio = cr.getDouble(4);
            prod.IVA = cr.getDouble(5);

            return prod;
        }
        else {
            return  null;
        }
    }

    public  Producto[] Read_All()
    {
        db = dbHelper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT id, nombre, clasificacion, cantidad, precio, iva FROM productos ORDER BY nombre", null);
        if (cr.getCount()>0)
        {
            Producto[] datos = new Producto[cr.getCount()];
            Producto prod;
            int i = 0;

            while (cr.moveToNext())
            {
                prod = new Producto();
                prod.Id = cr.getString(0);
                prod.Nombre = cr.getString(1);
                prod.Clasificacion = cr.getString(2);
                prod.Cantidad = cr.getInt(3);
                prod.Precio = cr.getDouble(4);
                prod.IVA = cr.getDouble(5);
                datos[i++]= prod;
            }
            return  datos;
        }
        else {
            return  null;
        }
    }

    public  Producto[] Read_ByNombre(String find)
    {
        db = dbHelper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT id, nombre, clasificacion, cantidad, precio, iva FROM productos WHERE nombre LIKE '%" + find + "%' ORDER BY nombre", null);
        if (cr.getCount()>0)
        {
            Producto[] datos = new Producto[cr.getCount()];
            Producto prod;
            int i = 0;

            while (cr.moveToNext())
            {
                prod = new Producto();
                prod.Id = cr.getString(0);
                prod.Nombre = cr.getString(1);
                prod.Clasificacion = cr.getString(2);
                prod.Cantidad = cr.getInt(3);
                prod.Precio = cr.getDouble(4);
                prod.IVA = cr.getDouble(5);
                datos[i++]= prod;
            }
            return  datos;
        }
        else {
            return  null;
        }
    }

    public boolean Update( String id, String nombre, String clasificacion, Integer cantidad, Double precio, Double iva)
    {
        db = dbHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("id", id);
        row.put("nombre", nombre);
        row.put("clasificacion", clasificacion);
        row.put("cantidad", cantidad);
        row.put("precio", precio);
        row.put("iva", iva);

        int qty = db.update("productos", row,"id='"+id+"'", null);
        return qty>0;
    }

    public boolean Delete( String id)
    {
        db = dbHelper.getWritableDatabase();
        int qty = db.delete("productos", "id='"+id+"'", null);
        return qty>0;
    }
}
