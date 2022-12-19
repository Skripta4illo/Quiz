package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if user is exist
                NavHostFragment.findNavController(LoginPage.this)
                        .navigate(R.id.action_LoginRegister_to_SecondFragment);
            }
        });

        binding.tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login fields gone...

                binding.TVName.setVisibility(View.GONE);
                binding.Name.setVisibility(View.GONE);
                binding.TVpass.setVisibility(View.GONE);
                binding.password.setVisibility(View.GONE);
                binding.login.setVisibility(View.GONE);
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
        binding.tvRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEmailId = binding.newEmail.getText().toString();

                // Check if email id is valid or not
                if (!emailValidator(getEmailId)){
                    Toast.makeText(LoginPage.this.getContext(), "Your Email Id is Invalid", Toast.LENGTH_SHORT).show();
                }
                else
                    if (binding.newPass.getText().toString().length() < 8){
                        Toast.makeText(LoginPage.this.getContext(), "Password is too short", Toast.LENGTH_SHORT).show();
                    }
                    else

                        //check that the passwords are the same
                        if (!binding.newPass.getText().toString().equals(binding.confPass.getText().toString())){
                            Toast.makeText(LoginPage.this.getContext(), "Passwords should be the same!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(LoginPage.this.getContext(), "User is created", Toast.LENGTH_SHORT).show();
                        //adding to the database

            }
        });
    }

    public boolean emailValidator(String emailToText) {

        // Android offers the inbuilt patterns which the entered
        // data from the EditText field needs to be compared with
        // In this case the entered data needs to compared with
        // the EMAIL_ADDRESS, which is implemented same below
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}