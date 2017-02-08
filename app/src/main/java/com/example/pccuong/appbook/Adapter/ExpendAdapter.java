package com.example.pccuong.appbook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.pccuong.appbook.Model.ObjectClass.Categories;
import com.example.pccuong.appbook.Model.XuLyHomePage.DataJsonMenu;
import com.example.pccuong.appbook.R;

import java.util.List;

/**
 * Created by PCCuong on 2/6/2017.
 */

public class ExpendAdapter extends BaseExpandableListAdapter {
    Context context;
    List<Categories> categoriesData;
    public ExpendAdapter (Context context , List<Categories> categoriesData){

        this.context = context;
        this.categoriesData = categoriesData;
        DataJsonMenu dataJsonMenu = new DataJsonMenu();
        int count = categoriesData.size();
        for (int i = 0 ; i < count ; i++){
            int id = categoriesData.get(i).getId();
            categoriesData.get(i).setCategories(dataJsonMenu.getChildPositionMenu(id));
        }

    }
    @Override
    public int getGroupCount() {
        return categoriesData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categoriesData.get(groupPosition).getCategories().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoriesData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categoriesData.get(groupPosition).getCategories().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return categoriesData.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return categoriesData.get(groupPosition).getCategories().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_group_position,parent, false);
        TextView txtGroupPosition = (TextView) view.findViewById(R.id.textGroupPosition);
        txtGroupPosition.setText(categoriesData.get(groupPosition).getName());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.custom_layout_group_position,parent, false);
//        TextView txtGroupPosition = (TextView) view.findViewById(R.id.textGroupPosition);
//        txtGroupPosition.setText(categoriesData.get(groupPosition).getCategories().get(childPosition).getName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
