package com.juawapps.newsspread.ui.common;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.juawapps.newsspread.data.Resource;

import java.util.List;

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

    public void setLiveData(@NonNull LifecycleOwner viewLifeCycle,
                            LiveData<Resource<List<DataType>>> liveData) {

        liveData.observe(viewLifeCycle, articleResource -> {

            if (articleResource != null && articleResource.data != null) {

                setData(articleResource.data);
            }
        });
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
