/*
 * [��������] Android ͨѶ¼
 * [����] xmobileapp�Ŷ�
 * [�ο�����] Google Android Samples 
 * [��ԴЭ��] Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
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

import java.io.IOException;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.util.Tools;

public class Contact extends ListActivity {
	private static final String TAG = "Contacts";
	private ListView myListView;
	private Button backToGroup;
	private Button add;
	private Button search;
	private EditText searchArea;
	private String fileName;
	private LayoutInflater layoutInflater;
	private View viewAddEmployee;
	
	private static final int ExportContact_ID = Menu.FIRST;
	private static final int EditContact_ID = Menu.FIRST + 1;
	private static final int CallContact_ID = Menu.FIRST + 2;
	private static final int SendMess_ID = Menu.FIRST + 3;
	private static final int IMPORT_ID = Menu.FIRST + 4;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		 myListView = (ListView)findViewById(android.R.id.list);
		search = (Button) findViewById(R.id.submitId);
		add = (Button) findViewById(R.id.add);
		backToGroup = (Button) findViewById(R.id.backToGroup);
		searchArea = (EditText) findViewById(R.id.searchArea);
		
		// ���ÿ�ݼ�֧��
		setDefaultKeyMode(DEFAULT_KEYS_SHORTCUT);

		// ��ȡ/����Intent�����ڴ�ContactsProvider����ȡͨѶ¼����
		Intent intent = getIntent();
		if (intent.getData() == null) {
			intent.setData(ContactsProvider.CONTENT_URI);
		}

		// ���ó���֧�֣������������Ĳ˵���
		getListView().setOnCreateContextMenuListener(this);

		// ʹ��managedQuery��ȡContactsProvider��Cursor
		Cursor cursor = null;
		if (getIntent().getData().toString().indexOf(ContactsProvider.CONTENT_URI.toString()) != -1) {
			cursor = managedQuery(getIntent().getData(), ContactColumn.PROJECTION, null, null, ContactColumn.NAME);
		} else {
			cursor = managedQuery(ContactsProvider.CONTENT_URI, ContactColumn.PROJECTION, ContactColumn.GROUPNUM + " = ?", new String[] { getIntent().getData().getPathSegments().get(1) }, ContactColumn.NAME);
			intent.setData(ContactsProvider.CONTENT_URI);
		}

		// ʹ��SimpleCursorAdapter����Cursor��Adapter�Ա�ʹ�ã����ݱ�ʾ��ʽΪ������ - �ֻ�����
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.contact_list_item, cursor, new String[] { ContactColumn.NAME, ContactColumn.MOBILE }, new int[] { R.id.name, R.id.contactinfo });

		// Ϊ��ǰListView����Adapter
		setListAdapter(adapter);
		// myListView.setAdapter(adapter);
		initButtonAction();
		initEditAction();
		Log.e(TAG + "onCreate", " is ok");

	}

	// Ŀ¼�����Ļص�����
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		// ��Ŀ¼�����ӡ���ӡ���ť��Ϊ֮�趨��ݼ���ͼ��
		menu.add(0, ExportContact_ID, 0, R.string.menu_export).setShortcut('4', 'e').setIcon(android.R.drawable.ic_menu_set_as);
		if(myListView.getSelectedItem() != null){
			menu.add(0, CallContact_ID, 0, R.string.service_call).setShortcut('5', 'f').setIcon(android.R.drawable.ic_menu_call);
			menu.add(0, SendMess_ID, 0, R.string.service_message).setShortcut('6', 'g').setIcon(android.R.drawable.ic_menu_agenda);
		}
		menu.add(0, IMPORT_ID, 0, R.string.menu_import).setShortcut('7', 'h').setIcon(android.R.drawable.ic_menu_upload);
		return true;
	}

	// Ŀ¼��ʾ֮ǰ�Ļص�����
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		final boolean haveItems = getListAdapter().getCount() > 0;

		// �����ǰ�б�Ϊ��
		if (haveItems) {
			Uri uri = ContentUris.withAppendedId(getIntent().getData(), getSelectedItemId());

			Intent[] specifics = new Intent[1];
			specifics[0] = new Intent(Intent.ACTION_EDIT, uri);
			MenuItem[] items = new MenuItem[1];

			Intent intent = new Intent(null, uri);
			intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
			menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0, null, specifics, intent, 0, items);

			// �����CATEGORY_ALTERNATIVE���͵Ĳ˵���,���༭ѡ������룬��Ϊ֮��ӿ�ݼ�
			if (items[0] != null) {
				items[0].setShortcut('1', 'e');
			}
		} else {
			menu.removeGroup(Menu.CATEGORY_ALTERNATIVE);
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		long id;
		Intent intent;
		
		
		switch (item.getItemId()) {
			case ExportContact_ID:
				initLayOutView();
				
				new AlertDialog.Builder(this).setTitle(getText(R.string.is_export)).setView(viewAddEmployee).setPositiveButton(getText(R.string.code_export), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						exportXML(fileName,true);
					}
	
				}).setNeutralButton(getText(R.string.uncode_export), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						exportXML(fileName,false);
					}
	
				}).setNegativeButton(getText(R.string.cancel), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).show();
				return true;
			case CallContact_ID:
				id = myListView.getSelectedItemId();
				Cursor cursor = managedQuery(ContentUris.withAppendedId(ContactsProvider.CONTENT_URI, id), ContactColumn.PROJECTION, null,null, null);
				cursor.moveToFirst();
				intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+cursor.getString(cursor.getColumnIndex(ContactColumn.MOBILE))));   
		        this.startActivity(intent);   
				return true;
			case SendMess_ID:
				id = myListView.getSelectedItemId();
				intent = new Intent(Intent.ACTION_SEND, ContentUris.withAppendedId(ContactsProvider.CONTENT_URI, id));
				startActivity(intent);
				return true;
			case IMPORT_ID:
				//TODO ����
				initLayOutView();
				new AlertDialog.Builder(this).setTitle(getText(R.string.is_import)).setView(viewAddEmployee).setPositiveButton(getText(R.string.menu_import), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
	
				}).setNegativeButton(getText(R.string.cancel), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).show();
				return true;
			default :
				break;
		}
			
			
		return super.onOptionsItemSelected(item);
	}


	private void initLayOutView() {
		if(null == layoutInflater){
			layoutInflater = LayoutInflater.from(this);
			viewAddEmployee = layoutInflater.inflate(R.layout.export, null);
			fileName = ((EditText)viewAddEmployee.findViewById(R.exportid.fileNameId)).getText().toString();
		}
	}

	// �����Ĳ˵���������ͨ��������Ŀ���������Ĳ˵�
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		AdapterView.AdapterContextMenuInfo info;
		try {
			info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		} catch (ClassCastException e) {
			return;
		}

		Cursor cursor = (Cursor) getListAdapter().getItem(info.position);
		if (cursor == null) {
			return;
		}

		menu.setHeaderTitle(cursor.getString(1));

		menu.add(0, EditContact_ID, 0, R.string.menu_delete);
	}

	// �����Ĳ˵�ѡ��Ļص�����
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info;
		try {
			info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		} catch (ClassCastException e) {
			return false;
		}

		switch (item.getItemId()) {
		// ѡ��༭��Ŀ
		case EditContact_ID: {
			Uri noteUri = ContentUris.withAppendedId(getIntent().getData(), info.id);
			getContentResolver().delete(noteUri, null, null);
			return true;
		}
		}
		return false;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);

		String action = getIntent().getAction();
		if (Intent.ACTION_PICK.equals(action) || Intent.ACTION_GET_CONTENT.equals(action)) {
			// ���ͨѶ¼�б��Activity�Ǳ�����Activity�����Է���ѡ���ͨѶ��Ϣ
			// ���磬���ų���ͨ����������ȡĳ�˵ĵ绰����
			setResult(RESULT_OK, new Intent().setData(uri));
		} else {
			// �༭ ��ϵ��
			startActivity(new Intent(Intent.ACTION_EDIT, uri));
		}
	}

	private void initButtonAction() {
		backToGroup.setOnClickListener(new BackToGroupAction());
		add.setOnClickListener(new AddAction());
		search.setOnClickListener(new SearchAction(this));
	}

	private void initEditAction() {
		searchArea.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (searchArea.getText().toString().equals("��ѯ����"))
					searchArea.setText("");
			}
		});
	}

	class SearchAction implements OnClickListener {

		private Contact contact;

		public SearchAction(Contact contact) {
			this.contact = contact;
		}

		public void onClick(View v) {
			String str = searchArea.getText().toString();

			Cursor cursor = managedQuery(getIntent().getData(), ContactColumn.PROJECTION, ContactColumn.NAME + " like ?", new String[] { "%" + str + "%" }, ContactColumn.NAME);

			// ʹ��SimpleCursorAdapter����Cursor��Adapter�Ա�ʹ�ã����ݱ�ʾ��ʽΪ������ - �ֻ�����
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(contact, R.layout.contact_list_item, cursor, new String[] { ContactColumn.NAME, ContactColumn.MOBILE }, new int[] { R.id.name, R.id.contactinfo });

			// Ϊ��ǰListView����Adapter
			setListAdapter(adapter);
		}
	}

	class BackToGroupAction implements OnClickListener {
		public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(Contact.this, ShowGroup.class);
			Contact.this.startActivity(intent);
		}
	}

	class AddAction implements OnClickListener {
		public void onClick(View v) {
			startActivity(new Intent(Intent.ACTION_INSERT, getIntent().getData()));
		}
	}

	private void exportXML(String fileName , boolean isCode) {
		
		int returnValue = 0;
		XmlTools xmlTools = new XmlTools();
		Cursor cur = getContentResolver().query(getIntent().getData(), ContactColumn.USER, null, null, null);
		Cursor cursor = managedQuery(GroupProvider.GROUP_URI, ContactColumn.GROUPPRO, null, null, null);
		try {
			returnValue = xmlTools.writeXml(fileName.equals("")?null:fileName+".xml", Tools.cursor2User(cur, Tools.getIdColumnMap(cursor), isCode));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (1 == returnValue)
			Toast.makeText(Contact.this, "�����ɹ�", Toast.LENGTH_SHORT).show();
		if (0 == returnValue)
			Toast.makeText(Contact.this, "����ʧ��", Toast.LENGTH_SHORT).show();
	}
}