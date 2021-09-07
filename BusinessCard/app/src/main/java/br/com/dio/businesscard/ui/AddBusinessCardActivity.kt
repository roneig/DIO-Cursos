package br.com.dio.businesscard.ui

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.businesscard.App
import br.com.dio.businesscard.R
import br.com.dio.businesscard.data.BusinessCard
import br.com.dio.businesscard.databinding.ActivityAddBusinessCardBinding
import com.google.android.material.slider.Slider

class AddBusinessCardActivity : AppCompatActivity() {

    private var vermelho = 255                    //  R
    private var verde = 255                       //  G
    private var azul = 255                        //  B
    private var alfa = 255                        //  Alpha  = transparência
    private var operacao: String? = "inclui"      // altera / inclui
    private var idPK : Int  = 0                   // Primary Key recebida

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        recebeParametros()
        insertListeners()

        binding.sliderRed.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) hideSoftKeyboard()
        }
        binding.sliderGreen.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) hideSoftKeyboard()
        }
        binding.sliderBlue.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) hideSoftKeyboard()
        }
        binding.sliderAlpha.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) hideSoftKeyboard()
        }
    }

    private fun recebeParametros(){

        //Log.d("Parametro recebido: ", "entrei na rotina")
        val extras = intent.extras
        if (extras != null) {
            operacao = extras.getString("operacao")

            if (operacao == "altera") {
                binding.tvTitle.setText(R.string.label_alteration_card)
                // armazena chave primária
                idPK = extras.getInt("id")

                // recupera informações do cartão e joga na tela
                binding.tilNome.editText?.setText(extras.getString("nome"))
                binding.tilEmpresa.editText?.setText(extras.getString("empresa"))
                binding.tilTelefone.editText?.setText(extras.getString("telefone"))
                binding.tilEmail.editText?.setText(extras.getString("email"))
                // recupera cor do cartão
                val cor: String? = extras.getString("fundoPersonalizado")
                alfa = Integer.parseInt((cor?.subSequence(1, 3)).toString(), 16)
                vermelho = Integer.parseInt((cor?.subSequence(3, 5)).toString(), 16)
                verde = Integer.parseInt((cor?.subSequence(5, 7)).toString(), 16)
                azul = Integer.parseInt((cor?.subSequence(7, 9)).toString(), 16)
                binding.sliderRed.value = vermelho.toFloat()
                binding.sliderGreen.value = verde.toFloat()
                binding.sliderBlue.value = azul.toFloat()
                binding.sliderAlpha.value = alfa.toFloat()
                pintaCor()
            }
        }  // else = inclui cartão
    }

    private fun insertListeners() {
        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                id = idPK,
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = "#${intColorToHexColor(alfa,vermelho, verde, azul)}"
            )

            if (!verificaPreenchimento(businessCard)) {
                Toast.makeText(applicationContext,
                    R.string.msg_preenchimento_ok, Toast.LENGTH_SHORT).show()
            } else {
                if (operacao == "altera") {
                    // altera cartão

                    mainViewModel.editOne(businessCard)
                    Toast.makeText(this, R.string.msg_alteracao_ok, Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    // inclui cartão novo
                    mainViewModel.insert(businessCard)
                    Toast.makeText(this, R.string.msg_inclusao_ok, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        // encerra activity
        binding.btnClose.setOnClickListener {
            finish()
        }


        // regula totalidade vermelha
        binding.sliderRed.addOnChangeListener(object : Slider.OnChangeListener{
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                vermelho = slider.value.toInt()
                verde  = binding.sliderGreen.getValue().toInt()
                azul = binding.sliderBlue.getValue().toInt()
                alfa = binding.sliderAlpha.getValue().toInt()
                pintaCor()
            }
        })

        // regula totalidade verde
        binding.sliderGreen.addOnChangeListener(object : Slider.OnChangeListener{
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                verde = slider.value.toInt()
                vermelho = binding.sliderRed.getValue().toInt()
                azul = binding.sliderBlue.getValue().toInt()
                alfa = binding.sliderAlpha.getValue().toInt()
                pintaCor()
            }
        })

        // regula totalidade azul
        binding.sliderBlue.addOnChangeListener(object : Slider.OnChangeListener{
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                azul = slider.value.toInt()
                vermelho = binding.sliderRed.getValue().toInt()
                verde = binding.sliderGreen.getValue().toInt()
                alfa = binding.sliderAlpha.getValue().toInt()
                pintaCor()
            }
        })

        // regula transparência
        binding.sliderAlpha.addOnChangeListener(object : Slider.OnChangeListener{
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                alfa = slider.value.toInt()
                vermelho = binding.sliderRed.getValue().toInt()
                verde = binding.sliderGreen.getValue().toInt()
                azul = binding.sliderBlue.getValue().toInt()
                pintaCor()
            }
        })
    }

    // apresenta a cor escolhida pelo usuário e habilta botão de confirmação
    private fun pintaCor() {
        binding.tilCor.setBoxBackgroundColor(Color.argb(alfa, vermelho, verde, azul))
        binding.tilCor.setHint("")
        binding.btnConfirm.setVisibility(View.VISIBLE)
    }

    // verifica se todos os campos foram prenchidos
    private fun verificaPreenchimento(businessCard: BusinessCard): Boolean{
        if ((businessCard.nome.length     > 0) and
            (businessCard.empresa.length  > 0) and
            (businessCard.telefone.length > 0) and
            (businessCard.email.length    > 0))
            return true
        else return false
    }

    // esconde o teclado para trabalhar com os sliders
    private fun Activity.hideSoftKeyboard(){
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    // converte  cor  Int  para Hexadecimal
    private fun intColorToHexColor(vararg cores: Int) : String {
        val tabhex= listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var corHex: String = ""
        for (cor in cores) {
            corHex += "${tabhex[cor / 16]}${tabhex[cor % 16]}"
        }
        return corHex
    }

}
