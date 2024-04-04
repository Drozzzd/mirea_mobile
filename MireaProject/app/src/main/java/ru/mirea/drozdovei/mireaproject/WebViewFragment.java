package ru.mirea.drozdovei.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import ru.mirea.drozdovei.mireaproject.R;
import ru.mirea.drozdovei.mireaproject.databinding.FragmentWebViewBinding;

public class WebViewFragment extends Fragment {

    private FragmentWebViewBinding binding;
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWebViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        webView = root.findViewById(R.id.webView);
        webView.loadUrl("https://github.com/Drozzzd/mirea_mobile");

        return root;
    }
}