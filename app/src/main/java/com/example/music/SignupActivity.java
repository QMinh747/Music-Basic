package com.example.music;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.example.music.databinding.ActivitySignupBinding;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailEdittext.getText().toString();
                String password = binding.passwordEdittext.getText().toString();
                String confirmPassword = binding.confirmPasswordEdittext.getText().toString();

                if (!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)) {
                    binding.emailEdittext.setError("Invalid email");
                    return;
                }

                if (password.length() < 6) {
                    binding.passwordEdittext.setError("Length should be 6 char");
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    binding.confirmPasswordEdittext.setError("Password not matched");
                    return;
                }

                createAccountWithFirebase(email, password);
            }
        });

    }

    private void createAccountWithFirebase(String email, String password) {
        setInProgress(true);
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    setInProgress(false);
                    Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    setInProgress(false);
                    Toast.makeText(getApplicationContext(), "Create account failed", Toast.LENGTH_SHORT).show();
                });
    }

    private void setInProgress(boolean inProgress) {
        if (inProgress) {
            binding.createAccountBtn.setVisibility(View.GONE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.createAccountBtn.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}
