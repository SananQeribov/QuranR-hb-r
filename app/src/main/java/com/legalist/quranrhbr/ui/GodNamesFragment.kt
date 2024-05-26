package com.legalist.quranrhbr.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ders.domain.model.GodNameModel
import com.ders.domain.model.GodNameResponseData


import com.legalist.mylibrary.managers.api.GodNameAPI
import com.legalist.quranrhbr.adapter.GodNameAdapter
import com.legalist.quranrhbr.databinding.FragmentGodNamesBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GodNamesFragment : Fragment(), GodNameAdapter.Listener {
    private var _binding: FragmentGodNamesBinding? = null
    private val binding get() = _binding!!

    private val BASE_URL = "https://ayazprogrammer.github.io/host_api/"
    private var godNameResponseData: GodNameResponseData? = null
    private var godNameAdapter: GodNameAdapter? = null

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGodNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable = CompositeDisposable()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GodNameAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError))
    }

    private fun handleResponse(responseData: GodNameResponseData) {
        godNameResponseData = responseData

        godNameResponseData?.let {
            godNameAdapter = GodNameAdapter(it, this@GodNamesFragment)
            binding.recyclerView.adapter = godNameAdapter
        }
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(requireContext(), "Error: ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(godNameModel: GodNameModel) {
        Toast.makeText(requireContext(), "Clicked : ${godNameModel.arabicName}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        compositeDisposable?.clear()
    }
}