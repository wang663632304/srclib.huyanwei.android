package srclib.huyanwei.phonelistener;

import java.util.Locale;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ConfigSQLiteOpenHelper extends SQLiteOpenHelper {

	private final String TAG = "srclib.huyanwei.phonelistener.ConfigSQLiteOpenHelper";
	
	static final String DATABASE_NAME 			= "config.db";

	static final int    DATABASE_VERSION_NUMBER = 3;
	
	private SQLiteDatabase mSQLiteDatabase;
	
	public ConfigSQLiteOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION_NUMBER);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		//Log.d(TAG,"ConfigSQLiteOpenHelper.onCreate() {");
		
		mSQLiteDatabase = db;
		
		String defaultconfig = "";
		
		// Create Table.
		String createtable = "create table if not exists "
		+ConfigContentProvider.TABLE_NAME+" ("
		+ConfigContentProvider.TABLE_FIELD_ID+" integer primary key autoincrement,"
		+ConfigContentProvider.TABLE_FIELD_NAME+" text, "
		+ConfigContentProvider.TABLE_FIELD_VALUE+" integer);";
		mSQLiteDatabase.execSQL(createtable);
		
		// proximity sensor listen enable.
		defaultconfig ="insert into "
		+ConfigContentProvider.TABLE_NAME+"("
		+ConfigContentProvider.TABLE_FIELD_NAME+","
		+ConfigContentProvider.TABLE_FIELD_VALUE
		+") values(\""
		+ConfigContentProvider.TABLE_CONTENT_CONFIG_ENABLE
		+"\",1);";
		mSQLiteDatabase.execSQL(defaultconfig);
		
		// proximity sensor listen enable.  answer/reject
		defaultconfig ="insert into "
		+ConfigContentProvider.TABLE_NAME+"("
		+ConfigContentProvider.TABLE_FIELD_NAME+","
		+ConfigContentProvider.TABLE_FIELD_VALUE
		+") values(\""
		+ConfigContentProvider.TABLE_CONTENT_CONFIG_ACTION
		+"\",1);";
		mSQLiteDatabase.execSQL(defaultconfig);
		
		// speaker on/off
		defaultconfig ="insert into "
		+ConfigContentProvider.TABLE_NAME+"("
		+ConfigContentProvider.TABLE_FIELD_NAME+","
		+ConfigContentProvider.TABLE_FIELD_VALUE
		+") values(\""
		+ConfigContentProvider.TABLE_CONTENT_CONFIG_SPEAKER
		+"\",1);";
		mSQLiteDatabase.execSQL(defaultconfig);
		
		// database inited.
		defaultconfig ="insert into "
		+ConfigContentProvider.TABLE_NAME+"("
		+ConfigContentProvider.TABLE_FIELD_NAME+","
		+ConfigContentProvider.TABLE_FIELD_VALUE
		+") values(\""
		+ConfigContentProvider.TABLE_CONTENT_CONFIG_INITED
		+"\",0);";
		mSQLiteDatabase.execSQL(defaultconfig);
		
		//Log.d(TAG,"ConfigSQLiteOpenHelper.onCreate() }");
	}

	public void Init(Context context,SQLiteDatabase db)
	{
		String sql = null ;
		int config_value;
	
		//Log.d(TAG,"ConfigSQLiteOpenHelper.Init() {");
		
		mSQLiteDatabase = db;
		
		config_value = context.getResources().getInteger(R.integer.config_proximity_sensor_enable);
		//Log.d(TAG,"ConfigSQLiteOpenHelper.Init() config_value="+config_value);
	    sql = String.format(Locale.ENGLISH, 
	    		"update "+ConfigContentProvider.TABLE_NAME
	    		+" set "+ConfigContentProvider.TABLE_FIELD_VALUE+"=%d"
	    		+" where "+ConfigContentProvider.TABLE_FIELD_NAME+"=\""
	    		+ConfigContentProvider.TABLE_CONTENT_CONFIG_ENABLE
	    		+"\"",config_value);
	    mSQLiteDatabase.execSQL(sql);
	    
		config_value = context.getResources().getInteger(R.integer.config_action);
		//Log.d(TAG,"ConfigSQLiteOpenHelper.Init() config_value="+config_value);
	    sql = String.format(Locale.ENGLISH, 
	    		"update "+ConfigContentProvider.TABLE_NAME
	    		+" set "+ConfigContentProvider.TABLE_FIELD_VALUE+"=%d "
	    		+"where "+ConfigContentProvider.TABLE_FIELD_NAME+"=\""
	    		+ConfigContentProvider.TABLE_CONTENT_CONFIG_ACTION
	    		+"\"",config_value);
	    mSQLiteDatabase.execSQL(sql);
	    
	    config_value = context.getResources().getInteger(R.integer.config_speaker);
	    //Log.d(TAG,"ConfigSQLiteOpenHelper.Init() config_value="+config_value);
	    sql = String.format(Locale.ENGLISH, 
	    		"update "+ConfigContentProvider.TABLE_NAME
	    		+" set "+ConfigContentProvider.TABLE_FIELD_VALUE+"=%d "
	    		+"where "+ConfigContentProvider.TABLE_FIELD_NAME+"=\""
	    		+ConfigContentProvider.TABLE_CONTENT_CONFIG_SPEAKER
	    		+"\"",config_value);
	    mSQLiteDatabase.execSQL(sql);

	    // inited 
	    sql = String.format(Locale.ENGLISH, 
	    		"update "+ConfigContentProvider.TABLE_NAME
	    		+" set "+ConfigContentProvider.TABLE_FIELD_VALUE+"=1 "
	    		+"where "+ConfigContentProvider.TABLE_FIELD_NAME+"=\""
	    		+ConfigContentProvider.TABLE_CONTENT_CONFIG_INITED
	    		+"\"");
	    mSQLiteDatabase.execSQL(sql);
	    
	    //Log.d(TAG,"ConfigSQLiteOpenHelper.Init() }");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}