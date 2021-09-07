package br.com.dio.businesscard.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import br.com.dio.businesscard.App
import br.com.dio.businesscard.data.BusinessCard
import br.com.dio.businesscard.databinding.ActivityMainBinding
import br.com.dio.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpPermissions()
        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListeners()
    }

    private fun setUpPermissions() {
        // write permission to access the storage
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
    }

    private fun insertListeners() {

        // Activity para incluir cartão novo
        binding.fab.setOnClickListener {
            val intent = Intent(this, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }

        // Compartilha imagem do cartão
        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }

        // Exclui cartão
        adapter.listenerDel = { businessCard: BusinessCard ->
            val builder = AlertDialog.Builder(this)
            with(builder)
            {
                setTitle("O cartão será excluido. ")
                setMessage("Você confirma a exclusão?")
                setPositiveButton("Sim", {_,_ ->
                    mainViewModel.delOne(businessCard)
                    Toast.makeText(
                        applicationContext,
                        "Cartão excluído", Toast.LENGTH_SHORT
                    ).show()
                })
                setNegativeButton("Não", {_,_ ->
                    Toast.makeText(
                        applicationContext,
                        "Exclusão cancelada", Toast.LENGTH_SHORT
                    ).show() })
                show()
            }
        }

        // Edita um cartão já existente
        adapter.listenerEdit = { businessCard: BusinessCard ->
            val intent = Intent(this, AddBusinessCardActivity::class.java)
            intent.putExtra("operacao", "altera")
            intent.putExtra("id", businessCard.id)
            intent.putExtra("nome", businessCard.nome)
            intent.putExtra("empresa", businessCard.empresa)
            intent.putExtra("telefone", businessCard.telefone)
            intent.putExtra("email", businessCard.email)
            intent.putExtra("fundoPersonalizado", businessCard.fundoPersonalizado)
            startActivity(intent)
        }

    }


    // Lista os cartões exixtentes
    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this, { businessCards ->
            adapter.submitList(businessCards)
        })
    }

}