package ec.com.empresa.appsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




import androidx.annotation.Nullable;

public class DBHellper extends SQLiteOpenHelper {
    public DBHellper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS productos( id text(10) not null primary key, nombre text(30), clasificacion text(20), cantidad int, precio numeric(10,3), iva numeric(10,3)  ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
