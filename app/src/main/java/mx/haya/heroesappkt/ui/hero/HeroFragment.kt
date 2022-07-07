package mx.haya.heroesappkt.ui.hero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import mx.haya.heroesappkt.R
import mx.haya.heroesappkt.databinding.HeroFragmentBinding

@AndroidEntryPoint
class HeroFragment : Fragment() {

    private val viewModel: HeroViewModel by viewModels()

    private lateinit var viewDataBinding: HeroFragmentBinding

    companion object {
        fun newInstance() = HeroFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Binding
        viewDataBinding = HeroFragmentBinding.inflate(inflater)

        viewModel.superHero.observe(viewLifecycleOwner, Observer {
            viewDataBinding.imgHero.load(it.image.url)
            viewDataBinding.tvName.text = it.name

            // Powerstats
            viewDataBinding.tvCombat.text = it.powerstats.combat
            viewDataBinding.tvDurability.text = it.powerstats.durability
            viewDataBinding.tvIntelligence.text = it.powerstats.intelligence
            viewDataBinding.tvPower.text = it.powerstats.power
            viewDataBinding.tvSpeed.text = it.powerstats.speed
            viewDataBinding.tvStrength.text = it.powerstats.strength

            // Biography
            viewDataBinding.tvFullName.text = it.biography.fullName
            viewDataBinding.tvAlterEgos.text = it.biography.alterEgos
            viewDataBinding.tvPlaceOfBirth.text = it.biography.placeOfBirth
            viewDataBinding.tvPublisher.text = it.biography.publisher

            // Appearance
            viewDataBinding.tvEyeColor.text = it.appearance.eyeColor
            viewDataBinding.tvGender.text = it.appearance.gender
            viewDataBinding.tvHairColor.text = it.appearance.hairColor
            viewDataBinding.tvHeight.text = it.appearance.height[0]
            viewDataBinding.tvRace.text = it.appearance.race
            viewDataBinding.tvWeight.text = it.appearance.weight[0]

            // Work
            viewDataBinding.tvBase.text = it.work.base
            viewDataBinding.tvOccupation.text = it.work.occupation

            // Connections
            viewDataBinding.tvGroupAffiliation.text = it.connections.groupAffiliation
            viewDataBinding.tvRelatives.text = it.connections.relatives

        })

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onCreate()


    }

}