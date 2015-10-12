package app.sbaloan.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by danielmurphy on 10/10/15.
 */
public class SavedSearchesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView text = new TextView(container.getContext());
        text.setText("Saved Searches Fragment!");
        text.setGravity(Gravity.CENTER);

        return text;
    }

    @Override
    public String toString() {
        return "Old Searches";
    }
}