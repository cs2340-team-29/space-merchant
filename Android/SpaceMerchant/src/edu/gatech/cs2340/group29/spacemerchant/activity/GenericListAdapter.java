package edu.gatech.cs2340.group29.spacemerchant.activity;
import java.util.ArrayList;

import edu.gatech.cs2340.group29.spacemerchant.R;
 
import android.content.Context;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ArrayAdapter;
 import android.widget.LinearLayout;
import android.widget.TextView;
 
 
/**
 * This is an adapter class for the TListItems
  *
 * @version 1.0, 05/21/12
 * @author Harrison Katz
 */
 public class GenericListAdapter<T> extends ArrayAdapter<T>
 {
    private int          selectedItem = -1;
     private ArrayList<T> items;
     private Context      context;
 
    public GenericListAdapter( Context context, int layoutResourceId, ArrayList<T> items )
     {
        super( context, layoutResourceId, items );
         this.context = context;
         this.items = items;
     }

    static class ViewHolder
     {
        protected TextView text;
     }

    /**
      * Returns what should be displayed by the list
     *
     * @param position
     * @param convertView
      * @param parent
     *
     * @return View
     */
     @Override
    public View getView( int position, View convertView, ViewGroup parent )
     {
        ViewHolder viewHolder;
 
        if ( convertView == null )
         {
//            convertView = View.inflate( context, R.layout.generic_list_item, null );
             viewHolder = new ViewHolder();
//             viewHolder.text = ( TextView ) convertView.findViewById( R.id.text );
             convertView.setTag( viewHolder );
         }
        else
         {
            viewHolder = ( ViewHolder ) convertView.getTag();
         }

        viewHolder.text.setText( items.get( position ).toString() );
 
        // set selected item
         LinearLayout activeItem = ( LinearLayout ) convertView;
         if ( position == selectedItem )
         {
//            activeItem.setBackgroundResource( R.color.generic );
         }
        else
         {
//            activeItem.setBackgroundResource( R.color.generic );
         }

        return activeItem;
     }

    public void setSelected( int position )
     {
        selectedItem = position;
     }
}