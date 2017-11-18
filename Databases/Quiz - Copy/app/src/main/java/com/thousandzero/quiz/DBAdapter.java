package com.thousandzero.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {
	static String name = "Quiz.sqlite";
	static String path = "";
	static ArrayList<Structure> a;
	static SQLiteDatabase sdb;

	@Override
	public void onCreate(SQLiteDatabase db) { 
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	private DBAdapter(Context v)
	{
		super(v, name, null, 1);
		path = "/data/data/" + v.getApplicationContext().getPackageName()
				+ "/databases";
	}

	public boolean checkDatabase()
	{
		SQLiteDatabase db = null;
		try
		{
			db = SQLiteDatabase.openDatabase(path + "/" + name, null,
					SQLiteDatabase.OPEN_READWRITE);
		}
		catch (Exception e)
		{
			
		}
		
		if(db==null)
		{
			return false;
		}
		else
		{
			db.close();
			return true;
		}
	}

	public static synchronized DBAdapter getDBAdapter(Context v)
	{
		return(new DBAdapter(v));
	}

	public void createDatabase(Context v)
	{
		this.getReadableDatabase();
		try
		{
			InputStream myInput = v.getAssets().open(name);
		    // Path to the just created empty db
			String outFileName = path +"/"+ name;
		    // Open the empty db as the output stream
			OutputStream myOutput = new FileOutputStream(outFileName);
		    // transfer bytes from the inputfile to the outputfile
			byte[] bytes = new byte[1024];
			int length;
			while ((length = myInput.read(bytes)) > 0)
			{
				myOutput.write(bytes, 0, length);
			}
		    // Close the streams
			myOutput.flush();
			myOutput.close();
			myInput.close();
			
		/*	
			
			
			
			InputStream is = v.getAssets().open("quiz.sqlite");
			// System.out.println(is.available());
			System.out.println(new File(path + "/" + name).getAbsolutePath());
			FileOutputStream fos = new FileOutputStream(path + "/" + name);
			int num = 0;
			while ((num = is.read()) > 0) {
				fos.write((byte) num);
			}
			fos.close();
			is.close();*/
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}

	public void openDatabase()
	{
		try
		{
			sdb = SQLiteDatabase.openDatabase(path + "/" + name, null,
					SQLiteDatabase.OPEN_READWRITE);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}
	public void  insertDB(String name,String add, String Phone,String email,String pass, String age)
	{
		ContentValues cv= new ContentValues();
		cv.put("Name",name);
		cv.put("Address",add);
		cv.put("Phone",Phone);
		cv.put("Email",email);
		cv.put("Password",pass);
		cv.put("Age",age);
		 
		 
		sdb.insert("Register", null, cv);
	}
	public ArrayList<Structure> getData(String tbl)
	{
		 
		Cursor c1 = sdb.rawQuery("select * from "+tbl, null);
		a = new ArrayList<Structure>();


		while (c1.moveToNext())
		{
			Structure q1 = new Structure();
			q1.setQues(c1.getString(1));
			q1.setO1(c1.getString(2));
			q1.setO2(c1.getString(3));
			q1.setO3(c1.getString(4));
			q1.setO4(c1.getString(5));
			 q1.setAns(c1.getString(6));


			a.add(q1);
		}
		return a; 
	}
	public Structure getDatanew(String email,String pass)
	{

		Cursor c1 = sdb.rawQuery("select Name,Address,Phone from Register where Email='"+ email+"' and Password='"+pass+"'", null);

		Structure q1 = new Structure();


		while (c1.moveToNext())
		{

		}
		return q1;
	}
	public void UpdateBal(String age,String email)
	{
		
		sdb.execSQL("UPDATE Register set Age='"+age+"' WHERE Email='"+email+"'");
	}
}
