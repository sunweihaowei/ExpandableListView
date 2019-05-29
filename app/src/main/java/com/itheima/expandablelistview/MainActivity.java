package com.itheima.expandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
//总结:
/*
ExpandableListView
1.建立
首先先建立布局，父控件，子控件（这里建立一个实体类，既可以get来得到，用到list，并且泛型为list）
得到ID（父控件，子控件），得到项数（子，父控件），得到某一项
加载布局父控件、子控件
设计是否可以点击
监听的话没什么好说的，（注意，show（））


*/
public class MainActivity extends AppCompatActivity {
    private String[] getGroupString;
    //多个时不可以都等于一个：如下=new ArrayList;
    //String 用length，list用size
    private List <xjlInfo> pkq, tmm, cxk;
    List <List <xjlInfo>> allxjl = new ArrayList <>();
    private ExpandableListView el;
    private ElvAdapter elvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initView();
        getGroupString = new String[]{"皮卡丘", "汤姆猫", "蔡徐坤"};
        pkq=new ArrayList <>(  );
        pkq.add( new xjlInfo( R.drawable.head, "1" ) );
        pkq.add( new xjlInfo( R.drawable.qing, "2" ) );
        allxjl.add( pkq );

        tmm=new ArrayList <>(  );
        tmm.add( new xjlInfo( R.drawable.head, "1" ) );
        tmm.add( new xjlInfo( R.drawable.qing, "2" ) );
        allxjl.add( tmm );

        cxk=new ArrayList <>(  );
        cxk.add( new xjlInfo( R.drawable.head, "1" ) );
        cxk.add( new xjlInfo( R.drawable.qing, "2" ) );
        allxjl.add( cxk );
        elvAdapter=new ElvAdapter();
        el.setAdapter( elvAdapter );
        //父控件，子控件到帮你找到项
        el.setOnGroupClickListener( new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText( MainActivity.this,"我是"+groupPosition+"父控件",Toast.LENGTH_LONG ).show();
                return false;//这个设计为true则不可以点击
            }
        } );
        el.setOnChildClickListener( new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText( MainActivity.this,"我是"+allxjl.get( groupPosition ).get( childPosition ).getName()+"子控件",Toast.LENGTH_LONG ).show();
                return false;
            }
        } );
    }
    private void initView() {
        el = (ExpandableListView) findViewById( R.id.el );
    }
    class  ElvAdapter extends BaseExpandableListAdapter{
        @Override
        public int getGroupCount() {//得到父控件的项
            return getGroupString.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {//得到list里面的泛型list的个数
            return allxjl.get( groupPosition ).size();
        }

        @Override
        public Object getGroup(int groupPosition) {//得到父控件的某一项
            return getGroupString[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {//得到list泛型里面的list的某一项
            return allxjl.get( groupPosition ).get( childPosition );
        }

        @Override
        public long getGroupId(int groupPosition) {//得到父控件的ID
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {//得到子控件的ID
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {//加载父控件布局
            View view=View.inflate( MainActivity.this,R.layout.item_group,null );
            TextView title=view.findViewById( R.id.group_tv );
            title.setText( getGroupString[groupPosition] );
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {//加载子控件布局
            View view=View.inflate( MainActivity.this,R.layout.item_child,null );
            ImageView imageView=view.findViewById(R.id.child_iv);
            TextView textView=view.findViewById( R.id.child_tv );


                imageView.setImageResource( allxjl.get( groupPosition ).get( childPosition ).getLogo() );
                textView.setText( allxjl.get( groupPosition ).get( childPosition ).getName() );



            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
        class GroupViewHolder{
            TextView groupItemText;
        }
        class ChildViewHolder{
            ImageView childItemloge;
            TextView childItemText;
        }
    }
}
