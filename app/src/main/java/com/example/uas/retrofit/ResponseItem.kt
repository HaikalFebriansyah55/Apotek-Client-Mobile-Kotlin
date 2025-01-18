package com.example.uas.retrofit

data class ResponseItem(
    val id: String? = null,
    val name: String? = null,
    val alternate_names: List<String?>? = null,
    val species: String? = null,
    val gender: String? = null,
    val house: String? = null,
    val dateOfBirth: String? = null,
    val yearOfBirth: Int? = null,
    val wizard: Boolean? = null,
    val ancestry: String? = null,
    val eyeColour: String? = null,
    val hairColour: String? = null,
    val wand: Wand? = null,
    val patronus: String? = null,
    val hogwartsStudent: Boolean? = null,
    val hogwartsStaff: Boolean? = null,
    val actor: String? = null,
    val alternate_actors: List<String?>? = null,
    val alive: Boolean? = null,
    val image: String? = null
)