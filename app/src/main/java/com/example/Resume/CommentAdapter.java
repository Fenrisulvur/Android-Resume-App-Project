package com.example.Resume;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
        private ArrayList<Comment> mCommentList;

        public static class CommentViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextViewLine1;
            public TextView mTextViewLine2;

            public CommentViewHolder(View itemView) {
                super(itemView);
                mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
                mTextViewLine2 = itemView.findViewById(R.id.textview_line_2);
            }
        }

        public CommentAdapter(ArrayList<Comment> exampleList) {
            mCommentList = exampleList;
        }

        @Override
        public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
            CommentViewHolder evh = new CommentViewHolder(v);
            return evh;
        }

        @Override
        public void onBindViewHolder(CommentViewHolder holder, int position) {
            if (mCommentList == null) return;
            Comment currentItem = mCommentList.get(position);
            if (currentItem == null) return;
            holder.mTextViewLine1.setText(currentItem.getAuthor());
            holder.mTextViewLine2.setText(currentItem.getComment());
        }

        @Override
        public int getItemCount() {
            return mCommentList.size();
        }

}
