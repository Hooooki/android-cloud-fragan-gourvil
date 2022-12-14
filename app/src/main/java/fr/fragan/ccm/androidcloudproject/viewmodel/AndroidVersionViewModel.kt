package fr.fragan.ccm.androidcloudproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.fragan.ccm.androidcloudproject.model.FilmDataFooterSample
import fr.fragan.ccm.androidcloudproject.model.FilmDataHeaderSample
import fr.fragan.ccm.androidcloudproject.model.FilmDataSample
import fr.fragan.ccm.androidcloudproject.model.SealedRecyclerViewItem
import fr.fragan.ccm.androidcloudproject.repository.AndroidVersionRepository

class AndroidVersionViewModel : ViewModel() {

    private val androidVersionRepository: AndroidVersionRepository by lazy { AndroidVersionRepository() }
    private val _androidVersionList = MutableLiveData<List<SealedRecyclerViewItem>>()
    val androidVersionList: LiveData<List<SealedRecyclerViewItem>> get() = _androidVersionList


    init {
        _androidVersionList.postValue(androidVersionRepository.generateFakeData())
    }

}