/*
 * [程序名称] Android 通讯录
 * [作者] xmobileapp团队
 * [参考资料] Google Android Samples 
 * [开源协议] Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.com.sjtu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "gcontacts.db";
	public static final int DATABASE_VERSION = 2;
	public static final String CONTACTS_USER_TABLE = "contacts_user";
	public static final String CONTACTS_GROUP_TABLE = "contacts_group";
	// 创建数据库
	private static final String DATABASE_USER_CREATE = "CREATE TABLE " + CONTACTS_USER_TABLE + " (" + ContactColumn._ID + " integer primary key autoincrement," + ContactColumn.NAME + " text," + ContactColumn.MOBILE + " text," + ContactColumn.EMAIL + " text," + ContactColumn.CREATED + " long," + ContactColumn.MODIFIED + " long," + ContactColumn.GROUP + " integer);";
	private static final String DATABASE_GROUP_CREATE = "CREATE TABLE " + CONTACTS_GROUP_TABLE + "(" + ContactColumn._ID + " integer primary key autoincrement," + ContactColumn.GROUP_NAME + " text)";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_USER_CREATE);
		db.execSQL(DATABASE_GROUP_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_USER_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_GROUP_TABLE);
		onCreate(db);
	}

}
