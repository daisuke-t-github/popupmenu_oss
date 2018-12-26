package test.android.com.oss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

import java.util.ArrayList;
import java.util.HashMap;

import test.android.com.oss.popupmenu.MYPopupMenu;
import test.android.com.oss.popupmenu.MYPopupMenuInterface;

public class MainActivity extends AppCompatActivity
		implements
		MYPopupMenuInterface
{

	private enum MENU_ITEM_TYPE{
		OSS,
		TEST,
	}

	private MYPopupMenu mPopupMenu;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		PopupMenuInit();
		ThreeDotInit();

	}



	//
	// region popup menu
	//

	private void PopupMenuInit()
	{
		mPopupMenu = new MYPopupMenu(this, findViewById(R.id.imageview_three_dot));
		mPopupMenu.ObserverSet(this);

		mPopupMenu.ArraySet(new ArrayList<>(PopMenuMap().values()));

	}

	public void onClick(int no)
	{
		MENU_ITEM_TYPE type = MENU_ITEM_TYPE.values()[no];

		if(type == MENU_ITEM_TYPE.OSS)
		{
			Intent intent = new Intent(this, OssLicensesMenuActivity.class);
			startActivity(intent);
		}
		else
		{
			Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
		}
	}

	private HashMap<MENU_ITEM_TYPE, String> PopMenuMap()
	{
		HashMap<MENU_ITEM_TYPE, String> res = new HashMap<MENU_ITEM_TYPE, String>()
		{
			{
				put(MENU_ITEM_TYPE.OSS, "Open source licenses");
				put(MENU_ITEM_TYPE.TEST, "TEST");
			}
		};

		return res;
	}

	//
	// endregion
	//



	private void ThreeDotInit()
	{
		findViewById(R.id.imageview_three_dot).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mPopupMenu.Show();
			}
		});
	}

}
