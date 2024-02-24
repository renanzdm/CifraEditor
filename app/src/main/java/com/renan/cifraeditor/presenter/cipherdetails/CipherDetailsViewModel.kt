package com.renan.cifraeditor.presenter.cipherdetails


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renan.cifraeditor.data.models.Resource
import com.renan.cifraeditor.data.repository.LocalRepositoryImpl
import com.renan.cifraeditor.domain.database.allToms
import com.renan.cifraeditor.domain.entities.entitiesrelations.WordWithChords
import com.renan.cifraeditor.domain.entities.tables.Chord
import com.renan.cifraeditor.domain.entities.tables.Tom
import com.renan.cifraeditor.domain.entities.tables.Word
import com.renan.cifraeditor.domain.entities.tables.WordChordCrossReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CipherDetailsViewModel @Inject constructor(private val localRepositoryImpl: LocalRepositoryImpl) :
    ViewModel() {
    private val _state = MutableStateFlow(CipherDetailsState())
    var state = _state.asStateFlow()


    fun getCipherById(id: Long) {
        _state.update {
            it.copy(
                loading = true,
            )
        }
        viewModelScope.launch {
            when (val res = localRepositoryImpl.getCipherById(id)) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            cipher = res.data!!.cipher,
                            wordsWithChords = res.data.wordWithChords,
                            wordsFormatted = chunkedList(res.data.wordWithChords)
                        )
                    }
                    getChordsByTom(idTom = _state.value.cipher!!.fkTom)
                    getTomById()

                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            errorMessage = res.message, loading = false
                        )
                    }
                }
            }
        }
    }


    private fun chunkedList(values: List<WordWithChords>): List<List<WordWithChords>> {
        val listWithSublists: MutableList<List<WordWithChords>> = mutableListOf()
        val groupedValuesByLine: Map<Int, List<WordWithChords>> =
            values.groupBy { wordWithChords: WordWithChords -> wordWithChords.word.numberLine }
        groupedValuesByLine.forEach { entry -> listWithSublists.add(entry.value) }
        return listWithSublists
    }

    private fun getChordsByTom(idTom: Long) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true,
                )
            }
            when (val response = localRepositoryImpl.getChordsByTom(id = idTom)) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            loading = false, chords = response.data ?: emptyList()
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            loading = false, chords = response.data ?: emptyList()
                        )
                    }
                }
            }
        }
    }

    fun insertWordChordCrossReference(word: Word, chord: Chord) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true,
                )
            }
            viewModelScope.async {
                localRepositoryImpl.insertWordChordCrossReference(
                    entity = WordChordCrossReference(
                        chordId = chord.chordId, wordId = word.wordId
                    )
                )
            }.await()
            viewModelScope.async {
                getCipherById(_state.value.cipher!!.cipherId!!)
            }.await()
            _state.update {
                it.copy(
                    loading = false,
                )
            }
        }
    }

    fun deleteWordChordCrossReference(word: Word, chord: Chord) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true,
                )
            }
            localRepositoryImpl.deleteWordChordCrossReference(
                entity = WordChordCrossReference(
                    chordId = chord.chordId, wordId = word.wordId
                )
            )
            _state.update {
                it.copy(
                    loading = false,
                )
            }
        }
    }

    private fun getTomById() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true,
                )
            }
            when (val response = localRepositoryImpl.getTomById(_state.value.cipher!!.fkTom)) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(tomOfCipher = response.data,
                            loading = false,
                            listToms = allToms.filter { tom ->
                                tom.major == response.data?.major
                            })
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            loading = false
                        )
                    }
                }
            }
        }
    }


    fun selectNewTom(newTom: Tom) {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }
            val chordsNewTom = localRepositoryImpl.getAllChords()
            if (chordsNewTom.data == null) {
                _state.update {
                    it.copy(loading = false, errorMessage = chordsNewTom.message)
                }
            } else {
                viewModelScope.async {
                    localRepositoryImpl.deleteAllChordsByWords(_state.value.wordsWithChords)
                }.await()
                for (wordWithChords: WordWithChords in _state.value.wordsWithChords) {
                    if (wordWithChords.chords == null) continue
                    for (chord: Chord in wordWithChords.chords) {
                        val tomTransposedName: String = transposeChord(
                            chord = chord,
                            interval = newTom.interval - _state.value.tomOfCipher!!.interval
                        )

                        val newChordTransposed = chordsNewTom.data.firstOrNull {
                            it.tonic.equals(
                                tomTransposedName, ignoreCase = true
                            ) && it.chordDegree == chord.chordDegree && it.major == chord.major
                        }

                        viewModelScope.async {
                            localRepositoryImpl.insertWordChordCrossReference(
                                entity = WordChordCrossReference(
                                    chordId = newChordTransposed?.chordId ?: chord.chordId,
                                    wordId = wordWithChords.word.wordId
                                )
                            )
                        }.await()

                    }
                }
                viewModelScope.async {
                    localRepositoryImpl.updateCipher(_state.value.cipher!!.copy(fkTom = newTom.tomId!!))
                }.await()
                getCipherById(_state.value.cipher!!.cipherId!!)
                _state.update {
                    it.copy(
                        loading = false,
                        tomOfCipher = newTom,
                    )
                }
            }

        }
    }


    private fun transposeChord(chord: Chord, interval: Int): String {
        val notes = allToms.filter { tom -> tom.major }.map { it.tonic }
        val indexNote: Int = notes.indexOf(chord.tonic)
        val transposedIndex = (indexNote + interval).mod(notes.size)
        return notes[transposedIndex]
    }

    suspend fun getChordsByTomId(idTom: Long): List<Chord>? {
        return viewModelScope.async {
            return@async localRepositoryImpl.getChordsByTom(id = idTom)
        }.await().data


    }


    fun deleteCipher() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true,
                )
            }
            localRepositoryImpl.deleteCipher(
                cipher = _state.value.cipher!!, words = _state.value.wordsWithChords
            )
            _state.update {
                it.copy(
                    loading = false,
                )
            }


        }
    }


    fun joinWordsToString(): String {
        var letter = ""
        for (wordWithChords in _state.value.wordsFormatted) {
            for (word in wordWithChords) {
                letter += word.word.wordName + " "
            }
            letter += "\n"
        }
        return letter
    }


    fun editCipher(words: String) {
        val anySpacesRemoved = words.trimIndent().replace(Regex("\\s+"), " ")
        val listWords: List<String> = anySpacesRemoved.split(" ")
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true,
                )
            }
            val newWordsCipher = mutableListOf<Word>()
            for (index in listWords.indices) {
                val targetWord: WordWithChords? =
                    _state.value.wordsWithChords.firstOrNull { w -> w.word.wordName == listWords[index] && w.word.order == index }
                newWordsCipher.add(
                    targetWord?.word ?: Word(
                        wordName = listWords[index],
                        fkChiper = _state.value.cipher!!.cipherId!!,
                        order = index,
                        numberLine = 1
                    )
                )
            }
            for (newWords in newWordsCipher) {
                viewModelScope.async { localRepositoryImpl.upsertWord(newWords) }.await()
            }

            viewModelScope.async {
                getCipherById(id = _state.value.cipher!!.cipherId!!)
            }.await()

            _state.update {
                it.copy(
                    loading = false,
                )
            }


        }
    }

    fun updateFontSize(size: Int) {
        _state.update {
            it.copy(fontSize = size)
        }

    }


}