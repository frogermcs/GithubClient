package frogermcs.io.githubclient.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.auto.factory.AutoFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.model.Repository;

/**
 * Created by Miroslaw Stanek on 11.06.2016.
 */
@AutoFactory(implementing = RepositoriesListViewHolderFactory.class)
public class RepositoryViewHolderBig extends RepositoryViewHolder {

    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvStars)
    TextView tvStars;
    @Bind(R.id.tvForks)
    TextView tvForks;

    public RepositoryViewHolderBig(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_big, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Repository repository) {
        tvName.setText(repository.name);
        tvStars.setText("Stars: " + repository.stargazers_count);
        tvForks.setText("Forks: " + repository.forks_count);
    }
}
