package tellh.com.recyclertreeview.viewbinder;

import android.os.Handler;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import tellh.com.recyclertreeview.R;
import tellh.com.recyclertreeview.bean.Dir;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewAdapter;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

/**
 * Created by tlh on 2016/10/1 :)
 */

public class DirectoryNodeBinder extends TreeViewBinder<DirectoryNodeBinder.ViewHolder> {

    private String TAG = "DirectoryNodeBinder";
    private TreeViewAdapter mAdapters;
    //    private TreeNode nodes;
    private View itemView;


    @Override
    public ViewHolder provideViewHolder(View itemView, TreeViewAdapter mAdapter) {
        this.mAdapters = mAdapter;
        this.itemView = itemView;
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(ViewHolder holder, int position, TreeNode node, TreeViewAdapter mTreeViewAdapter) {
//        this.nodes = node;


        if (node.isLocked()) {
            holder.ivArrow.setVisibility(View.INVISIBLE);
        } else {
            holder.ivArrow.setRotation(0);
            holder.ivArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_black_18dp);
            int rotateDegree = node.isExpand() ? 90 : 0;
            holder.ivArrow.setRotation(rotateDegree);

            if (node.isLeaf())
                holder.ivArrow.setVisibility(View.INVISIBLE);
            else holder.ivArrow.setVisibility(View.VISIBLE);
        }


        Dir dirNode = (Dir) node.getContent();
        holder.tvName.setText(dirNode.dirName);

        if (node.getIsChecked()) {
            holder.isCheckes.setChecked(true);
        } else {
            holder.isCheckes.setChecked(false);
        }

//        holder.isCheckes.setTag(position);
        holder.isCheckes.setOnClickListener(new MyOnClickListener().setDatas(holder, node, mAdapters, position));

//        holder.isCheckes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (holder.isCheckes.isChecked()) {
//                    node.setChildAllCheckedYes();
//                    Log.e(TAG, "bindView3: " + node.getIsChecked() + "    tag :" + position);
//                    mTreeViewAdapter.notifyDataSetChanged();
//                } else {
//                    node.setChildAllCheckedNo();
//                    Log.e(TAG, "bindView4: " + node.getIsChecked() + "    tag :" + position);
//                    mTreeViewAdapter.notifyDataSetChanged();
//                }
//            }
//        });
    }

    class MyOnClickListener implements View.OnClickListener {
        private TreeNode nodes;
        private TreeViewAdapter mAdapter;
        private ViewHolder holder;
        private int tag;

        public MyOnClickListener setDatas(ViewHolder holder, TreeNode nodes, TreeViewAdapter mAdapter, int tag) {
            this.holder = holder;
            this.nodes = nodes;
            this.mAdapter = mAdapter;
            this.tag = tag;
            return this;
        }

        @Override
        public void onClick(View view) {
            if (holder.isCheckes.isChecked()) {
                nodes.setChildAllCheckedYes();
                Log.e(TAG, "bindView3: " + nodes.getIsChecked() + "    tag :" + tag);
                mAdapter.notifyDataSetChanged();
            } else {
                nodes.setChildAllCheckedNo();
                Log.e(TAG, "bindView4: " + nodes.getIsChecked() + "    tag :" + tag);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dir;
    }

    public static class ViewHolder extends TreeViewBinder.ViewHolder {
        private ImageView ivArrow;
        private TextView tvName;
        private CheckBox isCheckes;

        public ViewHolder(View rootView) {
            super(rootView);
            this.ivArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
            this.isCheckes = (CheckBox) rootView.findViewById(R.id.is_checkes);
        }

        public ImageView getIvArrow() {
            return ivArrow;
        }

        public TextView getTvName() {
            return tvName;
        }

        public CheckBox getIsCheckes() {
            return isCheckes;
        }
    }
}
