package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.FragmentSecondBinding;
import com.example.quiz.databinding.LoginRegistrationBinding;

public class LoginPage extends Fragment {

    private LoginRegistrationBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = LoginRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //hide registration fields
        binding.tvNewName.setVisibility(View.INVISIBLE);
        binding.newName.setVisibility(View.INVISIBLE);
        binding.tvNewEmail.setVisibility(View.INVISIBLE);
        binding.newEmail.setVisibility(View.INVISIBLE);
        binding.tvNewPass.setVisibility(View.INVISIBLE);
        binding.newPass.setVisibility(View.INVISIBLE);
        binding.tvConfPass.setVisibility(View.INVISIBLE);
        binding.confPass.setVisibility(View.INVISIBLE);
        binding.tvRegist.setVisibility(View.INVISIBLE);
        binding.tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login fields gone...

                binding.TVName.setVisibility(View.GONE);
                binding.Name.setVisibility(View.GONE);
                binding.TVpass.setVisibility(View.GONE);
                binding.password.setVisibility(View.GONE);
                //show registration fields
                binding.tvNewName.setVisibility(View.VISIBLE);
                binding.newName.setVisibility(View.VISIBLE);
                binding.tvNewEmail.setVisibility(View.VISIBLE);
                binding.newEmail.setVisibility(View.VISIBLE);
                binding.tvNewPass.setVisibility(View.VISIBLE);
                binding.newPass.setVisibility(View.VISIBLE);
                binding.tvConfPass.setVisibility(View.VISIBLE);
                binding.confPass.setVisibility(View.VISIBLE);
                binding.tvRegist.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}