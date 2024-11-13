package com.example.listingsmovies.ui.blog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.listingsmovies.databinding.FragmentNotificationsBinding;
import com.example.listingsmovies.ui.blog.adaptercomment.CommentAdapter;
import com.example.listingsmovies.ui.blog.addcomment.AddCommentActivity;
import com.example.listingsmovies.ui.blog.modelcomment.Comentario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private DatabaseReference mDatabase;
    private List<Comentario> myComments;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        myComments = new ArrayList<>();

        loadComments();
        addComment();

        return root;
    }

    private void loadComments(){
        mDatabase = FirebaseDatabase.getInstance().getReference("Blog/comentarios");

        Query query1 = mDatabase.orderByChild("nombreUsuario").equalTo("Adrian Alvarez");

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                myComments.clear();
                
                if (datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        Comentario itemComment = snapshot.getValue(Comentario.class);
                        myComments.add(itemComment);

                    }
                }else{
                    Toast.makeText(getContext(), "No existe informacion", Toast.LENGTH_SHORT).show();
                }

                CommentAdapter commentAdapter = new CommentAdapter(myComments, getContext());
                binding.recyclerViewComment.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
                binding.recyclerViewComment.setAdapter(commentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),   "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void addComment(){
        binding.fabAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddCommentActivity.class);
                startActivity(intent);
            }
        });
    }

}