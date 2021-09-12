package edu.kiet.navigationdrawerex.ui.aboutus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import edu.kiet.navigationdrawerex.R;
import edu.kiet.navigationdrawerex.databinding.FragmentGalleryBinding;
import edu.kiet.navigationdrawerex.ui.gallery.GalleryViewModel;

public class AboutUs extends Fragment {


    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        //binding = FragmentGalleryBinding.inflate(inflater, container, false);
       // View root = binding.getRoot();
       View root =inflater.inflate(R.layout.aboutus,container,false);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

