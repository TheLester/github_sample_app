package com.asanarebel.githubapp.injection.activity

import androidx.lifecycle.ViewModel
import com.asanarebel.githubapp.feature.repo.detail.RepoDetailFragment
import com.asanarebel.githubapp.feature.repo.detail.RepoDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class RepoDetailModule {
    @ContributesAndroidInjector
    abstract fun contributesRepoDetailFragmentInjector(): RepoDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailViewModel::class)
    abstract fun bindInitialViewModel(viewModel: RepoDetailViewModel): ViewModel
}