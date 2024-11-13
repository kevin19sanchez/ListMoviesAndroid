package com.example.listingsmovies.ui.blog.adaptercomment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listingsmovies.databinding.ItemDetailsCommentBinding;
import com.example.listingsmovies.databinding.ListItemBinding;
import com.example.listingsmovies.ui.blog.modelcomment.Comentario;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comentario> mComment;
    private Context context;

    public CommentAdapter(List<Comentario> mComment, Context context) {
        this.mComment = mComment;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //ItemDetailsCommentBinding -> hace referencia al item que se vera en el recyclerView
        ItemDetailsCommentBinding binding = ItemDetailsCommentBinding.inflate(inflater,parent,false);
        return new CommentAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comentario comment = mComment.get(position);

        holder.txtComment.setText(comment.getComentario());
        Picasso
                .get()
                .load(comment.getCover())
                .into(holder.imageComment);
        holder.dateComment.setText(comment.getFechaComentario());
    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        private ItemDetailsCommentBinding binding;
        public CardView cardComment;
        public ImageView imageComment;
        public TextView txtComment, txtTitleMovieComment, dateComment;


        public ViewHolder(ItemDetailsCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            cardComment = binding.cardItemComment;
            imageComment = binding.imgMovieComment;
            txtTitleMovieComment = binding.titleMovieComment;
            txtComment = binding.titleComment;
            dateComment = binding.dateComment;
        }
    }
}
