package mx.haya.heroesappkt.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import mx.haya.heroesappkt.R
import mx.haya.heroesappkt.data.model.SuperHeroModel
import mx.haya.heroesappkt.databinding.HomeFragmentBinding
import mx.haya.heroesappkt.ui.adapters.RecyclerViewItemAdapter
import mx.haya.heroesappkt.ui.adapters.RecyclerViewItemClickListener
import mx.haya.heroesappkt.ui.home.rv.OnLoadMoreListener
import mx.haya.heroesappkt.ui.home.rv.RecyclerViewLoadMoreScroll

@AndroidEntryPoint
class HomeFragment : Fragment(), RecyclerViewItemClickListener {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var viewDataBinding: HomeFragmentBinding

    // RecyclerView
    lateinit var adapter: RecyclerViewItemAdapter
    lateinit var scrollListener: RecyclerViewLoadMoreScroll
    lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var rvHeroes: RecyclerView


    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Binding
        viewDataBinding = HomeFragmentBinding.inflate(layoutInflater)

        return viewDataBinding.root
    }

    private fun setRVLayoutManager() {
        layoutManager = LinearLayoutManager(activity)
        rvHeroes.layoutManager = layoutManager
        rvHeroes.setHasFixedSize(true)
    }

    private fun setRVScrollListener() {
        layoutManager = LinearLayoutManager(activity)
        scrollListener = RecyclerViewLoadMoreScroll(layoutManager as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                loadMoreData()
            }
        })
        rvHeroes.addOnScrollListener(scrollListener)
    }

    fun loadMoreData() {
        adapter.addLoadingView()
        viewModel.loadSuperHeroes()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val methodName = "onViewCreated"

        // Nav controller
        navController = Navigation.findNavController(view)

        viewModel.updated.observe(viewLifecycleOwner) {

            Log.d(methodName, "Processing observable response ")

            if (it) {
                // Cargas subsecuentes
                Log.e(methodName, "Subsequent call")

                Log.e(methodName, viewDataBinding.rvHeroes.adapter.toString())
                Log.e(methodName, adapter.toString())

                if (viewDataBinding.rvHeroes.adapter == null) {
                    Log.d(methodName, "Reasignando adapter...")
                    rvHeroes = view.findViewById(R.id.rvHeroes)
                    rvHeroes.adapter = adapter
                    setRVLayoutManager()
                    setRVScrollListener()
                } else {

                    adapter.removeLoadingView()
                    adapter.addData(viewModel.getSuperHeroes())
                    scrollListener.setLoaded()
                    adapter.notifyDataSetChanged()
                }
            } else {
                // Primera carga
                Log.e(methodName, "First call")
                rvHeroes = view.findViewById(R.id.rvHeroes)
                adapter = RecyclerViewItemAdapter(viewModel.getSuperHeroes(), this)
                adapter.notifyDataSetChanged()
                rvHeroes.adapter = adapter
                setRVLayoutManager()
                setRVScrollListener()
            }
        }

        viewModel.loadSuperHeroes()

    }

    override fun onItemClickListener(data: SuperHeroModel) {

        val methodName = "onItemClickListener"

        Log.d(methodName, "SuperHero: $data")

        val bundle = bundleOf("superHeroId" to data.id)

        navController.navigate(R.id.action_homeFragment_to_heroFragment, bundle)

    }


}
