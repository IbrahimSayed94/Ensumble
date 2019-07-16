package com.ensumble.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ensumble.AppConfig.Constant;
import com.ensumble.Model.ProductReviewsResponse;
import com.ensumble.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import hyogeun.github.com.colorratingbarlib.ColorRatingBar;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {


    private Context context;
    private List<ProductReviewsResponse.MessageBean> reviewList ;

    public ReviewsAdapter(Context context, List<ProductReviewsResponse.MessageBean> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.review_user_image)
        ImageView review_user_image;

        @BindView(R.id.review_user_name)
        TextView review_user_name;
        @BindView(R.id.review_user_review)
        TextView review_user_review;
        @BindView(R.id.review_date)
        TextView review_date;

        @BindView(R.id.review_user_rate)
        ColorRatingBar review_user_rate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_review,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ProductReviewsResponse.MessageBean object = reviewList.get(position);

        Glide.with(context)
                .load(Constant.USER_IMAGE_URL+object.getUser().get(0).getImg())
                .fitCenter()
                .placeholder(R.drawable.profile)
                .into(holder.review_user_image);

        holder.review_user_name.setText(object.getUser().get(0).getName());
        holder.review_user_rate.setRating(object.getValue());
        holder.review_date.setText(object.getDate());
        holder.review_user_review.setText(object.getMessage());

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }


}
