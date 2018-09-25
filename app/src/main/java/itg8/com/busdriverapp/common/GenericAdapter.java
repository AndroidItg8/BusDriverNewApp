package itg8.com.busdriverapp.common;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class GenericAdapter<T,E extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<E> {
    private List<T> items;
    private OnRecyclerItemClickListener listener;

    public abstract E setViewHolder(ViewGroup parent,OnRecyclerItemClickListener listener);
    public abstract void onBindData(E holder, T val);
    public abstract OnRecyclerItemClickListener getListener();

    public GenericAdapter(List<T> items){
        this.items = items;
        listener = getListener();
    }

    @Override
    public E onCreateViewHolder(ViewGroup parent, int viewType) {
        E holder=setViewHolder(parent,listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(E holder, int position) {
        onBindData(holder,items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<T> items){
        this.items=items;
        this.notifyDataSetChanged();
    }

    public T getItem(int position){
        return items.get(position);
    }
}
