package com.juawapps.newsspread.ui.common;

import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.juawapps.newsspread.data.Resource;

import java.util.List;

import timber.log.Timber;

/**
 * Recycler view to be used with data bound views.
 */

public abstract class DataBoundRecyclerAdapter <DataType, ViewBindingType extends ViewDataBinding>
        extends RecyclerView.Adapter<DataBoundRecyclerAdapter.DataBoundViewHolder<ViewBindingType>> {

    @Nullable
    private List<DataType> mData;

    @Override
    public DataBoundViewHolder<ViewBindingType> onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewBindingType binding = createBinding(parent);
        return new DataBoundViewHolder<>(binding);
    }

    @Override
    public final void onBindViewHolder(DataBoundViewHolder<ViewBindingType> holder, int position) {
        //noinspection ConstantConditions
        bind(holder.binding, mData.get(position));
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @SuppressWarnings("WeakerAccess")
    public void setData(@Nullable List<DataType> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void setDataResource(@Nullable Resource resource) {
        if (resource != null) {

            if (resource.getData() == null) {
                setData(null);
            } else if (!(resource.getData() instanceof List)) {
                Timber.w("Setting wrong resource data: %s", resource.getData().getClass());
                return;
            }

            setData((List) resource.getData());
        }
    }

    protected abstract void bind(ViewBindingType binding, DataType item);

    protected abstract ViewBindingType createBinding(ViewGroup parent);

    static class DataBoundViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

        public final T binding;
        DataBoundViewHolder(T binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
