package me.partypronl.quibble.pages.entry.column

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WriteColumnBodyViewModel : ViewModel() {
    private val _date = MutableStateFlow<Long?>(0)
    val date: StateFlow<Long?> = _date

    private val _typeId = MutableStateFlow<Long?>(0)
    val typeId: StateFlow<Long?> = _typeId

    private val _body = MutableStateFlow<String>("")
    val body: StateFlow<String> = _body

    private val _canSave = MutableStateFlow<Boolean>(false)
    val canSave: StateFlow<Boolean> = _canSave

    fun setDate(date: Long) {
        _date.value = date
    }

    fun setTypeId(typeId: Long) {
        _typeId.value = typeId
    }

    fun setBody(body: String) {
        _body.value = body
        _canSave.value = body.isNotBlank()
    }
}