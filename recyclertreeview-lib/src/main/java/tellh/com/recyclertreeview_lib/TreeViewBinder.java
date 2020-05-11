package tellh.com.recyclertreeview_lib;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public abstract class TreeViewBinder<VH extends RecyclerView.ViewHolder> implements LayoutItemType {
    public abstract VH provideViewHolder(View itemView,TreeViewAdapter mAdapter);

    public abstract void bindView(VH holder, int position, TreeNode node ,TreeViewAdapter mAdapter);

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View rootView) {
            super(rootView);
        }

        protected <T extends View> T findViewById(@IdRes int id) {
            return (T) itemView.findViewById(id);
        }
    }

}