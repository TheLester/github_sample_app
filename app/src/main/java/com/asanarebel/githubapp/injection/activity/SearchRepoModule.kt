package com.asanarebel.githubapp.injection.activity

import androidx.lifecycle.ViewModel
import com.asanarebel.githubapp.feature.repo.search.SearchFragment
import com.asanarebel.githubapp.feature.repo.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SearchRepoModule {
    @ContributesAndroidInjector
    abstract fun contributesSearchFragmentInjector(): SearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindInitialViewModel(viewModel: SearchViewModel): ViewModel
}