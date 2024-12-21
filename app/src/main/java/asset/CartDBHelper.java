package asset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CartDBHelper extends SQLiteOpenHelper {
    //Table name and column
    private static final String TABLE_CART_DETAIL = "CartDetails";
    private static final String ID = "ID";
    private static final String ITEM_NAME = "ItemName";
    private static final String ITEM_PRICE = "ItemPrice";
    private static final String ITEM_QUANTITY = "ItemQuantity";

    public CartDBHelper(Context context) {
        super(context, "CartData.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_CART_DETAIL + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ITEM_NAME + " TEXT NOT NULL, "
                + ITEM_PRICE + " REAL NOT NULL, "
                + ITEM_QUANTITY + " INTEGER NOT NULL) ";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART_DETAIL);
        onCreate(db);
    }

    public boolean cartInsert(String itemName, double itemPrice, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_NAME, itemName);
        contentValues.put(ITEM_PRICE, itemPrice);
        contentValues.put(ITEM_QUANTITY, quantity);
        long result = db.insert(TABLE_CART_DETAIL,null, contentValues);
        return result != -1;
    }

//    public boolean cartUpdate(String itemName, double itemPrice, int quantity){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ITEM_PRICE, itemPrice);
//        contentValues.put(ITEM_QUANTITY, quantity);
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART_DETAIL + " WHERE " + ITEM_NAME + "=?", new String[]{itemName});
//
//        if(cursor.getCount() >0){
//            long result = db.update(TABLE_CART_DETAIL, contentValues, ITEM_NAME + "=?", new String[]{itemName});
//            cursor.close();
//            return result != -1;
//        }
//        else {
//            cursor.close();
//            return false;
//        }
//    }

    public boolean cartDelete(String itemName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART_DETAIL + " WHERE " + ITEM_NAME + "=?", new String[]{itemName});

        if(cursor.getCount() >0){
            long result = db.delete(TABLE_CART_DETAIL, ITEM_NAME + "=?", new String[]{itemName});
            cursor.close();
            return result != -1;
        }
        else {
            cursor.close();
            return false;
        }
    }

    public Cursor getCartData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_CART_DETAIL, null);
    }



    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART_DETAIL, null, null);
        db.close();
    }

    public boolean isItemInCart(String itemName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART_DETAIL + " WHERE " + ITEM_NAME + "=?", new String[]{itemName});
        boolean exist = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exist;
    }

}
