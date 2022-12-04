package com.example.surveyapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var cPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register, container, false)

        username = view.findViewById(R.id.reg_username)
        password = view.findViewById(R.id.reg_password)
        cPassword = view.findViewById(R.id.reg_cPassword)


        view.findViewById<Button>(R.id.login_button_reg).setOnClickListener {
            val navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(LoginFragment(), false)
        }

        view.findViewById<Button>(R.id.register_button_reg).setOnClickListener {
            validateEmptyForm()
        }
        return view
    }

    private fun validateEmptyForm(){
        when{
            TextUtils.isEmpty(username.text.toString().trim())->{
                username.setError("Please Enter Username")
            }
            TextUtils.isEmpty(password.text.toString().trim())->{
                password.setError("Please Enter Password")
            }
            TextUtils.isEmpty(cPassword.text.toString().trim())->{
                cPassword.setError("Please Confirm Password")
            }

            username.text.toString().isNotEmpty() && password.text.toString().isNotEmpty() &&
                    cPassword.text.toString().isNotEmpty() ->
            {
                if (username.text.toString().matches(Regex("[pP]+[0-9]{7}"))){
                    if (password.text.toString().length>=5){

                        if (password.text.toString() == cPassword.text.toString()){
                            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show()
                        }else{
                            cPassword.setError("Password did not match")
                        }
                    }else{
                        password.setError("Please Enter Valid Password")
                    }
                }else{
                    username.setError("Please Enter a P-Number of DMU")
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}