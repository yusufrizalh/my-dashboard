package id.inixindosurabaya.rzandroiddashboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    String[] titles;
    TypedArray icons;
    Context context;

    public RecyclerViewAdapter(String[] titles,
                               TypedArray icons, Context context) {
        // constructor
        this.titles = titles;
        this.icons = icons;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        LayoutInflater layoutInflater =
                (LayoutInflater)parent.getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == 1) {
            // membaca drawer item layout
            View itemLayout = layoutInflater.inflate(
                    R.layout.drawer_item_layout, null);
            return new ViewHolder(itemLayout, viewType, context);
        }
        else if (viewType == 0) {
            // membaca header layout
            View itemHeader = layoutInflater.inflate(
                    R.layout.header_layout, null);
            return new ViewHolder(itemHeader, viewType, context);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {
        if (position != 0) {
            holder.navTitle.setText(titles[position - 1]);
            holder.navIcon.setImageResource(icons
                    .getResourceId(position - 1, -1));
        }
    }

    @Override
    public int getItemCount() {
        return titles.length + 1;
    }

    public int getItemViewType(int position) {
        if (position == 0) return 0;
        else return 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        // GLOBAL VARIABLES
        TextView navTitle;
        ImageView navIcon;
        Context context;

        public ViewHolder(View drawerItem,
                          int itemType, Context context) {
            super(drawerItem);
            this.context = context;
            drawerItem.setOnClickListener(this);

            if (itemType == 1) {
                navTitle = (TextView)itemView.findViewById(R.id.tv_NavTitle);
                navIcon = (ImageView)itemView.findViewById(R.id.iv_NavIcon);
            }
        }
        // memilih setiap menu yg diklik
        @Override
        public void onClick(View view) {
            MainActivity mainActivity = (MainActivity)context;
            mainActivity.drawerLayout.closeDrawers();
            FragmentTransaction transaction =
                    mainActivity.getSupportFragmentManager()
                    .beginTransaction();

            switch (getPosition()) {
                case 1:
                    Fragment fragment1 = new Fragment_1();
                    transaction.replace(R.id.containerView, fragment1);
                    transaction.commit();
                    break;
                case 2:
                    Fragment fragment2 = new Fragment_2();
                    transaction.replace(R.id.containerView, fragment2);
                    transaction.commit();
                    break;
                case 3:
                    Fragment fragment3 = new Fragment_3();
                    transaction.replace(R.id.containerView, fragment3);
                    transaction.commit();
                    break;
                case 4:
                    Fragment fragment4 = new Fragment_4();
                    transaction.replace(R.id.containerView, fragment4);
                    transaction.commit();
                    break;
            }
        }
    }

}