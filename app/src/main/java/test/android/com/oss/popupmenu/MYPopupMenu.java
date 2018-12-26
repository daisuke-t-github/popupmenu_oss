package test.android.com.oss.popupmenu;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;



public class MYPopupMenu
{
	//
	// region member
	//

	private PopupMenu mPopup;
	private ArrayList<String> mArray;
	private MYPopupMenuInterface mObserver;

	//
	// endregion
	//



	public MYPopupMenu(Context context, View anchor)
	{
		super();

		mPopup = new PopupMenu(context, anchor);
	}



	public void ObserverSet(MYPopupMenuInterface observer)
	{
		mObserver = observer;
	}

	public void ArraySet(ArrayList<String> array)
	{
		mArray = array;

		mPopup.getMenu().clear();
		for(String elm : mArray)
		{
			mPopup.getMenu().add(elm);
		}

		mPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem)
			{
				Action(menuItem);

				return false;
			}
		});

	}

	private void Action(MenuItem menuItem)
	{
		int no = 0;
		for(int i = 0; i < mArray.size(); i++)
		{
			if(!StringUtils.equalsIgnoreCase(menuItem.getTitle(), mArray.get(i))) continue;

			no = i;
			break;
		}

		if(mObserver == null) return;
		if(!(mObserver instanceof MYPopupMenuInterface)) return;

		mObserver.onClick(no);
	}

	public void Show()
	{
		mPopup.show();
	}
}
