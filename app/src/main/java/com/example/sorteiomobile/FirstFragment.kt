package com.example.sorteiomobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sorteiomobile.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val nomes = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAdicionar.setOnClickListener {
            val nome = binding.editTextNome.text.toString().trim()
            if (nome.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Digite um Nome",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            nomes.add(nome)
            atualizarLista()
            binding.editTextNome.text?.clear()
        }

        binding.buttonSortear.setOnClickListener {
            if (nomes.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Adicione ao menos um nome.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val nomeSorteado = nomes.random()

            val bundle = Bundle().apply {
                putString("nomeSorteado", nomeSorteado)
            }

            findNavController().navigate(
                R.id.action_FirstFragment_to_SecondFragment,
                bundle
            )

        }

    }

    private fun atualizarLista() {
        binding.textViewNomes.text = nomes.joinToString(
            separator = "\n"
        ) { nome ->
            " - $nome"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}